# Milestone 1 plan

Milestone 1 ships the mod and the first plot: soil you can dig, water that pools, an RV home, and frogs that find a pond you dug.

## Task tracker

Milestone 1 is one plot in one biome (a Southern Appalachian cove, 200 × 200 blocks). It has layered soil, finite water, a spring, erosion, ponds that come alive, the RV, and no hostile mobs. Roles map to the studio agents: Lead, Sim, Content, UI, Test.

| # | Task | Owner | Needs | Done when | Status |
| --- | --- | --- | --- | --- | --- |
| 1 | NeoForge project (ModDevGradle) on Minecraft 26.2 in a GitHub repo, Flowing Fluids dependency, CI build | Lead | — | M0 list in Minecraft development is complete: the build, ArchUnit, contentLint and smoke pass on the Mac and in Actions, and the dev client loads with Flowing Fluids | To do |
| 1b | Test slice, brainstorm steps 2–5 | Sim | 1 | All 5 slice steps pass on the Mac; results filed | To do |
| 2 | World preset, plot generator, no-mob rules | Sim | 1 | The plot matches the prototype's land; fieldstation:world/no\_hostiles and fieldstation:world/cove\_builds pass | To do |
| 3 | Soil blocks with layers, packed, seeded, textures; moisture as a client tint | Content | 1 | All 6 soil blocks place, render cleanly, and show their states | To do |
| 3b | Clock layer (clock package) | Sim | 1 | The `sleep_skips` and clock unit tests pass; ArchUnit finds no other time reader | To do |
| 4 | Water sim: Flowing Fluids bridge and column model, soaking, spring, rain, evaporation, outlet | Sim | 2, 3, 3b, 4b | Water-balance GameTests pass | To do |
| 4b | Weather for one plot: hourly state, rain and storm events, season temps | Sim | 3b | A 28-day headless run matches the season table within 10% | To do |
| 5 | Shovel, soil bag, tamper, seed pouch, field\_bucket, survey\_stake, benchmark\_stake | Content | 3 | Dig, dump, tamp and sow work as in the prototype | To do |
| 6 | Sediment sim: wear, carry, settle, silt and mud | Sim | 4 | Sediment GameTests pass; a dam breach looks right in play | To do |
| 6b | Plant growth: meadow grass, soft rush, bare-to-grass regrowth | Content | 3b, 7 | `fieldstation:terrain/grass_protects` passes | To do |
| 7 | Habitat scan, microhabitat tags, SavedData | Sim | 4 | Ponds and tags survive save and reload; re-tag within 100 ticks | To do |
| 7b | Data loader: species and plant JSON, codecs, contentLint | Lead | 1 | `./gradlew contentLint` passes on the green frog, green darner and soft rush files | To do |
| 7c | API package, services and events (Minecraft development → API events and services) | Lead | 1 | ArchUnit passes with real packages | To do |
| 8 | Ecology and entities: darner, green frog, tadpoles | Content | 7 | Frogs breed in a dug pond within 7 game days in a fixed-seed test | To do |
| 8b | Entity spawner and population links (Systems spec → Entity spawner) | Sim | 7 | `fieldstation:wildlife/spawn_by_tag` passes | To do |
| 9 | RV: bed and sleep, computer (shop, grants, terrain map), lab bench | UI | 5, 7 | A full day loop works: dig, test water, sleep, get paid | To do |
| 9b | Shop, delivery drone entity and drone pad | UI | 9 | `fieldstation:economy/drone_delivers` passes | To do |
| 10 | Binoculars, field journal, grants | UI | 8 | Logging a species pays its grant the next morning | To do |
| 11 | HUD and clean screen style | UI | 9 | All screens use one style; looked at in screenshots | To do |
| 11b | Payloads and HUD sync (Minecraft development → Network) | UI | 1 | The client smoke reports no unhandled payload | To do |
| 12 | GameTests in CI; benchmark on the Mac (CI reports it) | Test | 4, 6, 7 | Every gate below runs in CI | To do |
| 13 | Playtest pass (tester agent, 21 game days) | Test | 1–12 | No blocker bugs; a findings list filed | To do |

**Added tasks.**

| # | Task | Owner | Needs | Done when | Status |
| --- | --- | --- | --- | --- | --- |
| 14 | Onboarding in the cove: the first 15 minutes | UI and Content | 2, 5, 9 | A tester agent passes the stranger test with every score 6 or more | To do |
| 15 | Feel pass for dig, pack, pour and plant: sounds, crumbs, readouts | Content | 5 | The tester's Feel score is 6 or more | To do |

## Gate tests

Milestone 1 ships only when every test below passes in CI. They are Minecraft GameTests on small built structures with a fixed random seed. Tests advance time with `ClockService.advanceForTest(sim_hours)`, which runs the sim steps synchronously and skips entity ticks.

| Test | Setup | Passes when |
| --- | --- | --- |
| `fieldstation:water/water_conserved` | Closed 10 × 10 basin, 200 levels of water | After 2,000 ticks, water plus soil storage is within 0.5% of the start |
| `fieldstation:water/clay_pond_holds` | 6 m² basin in packed red clay, filled 4 levels, no rain, spring | Still ≥ 6 columns and ≥ 2 levels deep after 72 game hours |
| `fieldstation:water/subsoil_pond_leaks` | Same basin dug into subsoil over gravel (no clay layer), filled 2 levels | Fewer than 3 columns after 24 game hours |
| `fieldstation:water/spring_reaches_pond` | Spring, a cut channel, a basin | The pond is flagged spring fed within 2 game hours |
| `fieldstation:terrain/sediment_mass` | Bare slope, one storm, a basin at the bottom | Layers worn away = layers deposited + outlet loss, within 1 layer |
| `fieldstation:terrain/grass_protects` | Two identical slopes, one bare, one grassed, same storm | The bare slope loses at least 5× more layers |
| `fieldstation:terrain/delta_sorting` | Fast channel into a still pond | Gravel settles within 3 blocks of the mouth; silt lands farther out |
| `fieldstation:terrain/dam_breach` | Loose dirt dam overtopped by 2 levels; a copy with a stone spillway | The loose dam breaches within 2 game hours; the spillway dam holds for 48 |
| `fieldstation:terrain/bank_slump` | Saturated topsoil bank undercut by 2 blocks | The top block falls into the water |
| `fieldstation:habitat/retag_after_dig` | Dig and fill a basin | Its columns become open water or shallows within 100 ticks of filling |
| `fieldstation:wildlife/spawn_by_tag` | Pond with and without reeds | Frogs appear only on shallows and reed-bed tags |
| `fieldstation:wildlife/frog_breeding` | Established pond, 6 m², reeds at 10%, 4 frogs, late spring with water held at 20 °C | Tadpoles within 7 game days |
| `fieldstation:wildlife/corridor` | Two ponds 20 blocks apart: wet link, then a dry gap | Frogs move along the wet link and never across the dry gap |
| `fieldstation:world/no_hostiles` | Spawn each vanilla monster type and a modded test monster through `EntityType.spawn` and a spawner block, on and off the plot | Each one is rejected within 1 tick |
| `fieldstation:clock/sleep_skips` | Sleep at 22:00 with energy at 10 | Wake at 06:00 with full energy; water and populations step forward correctly |
| `fieldstation:world/cove_builds` | The cove built from a fixed seed | All 11 places exist, the Run flows to the outlet, and every habitat record has its starting population |
| `fieldstation:economy/drone_delivers` | Buy grass seed ×40 on the RV computer | A crate with the seed lands on the drone pad and cash drops by $20 |
| `fieldstation:economy/sighting_pays_grant` | Log a green frog with binoculars | The next 06:00 pays its $6 grant |
| `fieldstation:world/dedicated_boot` | Dedicated server, 600-tick boot | No client class loads; the world ticks cleanly |
| `fieldstation:wildlife/no_player_kills` | Mowing, handling, releasing, pumping a pond down and trapping, each next to live animals | Zero animal deaths from any of them |

**Benchmark gate.** `./gradlew benchmark` boots a dedicated server on the full cove, forces a 2-game-hour storm, and records spark over 2,000 ticks on Ruhan's Mac. Pass: mod time ≤ 13 ms average and ≤ 20 ms peak, whole-server MSPT < 30 ms average and no tick > 50 ms. CI runs it and reports the result but does not gate on it.

**Later gates (by version).** Each test joins the gate list in the version that ships its system.

- v0.1: `fieldstation:wildlife/no_player_kills` (in the table above)
- v0.3: `fieldstation:habitat/vernal_pool_hydroperiod`, `fieldstation:fish/stream_temperature`
- v0.4: `fieldstation:insects/emergence_normal_year`
- v1.0: `fieldstation:progression/reference_site`

**Playtest gate.** Besides the GameTests above, Milestone 1 must pass the stranger test with a tester agent's scores of at least 6 across the board (see Fun, feel and finish). From v0.2 on, scores must not drop between builds.

## Ready for development

On October 19, 2026, the studio reviewed the whole blueprint in three passes: engineering, core loops and fun, and living world and numbers. The review found 105 problems, 11 of them blockers. Every one was ruled on and fixed across the tabs, except the four decisions below, which are Ruhan's. Development starts at M0 once the open boxes are ticked.

**Fixed in the blueprint**

- [x] One release table (Fun, feel and finish → Path to a finished game); every other tab points to it.
- [x] The v0.1 onboarding uses only v0.1 items: the RV computer's Goals card, the start kit and $300, defined once in Player and tools.
- [x] No player action ever kills an animal, and animals in care never die (Definitions core rules). Gate `fieldstation:wildlife/no_player_kills`.
- [x] Wild and Founder animals are never sold, and phase 1 pays no cash anywhere.
- [x] One water-ownership rule: Flowing Fluids in loaded chunks, our column model in unloaded ones, with a handover on load and unload.
- [x] Saved data is split per package on the `fieldstation:appalachia` level. A world writer keeps blocks and arrays in sync both ways. There is a clock spec for 26.x and one tick table (12.2 ms average).
- [x] Network payloads, API events and services, and a `habitat` package.
- [x] Milestone 1 tasks cover the test slice, clock, weather, plants, data loader, API, spawner, drone and payloads. Gates use `fieldstation:<system>/<test>` names, and the fixtures pass on the spec's own numbers.
- [x] One population model (breeding pulses). Every cove species has an arrival rule, and every rated species has an allotment.
- [x] Climate numbers come only from Biomes. Stream temperature and stream health have formulas. The vernal pool holds water long enough.
- [x] Ids match across Mobs, Content registry and The cove. The plant roster is Blue Ridge natives. The entity cap is 64.
- [x] The energy budget fits a day's hand work, one survey visit refreshes a habitat, and all durations are in game days.

**Ruhan's decisions**

- [x] The "Proposed" rows in Minecraft development → Replacing vanilla (own weather, permit-only fire, no bone meal, no ores, vanilla advancements, cramming, F3). Decided October 19: all approved, and compost replaces bone meal. Rule ids are renamed to 26.2 names.
- [x] Food and sleep for the player. Decided: a player option, off, comfort (default) or real.
- [x] Keep or strike the new late-game money sinks (neighbor agreements, cove easement, larger RV). Decided: kept, plus a full Upgrades catalog (Upgrades tab).
- [x] Repo name: `ruhanu155/palladarium` (mod id `fieldstation`). Created on GitHub and initialized with the M0 scaffold.

**M0 checks before code**

- [x] Re-check NeoForge, ModDevGradle, Flowing Fluids and GeckoLib versions on the web, and pin them in `gradle.properties` (NeoForge `26.2.0.93`, ModDevGradle `2.0.148`, Flowing Fluids `1.0.7` / `ynkJWrlk`, GeckoLib `5.5.6` / `IEGPh4CJ`, JUnit `6.1.3`, ArchUnit `1.5.1`).
- [x] Confirm the 26.2 clock API names with javap (`ServerClockManager`, `ClockManager`, `WorldClock`, `Level#getOverworldClockTime`, `Level#getDefaultClockTime`, `LevelReader#getGameTime`) and the game rule ids in `net.minecraft.world.level.gamerules.GameRules`. Update every "(check at M0)" note.
- [ ] Boot the empty mod with Flowing Fluids and GeckoLib on the Mac. Smoke, ArchUnit and contentLint pass in CI.
- [ ] Run the test slice (task 1b) before tasks 4–8. If the Flowing Fluids API can't do the water handover, list the mixin it needs against the 8-mixin budget.
