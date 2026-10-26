# Systems spec

The rules and numbers each system runs on. Numbers come from the browser prototype, where they were tested; the mod ports them to Java.

## Water

Water is finite and measured in eighths of a block. Slow inputs (light rain, soaking) build up in a hidden per-column buffer until they add up to a visible level. All rates are per in-game hour (1,000 ticks, 50 seconds real time).

| Rule | Value | Notes |
| --- | --- | --- |
| One water level | 1/8 block = 0.125 m = 125 L | 8 levels make a full block |
| Sub-level buffer | Float per column, meters | Below 0.125 m it is a damp surface, not a visible level |
| Flow | Water moves to lower neighbors until surfaces level out | Loaded chunks: Flowing Fluids moves it. Unloaded chunks: our column model. See Water ownership below |
| Drizzle | 2–5 mm per hour for 3–9 hours | Only on columns open to the sky |
| Rain | 6–12 mm per hour for 4–12 hours | Fronts |
| Thunderstorm | 12–28 mm per hour for 1.5–3.5 hours | Half of summer rain events are storms |
| Spring | 1.1 m³ per hour (about 0.3 L/s), about 9 levels | Runs all year; 60% flow in late summer |
| Seep Line seeps | 0.1 m³ per hour in spring | Stop on summer day 1; they feed the vernal pool (6 levels deep at the center, under canopy, in clay) |
| Soil storage | Topsoil holds 0.30 m of water per m depth, subsoil 0.25 | A saturated column stops soaking and sheds runoff |
| Soaking rate (empty soil) | Topsoil 20 mm, subsoil 12, clay 0.4, gravel 90, stone 0 mm per hour | Packed ground soaks 8% of that |
| Drainage under soil | 0.6 mm per hour over clay, 15 over gravel | Clay perches water near the surface |
| Evaporation | 1.8 mm per hour, ×1.6 above 25 °C, 0.4 mm in winter | From open water; ×0.3 under full canopy |
| Plant uptake | 1.6 mm per hour × (0.3 + plant cover) | From soil storage |
| Outflow | Water that reaches the plot's downstream edge leaves the world | Keeps the valley from flooding forever |
| Infinite sources | Off on your land | Rivers and oceans in other biomes stay infinite |

Starting soil sits 82% full on slopes and up to 100% in the valley, so the first storm already runs off.

**Water ownership.** In a loaded chunk on the plot, Flowing Fluids moves the water. Every 20 ticks the water bridge reads each wet column's `minecraft:water` amount (1–8) into the arrays. When a chunk unloads, the arrays take over and move water with our column model. When the chunk loads again, the world writer writes the array levels back as `minecraft:water` with amounts, and Flowing Fluids resumes. Our column model, used only for unloaded chunks: each step, each column passes min(Δsurface/2, 2 levels) to its lowest of 4 neighbors. Columns are processed in a fixed shuffled order. dt is the sim ticks since that column's last step. Soaking, springs, rain, evaporation and outlets always run on the arrays, never inside Flowing Fluids. The arrays add and remove water as level deltas, and the bridge places or removes water blocks to match.

## Soil and ground blocks

The prototype stored layers as thicknesses. In Minecraft each layer becomes a stack of whole blocks. A column on the plot, from the bottom up: stone, gravel, red clay, subsoil, topsoil, then grass. What the player digs out goes into a soil bag, and what they place goes back as the same layer.

| Layer | Block id | Starting depth | Soaks (mm/h) | Holds water | What it does |
| --- | --- | --- | --- | --- | --- |
| Topsoil | `fieldstation:topsoil` (grass on top when planted) | 1 block | 20 | 0.30 m per m | Grows plants; erodes into muddy water |
| Subsoil | `fieldstation:subsoil` | 1 block | 12 | 0.25 m per m | Store of water for plants |
| Red clay | `fieldstation:red_clay` | 1 block on slopes, 1–2 in the coves | 0.4 | Seals | Pond liner; packing it seals it 12× better |
| Gravel (colluvium) | `fieldstation:gravel_layer` | 1–2 blocks | 90 | None | Drains; good for crayfish beds and channels |
| Stone | `minecraft:stone` | Everything below | 0 | None | Too hard to dig without a rented excavator |

**Block states.** `layers=1–8`, `packed=true|false` (tamped with the tamper; soaks at 8%), `seeded=none|grass|reed`. Moisture is drawn as a client-side `BlockColor` tint from `fieldstation:column_tint`, never as a block state.

**Digging.** A shovel takes one block per swing into a soil bag that holds 16 blocks of one layer. A wheelbarrow holds 64 of mixed layers. Dumped material lands loose (`packed=false`), so a dumped clay dam leaks until it is tamped.

**Mud.** Topsoil and subsoil that sit at moisture 7 for more than 24 hours turn to `fieldstation:mud`, which slows the player and makes turbid water when it is disturbed. Mud dries back into soil after 48 hours at moisture 3 or lower.

**Trail tread.** `fieldstation:trail_tread` is a packed soil variant with layers (`layers=1–8`) and erodibility 0.05. Steps on it never add to the trail-wear counter (Landscaping and terraforming → Trail craft).

**Erosion.** Moving water wears soil away and drops it somewhere else. The full rules are in *Water shaping the land*, below.

## Ponds and habitats

The mod does not have the player place a "pond" block. Ponds are found automatically: every 100 ticks, a flood fill groups connected water columns at least 1 level deep (about 0.1 m). Any group of 3 or more columns becomes a habitat record stored in the world save.

**Streams.** During the fill, columns with flow ≥ 2 levels/h are cut into stream reaches of up to 32 columns along the flow path, each its own record (kind `stream_reach`). Still water (flow < 2) groups as `pond`. A pond and a reach that touch are separate records linked as neighbors.

| Field | How it is measured | Used for |
| --- | --- | --- |
| Area | Number of columns in the group (m²) | Carrying capacity, entry thresholds |
| Max depth | Deepest column, in levels ÷ 8 | Bullfrogs, winter survival |
| Age | Average hours each column has held water | Breeding, tadpole survival |
| Spring fed | The spring's outflow reaches the group | Crayfish, cold-water species |
| Reed cover | Share of edge columns with reeds | Frog cover and egg sites |
| Turbidity | Mud stirred in (0–1), settles at 4% per hour | Lowers water quality |
| Dug share | Share of columns the player has dug or built | Goals only count ponds you made |
| Quality q | 1 − 0.6 × turbidity, × 0.7 if not refreshed for 7 days in summer | Scales carrying capacity |

**Lifecycle.** A new pond is *wet ground* for its first 24 hours, *young* until 72 hours, then *established*. If it shrinks below 3 columns, it keeps its record for 12 hours (a dry spell) before it is dropped and everything living in it dies.

**Splits and merges.** When two ponds join, the new one takes the larger pond's id and adds their populations together. When a pond splits, each part gets a share of the population matching its share of the area.

**Other habitats.** Meadow (open grass columns), woods edge (grass next to tree blocks) and reed bed (reeds in water 0–2 levels deep) are counted by area, but they are not tracked as separate records.

## Water shaping the land

Water keeps reshaping the plot after the player stops digging. Fast water picks up soil, carries it, and drops it where it slows down. Over a season this cuts channels, fills ponds and moves banks. Every layer that is worn away ends up somewhere downstream or leaves the plot over the outlet. Nothing disappears.

**Soil comes in eighths.** Every soil block (topsoil, subsoil, red clay, gravel, silt, mud) has a `layers=1–8` property, the way snow layers work. Water levels are also counted in eighths, so the two line up. The shovel still takes whole blocks. Erosion and deposition add or remove one layer at a time, so the ground changes smoothly instead of in jagged steps.

**Under water.** The world writer renders the top soil block under water as a whole block (layers=8 if the column has 4 or more layers, otherwise it removes the block). The exact layer count stays in the arrays. Partial layers render only on dry columns.

**Stream power.** Every sediment run (20 sim ticks), each column that water flowed through gets a power value:

P = q × (0.1 + s)

- q is the water that passed through, in levels per game hour.
- s is the drop in water surface to the lowest neighbor, in blocks.

| Situation | q | s | P |
| --- | --- | --- | --- |
| Still pond | 0 | 0 | 0 |
| Storm runoff on a slope | 1–2 | 0.1–0.2 | 0.2–0.6 |
| Spring outflow in a channel | 9 | 0.125 | 2 |
| Water spilling over a dumped dam | 30–50 | 1 | 35–55 |

**Carry and wear.** Water can carry C = 0.05 × P layers per hour. If it is carrying less than that, the ground under it wears down at (C − load) × erodibility × cover per hour. Wear builds up as a fraction on the column, and one layer is removed when it reaches 1. If the water is carrying more than it can hold, the extra settles out.

| Ground | Erodibility | Cover | Multiplier |
| --- | --- | --- | --- |
| Mud | 1.5 | Bare | 1.0 |
| Silt | 1.2 | Meadow grass | 0.1 |
| Topsoil | 1.0 | Shrub roots | 0.2 |
| Subsoil | 0.7 | Reeds | 0.05 |
| Gravel | 0.3 (moves only when P > 2) | Under ice | 0 |
| Red clay | 0.12 (packed 0.05) | Riprap or stone | 0 |
| Loose (just dumped) | ×2 of its type |  |  |

**What the water carries.** Each wet column holds three loads: gravel, silt (from topsoil, subsoil and mud) and clay. They settle at different speeds, which sorts them across the landscape:

| Load | Settles when | Rate | Lands as |
| --- | --- | --- | --- |
| Gravel | P < 2 | All of it at once | Gravel layers, a bar where a channel slows |
| Silt | P < 0.6 | 30% per hour | `fieldstation:silt` layers: fertile, reeds grow twice as fast |
| Clay | P < 0.1 (still water only) | 5% per hour | A thin clay film on the bottom |

The suspended silt and clay are what the player sees as muddy water. Turbidity = (silt + clay) ÷ water volume, and it feeds into pond quality.

**Ponds seal themselves.** Each clay film on a pond floor halves how fast that column soaks. A leaky pond dug into subsoil will slowly seal over 14–21 days of muddy inflow. That is how real farm ponds behave, and it gives a player who can't reach clay a slow way to make a pond hold.

**Bookkeeping.** Load that crosses the outlet edge is added to a *soil lost* count on the RV computer. Only columns with moving water are processed: at most 2,048 columns per sediment run (every 20 ticks), taken in turn, with wear and load kept per chunk and only for active columns. Layer changes are applied in batches every 100 ticks, so blocks don't flicker.

**World writer (package `terrain`).** Only the writer changes Minecraft blocks on the plot.

1. Every system marks changed columns in a dirty bitset through `TerrainService`.
2. Each tick the writer rewrites at most 256 dirty columns in loaded chunks. It writes the top 3 blocks of each column plus the water blocks, using `Block.UPDATE_CLIENTS | UPDATE_KNOWN_SHAPE` with no neighbor updates, and it lets lighting update.
3. On `ChunkEvent.Load`, every plot column in that chunk is compared with the arrays and rewritten where they differ.
4. Reverse path: `BlockEvent.EntityPlaceEvent`, `BreakEvent` and `FluidPlaceBlockEvent` on the plot call `TerrainService.applyWorldEdit(pos, newState)`, so the arrays always match the world.
5. Wildlife entities return false from `shouldBeSaved()`, so they are never stored in chunks.

## Landforms over time

These all come from the sediment rules above. None of them is scripted. Each one should be visible within a season and still leave the player time to react.

**Rills and gullies.** On bare slopes, storm runoff gathers in low lines. Once a column has lost 2 layers, water from its neighbors drains into it, so it cuts faster. That loop turns a rill into a gully. When a gully cuts through topsoil into the lighter subsoil, the scar is easy to spot. The step at the top of a gully (the headcut) wears 3× faster because the drop is large, so gullies grow uphill about 1 block per storm until they hit grass or the top of the ridge.

**Bank collapse.** Next to water, how tall a step can stand depends on how wet the soil is. Dry soil holds a 3-block step, saturated clay holds 2, and saturated topsoil or subsoil holds only 1. When water undercuts a bank past that limit, the top block slumps into the water and becomes load. Banks therefore fail after rain, not during dry weather.

**Meanders.** Water on the outside of a bend runs faster. The outer column gets P × 1.5 and the inner column P × 0.5, based on how sharply the flow turns. The outer bank wears away while gravel and silt build a bar on the inside, so the channel slowly swings sideways. If two bends touch, the water takes the shortcut, and the old loop becomes an oxbow pond. That pond keeps its age but is no longer spring fed.

**Deltas.** Where a channel enters a pond, gravel drops first, then silt spreads farther out. The delta grows into the pond, and reeds take over the new silt within 7 days.

**Pond infill and succession.** Every pond slowly fills unless it is dredged.

| Stage | When | Who it suits |
| --- | --- | --- |
| Open pond | Mean depth ≥ 3 levels | Dragonflies, bullfrogs, heron |
| Marsh | Mean depth below 3 levels; reeds cover 50% | Green frogs, tadpoles; bullfrogs leave |
| Wet meadow | No standing water in summer | Bluebirds, deer; frogs leave |

Dredging with a shovel removes silt. The silt counts as topsoil in the soil bag, so maintenance also supplies material for building.

**Dam breaches.** When water runs over the crest of a dam, it wears the downstream face at the high P of the spillover. A loose dumped dam breaches in 1–2 hours and a packed clay dam in about a day. A dam with a stone or gravel spillway that sits lower than the crest doesn't breach. A breach releases the pond as a flood that cuts the channel below and spreads silt over the flats. The RV computer warns when any pond is within 1 level of its lowest crest.

**Winter.** Freezing turns the top water levels of still columns into `fieldstation:ice_layer` with the same count; thaw reverses it, and water is conserved. Nothing under ice erodes. On frozen nights, packed blocks at moisture 5 or more have a 20% chance per 7 days to come loose (frost heave). Bare soil on slopes creeps 1 layer downhill each winter. At the thaw, snow layers melt into water over 3 days. That pulse is the year's biggest erosion event.

**How fast things change over one year (28 days):**

| Change | Bare ground | Grass or reeds |
| --- | --- | --- |
| Spring channel deepens | 1 block in 3–4 days | Banks hold; the bed still cuts slowly |
| Gully grows uphill | About 1 block per storm | Stops at the grass edge |
| Meander bank moves | 1 block per season | 1 block per year |
| Pond depth lost to silt | 2 layers per season | 1 layer per year |
| Soil lost over the outlet | 30–60 blocks | Under 5 blocks |

**What the player can do about it:**

| Fix | Block or item | Effect |
| --- | --- | --- |
| Plant cover | Grass seed, reed plugs | Cuts erosion 90–95% once grown |
| Silt fence | `fieldstation:silt_fence` | Lets water through and catches silt behind it; full after 8 layers and must be cleaned |
| Coir log | `fieldstation:coir_log` | Holds a bank for one season while reeds take root, then rots away |
| Riprap | Cobblestone on a bank or spillway | Erodibility 0 |
| Check dam | A row of stones across a gully | Turns a gully into small steps of pools that trap silt |
| Spillway | A low gravel or stone notch in a dam | Stops the dam from breaching |
| Dredging | Shovel | Resets pond succession and yields topsoil |

**Terrain-change map.** A tab on the RV computer shows every column that lost ground in the last 7 days (red) or gained it (blue), plus soil lost and the muddiest water. Filing the Park Service season report on the last evening of each season pays $15 and +3 reputation. This gives the player a reason to look at erosion instead of only noticing it after a dam fails.

## How the landscape drives the world

The land is the input to everything else. Vanilla decides spawning by biome and light level. Field Station decides it by what the ground is like right where the player stands. Every time the player digs, or water cuts or fills the ground, spawning, plants, microclimate and sound change with it.

**Microhabitat map.** The habitat scan also tags every column on the plot. Only chunks where blocks changed are re-tagged.

| Input | Read from |
| --- | --- |
| Water depth and flow | Water levels and stream power |
| Soil moisture | Soil moisture and storage in the soil arrays |
| Slope and facing | Height difference to neighbors; south-facing slopes count as warm |
| Hollow or rise | Height compared with the average within 8 blocks |
| Cover | Grass, reeds, shrubs, tree canopy, bare, silt |
| Distance to water | Blocks to the nearest pond or channel |
| Disturbance | Hours since the column was dug, dumped or flooded |

Those inputs combine into one tag per column: *open water, shallows, reed bed, wet meadow, dry meadow, woods edge, woods, bare ground, gravel bar, frost hollow, warm slope*. Spawning, plant growth and ambient effects all read this tag.

**Spawning follows the tags.** Vanilla passive animals (cows, sheep, pigs, chickens) don't spawn on the plot either, so every animal present is there because of the habitat. Each species has a list of tags it can appear on and the conditions above it needs:

| Terrain change | What spawns or leaves |
| --- | --- |
| Dig a pond | Dragonflies over open water; frogs once there are reeds; deer come to drink at dusk |
| Shallows and reed beds form | Tadpoles, red-winged blackbirds, more frogs |
| Silt delta or gravel bar | Killdeer nest on bare gravel; reeds take the silt within 7 days |
| Wet meadow in summer | Fireflies at dusk; bluebirds hunt over it |
| Fresh digging | Nothing spawns on the column for 24 hours; then pioneer weeds |
| Muddy water (turbidity above 0.5) | Crayfish and dragonfly larvae stop breeding there |
| Clear the woods edge | Deer lose cover and visit less; meadow birds gain ground |
| Pond fills to marsh, then meadow | Species shift as in *Pond infill and succession* |
| Build a vernal pool | Spotted salamanders and wood frogs on the next Big Night; it dries each fall, which keeps bullfrogs out |
| Sow a native meadow | American toads, monarchs on milkweed, Diana fritillaries where violets grow nearby; box turtles along the woods edge; woodcock where it borders young trees |
| Leave logs or build a brush pile | Red-backed salamanders (logs raise their limit); box turtles at the woods edge |
| Build a rock pile | Five-lined skinks and fence lizards once 3 or more piles sit on warm slopes |
| Place boulders or large wood in the Run | More good lies and clean riffles: brook trout, dace and sculpin; Louisiana waterthrush once stream health reaches 6 |
| Put up a nest box or bat box | Wood ducks (box within 30 blocks of a pond ≥ 100 m²), bluebirds (box on meadow), bats (box within 100 blocks of water) |
| Plant shade over the Run | Cooler water for brook trout; dusky salamanders on shaded seeps and stream edges |

**Corridors.** Frogs and tadpole-bearing adults travel between ponds only along wet ground. Two ponds within 30 blocks that are joined by columns at moisture 4 or more share frogs. A dry gap cuts them off. Deer cross water up to 2 levels deep and walk around anything deeper. Ditches and berms are therefore also wildlife routes and walls.

**Water table and trees.** Draining a hollow lowers the soil moisture around it, and trees that need wet ground (willow, alder) die back over a season. Flooding woods for more than 7 days drowns the trees and leaves standing dead snags, which woodpeckers come to. Oak and pine on dry rises are unaffected.

**Microclimate.** Temperature varies by tag on top of the season's value. A frost hollow is 3 °C colder at night, so frost comes earlier there, snow lies longer and plants start later in spring. A warm slope is 2 °C warmer by day. Within 4 blocks of open water, nights are 1 °C milder. On cool, still mornings, mist forms over ponds and in hollows, and only there.

**Vanilla systems that read the land.**

- Farmland and saplings use the soil moisture value instead of the vanilla "water within 4 blocks" check.
- Snow builds up 2 layers deeper in hollows.
- Only still water freezes; flowing channels stay open all winter.
- Grass spreads only onto columns at moisture 2 or more.

**Sound.** Ambient sound comes from the tags within 24 blocks of the player: frog chorus from reed beds at night, wind in reeds, running water whose volume follows the flow, and birdsong from meadow and woods edge. A finished landscape sounds different from a bare one, which tells the player it is working without opening a screen.

## Wildlife rules

Populations are numbers stored on each habitat record. Only a few animals are spawned as actual entities near the player, up to 4 per species per pond within 48 blocks. When the player walks away, the entities despawn but the numbers keep running. Animals arrive from outside the plot; the player can't buy them.

**Entity spawner** (wildlife package, every 40 ticks). For each habitat record within 48 blocks of the player, wanted(species) = min(4, ceil(N × 0.2)). Fish count at most 6 per pool or pond and 16 in total, and a school counts as 1. If the sum of wanted is more than `wildlife_entity_cap` (64, counting the 8 insects, not counting tame or owned animals), cut each record's wanted in proportion, keeping at least 1 for every species with N ≥ 1. Owned animals sit outside the cap but inside the 3 ms wildlife budget. The spawner places entities on columns with the species' microhabitat tags, out of the player's line of sight and at least 16 blocks away. It despawns entities more than 64 blocks away or whose record is dropped. Each entity carries the attachment `fieldstation:population_link {habitat_id, species, named_id?}`. The numbers are the truth: an entity's despawn or death never changes N. Capture calls `PopulationService.remove(habitat_id, species, 1)` and release calls `add`. Named animals are records in `fieldstation:habitat`; the spawner fills one slot with a named animal when its home record is in range, so the same UUID reappears.

| Species | Arrives when | Chance | Grows / limit | Lost when |
| --- | --- | --- | --- | --- |
| Green darner (dragonfly) | Pond age ≥ 24 h, area ≥ 4 | 5% per hour, day only | Limit area × 0.3 | Pond dries |
| Green frog | Age ≥ 72 h, area ≥ 6, reed ≥ 6% | 2% per hour, spring–summer | Limit K = min(area × 0.2 × (0.4 + reed) × q, shoreline blocks ÷ 3) | Pond dries, or a heron takes 1 per day |
| Tadpoles | Frogs present in spring | One pulse per spring breeding window: 40 young per female | Turn into froglets at 96 h; 25% survive and join the adults after 1 year (pulse model) | Any dry hour kills all of them |
| Bullfrog | Area ≥ 40, depth ≥ 1 block, age ≥ 168 h | 1% per hour, summer | Limit area × 0.01 | Eats 1 green frog every 4 days |
| Crayfish | Spring fed, age ≥ 48 h, gravel on the bottom | 3% per hour | Limit area × 0.4 × q | Turbidity above 0.5 for 24 h |
| Great blue heron | Pond prey (frogs + crayfish) ≥ 15 | Visits at dawn | Visitor; doesn't breed | Leaves when prey drops below 8 |
| White-tailed deer | Woods edge area ≥ 200 | Comes to drink at dusk | Herd of 2–4 | Fences block it |
| Eastern bluebird | Meadow area > 700 m² | 4% per hour, day | Pair; nest box doubles the chance | Meadow drops below 500 |
| Spotted salamander | Vernal pool ≥ 60 m², fishless, holds ≥ 2 levels from spring day 1 to summer day 4, woods ≥ 50% within 150 blocks | 4–8 adults on the next Big Night | Limit pool area × 0.6 | Habitat falls below the arrival rule |
| Wood frog | Same vernal pool | 2–6 adults on the first warm rain | Limit pool area × 0.3 | Habitat falls below the arrival rule |
| Spring peeper | Still water ≥ 20 m² with shrubs on ≥ 30% of edge columns | 2% per hour on spring nights | Limit shrubby edge columns × 1.5 | Habitat falls below the arrival rule |
| Red-spotted newt | Pond ≥ 40 m², submerged plants ≥ 20%, age ≥ 168 h | 1% per hour in spring | Limit area × 0.1 | Habitat falls below the arrival rule |
| American toad | Shallow pool ≥ 4 m² in spring | 2% per hour | Limit 10 per pool | Habitat falls below the arrival rule |
| Red-backed salamander | Woods columns with litter | Grows on the plot | Limit 0.03 × woods columns × (0.5 + litter) × (1 + 2 × log share); about 940 for the cove's roughly 19,000 woods columns | Habitat falls below the arrival rule |
| Dusky salamander | Seep or stream-edge columns at moisture ≥ 6 in shade | Grows on the plot | Limit 0.5 per such column | Habitat falls below the arrival rule |
| Five-lined skink, fence lizard | ≥ 3 rock or log piles on warm\_slope columns | 1% per hour, summer days | Limit 2 per pile | Habitat falls below the arrival rule |
| Box turtle | Woods–meadow edge ≥ 100 blocks long with berries or mushrooms | 1 adult per year | Limit edge length ÷ 10 | Habitat falls below the arrival rule |
| Painted turtle | Pond ≥ 100 m² with ≥ 2 basking logs and a sandy bank | 0.5% per hour | Limit basking logs × 3 | Habitat falls below the arrival rule |
| American woodcock | Open field ≥ 1,500 m² beside ≥ 400 m² of young trees on moisture ≥ 4 | 1 displaying male next spring | Limit 1 | Habitat falls below the arrival rule |
| Ruffed grouse | ≥ 2,000 m² of young-stage trees on the east slope | After 1 season | Limit 1–2 | Habitat falls below the arrival rule |
| Louisiana waterthrush | Stream health ≥ 6 over ≥ 100 blocks of the Run | Next spring | 1 pair | Habitat falls below the arrival rule |
| Wood duck | Nest box within 30 blocks of a pond ≥ 100 m² | 5% per spring day | 1 pair per box, max 2 | Habitat falls below the arrival rule |
| Red-winged blackbird | Reed bed ≥ 50 m² | 3% per hour in spring | 1 male per 50 m² | Habitat falls below the arrival rule |
| Chimney swift | Old chimney standing | Spring day 3 | 1 nesting pair; fall roost of 50–200 | Habitat falls below the arrival rule |
| Bats (big brown, eastern red, tri-colored) | Bat box or sunny snag within 100 blocks of water | 2% per night in summer | +5 per roost; tri-colored only when mayfly hatches occur | Habitat falls below the arrival rule |
| Monarch | ≥ 20 milkweed plants | Summer day 1 | Milkweed ÷ 2 adults | Habitat falls below the arrival rule |
| Diana fritillary | ≥ 50 violet columns in woods plus field nectar | Summer | 3–6 | Habitat falls below the arrival rule |
| Brook trout, dace, sculpin | Connected reach only (passage rules in Fish mechanics) | — | Trout = good lies × 2.5; dace = riffle columns × 0.5; sculpin = clean-cobble riffle columns × 1 | Habitat falls below the arrival rule |
| Beaver | ≥ 40 hazel alder or willow columns within 30 blocks of a pond ≥ 150 m², or of a reach with flow under 6 levels/h | 1 pair, rolled once each spring | Limit 1 colony | Habitat falls below the arrival rule |
| Muskrat | Pond ≥ 100 m² with cattail ≥ 20% | 1% per hour in spring | Limit cattail columns ÷ 20 | Habitat falls below the arrival rule |
| Killdeer | Bare gravel or mud ≥ 50 m² beside water | 3% per hour in spring | Limit 1 pair | Habitat falls below the arrival rule |
| Tree swallow | Nest box within 50 blocks of open water ≥ 100 m² | 5% per spring day | 1 pair per box, max 4 | Habitat falls below the arrival rule |
| Canada goose | Pond ≥ 200 m² with a mowed grass edge | Visitor, 2% per day | Limit 2 (maximum) | Habitat falls below the arrival rule |

**Population model.** Each species' data file sets `breeding_window`, `young_per_female`, `young_survival_fraction`, `years_to_adult` and `adult_survival_per_year`. Births are one pulse per breeding window: young = females × young\_per\_female × young\_survival\_fraction × (condition ÷ 100) × (1 − N/K). Young join the adult count after years\_to\_adult. Adult deaths are spread evenly across the year. Insects, plankton and the tadpole stage keep the continuous logistic at r = 0.04/h. Design targets: fast species (insects, dace, frogs) reach K within 1 game year of arriving; medium species (salamanders, trout, songbirds) within 2–3 years; slow species (turtles, hellbender) grow only by arrival or permitted release. Starting values per species are in the Mobs life-cycle table. In winter, frogs in ponds shallower than 1 block die off at 30% per 7 days.

**Lab gates.** Some species won't stay until the lab confirms the water: crayfish need a water test showing nitrate below 10 mg/L. The heron and bluebird only count for grants once the player has logged them with binoculars.

**No hostile mobs.** Field Station is a calm game with no combat. Zombies, skeletons, creepers, spiders, slimes, drowned, witches, phantoms and pillagers never spawn.

- A biome modifier removes every monster spawn entry from every biome.
- A spawn check rejects any remaining monster spawn as a backstop.
- The world preset sets `minecraft:spawn_monsters`, `minecraft:spawn_phantoms`, `minecraft:spawn_patrols`, `minecraft:spawn_wandering_traders`, `minecraft:spawn_wardens` and `minecraft:raids` to false (verified in 26.2 `GameRules`).
- Difficulty stays Normal so the mod's own energy rules apply, and no vanilla hunger damage stacks on top of them.

The only danger in the game is losing the habitats you built.

## Surveys and Park Service allotments

The player never sees the simulation's true populations, except in Sandbox mode. The journal shows two numbers for each species in each habitat.

**1. Survey estimate.** This is what the player has measured.

| Method | Gives | Precision |
| --- | --- | --- |
| Sightings with binoculars | Present or not; a rough minimum count | Low |
| Call survey (audio recorder) | Calling males | Medium, for frogs and birds |
| Camera trap | Counts of shy mammals and birds | Medium |
| eDNA | Present or not, even for hidden species | Presence only |
| Quadrat counts | Plant cover | High, for the area sampled |
| Mark and recapture | A population estimate with a range | High, with effort |

An estimate is shown with its range and its age. After 14 days it fades in the journal, marked as stale, until the player surveys again.

**Survey visits.** One survey visit to a habitat (kick-net, call survey, camera-trap night, quadrat or eDNA filter) refreshes the estimate for every species that method detects there. Survey time per habitat: 1–3 real minutes. Faster ways: camera traps and loggers refresh nightly, the eDNA autosampler every 7 days, the survey drone the whole plot for deer and herons, and Park Service survey contracts refresh any 3 habitats.

**2. Park Service allotment.** This is the population range the Park Service (the game's fictional parks agency) expects the area to hold when the habitat is healthy. It is set for every expected native species when the plot starts and revised each season from the habitat the player has built: more good habitat means a higher allotment. Invasive species always have an allotment of 0.

**What the two numbers do together.**

| Use | Rule |
| --- | --- |
| Status | Each species shows *below*, *within* or *above* allotment |
| Grants | A species present earns its base grant. If a survey from the last 14 days places it within its allotment, the grant is 50% higher, so surveying pays |
| Take limits | Founders for breeding, and plant harvests such as ramps or reed stems, come only from the surveyed surplus above the low end of the allotment |
| Over allotment | A species well above allotment (for example, deer browsing too hard) creates management tasks: fencing, planting, or an agency relocation request. There is never any killing |
| Contracts | Park Service survey contracts pay the player to refresh estimates that are stale |
| Park Service season report | Each season, the Park Service rates every species against its allotment: +1 reputation per species within or above, −1 per species below (caps +20 and −10) |

### Mystery records

**Version:** v0.4.

**How it plays.** Sometimes the evidence comes before the animal: a track in the creek mud, a feather under a hemlock, a bat call the detector can't match, an eDNA hit in a reach where that animal has never been seen. The journal opens a mystery card with the clue and a short list of suspects. Each new survey in the right place adds a clue and rules a suspect out. The player narrows it down and confirms it in person.

**In the sim.** A mystery is a population with N ≥ 1 in a habitat record that no survey has detected yet, for a species whose data file has `mystery: true`. These species are secretive: an ordinary survey visit detects them with `detect_chance_per_visit` 0.10. Each survey visit in that habitat, by a method in the species' `clue_methods`, has a 0.35 chance to turn up the opening clue instead. If an ordinary detection comes first, the species is logged the normal way and no card opens.

**Rules.**

- The opening clue shows the evidence, where it was found, and 4 suspects: the true species and 3 decoys from the same group.
- Each later survey visit within 16 blocks of the clue site, by a method listed on the card, reveals the next clue with a 0.6 chance. The third miss in a row always reveals it.
- Each clue (season, time of day, microhabitat or size) rules out one decoy. After 3 clues one suspect is left, and the card shows where and when to look.
- The player can confirm at any stage: a binocular sighting, a photo, a sketch, a camera-trap frame or a capture of that species in that habitat. Confirming early pays the same.
- eDNA clues name the species. Their clues narrow the spot and the hour instead of the suspect list.
- At most 3 cards are open at once; the rest queue, oldest first.
- If N for that species in that habitat falls to 0, the card goes cold and pauses. It reopens with its clues kept when the species returns.
- **Reach mysteries.** When eDNA finds a logged species in a habitat where it has never been recorded (a hellbender in the upper Run), a reach card opens for that habitat. At most 1 reach card is open at a time, inside the cap of 3.

**The cove's 11 species mysteries.**

| Species | Opening clue | Methods that add clues | Decoys |
| --- | --- | --- | --- |
| `mink` | Five-toed tracks in Run-bank mud | Camera trap; night sightings | Long-tailed weasel, river otter, raccoon |
| `tricolored_bat` | A high call over the pond the detector can't match | Bat detector; dusk sightings | Little brown bat, northern long-eared bat, eastern red bat |
| `southern_flying_squirrel` | Hickory nuts opened with a smooth, round hole | Camera trap at night; thermal camera | Gray squirrel, eastern chipmunk, white-footed mouse |
| `bobcat` | Round tracks with no claw marks under a ledge | Camera trap | Gray fox, coyote, house cat |
| `ring_necked_snake` | A small shed skin under a cover board | Cover boards | Worm snake, garter snake, red-bellied snake |
| `timber_rattlesnake` | A large shed with keeled scales and one row of scales under the tail | Day sightings from 3+ blocks; camera trap | Eastern rat snake, northern water snake, copperhead |
| `ruffed_grouse` | A low thumping at dawn that speeds up | Audio recorder; sightings | Pileated woodpecker, wild turkey, American woodcock |
| `american_woodcock` | Rows of small probe holes in soft mud at the field edge | Dusk sightings; audio recorder | Wilson's snipe, killdeer, American robin |
| `barred_owl` | A large barred feather and a pellet under a hemlock | Audio recorder at night; camera trap | Great horned owl, eastern screech-owl, red-tailed hawk |
| `red_salamander` | eDNA hit in the spring run | Night sightings; rock flip | Named by eDNA |
| `mottled_sculpin` | eDNA hit below the culvert | Kick net; night rock flip | Named by eDNA |

**Data.** One file per mystery at `data/fieldstation/mystery/<species>.json`: `species`, `group`, `opening_clue` {`kind`: track, feather, call, shed, sign or edna; `text_key`}, `clue_methods`, `clues` \[{`reveals`, `rules_out`}\], `decoys`, `confirm_methods`. The rules live in `com.fieldstation.sim.mystery`. Card state is the level attachment `fieldstation:mystery_cards`.

**Rewards** (amounts in Progression and economy). A solved species mystery pays 2× that species' first-sighting bonus ($50–$160) instead of the single bonus, +10 reputation (the usual +5 for a new species plus 5 for the solve) and 5 SC. A reach mystery pays $40, +3 reputation and 3 SC.

**Mistakes.** Filing a suspect from the card without confirming evidence sends it back from the Park Service reviewer: −3 reputation, and the card locks for 1 day. A photo of a decoy logs that species if it is really there, and the card stays open. Chasing the animal resets its wariness (Mobs tab), which costs time.

**Hooks.** Survey visits, eDNA, the bat detector, cover boards and camera traps; Mobs wariness and daily schedules; the journal; the morning briefing's Discovery suggestion ("A clue near the Run bank"); BioBlitz volunteer finds can confirm a card; first-sighting bonuses and science credits.

**GameTests.**

- `fieldstation:surveys/mystery_opens_on_undetected`: a habitat holds 3 undetected mink; 20 forced camera-trap visits open exactly one card with 4 suspects, and no ordinary detection logs the mink first more than the 0.10 rate allows.
- `fieldstation:surveys/mystery_solve_pays_double`: a photo confirms an open card; it pays 2× the first-sighting bonus and 5 SC once, closes the card, and a second photo pays nothing more.

## Weather, seasons and plants

Each season lasts 7 in-game days (1 day = 24,000 ticks, the vanilla length), so a year takes 28 days. The mod uses vanilla rain and thunder for the visuals and sets its own rates underneath.

Per-biome climate lives in Biomes. These are the cove's values.

| Season | Temp (°C) | Rain events per day | Storm share | Plant growth | Notes |
| --- | --- | --- | --- | --- | --- |
| Spring | 5–18 | 0.9 | 30% | ×1.0 | Frogs breed; ponds fill |
| Summer | 16–28 | 1.0 | 50% | ×1.2 | Evaporation ×1.6 above 25 °C; the spring drops to 60% |
| Fall | 5–18 | 0.6 | 20% | ×0.5 | Reeds turn brown; growth stops at the end |
| Winter | −8 to 6 | 0.5 | 0% | ×0 | Water 1 level deep freezes below 0 °C; frogs overwinter |

Temperature follows a daily curve (coldest at 05:00, warmest at 15:00) on top of the season's range.

**Plants.** The player sows seed from a pouch onto topsoil.

| Plant | Sown on | Grows | Effect |
| --- | --- | --- | --- |
| Meadow grass | Dry topsoil | Full cover in 48 h | Stops erosion; counts as meadow |
| Soft rush (reed) | Soil or water 0–2 levels deep | Full in 72 h; spreads 1 column per 5 days next to water | Frog cover; filters 2% turbidity per hour |
| Native shrub | Topsoil | Grows over 10 days | Woods-edge habitat; deer browse it |

Plants sitting under more water than their own depth range for 48 hours die. Land plants have a range of 0. Bare soil grows back to grass by itself at 1% per hour.

The full weather model (states, fronts, mountain effects, forecasts and extremes) is in Weather and climate. The rain rates and seasons above are its averages.

## Energy, money and grants

**Energy** goes from 0 to 100 and drops 2 per waking hour, plus 0.25 for each shovel swing, 0.1 for each block dumped and 0.5 for each tamp. Below 20 the player digs at half speed. Sleeping in the RV bed restores 12 per hour and skips the night, waking at 06:00. A day of hard work has to end in bed.

**Money.** The player starts with $300. Most income comes from science; plugs, seed, crafted boxes and honey sell through capped orders.

| Source | Amount | When |
| --- | --- | --- |
| Species grant | $6 per logged species | Paid each morning at 06:00 |
| First sighting | The species' one-time bonus ($25–$80) | When first logged |
| Goal reward | $80–$500 | When a field goal is done |
| Lab report | $15 per water test filed | When filed on the RV computer |

**Shop** (ordered on the RV computer, dropped at the RV's drone pad by the delivery drone within seconds):

| Item | Price |
| --- | --- |
| Grass seed ×40 | $20 |
| Reed plugs ×20 | $35 |
| Sample bottles ×3 | $10 |
| Test reagents ×10 | $25 |
| Bluebird box | $30 (tuning value) |
| Wood duck box | $45 (tuning value) |
| Bat box | $35 (tuning value) |
| Wheelbarrow | $80 |
| Hand tamper | $40 |
| Gravel, 16 blocks | $30 |
| Mini excavator, 1 day | $250 (digs 4 blocks per swing and can cut stone) |

The balance works out so that one established pond with frogs and dragonflies ($12/day plus bonuses) pays for seed and reagents. Renting the mini excavator and building a large pond takes about 2 weeks of saving.

## Mod architecture and tick budget

The architecture is the package map in Minecraft development.

All of the simulation runs on the server, so single player and multiplayer behave the same. The client only draws blocks, entities and screens from synced data. The plot is 200 × 200 blocks (about 13 × 13 chunks, roughly 10 acres). A Minecraft tick is 50 ms, and the mod's average share is 13 ms of the 30 ms MSPT budget in Minecraft development, with peaks of 20 ms or less, measured on Ruhan's Mac.

| System | Cadence | Work cap | Avg ms | Peak ms |
| --- | --- | --- | --- | --- |
| Water bridge and column model | Every tick | 4,096 columns | 3.0 | 4.0 |
| Sediment | Every 20 ticks, at tick % 20 = 7 | 2,048 columns with moving water | 1.5 | 3.0 |
| Layer batch | At tick % 100 = 50 | Pending layer changes | 0.3 | 3.0 |
| World writer | Every tick | 256 columns | 1.5 | 3.0 |
| Habitat scan | At tick % 100 in 10–19 | Flood fill of 40,000 columns over 10 ticks | 0.4 | 0.6 |
| Soil moisture and uptake | Every 20 ticks | 2,000 columns in turn | 1.0 | 1.5 |
| Plants | Every tick, chunks in turn (each chunk every 1,000 ticks) | About 120,000 plant entries | 0.5 | 1.0 |
| Ecology | At tick % 1,000 = 500 | Each habitat record | 0.1 | 0.5 |
| Entity spawner | Every 40 ticks | Records within 48 blocks | 0.2 | 0.5 |
| Wildlife entity AI | Every tick | ≤ 64 wildlife + 10 owned | 3.0 | 4.0 |
| Weather and seasons | Once a game hour | A few numbers | 0.05 | 0.2 |
| Economy, journal and story | On events and at 06:00 | Small | 0.1 | 1.0 |
| Sync to players | 4 Hz | Payloads in Minecraft development → Network | 0.5 | 1.0 |
| Total | — | — | ≤ 12.2 | — |

No tick may exceed 20 ms of mod time. Jobs with offsets never share a tick. Every column-based system stores each column's last step tick and applies its hourly rates over dt = now − last. dt is capped at 200 sim ticks per step; when speed-up needs more, the system runs extra steps within the peak budget above.

## The director: briefing, storyteller and Chronicle

These run once per game day at 06:00, on the *Day began* event, and on demand for alerts. They read the shared data and never change the land themselves. They only schedule events that the simulation then plays out.

| Part | What it does | Numbers |
| --- | --- | --- |
| Storyteller | Picks and schedules events from what the land could really do, and spends a tension budget | Budget 0–100. Refill per day: Relaxed 5, Standard 8, Realist 12. Cost: small 10, medium 30, large 60, opportunity 15. Cooldown per event type: small 3 days, medium 7, large 14 |
| Briefing | Scores candidate suggestions and shows the top 3, never two of the same type | score = 0.5 × urgency + 0.3 × value + 0.2 × freshness. Freshness is 0 for a type suggested yesterday and back to 1 after 3 days |
| Chronicle writer | Turns notable events into one-line history entries | One entry at most per day, picked by importance |
| Named-animal tracker | Promotes an individual after 3 sightings and keeps its record | ID, name, species, home, sightings, photos, fate |
| Year card builder | Compiles the year's stats on the last day of winter | One card per year |

All five together cost under 1 ms per game day.
