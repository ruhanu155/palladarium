# Minecraft development

How Field Station gets built. The other tabs say what the game is; this one says how it becomes a NeoForge mod. It follows the studio's mod standard, the same one Year of Ice uses, so both mods share conventions, tests and tooling. When a design tab changes, this tab changes only if *how* something is built changes.

## The pin

The pin is the newest Minecraft version with a stable NeoForge release and releases of every library we need. On October 19, 2026 that is **26.2**. NeoForge for 26.3 is still beta (26.3.0.9-beta, September 15), and Flowing Fluids has no 26.3 build yet.

| Piece | Version | Notes |
| --- | --- | --- |
| Minecraft Java | 26.2 | Unobfuscated, Mojang names, no remapping |
| NeoForge | 26.2.0.88 | Stable |
| Java | 25 | Temurin 25 on the Mac |
| Gradle | 9.1 or later | Wrapper checked into the repo |
| Build plugin | ModDevGradle | Newest release at M0 |
| Flowing Fluids | 1.0.7, NeoForge build for 26.2 | Required. Finite, flowing water that the erosion system reads |
| GeckoLib | 5.5.6 for 26.2 | Required for animated animals |
| JUnit 5, ArchUnit | Newest at M0 | Tests only |
| spark | Newest at M0 | Profiling, dev runs only |

Curios is not used: Field Station adds no extra equipment slots.

Rules:

- Re-check every version on the web at M0 and write the exact numbers into `gradle.properties`. Never pin from memory.
- Move the pin only in its own round after a milestone ships, on a branch named `upgrade/mc-<version>`. The likely next pin is 26.3, once NeoForge 26.3 is stable and Flowing Fluids ships for it.
- GameTests use `test_instance` data files, the format used since 1.21.5.

## Repo and workflow

- **Repo:** proposed name `ruhanu155/field-station`. It does not exist yet; Ruhan confirms the name before it is created.
- **Builds:** on Ruhan's Mac (`./gradlew runClient`, `./gradlew build`) and in CI on every pull request.
- **Branches:** all work happens on `dev/<department>/<topic>` (for example `dev/water/stream-power-erosion`) and lands by pull request. Nobody pushes to `main`.
- **Versions:** `MAJOR.MINOR.PATCH+mc<version>`. Before 1.0 the minor is the milestone, so the v0.1 release is `0.1.0+mc26.2`.
- **Changelog:** built from `Changelog:` trailers on commits.

```
field-station/
  build.gradle, settings.gradle, gradle.properties
  src/main/java/com/fieldstation/...        mod code, by package below
  src/main/resources/META-INF/neoforge.mods.toml
  src/main/resources/fieldstation.mixins.json
  src/main/resources/data/fieldstation/...  hand-written data
  src/main/resources/assets/fieldstation/... models, textures, sounds, lang
  src/generated/resources/                   datagen output, checked in
  src/test/java/...                          JUnit: sim, data validation, ArchUnit
  src/gametest/...                           GameTests
  tools/sim/                                 headless balance simulator
  fixtures/saves/                            one save per release, for migration tests
  pack/                                      packwiz modpack
```

## Package map

Mod id `fieldstation`; Java packages `com.fieldstation.<system>`. Each package owns its own state and is the only one that writes it. Systems talk to each other only through service interfaces and NeoForge events in `com.fieldstation.api`. An ArchUnit test fails the build on any other cross-package import.

| Package | Owns (only writer) | Design tab |
| --- | --- | --- |
| `api` | Service interfaces and events. Holds no state | — |
| `sim` | Pure rules with no Minecraft imports: stream power erosion, flood fill, soil, plant growth, population dynamics, weather generation, economy maths, the storyteller's tension budget | Systems spec |
| `clock` | The one clock layer: sim hours, 7-day seasons, growth and erosion rates, night time-lapse | Systems spec (tick table) |
| `terrain` | Column arrays (the source of truth), the world writer, the 8-layer top block, landforms | Landscaping and terraforming |
| `water` | The water bridge (Flowing Fluids moves water in loaded chunks, our column model in unloaded ones), soaking, springs, evaporation, outlets, erosion and deposition. Rule: Systems spec → Water ownership | Systems spec |
| `soil` | Soil moisture, nutrients, compaction | Systems spec |
| `habitat` | Habitat scan (flood fill), habitat records and their populations, microhabitat tags | Systems spec |
| `plants` | Plant populations, spread, succession | Plants |
| `wildlife` | The entity spawner (entities from the habitat package's populations, changed only through PopulationService), AI goals, named animals, the studbook; mammals, birds, amphibians, reptiles and insects | Mobs; Insects and bugs |
| `fish` | Fish populations, stream and pond behavior, catching and tagging | Fish mechanics |
| `tame` | Tame and working animals, including the field dog | Tame and working animals |
| `weather` | Daily weather, storms, floods, drought | Weather and climate |
| `survey` | Surveys, survey estimates, Park Service allotments, the journal's numbers | Systems spec |
| `economy` | Cash, science credits, the shop, drone delivery, contracts, grants, capture and trade (phase 1) | Economy, crafting and drones; Capture and animal trade |
| `station` | The RV, the lab, crafting stations, recipe unlocks | Lab and RV |
| `player` | Tools, the field tablet, energy, onboarding | Player and tools |
| `story` | The director (storyteller), morning briefing, events, the Chronicle, year cards | Fun, feel and finish |
| `world` | The `fieldstation:appalachia` dimension, the 200×200 plot and its bounds, vanilla replacement | Biomes; The cove |
| `data` | Reload listeners, codecs, the `contentLint` validator | Definitions and rules |
| `registry` | All `DeferredRegister`s | Content registry |
| `client` | All rendering, HUD layers, screens, sound playback, tablet UI | — |
| `compat` | Patches to other mods, only through their public APIs (`compat.flowingfluids`) | — |

**Server decides, client shows.** Every simulation runs on the server. The client only renders what the server sends. Client-only code lives in `com.fieldstation.client`, registered with `Dist.CLIENT`. A dedicated-server boot test catches any client class that leaks into common code.

**Mixins:** at most 8, each listed in `fieldstation.mixins.json` and each with its own test. Milestone 1 plans none; events and the Flowing Fluids API should cover it. If the Flowing Fluids public API can't hand water over at chunk unload, test slice step 4 names the mixin it needs.

## API events and services

All events are on `NeoForge.EVENT_BUS` and posted on the server thread: `ColumnsChangedEvent` (one per tick, carrying the dirty bitset), `HabitatChangedEvent`, `PopulationChangedEvent`, `SpeciesArrivedEvent`, `SpeciesLostEvent`, `AnimalCaughtEvent`, `AnimalReleasedEvent`, `WeatherChangedEvent`, `SeasonChangedEvent`, `PurchaseEvent`, `RewardEvent`, `DayBeganEvent`, `YearEndedEvent`, `StoryEventScheduledEvent`, `BriefingBuiltEvent`, `AnimalNamedEvent`, `ChronicleEntryEvent`.

Services in `com.fieldstation.api`, each implemented by its owning package: `TerrainService`, `WaterService`, `SoilService`, `HabitatService`, `PopulationService`, `ClockService`, `EconomyService` and `JournalService`.

## Network

All payloads are registered in `RegisterPayloadHandlersEvent` with network version `1` and a `StreamCodec`. Server to client:

- `fieldstation:clock_sync` (sim ticks, season, day, speed): on change and every 5 s.
- `fieldstation:hud` (energy, weather state, air °C, cash): on change.
- `fieldstation:column_tint` (chunk x/z, 256 bytes of moisture and turbidity): when a chunk is sent, then as deltas at 4 Hz.
- `fieldstation:screen_data` (screen id + a record per screen: shop catalog and cash, grants list, journal page, terrain-change map as 40,000 signed bytes, deflated, about 15 KB): sent only in reply to a request.
- `fieldstation:briefing`.

Client to server, intents only, each checked on the server: `fieldstation:open_screen`, `fieldstation:buy`, `fieldstation:file_report`, `fieldstation:log_sighting`.

Screen view-models are records in `com.fieldstation.api.view`, built by the owning package.

## The clock layer

Time in Minecraft is ticks, and Field Station runs fast on purpose: growth and erosion should be visible in days, not months. One package, `clock`, turns ticks into sim time and sets each system's rate. The clock package is the only reader and writer of time, and an ArchUnit rule enforces that.

- **State:** it stores `long sim_ticks` in `fieldstation:clock` and owns the `fieldstation:appalachia` world clock (26.1 per-dimension `WorldClock`). It sets that clock from `sim_ticks` and turns vanilla time advance off for the dimension (check the exact 26.2 name at M0).
- **What it provides:** `simTicks()`, `simHours()`, `season()`, `dayOfSeason()`, `year()`, and `rate(channel)` for the channels world, water, erosion, growth, populations and weather.
- **Rates:** 1.0 by default. They are multiplied by the settings `fieldstation:erosion_multiplier` (erosion), `fieldstation:rain_multiplier` (weather input) and the difficulty preset, never inside a system's own maths.
- **Time-lapse:** speeds up to `max_time_speed` (60) by giving each system a larger dt, capped at 200 sim ticks per system step. Above that cap, a system runs more steps.
- **Sleep:** 22:00 to 06:00 is 8,000 sim ticks and runs over 40 real ticks at 200 sim ticks each, so erosion and growth advance smoothly. During sleep the mod may use up to 35 ms per tick, which stays under the 50 ms hitch limit.
- **Events:** it posts `DayBeganEvent` at 06:00, `SeasonChangedEvent` and `YearEndedEvent`.

## Naming conventions

Every doc and file follows these. The examples are real Field Station names.

| Thing | Convention | Example |
| --- | --- | --- |
| Registry id | `fieldstation:<snake_case>` | `fieldstation:green_frog`, `fieldstation:field_tablet` |
| Content data | `data/fieldstation/<kind>/<id>.json`, singular kind, one entry per file, loaded by reload listeners. Never a doubled namespace | `data/fieldstation/species/green_frog.json`, `plant/`, `shop_item/`, `reward_crate/`, `contract/` |
| Vanilla kinds | Singular folders | `recipe/`, `loot_table/`, `tags/block/`, `worldgen/`, `neoforge/biome_modifier/` |
| Per-item and per-block numbers | NeoForge data maps | `data/fieldstation/data_maps/item/sale_price.json` |
| JSON fields | snake\_case, with a unit suffix when there is a unit. `contentLint` rejects camelCase | `flood_tolerance_hours`, `water_use_l_per_day` |
| Player and level state | Player data attachments; level SavedData, one file per owning package (Content registry → Saved data). Chunk attachments are not used for simulation state | `fieldstation:player_state` (player), `fieldstation:terrain` (SavedData) |
| Per-stack state | Data components | `fieldstation:sample` (vial contents), `fieldstation:animal_tag` |
| Sounds | `fieldstation:<group>.<name>` | `fieldstation:green_frog.call`, `fieldstation:drone.arrive` |
| GameTests | `fieldstation:<system>/<test>`, batched by milestone | `fieldstation:water/stream_cuts_bank` |
| Lang | `entity.fieldstation.x`, `item.fieldstation.x`; UI text uses `fieldstation.ui.` | `fieldstation.ui.tablet.survey_title` |
| Game rules | `fieldstation:<snake_case>` | `fieldstation:erosion_multiplier` |

## Replacing vanilla

Every vanilla system the design changes is switched off on purpose, not left to chance. "Decided" rows come from the main tab; "Proposed" rows need Ruhan's yes.

| Vanilla system | Field Station | How | Status |
| --- | --- | --- | --- |
| Hostile mobs | None, anywhere | `EntityJoinLevelEvent`: if the entity is a `Mob` and its type is not in `#fieldstation:allowed_entities` (`data/fieldstation/tags/entity_type/allowed_entities.json`), cancel it. If the type is allowed but the wildlife or tame package did not spawn it (no `fieldstation:population_link` attachment and not a named or owned animal), also cancel it. Non-mob entities are never touched. Vanilla mobs reused by the Mobs tab are in the tag and have their vanilla breeding and spawn goals stripped. Game rules `minecraft:spawn_patrols`, `minecraft:spawn_wandering_traders`, `minecraft:spawn_wardens` and `minecraft:spawn_phantoms` set to false (check every vanilla rule id against the 26.2 game rule registry at M0, `/gamerule` tab-complete) | Decided |
| Vanilla animals (cow, pig, sheep and so on) | Replaced by the cove's real species | Natural spawning off (`minecraft:spawn_mobs` false; check the exact 26.2 name at M0); the `wildlife` package spawns entities from populations only. The same allow-list removes vanilla animals | Decided |
| Mob drops | No loot tables for animals | `minecraft:mob_drops` false (check the exact 26.2 name at M0). A natural death adds to the habitat's decomposer food; no carcass entity or block appears | Decided |
| Hunger | No vanilla hunger. Food and sleep needs are a player option (`player_needs`: off, comfort or real) | Food bar hidden through NeoForge GUI layer registration; vanilla hunger never drains. The `player` package runs the needs option on the energy bar (Player and tools → Food and sleep) | Decided |
| Sleep | Skips the night and restores energy | The RV bed (`rv_bed`) calls the clock's night time-lapse | Decided |
| Death | Wake in the RV bed with all items, −20 energy | `minecraft:keep_inventory` true; `LivingDeathEvent` on the player applies the energy cost; respawn at `rv_bed` | Decided |
| Crafting | A mix of crafting, drone purchases and earned drops | A built-in data pack filters vanilla recipes down to the allowed set; our recipes name a station and an unlock | Decided |
| Weather | Field Station's own weather | `minecraft:advance_weather` false (check the exact 26.2 name at M0); the `weather` package sets vanilla rain and thunder only as visuals | Decided |
| Fire | Only controlled burns, under permit | `minecraft:fire_spread_radius_around_player` 0 (vanilla fire never spreads; check the exact 26.2 name at M0); the burn tool runs Field Station's own fire spread | Decided |
| Bone meal | Replaced by compost. Growth comes from soil, water and light; compost adds soil nutrients | Vanilla bone meal is removed: the built-in data pack drops its recipes and loot, and it is hidden from the creative tab. `fieldstation:compost` from the compost bin raises soil nutrients on a 3 × 3 patch instead (Plants → Compost) | Decided (compost replaces it) |
| Villages and structures | No villages | Structures off in the `fieldstation:appalachia` dimension | Decided |
| Ores and mining | Not a mining game | No ores in the dimension's worldgen | Decided |
| Vanilla advancements | Replaced by Field Station's | Built-in data pack filters them out | Decided |
| Entity cramming | No cramming damage in pens and tanks | `minecraft:max_entity_cramming` 0 (check the exact 26.2 name at M0) | Decided |
| F3 coordinates | Kept: the game has no exploration tension to protect | No change | Decided |

The smoke counts every Mob that joins the world and fails if it sees one banned type.

## Adding content end to end

The same nine steps for every species, plant, item or block. Example: the green frog.

1. Open a ticket naming the files it touches.
2. Add the entry to the Content registry tab with its milestone.
3. Write `data/fieldstation/species/green_frog.json` with the numbers from the Mobs tab.
4. Add the GeckoLib model and animations, the textures, and the sounds (mono ogg, 44.1 kHz).
5. Add the lang lines, the tags (`fieldstation:amphibians`, `fieldstation:allowed_entities`) and any yield.
6. Run `./gradlew contentLint` until it is clean.
7. Write at least two GameTests, each proving a number from the spec (for example: frogs arrive only once a pond has 6 or more blocks of shallows and is 72 hours old).
8. Add a screenshot shot if the silhouette is new.
9. Run the smoke, then open the pull request.

## Tests and the smoke

| Layer | Command | What it covers |
| --- | --- | --- |
| Unit | `./gradlew test` | The `sim` rules, data validation, ArchUnit package rules |
| GameTests | `./gradlew runGameTestServer` | Rules in a real world: water cuts a bank, frogs arrive, drones deliver, no hostile mob ever joins |
| Client smoke | under a virtual display | Client boots, renders the cove, no NaN, no unhandled payload |
| Dedicated server | 600-tick boot | No client code in common code; the world ticks cleanly |
| Balance | `tools/sim` | Runs a year of the cove headless to check pace, money and population numbers |

**SMOKE PASSED** (`./gradlew smoke`) needs all of: the build plus datagen output matching what is checked in, every test passing, a clean server boot, a clean client boot, and zero banned mobs. Only one client run at a time.

ArchUnit rules:

- `sim` imports nothing from `net.minecraft` or `net.neoforged`.
- System packages import each other only through `api`.
- Only `com.fieldstation.clock` may call Level, WorldClock or ClockManager time methods (`getOverworldClockTime`, `getGameTime` and the 26.2 clock APIs; check the exact names with javap at M0).
- Only `client` uses client classes.

## Performance budget

20 TPS, an average MSPT under 30 ms, and no single tick over 50 ms. The plot is 200×200, so a full water flood fill touches 40,000 columns; heavy work is spread across ticks.

| System | Share of the budget (average MSPT) |
| --- | --- |
| Vanilla and NeoForge baseline | about 10 ms |
| Terrain and erosion | 4 ms |
| Water bridge | 3 ms |
| Wildlife and fish (live entities only) | 3 ms |
| Plants and soil | 2 ms |
| Weather, economy, story | 1 ms |
| Slack | 7 ms |

These are targets to measure in Milestone 1 with spark, not measurements. Populations are numbers, and only animals near the player exist as entities, which keeps the entity count low. Spark target: 64 wildlife entities plus 10 owned animals use 3 ms or less.

## Saves

- From the first public beta, never re-use or rename an id. Retire old ones with `DeferredRegister#addAlias`.
- SavedData files, attachments and components use versioned codecs (data\_version).
- Migrations are lazy and take a backup first.
- Each release adds a fixture save to `fixtures/saves/`, and a migration test loads every one.

## Modpack and other mods

- **Modpack:** packwiz in `pack/`, exported as `.mrpack` and a CurseForge zip. The pack's MAJOR.MINOR always equals the mod's. It contains Field Station, Flowing Fluids and GeckoLib.
- **Tested alongside:** performance and cosmetic mods (Sodium, Iris, Distant Horizons) where they ship NeoForge 26.2 builds. Minimap mods are fine, as Definitions says.
- **Warned on first launch:** mods that add hostile mobs (their mobs are blocked anyway).
- **Blocked:** other water physics mods, season mods, and mods that replace hunger, inventory or time, by listing their exact mod ids as `type="incompatible"` dependencies in `neoforge.mods.toml`. The list is kept in Definitions → Conflicts and checked at each pin move (start with `sereneseasons`).

## M0: before the first line of game code

- [ ] Ruhan confirms the repo name; create the repo.
- [ ] Re-check NeoForge, ModDevGradle, Flowing Fluids and GeckoLib versions on the web; write them into `gradle.properties`.
- [ ] Check the 26.2 clock API names with javap and every vanilla game rule id with `/gamerule` tab-complete; fix the names in this tab, Definitions, Content registry and Systems spec.
- [ ] Generate the NeoForge project, mod id `fieldstation`, with the package map above as empty packages.
- [ ] Add the ArchUnit rules, the `contentLint` task and the `smoke` task, all passing on the empty mod.
- [ ] Add CI that runs `./gradlew smoke` on every pull request (client smoke where a virtual display exists).
- [ ] Boot the empty mod on the Mac with Flowing Fluids loaded. This is Milestone 1's task 1.
