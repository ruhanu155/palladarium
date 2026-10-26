# Content registry

Every block, item, entity and screen the mod adds, with its id and the milestone it lands in. Ids use the `fieldstation:` namespace.

## Blocks

Every id is under the `fieldstation:` namespace unless it says `minecraft:`. M1 means the block is needed for Milestone 1.

| Id | Properties | Purpose | M1 |
| --- | --- | --- | --- |
| `topsoil` | layers 1–8, packed, seeded (none, grass, reed) | Top ground layer; grows plants | Yes |
| `subsoil` | layers, packed | Water store under topsoil | Yes |
| `red_clay` | layers, packed | Pond liner; seals when packed | Yes |
| `gravel_layer` | layers | Drains; channel and crayfish beds; settles from fast water | Yes |
| `silt` | layers | Settles from slowing water; fertile; dredged back as topsoil | Yes |
| `mud` | layers | Waterlogged soil; slows the player; muddies water | Yes |
| `clay_film` | none (thin carpet) | Settled clay on a pond floor; halves soaking | No |
| `spring_head` | flow (0–15) | Source of the spring; can't be broken | Yes |
| `soft_rush` | age 0–3, waterlogged | Reed; frog cover; filters muddy water | Yes |
| `meadow_grass` | age 0–2 | Grass cover that stops erosion | Yes |
| `native_shrub` | age 0–4 | Woods-edge habitat; browsed by deer | No |
| `silt_fence` | fill 0–8, facing | Traps silt; needs cleaning when full | No |
| `coir_log` | age 0–3, facing | Holds a bank for one season, then rots | No |
| `bluebird_box`, `wood_duck_box`, `bat_box` | facing, occupied | Nest and roost boxes; arrival rules in Systems spec (a bluebird box doubles bluebird arrival chance) | No |
| `rv_bed`, `rv_computer`, `lab_bench`, `rv_door` | facing, part | RV interior; all of them have screens | Yes |
| `minecraft:cobblestone` | — | Riprap and spillways (erodibility 0) | No |
| `minecraft:stone` | — | Bedrock layer; only the excavator cuts it | Yes |
| `drone_pad` | facing | Landing pad for the delivery drone; part of the RV | Yes |
| `field_beacon` | facing | A drop point for the delivery drone away from the RV | No |
| `ice_layer` | layers 1–8 | Ice on still water, with the same level count it froze from; thaw turns it back to water | No |

**Water.** The mod adds no fluid of its own. Water is vanilla `minecraft:water`, which Flowing Fluids makes finite; it adds no block. In a loaded chunk on the plot, Flowing Fluids moves the water. Every 20 ticks the water bridge reads each wet column's `minecraft:water` amount (1–8) into the arrays. When a chunk unloads, the arrays take over and move water with our column model. When the chunk loads again, the world writer writes the array levels back as `minecraft:water` with amounts, and Flowing Fluids resumes. Our column model, used only for unloaded chunks: each step, each column passes min(Δsurface/2, 2 levels) to its lowest of 4 neighbors, in a fixed shuffled order, with dt the sim ticks since that column's last step. Soaking, springs, rain, evaporation and outlets always run on the arrays, never inside Flowing Fluids. The arrays add and remove water as level deltas, and the bridge places or removes water blocks to match.

Every plant species, its blocks and growth rules: Plants.

**Plant ids added for the Blue Ridge cove** (details in Plants): `eastern_white_pine`, `black_cherry`, `wild_bergamot`, `broadleaf_arrowhead`, `jewelweed`, `bur_reed`, `hazel_alder`, `tall_fescue`, `japanese_stiltgrass`, `chinese_privet`, `japanese_honeysuckle`, `tree_of_heaven`, `mockernut_hickory`, `chestnut_oak`, `northern_red_oak`, `serviceberry`, `common_blue_violet`, `sassafras`, `old_apple`.

**Ids added for the round 2 concepts** (adopted October 19, 2026; M1 No; each one's version is in the Fun release table, and its spec is in the named tab):

- **Tools** (Player and tools): `sketchbook`, `loppers`, `post_driver`, `fencing_pliers`, `mcleod`, `clinometer`, `grafting_knife`.
- **Survey and night gear** (Capture, Weather, Insects): `monarch_tag`, `bird_band`, `staff_gauge`, `hail_pad`, `rain_gauge`, `star_blanket`, `bioblitz_flag`. Lighting is block items: `warm_bulb`, `shielded_lamp`, `red_lamp`, `light_shield`, `motion_switch`.
- **Earned items:** `dark_sky_plaque`, `paper_reprint`, `framed_sketch`.
- **Trail craft and beaver dam analogs** (Landscaping): items `water_bar_stone`, `step_stone`, `puncheon`, `bda_post`, `willow_whip`; blocks `trail_tread` (packed soil with layers) and `beaver_dam_analog`.
- **Orchard** (Plants): items `apple_rootstock`, `scion_wood`, `grafted_apple`, `heirloom_apple`; block `apple_tree`.
- **Seed vault** (Additions): block `seed_vault`; seed packets get an `origin` data component (plot or bought).
- **Cleanup** (The cove): item `trash`, with a `kind` data component.
- **Entities:** `volunteer` (one shared visitor entity for the cleanup crew, BioBlitz volunteers and later guided walks; at most 8 at once, outside the wildlife cap), `old_timer`, `old_timer_truck`.
- **Data kinds:** `data/fieldstation/old_timer_story/<id>.json`. Species files gain `mystery`, `detect_chance_per_visit`, `clue_methods` and `sketch_time_s` (Mobs).

## Items and tools

| Id | Stack | What it does | M1 |
| --- | --- | --- | --- |
| `field_shovel` | 1 | Digs one whole block into the soil bag; dredges silt | Yes |
| `soil_bag` | 1 | Holds 16 blocks of one layer; right-click dumps 1 block as loose | Yes |
| `wheelbarrow` | 1 (placeable) | Holds 64 blocks of mixed layers; slows the player while pushed | No |
| `hand_tamper` | 1 | Packs the targeted block (packed=true); costs 0.5 energy | Yes |
| `seed_pouch` | 1 | Holds grass and reed seed; sows a 3 × 3 area | Yes |
| `binoculars` | 1 | Zoom; logs a species to the journal when it stays in view for 2 s | Yes |
| `sample_bottle` | 16 | Takes a water sample from the targeted column | Yes |
| `test_reagent` | 64 | Used up at the lab bench for each test | Yes |
| `field_journal` | 1 | Opens the journal screen anywhere | Yes |
| `rental_key` | 1 | Good for one day; the machine (mini excavator or skid steer) is delivered by truck, and the key summons it at the drone pad | No |
| `reed_plug` | 64 | Places a soft rush directly in shallows | Yes |
| `bluebird_box`, `wood_duck_box`, `bat_box`, `silt_fence`, `coir_log` | 16 | Block items; place their blocks | No |
| `field_bucket` | 1 | Holds 8 water levels; scoops from and pours onto the targeted column | Yes |
| `survey_stake` | 16 | Marks a column for a dig (flags are a variant of the same item); the shovel readout counts down to the floor set from the benchmark stake | Yes |
| `benchmark_stake` | 1 | Sets the 0 height for the shovel readout; survey stakes and the laser level measure from it | Yes |
| `planting_trowel` | 1 | Plants plugs and seedlings in the targeted column; part of the start kit | Yes |
| `compost_bin` | 1 | Block item. Turns cuttings, litter and scraps into 8 compost in 3 days (replaces bone meal; Plants → Compost) | No |
| `compost`, `worm_castings`, `mycorrhizal_inoculant` | 16 | Soil boosters used on a 3 × 3 dry patch (Plants → Compost) | No |
| `grocery_box`, `sandwich`, `camp_stew`, `coffee`, `blackberries`, `ramps`, `chanterelles` | 16 | Food for the food and sleep option (Player and tools → Food and sleep) | No |
| `work_gloves` | 1 | Dry gloves for handling logs, stone and rough material; part of the start kit | Yes |
| `wading_gloves` | 1 | Wet gloves for handling in water and wet ground; part of the start kit | Yes |

Items come three ways: crafted from gathered materials, bought on the RV computer and dropped by the delivery drone, or earned as reward drops. Which item comes which way is set in the Economy, crafting and drones tab. Vanilla tool, weapon and armor recipes are removed; basic building recipes stay.

## Entities

Wild animals are numbers in habitat records. An entity is only a view of that number near the player (see *Wildlife rules*). Entities have no combat AI and never attack. All of them flee from a running player and come back after a while.

| Id | Appears on (microhabitat) | Behavior | M1 |
| --- | --- | --- | --- |
| `green_darner` | Open water, by day | Patrols over the water, hovers, perches on reeds | Yes |
| `green_frog` | Shallows, reed bed | Sits on the bank, jumps in when approached, calls at night | Yes |
| `tadpole` | Shallows | Our own entity; the vanilla tadpole model is a visual base only. Schools; grows legs as it ages | Yes |
| `bullfrog` | Open water at least 1 block deep | Like the green frog, larger; eats small frogs | No |
| `crayfish` | Spring-fed gravel bottom | Walks the bottom; hides under stones by day | No |
| `great_blue_heron` | Shallows at dawn | Wades and strikes at frogs; flies off when the player comes within 16 blocks | No |
| `white_tailed_deer` | Woods edge, water at dusk | Grazes; herd of 2–4; crosses water up to 2 levels deep | No |
| `eastern_bluebird` | Dry meadow, nest box | Perches low, drops onto insects | No |
| `red_winged_blackbird`, `killdeer`, `pileated_woodpecker` | Reed bed, gravel bar, dead snags | Ambient species from *How the landscape drives the world* | No |
| `delivery_drone` | Drone pad and field beacons | Flies a crate to the drone pad or a field beacon; grounded in storms | Yes |
| `field_dog` | With the player; sleeps in the RV | Follows the player; never chases, catches or harms wildlife | No |
| `mini_excavator` | Drone pad, when rented | Vehicle; digs 4 blocks per swing and can cut stone; packs the columns it drives over unless on crane mats | No |
| `skid_steer` | Drone pad, when rented | Vehicle; moves and fills soil like the hand tools, just bigger; packs the columns it drives over unless on crane mats | No |
| `mottled_sculpin`, `blacknose_dace`, `rainbow_trout` | See the Mobs roster | Fish; behavior in Mobs and Fish mechanics | No |
| `wood_frog` | See the Mobs roster | Amphibian; behavior in Mobs | No |
| `snapping_turtle`, `garter_snake`, `ring_necked_snake`, `eastern_rat_snake`, `timber_rattlesnake`, `five_lined_skink`, `fence_lizard` | See the Mobs roster | Reptiles; behavior in Mobs | No |
| `american_woodcock`, `chimney_swift`, `eastern_phoebe`, `carolina_wren`, `red_tailed_hawk`, `common_raven`, `canada_goose` | See the Mobs roster | Birds; behavior in Mobs | No |
| `bobcat`, `mink`, `woodchuck`, `eastern_chipmunk`, `gray_squirrel`, `southern_flying_squirrel`, `raccoon`, `virginia_opossum`, `feral_hog` | See the Mobs roster | Mammals; behavior in Mobs | No |
| `eastern_red_bat`, `tricolored_bat` | See the Mobs roster | Bats; behavior in Mobs | No |

Fireflies are a particle swarm driven by the population, with no entity.

Species count: see the Entities table.

Models are low-poly in the vanilla style, animated with GeckoLib. Each species has its own call sound for the ambient soundscape.

The full roster for every biome, with behavior and handling rules, is in Mobs.

## Screens, saved data, world rules and advancements

**Screens** (all flat, clean panels in one style: off-white cards, one accent color, no vanilla inventory grid unless one is needed):

| Screen | Opened from | Tabs |
| --- | --- | --- |
| RV computer | `rv_computer` | Shop, Grants and cash, Terrain-change map, Pond alerts, Reports, Goals |
| Lab bench | `lab_bench` | Load sample, Run test (nitrate, pH, turbidity, oxygen), File report |
| Field journal | `field_journal` item or J key | Species pages, sightings, habitat notes |
| HUD | Always on | Time, season, energy, weather, held-tool hint |

**Saved data.** State lives in level SavedData on the `fieldstation:appalachia` level, one file per owning package, each with a versioned codec (`data_version` int):

- `fieldstation:terrain`: per column (200×200, row-major from the plot origin) `short stone_top_y`, `byte[6]` whole-block counts for topsoil, subsoil, red\_clay, gravel, silt, mud, `byte top_layers` 1–8, `byte flags` (packed, dug, loose); plus the height map from 7 days ago (for the terrain-change map) and the soil-lost total.
- `fieldstation:water`: `short water_levels`, `float buffer_m`, `float flow_levels_per_h`, `float load_gravel`, `load_silt`, `load_clay`, `float wear`, `int last_step_tick`.
- `fieldstation:soil`: `float topsoil_store_m`, `subsoil_store_m`, `byte moisture` 0–7, `short wet_age_h`, `short disturbed_h`, `float nutrients`.
- `fieldstation:plants`: `byte cover`, `byte stage`, `byte health`, `short seed_bank`.
- `fieldstation:habitat`: `byte microhabitat`, `int record_id` per column, plus the habitat records and their populations.
- `fieldstation:survey`: survey estimates with their age, and Park Service allotments.
- `fieldstation:station`: cash, credits, reputation, journal, goals, shop queue, studbook, permits.
- `fieldstation:clock` and `fieldstation:story`.

Arrays are stored as primitive arrays (`ByteArrayTag`, `IntArrayTag`), about 2.5 MB in total. They are marked dirty at most once per game hour. Player energy and progress live in the player attachment `fieldstation:player_state`. Chunk attachments are not used for simulation state.

**World rules** (set by the `fieldstation:field_station` world preset):

- The 200 × 200 Appalachian plot is generated from the prototype's land function, with layered soil columns.
- The world preset `fieldstation:field_station` makes the overworld a single-biome void (`minecraft:the_void`, no structures). The plot dimension is `data/fieldstation/dimension/appalachia.json` with type `fieldstation:appalachia`. Its generator is the vanilla mountain noise generator, with the cove generator overriding the plot chunks.
- On first login (player attachment `fieldstation:player_state.arrived` = false), the player is teleported to the RV door. Respawn is set to the `rv_bed` in `fieldstation:appalachia`.
- The plot's origin is block (0, 0); it spans x and z 0–199, which is chunks 0–12 (13 × 13). Columns outside 0–199 are not simulated.
- Beyond the plot, the dimension generates vanilla mountain terrain.
- No monster spawns anywhere, and no vanilla passive animals on the plot.
- `minecraft:spawn_monsters`, `minecraft:spawn_phantoms`, `minecraft:spawn_patrols`, `minecraft:spawn_wandering_traders`, `minecraft:spawn_wardens` and `minecraft:raids` are set to false, so monsters, phantoms, patrols, traders, wardens and raids never happen (verified in 26.2 `GameRules`).
- Vanilla time advance is off in `fieldstation:appalachia`; the clock package sets the dimension's clock.

**Advancements** (tab: Field Station):

| Id | Title | Earned when |
| --- | --- | --- |
| `root` | Parked | Enter the world |
| `first_pond` | It holds! | A pond you dug lasts 3 days |
| `journal_4` | Field notes | 4 species logged |
| `first_tadpoles` | Next generation | Tadpoles appear in a pond you made |
| `spring_fed` | Living water | Crayfish in a spring-fed pond |
| `big_water` | Big water | A pond of 40 m², 1 block deep |
| `no_soil_lost` | Holding ground | A whole season with under 1 block of soil lost |
| `corridor` | Wet road | Frogs move between two of your ponds |
| `oxbow` | Cut off | A meander forms an oxbow pond |

**Other registries.**

| Kind | Ids |
| --- | --- |
| Player attachment | `fieldstation:player_state` |
| Entity attachment | `fieldstation:population_link` |
| Data components | `fieldstation:sample`, `fieldstation:animal_tag`, `fieldstation:soil_bag_contents` |
| Sounds (by group) | `fieldstation:green_frog.call`, `fieldstation:drone.arrive`, `fieldstation:shovel.dig`, `fieldstation:water.trickle` |
| Payloads (Minecraft development → Network) | `fieldstation:clock_sync`, `fieldstation:hud`, `fieldstation:column_tint`, `fieldstation:screen_data`, `fieldstation:briefing`, `fieldstation:open_screen`, `fieldstation:buy`, `fieldstation:file_report`, `fieldstation:log_sighting` |
| Entity type tags | `#fieldstation:allowed_entities`, `#fieldstation:amphibians` |
| Game rules | `fieldstation:erosion_multiplier` and the rest in Definitions → Mod settings and game rules |
| Level SavedData | The per-package files under Saved data above |

## Added by later tabs

**Items** (full details in the named tab):

| Tab | Items |
| --- | --- |
| Fun, feel and finish | `field_tablet` |
| The cove | `kick_net`, `sorting_tray`, `cover_board`, `tag_reader` |
| Landscaping and terraforming | `spade`, `mattock`, `hand_auger`, `string_line`, `sod_cutter`, `watering_can`, `crane_mat`, `bentonite`, `erosion_blanket`, `straw_bale`, `geotextile`, `drain_pipe`, `culvert_pipe_1`, `culvert_pipe_2`, `arch_culvert`, `log_bridge_kit`, `boulder`, `water_bar_log` |
| Capture and animal trade | `seine_net`, `minnow_trap`, `hoop_trap`, `drift_fence`, `pitfall_bucket`, `box_trap`, `mist_net`, `snorkel_mask`, `pit_tag`, `elastomer_kit`, `live_carrier`, `breeding_tank`, `breeding_pen` |
| Economy, crafting and drones | All recipe outputs, `field_beacon`, `hardware_kit`, reward crates |

**Screens.** Field tablet, morning briefing, Chronicle, year card, plan mode with storm preview, kick-net sorting, capture work-up, studbook, Research exchange, Contracts app (Contracts and Orders tabs).

**Saved data.**

- `fieldstation:story`: storyteller budget and cooldowns, briefing history, Chronicle entries and year cards
- `fieldstation:habitat`: named animals, as records with permanent ids
- `fieldstation:station`: studbook
- `fieldstation:survey`: survey estimates with their age, and Park Service allotments
- Player attachment `fieldstation:player_state`: plans

**More advancements.**

| Id | Title | Earned when |
| --- | --- | --- |
| `year_one` | First year card | The first year ends |
| `old_friends` | Old friends | 5 named animals alive at once |
| `stream_health` | Clean water | A stream health score of 8 |
| `reference_site` | Reference site | Every native species on the cove list rated within or above its allotment for one full season (finale rule in Progression and economy) |
