# Definitions and rules

## Glossary

| Term | Meaning |
| --- | --- |
| Block | One 1 m cube of the world |
| Column | A 1 × 1 m stack of blocks from bedrock to sky; most simulation data is stored per column |
| Level | One eighth of a block of water (0.125 m) |
| Layer | One eighth of a soil block; erosion and deposition add or remove layers |
| Plot | The player's simulated land in a biome: 200 × 200 columns (about 10 acres) |
| Habitat record | The saved data for one pond or stream reach: area, depth, age, water quality, populations |
| Microhabitat tag | The label each column gets from its conditions (open water, reed bed, warm slope, and so on); spawning and plants read it |
| Population | The count of a species in a habitat record, simulated as numbers |
| Individual | A mob spawned near the player that stands for part of a population |
| Condition | 0–100 health of an individual or a population average, from how well its needs are met |
| Wariness | How easily a mob flees from the player; it drops with calm habituation |
| Stream power (P) | How strongly flowing water can cut: flow × (0.1 + drop) |
| Load | Soil being carried by water: gravel, silt and clay |
| Turbidity | How muddy water is, 0–1 |
| Catchment | All the land that drains to one point |
| Crest | The top of a dam |
| Spillway | A low, hardened notch that lets floods pass over a dam safely |
| Benchmark | The fixed stake the laser level measures from |
| Corridor | A path of suitable ground (wet for frogs, woods for salamanders) linking habitats |
| Dispersal | Young animals leaving home to settle new habitat |
| Succession | Bare ground changing to meadow, shrubs, then woods over time |
| Seed bank | Living seeds stored in topsoil for up to 2 years |
| Game hour, day, season, year | 1,000 ticks; 24 hours; 7 days; 28 days |
| Tier | A reputation level that unlocks gear, permits and biomes |
| Reputation | Lasting standing earned by good work; never spent |
| Science credits (SC) | Currency earned from high-quality data and spent at the Research exchange |
| Grant | Daily pay for each species with a viable population on the plot |
| Contract | A fieldwork job with a deadline, a pay rate and a data-quality grade |
| Order | A request to buy crafted goods, in the Orders tab of the Contracts app |
| Drone drop | Delivery of a purchase or reward crate by the station drone |
| Reward crate | A fixed set of items earned from a goal, contract, tier, badge or event |
| Permit | Permission for a regulated action: burning, handling protected species, moving animals, stocking |
| Quarantine | Holding new animals apart in the lab before release |
| Head-starting | Raising young animals in the lab to release them at a safer size |
| Native range | Where a species occurs naturally; release outside it is blocked |
| Catch-up | Simulating a plot's time while the player was away |

| More terms | Meaning |
| --- | --- |
| Morning briefing | Up to 3 optional suggestions each morning, built from the land's state |
| Storyteller | The system that paces events using a tension budget and cooldowns |
| Tension budget | Points the storyteller spends on events; refills daily |
| Opportunity | A good event, such as a firefly night or a migration |
| Named animal | A long-lived individual with a permanent ID and journal page |
| Chronicle | The journal's automatic history of the plot |
| Year card | The one-card summary of a game year |
| Field tablet | The handheld version of the RV computer |
| Time-lapse work | Holding use on a repeated job to speed game time while it runs |
| Crew | Volunteers or hired helpers for simple jobs from a job list |
| Stream health score | 0–10 grade of a stream from a kick-net sample |
| Stable step | The steepest step a material can hold between neighboring columns |
| Stranger test | The definition of done: what a new player must manage in 15 minutes |
| Reference site | The finale status the Park Service gives a fully restored cove |

## Core game rules

These hold everywhere in the game. A feature that breaks one of them needs a decision on the main tab first.

1. **Water is conserved.** It enters only from rain, springs and creeks, and leaves only by soaking, evaporation, plant use and outlets. There are no infinite sources on a plot.
2. **Soil is conserved.** Every layer worn away ends up somewhere downstream or leaves over the outlet. The only new topsoil comes from compost.
3. **No combat.** No hostile mobs spawn anywhere. The player can't hurt any mob, and nothing hurts the player except vanilla falling and drowning.
4. **No player kills.** No player action ever causes an animal's death. Mistakes cost condition, reputation and data, never a life.
5. **Animals in care never die.** Eggs can fail to develop, and that is the one natural loss in the lab. Neglect lowers condition, and below 20 condition the state wildlife vet collects the animal (−10 reputation).
6. **Life comes on its own.** Wild animals arrive because the habitat suits them. The only exceptions are permitted lab releases and stocking.
7. **What you see is what the land supports.** Every wild mob near the player stands for a real population in a habitat record.
8. **Plants grow; they are never placed full-grown.**
9. **Real behavior.** Species keep their real hours, calls, food, range and life cycle.
10. **Mistakes hurt habitats, not the player.** Setbacks cost time, money, reputation or populations, never the player's life or items.
11. **Nothing wild is sold.** Only captive-bred animals with a permit, and plants or seed you grew or gathered, can be sold.
12. **Orders don't wait,** except when storms ground the drone.
13. **Crafted and bought stay separate.** Local, simple builds are crafted. Manufactured gear is bought. Prototype gear is earned.
14. **Rewards are fixed and visible.** No random loot.
15. **Tiers never drop.**
16. **The server decides.** All simulation runs on the server; clients only draw.
17. **Time is in game days.** All game durations are in game days. "Week" means 7 game days and is used only for the economy.

**Play rules.**

- Every goal is optional; ignoring one has only its natural consequence.
- Every event is telegraphed, has more than one fix, and can be recovered from.
- No chore takes over 5 real minutes without a faster way within reach.
- The HUD shows honest numbers and never cheers.
- Nothing locks the player into a step.

## Mod settings and game rules

Settings live in the NeoForge server config `fieldstation-server.toml`, which is per world under `serverconfig/`. Keys are snake\_case. Values that can change in a running world are also game rules with ids `fieldstation:<snake_case>`. No hostile mobs is a constant, not a setting.

| Setting | Default | Range | Effect |
| --- | --- | --- | --- |
| `difficulty_preset` | standard | relaxed, standard, realist, sandbox | Sets the values below at once |
| `player_needs` | comfort | off, comfort, real | How much food and sleep matter (Player and tools → Food and sleep). Chosen at world creation; can change any time; not set by the difficulty preset |
| `fieldstation:erosion_multiplier` | 1.0 | 0–2 | Scales wear and carry |
| `fieldstation:rain_multiplier` | 1.0 | 0.25–2 | Scales rain amounts |
| `fieldstation:grant_multiplier` | 1.0 | 0.5–3 | Scales species grants |
| `fieldstation:invasive_pressure` | 1.0 | 0–2 | Scales invasive arrival and spread |
| `fieldstation:disease_enabled` | true | true or false | Turns disease on or off |
| `fieldstation:dam_breach` | true | true or false | Whether overtopped dams can fail |
| `vanilla_passive_on_plot` | false | true or false | Allows vanilla farm animals on plots |
| `sim_budget_ms` | 13 | 8–20 | Most time per tick the simulation may use on average |
| `max_time_speed` | 60 | 1–60 | The fastest time speed allowed |
| `catch_up_max_days` | 28 | 0–28 | Longest catch-up simulated on return |
| `drone_grounded_in_storms` | true | true or false | Storms ground the delivery drone |
| `wildlife_entity_cap` | 64 | 32–96 | Most wildlife, fish and insect entities near the player at once |

## Data definitions

Species, plants, recipes, shop items, reward crates and contracts are all JSON data files, so content can be added without new code.

**Species** — `data/fieldstation/species/<id>.json`

```json
{
  "scientific_name": "Lithobates clamitans",
  "category": "wildlife",
  "native_regions": ["fieldstation:appalachia"],
  "microhabitats": ["shallows", "reed_bed"],
  "active_hours": "day_night",
  "goals": ["flee", "hide", "forage", "call", "breed"],
  "senses": { "range_blocks": 12, "wariness": 0.8 },
  "needs": { "skin_moisture": true, "temp_active_c": [12, 30], "temp_stress_c": 32, "cover_min_fraction": 0.2 },
  "arrival": { "min_habitat_age_hours": 72, "min_area_blocks": 6, "min_reed_fraction": 0.06, "chance_per_hour": 0.02 },
  "breeding": { "breeding_window": "spring", "trigger": "warm_water", "water_temp_min_c": 18, "young_per_female": 40, "young_survival_fraction": 0.25, "years_to_adult": 1, "adult_survival_per_year": 0.6 },
  "isolated": true,
  "lifespan_years": 4,
  "grant_dollars_per_day": 6,
  "protected": false,
  "sounds": { "call": "fieldstation:green_frog.call", "alarm": "fieldstation:green_frog.squeak" }
}
```

Economy numbers in this example must match the Systems spec Money table. GeckoLib models are bound in the client renderer by entity id, never in this file.

The id comes from the file path (`fieldstation:green_frog`), and the display name lives in the lang file as `entity.fieldstation.green_frog`. Every JSON field is snake\_case with a unit suffix where it has a unit; `./gradlew contentLint` rejects camelCase fields. Per-item and per-block numbers (fuel value, compost value, sale price) go in NeoForge data maps at `data/fieldstation/data_maps/item/<name>.json` instead of these files.

**Plant** — `data/fieldstation/plant/<id>.json`. Fields:

- `id`, `group`, `layer` (ground, shrub, canopy, edge, aquatic)
- `moisture` (band range), `light`, `soils`, `depth_levels` (for water plants)
- `growth_hours` per stage, `spread_method`, `spread_reach_blocks`, `seed_season`
- `erosion_cover_fraction`, `water_use_l_per_day`, `phosphorus_uptake_g_per_day`
- `flood_tolerance_hours`, `native_regions` (dimension ids, such as `fieldstation:appalachia`), `invasive`

**Recipe.** Type `fieldstation:station_recipe` (its own `RecipeSerializer` record and `RecipeType`), in `data/fieldstation/recipe/`. Fields: `station`, `ingredients`, `result`, `unlock` (one of `{"tier": …}`, `{"goal": …}` or `{"contract": …}`). Vanilla crafting-table recipes for the same outputs are removed by the built-in data pack.

**Shop item** — `data/fieldstation/shop_item/<id>.json`. Fields: `item`, `count`, `price_dollars`, `tier`, `category`, `delivery` (drone or truck), `seasonal_price_multiplier`.

**Reward crate** — `data/fieldstation/reward_crate/<id>.json`

```json
{
  "source": { "tier": "technician" },
  "contents": [
    { "item": "fieldstation:data_logger", "count": 2 },
    { "item": "fieldstation:edna_kit", "count": 3 }
  ],
  "cash_dollars": 0,
  "science_credits": 10
}
```

**Contract template** — `data/fieldstation/contract/<id>.json`. Fields:

- `client`, `title`, `task` (one of: survey, map, sample, monitor, restore, order)
- `targets` (species, habitat or count), `window_hours`, `tools`
- `pay_dollars`, `science_credits`, `reputation`, `quality_checks`, `min_tier`, `biomes`

## Add-ons and compatibility

**Add-ons.** Datapacks and add-on mods can add species, plants, recipes, shop items, reward crates, contracts and biomes, using the formats above. On load, every file is checked, and any that fails is skipped with a clear log message.

| Check | Rule |
| --- | --- |
| Species behavior | Can't include an attack goal or deal damage |
| Species spawning | Must declare native regions and microhabitat tags; spawns only through populations |
| Species drops | No loot tables |
| Plants | Must declare a layer, a moisture band and native regions |
| Recipes | Must name a station and an unlock |
| Shop items | Need a price, a tier and a delivery method; prices must be $1–$10,000 |
| Reward crates | Contents must be fixed, never random |
| Native range | A species can't be released outside its declared native regions |

**Required:** NeoForge 26.2.0.93, Flowing Fluids 1.0.7 (NeoForge build), GeckoLib 5.5.6.

**Works alongside:** performance and cosmetic mods (Sodium, Iris, Distant Horizons) where they ship NeoForge 26.2 builds, and minimap mods.

**Conflicts:**

- Other water physics mods: they replace Flowing Fluids, which this mod depends on.
- Season mods: they fight over the season clock; use one or the other.
- Mods that add hostile mobs: their monsters never spawn, because the monster rule applies everywhere.

Water physics and season mods are blocked by listing their exact mod ids as `type="incompatible"` dependencies in `neoforge.mods.toml`. The list is kept here and checked at each pin move (start with `sereneseasons`).

**Multiplayer.** Solo only for now. Single-player runs on an integrated server, which is still the authority for all simulation, so the same code supports multiplayer later.
