# Player and tools

## The player

**Energy** (0–100) is the only body stat. It drops 2 an hour while awake, plus a cost per tool use (see the tool list). Below 20, the player works at half speed. Sleep restores it, and meals help too, depending on the food and sleep option below. There is no vanilla hunger bar; vanilla hunger, armor and XP are hidden.

**Food and sleep (player option).** The player picks how much food and sleep matter when the world is made, and can change it any time in Settings (`player_needs`). No mode hurts the player, costs items or ends the game.

| Mode | Food | Sleep |
| --- | --- | --- |
| Off | Not a need. Meals can still be cooked for flavor | Energy refills to full at 06:00 whether you slept or not; the bed only skips the night |
| Comfort (default) | A meal gives Well fed: +15 energy now, and energy drains 25% slower for 6 game hours. Skipping meals costs nothing | Sleep restores 12 energy an hour |
| Real | Three meal windows a day (06:00–09:00, 11:00–14:00, 17:00–20:00). Each missed meal lowers max energy by 10 until the next meal, never below 50. Meals also give Well fed | Staying up through the night lowers max energy by 20 until you sleep a full night. A 2-hour nap restores 20 |

**Food.** Groceries come by drone: the grocery box ($35, 21 meals, Volunteer). The RV kitchen counter cooks three things:

- **Sandwich:** a meal.
- **Camp stew:** a meal that is also warm, removing the cold drain for 4 hours.
- **Coffee:** +10 energy, once every 4 game hours.

The land adds snacks: blackberries in summer, ramps in spring (the readout says take 1 plant in 10) and chanterelles after summer rain. Each gives +5 energy and counts as half a meal. Kitchen scraps go in the compost bin. Item ids: `grocery_box`, `sandwich`, `camp_stew`, `coffee`, `blackberries`, `ramps`, `chanterelles`.

**Movement.**

| Ground | Speed |
| --- | --- |
| Normal ground | 100% |
| Packed trail | 110% |
| Built trail tread (Landscaping and terraforming → Trail craft) | 120% |
| Mud | 60% |
| Water 0–2 levels deep | 80% (100% in waders) |
| Water 3–4 levels deep | 50% (100% in waders) |
| Deeper water | Vanilla swimming |
| Full soil bag | −10% |
| Pushing the wheelbarrow | 70% |

**Cold.** Outside below 0 °C, energy drains 25% faster unless the player wears the winter jacket.

**Damage.** Vanilla falling and drowning are the only ways to lose health, and health regenerates. If it runs out, the player wakes in the RV bed with all items and 20 less energy. Nothing in the world attacks.

## Tools

| Tool | What it does | Energy per use | Price | Tier |
| --- | --- | --- | --- | --- |
| Field shovel | Digs 1 block into the soil bag; dredges silt | 0.25 | Start | — |
| Soil bag | Holds 16 blocks of one layer; dumps them loose | 0.1 per dump | Start | — |
| Pick | Breaks caliche, packed clay and loose rock | 1 | $40 | Volunteer |
| Rake | Levels loose soil; fills small dips from the blocks around them | 0.3 | $20 | Volunteer |
| Hand tamper | Packs 1 block | 0.5 | Start | — |
| Plate compactor (rental) | Packs 3 × 3 blocks | 1 | $60 a day | Technician |
| Rock bar | Sets stones for riprap, check dams and spillways | 1 | $30 | Volunteer |
| Wheelbarrow | Carries 64 mixed blocks | — | $80 | Volunteer |
| Pump and hose | Moves 4 water levels a minute up to 20 blocks; uses power | — | $120 | Volunteer |
| Seed pouch | Sows 3 × 3 | 0.2 | Start | — |
| Seed spreader | Sows 5 × 5 | 0.2 | $60 | Volunteer |
| Planting trowel | Plants plugs and saplings | 0.3 | Start | — |
| Seed collector | Collects seed from mature plants | 0.1 | $20 | Volunteer |
| Dip net | Catches small water animals; skims duckweed | 0.2 | $25 | Volunteer |
| Gloves (dry and wet) | Hand catching; amphibians need wet gloves | — | Start | — |
| Ventilated cup | Carries insects and small frogs | — | $5 | Volunteer |
| Binoculars | Zoom; logs species | — | Start | — |
| Camera | Journal photos; contracts | — | $90 | Volunteer |
| Sample bottle | Water sample | — | $10 for 3 | Volunteer |
| Moisture probe | Reads soil moisture and the layers below the crosshair | 0.1 | $30 | Volunteer |
| Laser level | Reads elevation against a benchmark; sets plan levels | — | $150 | Volunteer |
| Survey stake (`fieldstation:survey_stake`; flags are a variant of the same item) | Mark plan outlines; 8 come in the starting kit | — | $10 for 20 | Volunteer |
| Benchmark stake (fieldstation:benchmark\_stake) | Sets the 0 height that the shovel readout and laser level measure from; 1 comes in the starting kit | — | Start | — |
| Field bucket (fieldstation:field\_bucket) | Holds 8 water levels; fills test pits for the perc test and waters plantings | — | Start | — |
| GPS and transect tape | Mapping contracts | — | $80 and $15 | Volunteer |
| Quadrat frame | Counts plant cover in 1 m² | — | $10 | Volunteer |
| Audio recorder | Records calls | — | $70 | Volunteer |
| Camera trap | Overnight photos | — | $100 each | Volunteer |
| Bat detector | Hears bats | — | $120 | Technician |
| eDNA kit | Filters water for DNA | — | $40 a use | Technician |
| Data logger | Hourly oxygen and temperature | — | $60 | Technician |
| Scythe | Mows 1 column | 0.2 | $35 | Volunteer |
| Brush cutter | Clears shrubs and saplings | 0.5 | $150 | Technician |
| Hatchet | Girdles a tree (makes a snag) | 1 | $25 | Volunteer |
| Saw | Fells a tree into logs | 3 | $60 | Volunteer |
| Drip torch | Lights a prescribed burn (permit needed) | — | $80 | Technician |
| Headlamp | Light at night without scaring frogs (red mode) | — | $25 | Volunteer |
| Waders | Full speed in water up to 4 levels deep | — | $60 | Volunteer |
| Winter jacket | No extra energy drain in the cold | — | $50 | Volunteer |
| Mini excavator (rental) | Digs 4 blocks per swing; cuts stone | 0.5 | $250 a day ($200 at Technician) | Volunteer |
| Sketchbook (`fieldstation:sketchbook`) | Hold still near an animal; a pencil sketch fills in over 10–30 s and logs the species like a photo; holds 64 sketches, and spoiled pages erase. From v0.3 | — | $15 | Volunteer |
| Loppers (`fieldstation:loppers`) | Cuts willow whips for beaver dam analogs (2 per shrub per day), cuts apple scion wood in winter, and prunes fruit trees. From v0.3 | 0.2 | $30 | Volunteer |
| Post driver (`fieldstation:post_driver`) | Drives a BDA post into a gully or stream bed; won't finish a Run BDA without a fish notch. From v0.3 | 0.5 | $40 | Volunteer |
| Fencing pliers (`fieldstation:fencing_pliers`) | Cuts old wire free of fence posts, 1 use per fence block, for spring cleanup day. From v0.3 | 0.2 | $20 | Volunteer |
| McLeod (`fieldstation:mcleod`) | Trail tool: cuts and packs tread (3 uses make a column built tread, 1 on worn trail) and levels switchback landings. From v0.4 | 0.3 | $45 | Volunteer |
| Clinometer (`fieldstation:clinometer`) | Reads trail grade as 1 in N, the waterbar spacing it needs, and the half rule. From v0.4 | — | $20 | Volunteer |
| Grafting knife (`fieldstation:grafting_knife`) | Grafts scion wood onto apple rootstock at the awning workbench: cut, fit, wrap. From v0.4 | 0.1 | $25 | Volunteer |

**Starting kit.** The RV arrives with $300 and: field shovel, soil bag, hand tamper, seed pouch with 32 grass and 16 reed seed, planting trowel, dry and wet gloves, binoculars, field journal, field bucket (`fieldstation:field_bucket`, holds 8 water levels), 3 sample bottles, 10 test reagents, 8 survey stakes, 1 benchmark stake.

## Survey and planning

Good builds start with reading the land. These tools turn guesswork into a plan the player can follow block by block.

**Test pit.** Dig a single column to see the layers and how deep the clay sits. The moisture probe reads the same from the surface, without digging.

**Perc test.** Pour a full field bucket into a test pit and watch the water drop. The HUD times it and gives a verdict:

| Drop rate | Verdict |
| --- | --- |
| Under 1 level an hour | Will hold |
| 1–4 levels an hour | Needs a clay liner |
| Faster | Won't hold |

**Benchmark and laser level.** Place a benchmark stake, and the laser level reads every other block as ± blocks and levels from it. The player sets target levels in plan mode, for example:

- pond floor at −1.5
- dam crest at +0.8
- spillway at +0.5

**Plan overlay.**

- Flags outline an area, and each area gets a target level.
- The plan shows as a faint overlay in the world and on the terrain map: blocks to cut in one color, blocks to fill in another, each with a count.
- The cut and fill totals show whether the spoil from digging is enough to build the dam.

**Dig assistance.** While a plan is active, the shovel readout shows how far each column still is from its target. Columns that reach target show a small check mark.

**Catchment tool.** Point at any spot on the terrain map, and it outlines all the land that drains to that spot. It also estimates the water a big storm (100 mm) would bring. From that it suggests a spillway width: catchment area in m² ÷ 500, at least 1 block.

## Building each water feature

These are the step-by-step processes behind the water features table on the main tab. Quantities are for a typical first build.

**Rain pond** (about 6 m², 1 block deep)

1. Pick low ground where the catchment is at least 10× the pond's area.
2. Dig a test pit and run a perc test.
3. Stake the outline and set the floor level.
4. Dig. Keep topsoil apart for the banks.
5. If the perc test said *liner*, line the floor and sides with 1 block of clay (about 20 blocks) and tamp every block.
6. Shape a shallow shelf, 1–2 levels deep, along a third of the edge for plants.
7. Put topsoil back on the banks, sow grass and plant reed plugs on the shelf.
8. Wait for rain.

It passes if it holds water for 3 days.

**Spring pond**

1. Find the spring head (it reads saturated on the moisture probe).
2. Dig the pond just downstream so the spring feeds it.
3. Cut a gravel-lined outlet notch so water flows through and stays cold.
4. Lay 4+ blocks of gravel bottom for crayfish.

**Stream**

1. Start from a spring or a pond's overflow.
2. Keep the bed's drop at 1 block per 8 blocks or gentler; steeper cuts and carries silt.
3. Set a gravel riffle every 6–8 blocks with pools between.
4. Slope the banks 1 up to 2 across, then add coir logs and reeds.
5. Let it meander.

**Lake with a dam**

1. Choose a narrow point in the valley.
2. Dig a core trench 1 block down into clay along the dam line.
3. Build the dam from clay in layers, tamping each layer. The base must be at least 3× as wide as the dam is high.
4. Set the crest 1 block above full water.
5. Cut a spillway of gravel or cobble half a block below the crest, sized with the catchment tool.
6. Riprap the water face.

A 3-block-high, 20-block-long dam takes about 200 clay blocks, which means renting the mini excavator.

**Marsh**

1. On flat ground, build a berm 1 block high around the area.
2. Set the outlet at 3 levels.
3. Plant sedges, rush and broadleaf arrowhead across the flooded flat.

**Vernal pool**

1. Dig a 12 × 8 basin, 6 levels deep at the center, into clay in the woods, under canopy.
2. Don't connect it to any stream, so fish can't reach it.
3. It should hold at least 2 levels until summer day 4 and dry out by fall in most years; the drying keeps bullfrogs out.
4. No planting needed; fallen leaves feed it.

**Constructed wetland** (cleans RV grey water)

1. Dig a lined cell 2 × 6 blocks.
2. Lay 1 block of gravel.
3. Plant reeds.
4. Run the grey-water hose in.

It cleans one tank a day.

**Sediment basin**

1. Dig a basin 2 blocks deep where muddy water enters.
2. Dredge it when it is half full of silt.

## HUD and screens

**HUD layout.**

| Spot | Shows |
| --- | --- |
| Top left | Day, time, season, weather icon, temperature |
| Top right | Energy bar and cash |
| Bottom center | The held tool's readout |
| Right edge | Up to 3 notices; each fades after 6 seconds |
| Left edge (when active) | Onboarding or contract checklist |

**Tool readouts** (what each tool shows when pointed at a block):

| Tool | Readout |
| --- | --- |
| Shovel | Layer name, layers below, and the distance left to the plan target |
| Tamper | Packed or loose |
| Moisture probe | Moisture 0–7 and the depth of each layer below |
| Laser level | Height above or below the benchmark |
| Binoculars | Species name once logged, and its condition |
| Sample bottle | Habitat name, and whether a sample from here is due |
| Seed pouch | How well the species fits this spot: good, fair or poor |
| Clinometer | Grade ahead as 1 in N: green to 1 in 8, amber to 1 in 5, red when steeper; the waterbar spacing needed; a half-rule warning |
| Sketchbook | Sketch progress, and "Hold still" when the animal grows wary |
| Grafting knife | Fit: good, fair or poor, and the days until the graft takes |

**Screen style.** Every screen follows the same rules:

- flat off-white cards with soft rounded corners
- one teal accent for water and actions
- green, amber and red only for status
- a clean pixel sans font
- no textures behind text

Vanilla inventory grids appear only where the player actually moves items.

**Notices** are short and use plain words: "Big storm tomorrow. West pond is 1 level below its dam crest." Each one links to the app that explains it.

## Field journal

The journal is the game's record and its scoreboard. It opens from the handheld item, the J key, or the computer.

| Page | Shows |
| --- | --- |
| Species | Best photo, common and scientific name, status (common or protected), first sighting, sightings count, survey estimate (with its range and age) and the Park Service allotment for each habitat, condition, life stages, deaths by cause each season, genetic diversity once tested, and short notes in the style of the prototype species notes |
| Habitat | A map thumbnail, area, depth, age, water chemistry charts, species living there, recent events (bloom, flood, breach) |
| Plant | Where it grows, cover, health, flowering and seed season |
| Site summary | Biome completion (species logged out of species possible), grant income per day, soil lost this season, invasive cover |
| Notes | Automatic event entries ("Day 12: first tadpoles in East pond") plus the player's own notes |
| History (from v0.3) | The cove's past: spring cleanup finds, the witness tree, and the old-timer's stories with their map pins. An entry is stamped Seen once the player logs or finds what it points at |

Logging every cove species earns the Appalachian badge ($300, +100 reputation, 50 SC and a biome crate; see Progression and economy). Late species arrive only as their habitat is restored, so the badge is targeted for years 5–6. The first entry for each species also earns reputation, so exploring pays.

**Nature calendar.** Each year the journal records the first-of-year dates: first frog call, first leaf-out, first firefly and first frost. Years line up side by side, so shifts show at a glance. This is the nature calendar the cove paper reads.

## Onboarding

The first 15 minutes in the cove teach the core loop by doing it once: read the land, change it by hand, wait for water, meet an animal. Guidance is a short checklist on the left of the HUD, never pop-ups. Every step can be skipped, and returning players can turn it off. The goal is to pass the stranger test (see *Fun, feel and finish*).

| Minutes | What happens | What it teaches |
| --- | --- | --- |
| 0–2 | The RV rolls onto its pad at the cove's foot in morning mist. A note from the Park Service on the dash: the cove is yours to restore, and grants pay for every species that comes back | The premise and how money works |
| 2–5 | The RV computer's Goals card lists three things to try. Walk to Miller Pond with binoculars and log the first species (a green frog calling from the old pond) | Goals, logging, the pond |
| 5–8 | Fill a sample bottle at the spring; the test bench in the RV reads it as cold and clean | Water matters; honest numbers |
| 8–11 | At Miller Pond, dig a test pit and run a perc test; the cut face shows topsoil, silt and clay | Reading the ground; layers |
| 11–15 | Push 4 survey stakes around a small pool off the pond's edge. The shovel readout counts down to a −1 floor set from the benchmark stake by the RV. Dig and tamp. The dash radio warns of rain tonight | Planning, digging, packing |
| After the first sleep | Wake to a filled pool and the first dragonfly over it; the year's first Chronicle entry is written | The land answers back |

**Scripted start.** The game starts at 08:00 on day 1 of spring. Night 1 always brings 15 mm of rain, and a darner is spawned over the player's first filled basin at 06:00 on day 2.

After that, the checklist hands off to the morning briefing (the Goals card in v0.1, before the field tablet ships in v0.2), and the game stops teaching.

## Art, audio and accessibility

**Look: flush and clean.**

- 16× textures with flat color and very little noise, without hard outlines.
- A natural palette: clay red, moss green, water teal, bark brown, soft sky blue.
- Every block reads at a glance from 20 blocks away.

**Water shows its condition.** It is clear and tinted teal when healthy, browner as turbidity rises, and greener as algae grows. The player can judge a pond by eye before testing it.

**Atmosphere.** Mist in hollows and over ponds on cool mornings, seasonal grass and leaf color, and snow that settles deeper in hollows. Shaders are optional and never needed to read the land.

**Audio.**

- Real recorded calls for every species, from properly licensed sources.
- Flowing water whose volume follows the flow, plus wind in reeds and grass.
- Rain on the RV roof.
- Music is sparse: a few quiet acoustic pieces at dawn and dusk only, so the land's sounds stay in front.

**Accessibility.**

- Subtitles for every call and sound, naming the species once it is logged.
- A colorblind-safe palette option for the terrain map and status colors.
- Larger text, reduced motion, and toggle instead of hold for every hold action.
