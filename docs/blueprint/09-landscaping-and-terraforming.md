# Landscaping and terraforming

Shaping the land is the core of Field Station. Every pond, path and planting goes through the same loop:

**read the land → plan → stake → cut → haul → place → compact → finish → protect → plant → watch it weather**

The soil blocks, water and erosion rules are in the Systems spec. Surveying and the step-by-step water features are in Player and tools. This tab covers how earth itself behaves when you move it, what you can build with it, the rules for working land, and the tools for it.

## Earth physics

**Stable steps.** Soil can stand only so steep. Between two neighboring columns, the top surfaces may differ by at most the *stable step* for the material on top. Steps are measured in layers (8 layers = 1 block).

| Material | Loose and dry | Packed and dry | Saturated |
| --- | --- | --- | --- |
| Topsoil, subsoil | 8 (1 block) | 24 (3 blocks) | 8 (1 block) |
| Red clay | 12 | 24 | 16 when packed, 8 when loose |
| Gravel | 6 | 6 (gravel doesn't pack) | 6 |
| Sand | 4 | 4 | 2 |
| Silt, mud | 2 | — | 2 |
| Stacked cobble (riprap, walls) | 12 | — | 12 |
| Bedrock and boulders | Any | Any | Any |

These match the bank-collapse rule in *Landforms over time* (Systems spec), which is the same physics applied beside water.

**Sliding.** When a step is too steep, the extra layers slide one at a time to the lowest neighbor, and the check repeats until everything is stable. The checks run:

- whenever a block changes
- every game hour for saturated columns, which is why slopes fail after rain

Material that slides into water becomes sediment load.

**Settling.** Loose fill settles 1 layer every 3 days per block of fill depth, until it is packed or has settled 2 layers. A loose dam shrinks, sags at the crest and leaks. A packed dam doesn't settle.

**Compaction** (the hand tamper, plate compactor, or heavy machines driving over ground):

- soaking drops to 8% (as in the Systems spec)
- erodibility halves
- the stable step grows
- roots struggle: seedlings on packed topsoil grow at a quarter speed until the rake loosens it

Compaction is right for dams and paths, and wrong for planting beds.

**Groundwater in holes.** Dig below where the soil is saturated, and the hole fills with seepage: 1 level an hour in clay, 4 in gravel. Work in the dry season, or dewater with the pump. This makes late summer the natural season for pond work.

**Perched water.** Topsoil laid over packed clay holds water at the clay line. That is good for wetland plantings, and bad under a path.

**Wet weight.** Moving saturated soil or mud costs 50% more energy per block. Mud counts as 2 slots in a soil bag.

**Spoil.** Dug material has to go somewhere. Spoil piles follow the same stable-step rules. A pile dumped on a slope above the Run sheds load in the next storm. Use spoil for dams and berms, or haul it to a flat spot.

## Earthwork operations

| Operation | Tools | How it works | Watch out for |
| --- | --- | --- | --- |
| Cut | Shovel (whole blocks), spade (one layer at a time), mattock (rocky or rooted ground) | Remove material into a bag or barrow | Cutting below the water table floods the hole |
| Fill | Soil bag, wheelbarrow, skid steer | Place material; it lands loose | Loose fill settles and erodes |
| Compact | Hand tamper, plate compactor | Packs the top block | Kills planting beds |
| Grade | Rake; laser level with a plan | The rake moves a layer from high neighbors to low ones; with a plan it stops at the target level | Leaves bare soil to protect |
| Trench | Spade | A 1-block-wide cut, layer by layer | Trenches on slopes become gullies unless filled or lined |
| Terrace | Shovel, rock bar, logs | Cut a level bench into a slope; hold the riser with a log or stone wall | Each riser must respect its material's stable step |
| Strip and respread topsoil | Shovel, sod cutter | Lift topsoil and sod first, stockpile them, and spread them back after shaping | Stripping first keeps the seed bank and fungi; skipping it leaves the poor subsoil on top |
| Transplant | Spade | Lifts a plant with its root ball as an item; replant within a day | Fails in summer heat unless watered |
| Place boulders | Rock bar | Pry a boulder from an outcrop and roll it into place (1 block a second) | Boulders don't slide; use them to anchor banks and riffles |
| Remove a stump | Mattock | Takes 10 swings | Stumps hold slopes; leave them on banks |

**Heavy equipment** (rented for one day and delivered by truck; the rental key summons the machine at the drone pad):

| Machine | Does | Price | Tier | Side effects |
| --- | --- | --- | --- | --- |
| Mini excavator | Digs 4 blocks a swing, reaches 6 blocks, cuts rock | $250 a day ($200 at Technician) | Volunteer | Packs every column it drives over unless it runs on crane mats; loud: wildlife hides within 32 blocks |
| Skid steer | Scoops and carries 16 blocks; grading blade | $150 a day | Technician | Packs soil; loud |
| Plate compactor | Packs 3 × 3 | $60 a day | Technician | Loud |
| Trash pump | Dewaters a hole at 20 levels a minute | $40 a day | Volunteer | Needs fuel |

Crane mats (crafted from planks) spread a machine's weight, so the ground under them stays loose.

## Water-shaping structures

| Structure | What it does | How to build it | Physics in the game |
| --- | --- | --- | --- |
| Swale | Catches runoff along a slope and soaks it in | Dig a shallow ditch exactly on the contour; put the spoil on its downhill side as a low berm | Holds 2–4 levels per column; overflow spreads out evenly if it is truly level |
| Berm | Steers or holds water | Packed soil ridge | Follows stable-step rules; overtopped berms erode like dams |
| Terrace | Turns a slope into level steps | Benches with log or stone risers | Cuts stream power on the slope to near zero |
| Water bar | Turns runoff off a path or old road | A log or stone set diagonally across the path | Diverts all surface flow up to 10 levels an hour |
| Rain garden | Soaks runoff from the RV roof or drive | A shallow basin with loose soil and wet-tolerant plants | Soaks 3× faster than lawn; fills and drains within a day |
| French drain | Drains a wet spot under a path | A trench with perforated pipe and gravel | Moves soil water to an outlet. Draining a natural seep is a wetland violation |
| Culvert | Carries a stream under a road or drive | Pipe or open-bottom arch, bought | Each size passes a set flow. Above that, water backs up and runs over the road. A pipe set above the streambed (perched) blocks fish |
| Ford or log bridge | Lets the drive cross water | Cobble ford, or crafted log bridge | A bridge doesn't touch the stream, so fish and sediment pass freely |
| Spillway | Lets floods pass a dam | Cobble or gravel notch below the crest | Sized with the catchment tool |
| Weir | Sets a pond or pool level | A level stone or log sill | Water above the sill flows over; the pool behind stays full |
| Riffle | Adds oxygen; spawning gravel | Cobble sill across a stream with gravel downstream | Creates a riffle unit and the pool above it |
| Rock or log vane | Pushes current away from a bank | A line of boulders or a log angled upstream from the bank | Halves stream power on that bank; scours a pool off its tip |
| Liner | Seals a leaky pond | Packed clay, or bentonite (bought) raked into sand or gravel | Bentonite: one bag seals 4 m² to clay-like soaking |
| Erosion blanket | Protects bare slopes until plants grow | Bought roll, pinned with stakes | Erosion ×0.2; rots away in one season |
| Straw mulch | Quick cover for bare soil | Bought or baled from mowing | Erosion ×0.4; lasts half a season |

**Culvert capacity.**

| Size | Passes (levels an hour) | Fish |
| --- | --- | --- |
| 1-block pipe | 40 | Only if set at streambed level |
| 2-block pipe | 160 | Only if set at streambed level |
| Open-bottom arch, 3 blocks wide | 400 | Always; the natural bed runs through it |

### Beaver dam analogs (v0.3)

A beaver dam analog (BDA) is a real, low-cost restoration method: a row of wooden posts driven across a small channel, woven with live willow or alder, and packed with mud. It works like a leaky beaver dam. It slows water, traps silt and raises the water table, so a cut-down gully floor turns back into wet meadow.

**How it plays.** Cut willow whips with the loppers. Drive a post into every column across the channel with the post driver. Weave 2 whips into each post, then dump one soil bag of mud or subsoil against the upstream face and tamp it. Build them in a series, so the pool behind each one reaches the foot of the next one up.

| Rule | Number |
| --- | --- |
| Where | Skid Road Gully, side channels and seep runs. On Hemlock Run only with a fish notch |
| Crest height | Up to 1 block above the bed (4 levels on the Run) |
| Spacing in a series | 6–10 blocks, so each pool backs up to the next structure's foot |
| Fish notch (Run only) | 1 column left open at bed level. The post driver won't finish a Run BDA without it |
| Leak rate | Passes up to 6 levels an hour through the weave; above that, water spills over the crest |
| Silt trap | The pool drops 80% of the sediment load until it is full of silt (about one season of storms in the gully) |
| Water table | Columns within 6 blocks of a full pool gain +2 moisture. Soft rush, tussock sedge and joe-pye weed germinate there by the next spring |
| Storm damage | −25 condition in a 40–60 mm storm, −50 in the 100 mm big storm. Below 50 it leaks at the crest and lets silt through. At 0 the weave washes out and the posts stay |
| Patch | 2 willow whips and 1 soil bag dump restore +50 condition, about 20 s of work |
| Living posts | Each spring, 1 in 4 woven whips standing in saturated soil roots into a black willow seedling |
| Permits | On the Run or within 3 blocks of it: the $40 stream work permit and the work windows (Rules for working the land) |

| Item | Id | Get it | Price or recipe | Tier |
| --- | --- | --- | --- | --- |
| BDA post | `fieldstation:bda_post` | Craft at the awning workbench | 1 log → 4 posts | Volunteer |
| Willow whip | `fieldstation:willow_whip` | Cut with the loppers from black willow, hazel alder or silky dogwood; 2 per shrub per day, and the shrub regrows | Free | Volunteer |
| Beaver dam analog (block) | `fieldstation:beaver_dam_analog` | Built in place: posts, then whips, then packed fill. States `stage=posts\|woven\|packed`; condition, fill and pool level live in the column data | 1 post per channel column, 2 whips per post, 1 soil bag | Volunteer |
| Post driver, loppers | See Player and tools | Buy | $40, $30 | Volunteer |

**Rewards.** Each BDA that comes through its first 40 mm storm with condition above 0: +3 reputation (first 5 only). When a series turns 50 m² of former gully floor to moisture 5 or more: a journal milestone, +15 reputation and a one-time $100 from the land trust, plus 5 SC from v0.4. Less silt in the Run raises the stream health score and the downstream score.

**Mistakes.** A blowout releases half the silt trapped in that pool, so the Run runs muddy for 1 day. A pool that backs up onto the drive or a path turns it to mud (60% speed) until the crest is lowered with the spade. The storm preview shows both before the first post goes in. Only time and silt are lost.

**Hooks.** Sediment, stream power and rills (Systems spec); moisture bands and germination (Plants); the stream health score (The cove); the beaver (Additions → Animals that reshape the land, and Mobs → Beavers and beaver dam analogs); storm forecasts (Weather and climate).

**GameTests.** `fieldstation:bda/series_traps_silt`: three BDAs 8 blocks apart in a test gully cut the sediment leaving the reach by at least 60% in a 50 mm storm. `fieldstation:bda/notch_passes_trout`: a brook trout swims upstream through a notched Run BDA, and the post driver refuses to finish one without a notch.

## Rules for working the land

Real land work comes with rules. Here they are part of the challenge, and each one protects something the player will care about.

| Rule | What it says | If broken |
| --- | --- | --- |
| Stream work permit | Digging or placing anything in the Run, or within 3 blocks of it, needs a $40 permit from the Park Service | Work is flagged; −15 reputation; the agency biologist visits |
| Work windows | No in-stream work from the first redd of fall until 4 days after the first day of winter, and never within 4 blocks of a marked redd | −25 reputation; tools will not dig or place within 4 blocks of a marked redd, so no redd is lost |
| Wetlands | Seeps, springs, marsh and vernal pools can't be filled or drained | −50 reputation and a fine; the land must be restored |
| Stream buffer | Keep at least 5 blocks of plants along the Run | Inside the buffer, grants are paid at half rate for the Run's species |
| Disturbance limit | No more than 400 m² of bare soil at once without silt fence, wattle or blankets; stabilize new bare ground within 3 days | A warning, then −5 reputation a day |
| Protected spots | No digging or rock work within 8 blocks of the rattlesnake den; the witness tree and chestnut sprouts can't be cut | −30 reputation |
| Nesting season | No mowing, clearing or felling within 16 blocks of an active nest in spring and summer | −15 reputation per nest |
| Clean materials | Mulch, compost and plants from outside must be certified clean | Jumping worms or invasive seed arrive |

The RV computer's Permits app lists these rules. The HUD shows a small notice when the held tool would break one, before the player acts.

## Landscaping and planting

**Plant by slope position.** Water collects downhill, so a slope sorts plants by itself:

| Position | Moisture | Plant there |
| --- | --- | --- |
| Ridge and upper slope | Dry | Oaks, chestnut, mountain laurel, little bluestem |
| Mid slope | Moist | Tulip poplar, hemlock, spicebush, ferns |
| Slope foot and seeps | Wet | Yellow birch, rhododendron, cinnamon fern, sedges |
| Stream bank | Wet, flooded at times | Silky dogwood, willow live stakes, river birch |
| Pond edge and shallows | Saturated to 2 levels | Soft rush, broadleaf arrowhead, hazel alder |

The seed pouch's readout grades each spot as good, fair or poor for the species in hand.

**Planting methods.**

| Method | Speed | Survival | Best for |
| --- | --- | --- | --- |
| Broadcast seed | Fastest | 30–50% | Meadows |
| Plugs | Medium | 80% | Wildflowers, wetland edges |
| Live stakes | Fast | 70% (winter planting) | Stream banks |
| Bare-root saplings | Medium | 60–80% (winter or early spring) | Trees and shrubs in bulk |
| Container plants | Slow and costly | 90% | Showpiece trees, key spots |
| Transplanted wild plants | Medium | 60% | Moving plants out of a work area |

**Spacing.** Trees 4 blocks apart, shrubs 2, plugs 1. Closer planting still works, but the losers die off as they compete.

**Care for new plantings.** In the first 7 days, a planting needs water if no rain falls for 2 days: a watering can, or the hose from the pump. Mulch cuts the watering needed in half. Tree guards stop deer and beavers. Survival is shown per planting in the journal.

**Paths and viewing spots.** Paths matter because walking compacts soil and tramples plants:

- **Mulch paths** keep foot traffic on one line.
- **Stepping stones** cross wet ground.
- **The boardwalk** crosses the marsh edge without touching it.
- **The observation blind** at Miller Pond and a bench spot at Raven Rock give quiet places to watch.

Foot traffic off paths slowly packs the soil, which shows on the terrain map as a worn line.

## Trail craft (v0.4)

Build a footpath network with real trail techniques. A trail that follows the land lasts for years. A trail that runs straight downhill becomes a ditch.

**How it plays.** Flag a line with survey stakes and read its grade with the clinometer. Cut tread with the McLeod. Set a waterbar wherever water would run down the trail, lay stone steps on steep pitches, turn on switchbacks instead of climbing straight, and cross wet spots on puncheon. Then watch the next storm run off the trail instead of down it.

**Grade rules.** Grade is rise over run along the trail, shown as 1 in N. These follow real trail practice: keep grades gentle and get water off the tread often. The clinometer also checks the half rule: the trail's grade should be no more than half the grade of the slope it crosses. A segment that breaks it counts one band steeper.

| Grade | What the tread needs |
| --- | --- |
| 1 in 12 or gentler | Nothing; it sheds water on its own |
| 1 in 12 to 1 in 8 | A waterbar every 16 blocks |
| 1 in 8 to 1 in 5 | A waterbar every 8 blocks |
| Steeper than 1 in 5 | Stone steps, or switchback to a gentler line |

| Structure | Id | What it does | Recipe | Tier |
| --- | --- | --- | --- | --- |
| Built tread | `fieldstation:trail_tread` | Packed 1-block tread. Walking speed 120%, erodibility 0.05, and footsteps on it never add wear | McLeod: 3 uses on a column, 1 on worn trail | Volunteer |
| Waterbar, log | `fieldstation:water_bar_log` (exists) | Set diagonally across the tread; turns all surface flow up to 10 levels an hour off the downhill side. Rots in 3 years | 1 log, 2 stakes | Start |
| Waterbar, stone | `fieldstation:water_bar_stone` | The same, and never rots | 4 cobble | Volunteer |
| Stone step | `fieldstation:step_stone` | Holds a 4-layer rise on any grade; erodibility 0. Must sit on packed fill | 1 boulder → 2 at the awning workbench; set with the rock bar | Volunteer |
| Switchback landing | Built tread | A 3 × 3 turn at 1 in 12 or gentler. Legs between turns stay at 1 in 8 or gentler | Cut and pack with the McLeod | Volunteer |
| Puncheon | `fieldstation:puncheon` | Low log walkway over columns at moisture 5 or more. 100% speed instead of mud's 60%; the soil under it is never packed or trampled. Rots in 3 years | 1 log, 2 planks → 2 | Volunteer |

**Water and wear.** Trail craft adds no new physics. Tread is a packed low line, so the column flow model already sends runoff down it, and a waterbar cuts that run short. A waterbar's outlet must drop onto plants, leaf litter or 3 cobble (an armored outlet); onto bare soil, it starts a rill there. Worn trail comes from the existing wear rule (Additions → Animals that reshape the land, deer row): columns walked 20+ times in 7 days become packed trail, and runoff follows them. That rule counts the player's steps, deer and the old-timer alike. Built tread stops it, because steps on tread never count. Columns beside the tread still count, so a shortcut across a switchback wears in like a deer path. Two boulders or a brush pile on the inside of the turn stop the shortcut, as real trail crews do.

**Why build it.** Built tread gives 120% walking speed (worn trail stays at 110%). Walking on it is quieter: noise radius 3 instead of 4 (Mobs → Perception). It keeps feet off the plants beside it, so seedlings there are never trampled. The old-timer walks only on the drive, tread and gentle ground, so trails take him to stops he can't reach otherwise (The cove → The old-timer).

**Rewards.** At each Park Service season report, every trail segment of 20 blocks or more (tread between two junctions or ends) that lost less than 1 layer on every column and started no rill is rated sustained. It pays $3 a block, up to $150 a season, plus +5 reputation and 5 SC per segment the first time it is rated.

**Mistakes.** A gullied trail loses layers, and the rill grows like any other (Systems spec → Rills and gullies). The fix is time: fill the rill with the spade, re-cut the tread and add the missing waterbar. A step set on loose fill tips over in the next storm and must be reset. Rotting puncheon and log waterbars show amber in the journal half a season before they fail.

**Hooks.** The trail-wear rule (Additions), compaction and stable steps (this tab), the column flow model and rills (Systems spec), trampling (Plants), hearing (Mobs), survey stakes and the plan overlay (Player and tools), the season report (Progression and economy), the old-timer (The cove).

**GameTests.** `fieldstation:trail/waterbars_hold`: a 32-block tread at 1 in 6 with no waterbars loses 2 layers on at least one column over two 50 mm storms; with a waterbar every 8 blocks and armored outlets, it loses less than 1. `fieldstation:trail/switchback_shortcut`: 20 corner cuts in 7 days pack a shortcut line; with two boulders on the inside of the turn, a test walker stays on the tread.

## Gameplay loop and feedback

1. **Read.** Test pits, the hand auger, the moisture probe and the catchment tool show what is under the ground and where water goes.
2. **Plan.** In plan mode (laser level held), the player draws areas with flags and sets each area's target level, material and finish: packed, loose, mulched or seeded.
3. **Preview.** A *storm preview* button runs one 100 mm storm on a copy of the land around the plan, in a few seconds, and paints the result on the overlay: where water pools, where it runs, and where the plan would erode. Players fix the plan before moving a single block.
4. **Job list.** The plan becomes a checklist of block counts: cut 46, fill 38, pack 30, seed 120 m². It also estimates energy, game hours and the materials to buy, and flags any rule the plan would break.
5. **Build.** Tools show distance-to-target as you work. Finished columns get a small check mark.
6. **Protect.** Any bare soil left counts toward the disturbance limit until it is seeded, mulched or blanketed.
7. **Watch.** The terrain-change map and the journal show how the build weathers through the next storms.

**There is no undo.** Plans can be changed freely before digging. Once a block is moved, fixing a mistake means more work, as in real life. The storm preview is the safety net.

## Terraforming items

These add to the tool list in Player and tools.

| Item | What it does | Get it | Price or recipe | Tier |
| --- | --- | --- | --- | --- |
| Spade | Cuts or places one layer at a time; trenches; transplants plants with their root ball | Buy | $30 | Volunteer |
| Mattock | Digs rocky or root-bound ground; removes stumps | Buy | $45 | Volunteer |
| Hand auger | Pulls a 3-block soil core and shows its layers without a test pit | Buy | $55 | Volunteer |
| String line and line level | Marks a level line between two stakes; good for swales without the laser level | Craft | 2 stakes, 1 flagging tape (bought) | Start |
| Sod cutter | Lifts grass and its soil as a sod item to replant elsewhere | Buy | $70 | Technician |
| Watering can | Waters 3 × 3 | Craft | 3 scrap metal | Start |
| Crane mats | Stop machines from packing soil | Craft | 6 planks | Volunteer |
| Bentonite clay | Seals 4 m² of sandy or gravel pond floor | Buy | $25 a bag | Volunteer |
| Erosion blanket | Covers 3 × 8 of bare slope for one season | Buy | $30 a roll | Volunteer |
| Straw bales | Mulch for 9 m²; also a temporary check dam | Buy or craft | $8, or 9 plant fiber | Start |
| Geotextile fabric | Goes under gravel paths and riprap so they don't sink | Buy | $20 a roll | Volunteer |
| Perforated drain pipe | French drains | Buy | $15 a section | Technician |
| Culvert pipe, 1 or 2 blocks | Stream and drive crossings | Buy | $40 or $90 a section | Technician |
| Open-bottom arch culvert | Fish-friendly crossing | Buy | $600 | Technician |
| Log bridge kit | Crosses the Run without touching it | Craft | 4 logs, 6 planks, 1 hardware kit | Technician |
| Boulder | Anchors banks, riffles and vanes | Gather with the rock bar from outcrops, or buy | $20 each | Volunteer |
| Cobble (16) | Riprap, riffles, walls | Gather or buy | $20 | Start |
| Water bar log | Diverts runoff off a path | Craft | 1 log, 2 stakes | Start |

## How it works in Minecraft

- **Layers.** Only the top block of each column uses the 8-layer shape. Blocks below are whole blocks of their material, as leaned in the integration brainstorm. The spade edits that top block's layer count; the shovel removes whole blocks. Under water, the top block renders as a whole block, and partial layers show only on dry columns (see the Systems spec).
- **Sliding.** A column changing marks it and its 4 neighbors "to check". A small job each tick works through the list, moving one layer at a time from any column over its stable step to its lowest neighbor. Saturated columns re-enter the list every game hour.
- **Settling and compaction** live in the column data (fill depth, packed flag, days since placed), not in block states. That keeps blocks simple and the timers cheap.
- **Plan overlay.** Plans are stored per player. The client draws them as faint ghost blocks: one color for cut, another for fill, check marks when done. The overlay costs nothing on the server.
- **Storm preview.** The server copies the water and soil arrays for up to 64 × 64 columns around the plan. It runs one storm at reduced detail on a background thread and sends back a heat map. The live world is never touched.
- **Machines.** The mini excavator (fieldstation:mini\_excavator) and skid steer (fieldstation:skid\_steer) are vehicle entities, summoned at the drone pad by a one-day rental key (fieldstation:rental\_key). They drive on the terrain, mark columns they cross as packed (unless on crane mats), and call the same dig and fill actions as hand tools, just bigger.
- **Rule checks** run before any tool action. A broken rule shows a notice first and only applies its penalty if the player goes ahead.
- **GameTests:** a slope steeper than its stable step slides to stable; loose fill settles and packed fill doesn't; a perched culvert blocks fish passage; a swale on true contour spreads overflow evenly; a storm preview matches a real storm within 10%.
