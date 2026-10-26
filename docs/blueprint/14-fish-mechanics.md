# Fish mechanics

How fish live, move and react, beyond the species lists in *The cove*. The core idea: fish placement comes from the water itself, so every boulder, log and pool the player builds changes where fish live and where they can be seen.

## The water a fish needs

**Depth.** Small fish need at least 2 levels of water and adult trout at least 4. In anything shallower, a fish is stranded: it moves to deeper water if it can, or dies if the water drops around it. The pump and the mini excavator refuse to lower a pond or reach below a fish's minimum depth while it still holds fish. A 'fish salvage' step (seine or electrofish, then move the fish under the relocation permit) clears it first, as on real dewatering jobs.

**The flow field.** The water simulation already knows, for every column, which way the water moves and how fast. Fish read that flow field to decide where to hold and where to swim. Units are blocks per second; a calm pool is near 0 and a fast riffle about 1.5.

**Lies.** A *lie* is a spot where a fish can hold with little effort while food drifts past. A column scores as a good lie when:

- its own current is slow, behind a boulder, under a log, or in a deep pool
- a faster current runs beside it, along a *seam*, bringing drifting food
- cover is close: an undercut bank, wood, deep water, or overhanging plants

Every boulder, log and vane the player places creates new lies. More lies hold more trout, and the biggest ones get the best lies.

## Swimming and holding

**Facing the current.** Stream fish hold facing upstream, as real fish do. Holding in faster current costs energy every second. A fish moves to a better lie when the current costs more than the food it brings.

**Dominance.** Trout defend lies. The largest trout takes the best lie in a pool, the next largest the next, and small fish get pushed to poor edges or out of the pool. The player sees the big fish at the head of the pool and the small ones at the tail.

**Schooling.** Dace, chubs, bluegill and minnows move as schools: each fish keeps near its neighbors, matches their heading and avoids crowding. A school turns together when spooked, which reads well from the bank.

**Habitat units** (riffle, run, pool, glide) are tagged per column, as in *The cove*. Each species has a preferred unit and moves there at its active hours.

## Feeding

| Style | Fish | What the player sees |
| --- | --- | --- |
| Drift feeding | Brook trout, dace | Fish hold at a lie and dart a block to the side to take drifting insects |
| Rising | Trout during hatches | Rings on the surface as fish take insects at the top; the best moment for angling surveys |
| Bottom feeding | Sculpin, creek chub | Fish nose along the bed and flip small stones |
| Ambush | Largemouth bass | Fish hang still near weeds or wood, then lunge |
| Plankton picking | Young bluegill | Small fish pick at open water in loose groups |

**Food supply.** Drift food comes from the stream insect numbers (see *Insects and bugs* and *The cove*) plus land insects that fall in from overhanging plants. Bank plants therefore feed trout twice: shade plus falling insects. A hatch multiplies the drift for an evening, and every trout in the reach rises.

**Hunger.** A fish that can't get enough food loses condition (see *Growth*) and takes poorer lies over time.

## Senses and spooking

| What spooks fish | Range | How to avoid it |
| --- | --- | --- |
| Shadow over the water (the player between the sun and the fish) | The player's shadow on the water's surface | Approach with the sun in front of you; stay low |
| Footsteps near the bank | 4 blocks from the edge; double when running | Sneak; it cancels footstep vibration |
| Splash or something thrown in | 8 blocks | Don't throw items into pools |
| A player standing tall in plain view | 6 blocks in clear water, less in murky water | Crouch; stand behind bank plants |

A spooked fish darts to the nearest cover and stays there for 2–5 minutes before easing back to its lie. Spooked big fish warn the rest: when the biggest trout bolts, the whole pool goes quiet. Habituation (see the Mobs tab) works on fish too, so a pool the player visits calmly every day gets easier to watch.

## Temperature, oxygen and refuges

| Water | Brook trout | Sculpin and dace | Bluegill and bass |
| --- | --- | --- | --- |
| Below 4 °C | Sluggish; deep slow pools | Sluggish | Torpid on the bottom |
| 10–16 °C | Best growth | Active | Slow |
| 16–20 °C | Active | Active | Active |
| 20–25 °C | Stressed: stops feeding; seeks cold water | Stressed above 23 | Best growth |
| Above 25 °C | Dies within hours | Dies above 27 | Fine to 30 |

Oxygen follows the water chemistry rules in the Additions tab.

**Thermal refuges.** On hot days, trout crowd into cold spots: spring inflows, seeps entering the Run, deep shaded pools. The player sees a dozen trout packed into one small cold pocket, which shows the value of the spring and the shade at a glance. Protecting refuges is the difference between losing a hot spell's fish and keeping them.

**Winter.** Fish move to deep, slow pools and barely move. Ice over a pool is fine if there is water below it. Shallow riffles that freeze solid kill any fish left in them.

## Growth, size and condition

**Growth.** Each fish grows by length every day it feeds. Growth is fastest in its best temperature band with plenty of food, and zero when stressed or starving.

| Species | Typical adult length | Large one |
| --- | --- | --- |
| Brook trout | 15–20 cm | 30 cm or more (a year-card highlight) |
| Mottled sculpin | 7–10 cm | 13 cm |
| Blacknose dace | 5–7 cm | 9 cm |
| Creek chub | 10–15 cm | 25 cm |
| Bluegill | 12–18 cm | 23 cm |
| Largemouth bass | 25–35 cm | 50 cm |

**Weight and condition.** Weight follows length the way it does in real fish (weight rises with the cube of length). A fish heavier than expected for its length is in good condition; a thin one is not. Measuring fish during surveys gives each population a **condition factor**, the same health measure fisheries biologists use.

**Age.** Length classes give a rough age, so a survey's length chart shows whether young fish are coming through. Missing young fish means a spawning problem, such as silted gravel.

## Spawning

| Species | When | Where | What the player sees |
| --- | --- | --- | --- |
| Brook trout | Fall, cold nights | Clean gravel at the tail of a pool, where water wells up through the gravel | The female turns on her side and fans a hollow in the gravel (a puff of fine sediment); the male guards and chases rivals. The finished redd shows as a patch of clean, bright gravel |
| Mottled sculpin | Spring | Under a flat rock in a riffle | The male guards eggs stuck to the underside of the rock |
| Creek chub | Spring | Pools with gravel | The male carries pebbles in his mouth to build a mound; dace spawn on it too |
| Bluegill | Summer | Shallow gravel in Miller Pond | A colony of round nests, each guarded by a male |
| Largemouth bass | Late spring | Shallow pond edge | A single guarded nest; the male also guards the fry |

**Silt and eggs.** Trout eggs need water flowing through the gravel. Each redd tracks the fine silt settling on it. Above 20% silt, eggs suffocate. This ties egg survival directly to the gully and to bare soil upstream. A clean winter means a strong year of young trout in spring.

## Movement and passage

Fish move between habitats along the stream, but steps, fast water and culverts can stop them. Passage is checked with simple numbers:

| Species | Burst speed (blocks per second, a few seconds) | Highest jump | Needs below the jump |
| --- | --- | --- | --- |
| Brook trout | 3 | 0.75 block | A plunge pool at least 1.25× as deep as the jump |
| Rainbow trout | 3.5 | 1 block | Same |
| Dace, sculpin, chub | 1.5 | 0.25 block | Any |

**Rules.**

- A fish passes a step if the step is no higher than its jump and the pool below is deep enough.
- A fish passes fast water if the current is slower than its burst speed over the length of that fast stretch (about 2 seconds' swim).
- Downstream moves always pass. Each season, 5% of juveniles move one reach downstream.
- A culvert set above the bed (perched) adds a step. A smooth pipe with fast water inside adds a velocity barrier. Either can stop fish.
- A fish ladder is a series of small steps (0.5 block or less), each with a resting pool.
- The cascade barrier proposed in *The cove* is 1.25 blocks with no plunge pool, which nothing climbs. Brook trout above it are protected, and rainbows below it stay out.

Passage is checked per stream segment and stored, so the map can show which fish can reach which reach.

## Population dynamics

Fish populations run per stream reach and per pond on the pulse model in Wildlife rules (Systems spec), like every other vertebrate: one birth pulse per breeding window, young join the adults after years\_to\_adult, and adult deaths are spread across the year. The fish-specific steps below set the species' fields. Brook trout: young\_per\_female 100, young\_survival\_fraction 0.04, years\_to\_adult 1, adult\_survival\_per\_year 0.5.

| Step | Depends on |
| --- | --- |
| Eggs laid (young\_per\_female) | Number of adult females, their size (bigger fish lay more eggs), clean spawning gravel |
| Eggs surviving (part of young\_survival\_fraction) | Silt on the redd, winter scour from floods, water temperature |
| Young surviving the first summer (rest of young\_survival\_fraction) | Shallow, slow edges with cover; summer temperature; predation |
| Adults surviving (adult\_survival\_per\_year) | Lies and cover, food, refuges in heat waves, deep pools in winter, predators (heron, kingfisher, mink, otter) |
| Room (the limit K) | The number of good lies sets how many adults a reach holds (trout); pond area sets it for pond fish |

Because lies and gravel are things the player builds, the player can directly raise how many trout a reach holds, and the Park Service allotment rises to match.

## Fish surveys

| Method | How it plays | Gives | Notes |
| --- | --- | --- | --- |
| Snorkel count | Put on the mask and float slowly upstream through a pool; fish in view are counted automatically | Counts by size | Visibility falls with turbidity; murky water undercounts |
| Electrofishing (agency contract) | A crew stuns fish briefly in a blocked-off reach; the player nets and measures them, then releases them. Three passes are made | A population estimate from how the catch drops each pass: if pass 1 catches 30 and pass 2 catches 15, most fish are already counted | The most accurate method; contract only |
| Seine | Stake one end and sweep a pool | Small-fish counts | Easy in ponds |
| Angling | Barbless rod; measure and release | Catch per hour, which tracks the trend over the years | Relaxing; counts as survey effort |
| Minnow trap | Set overnight | Small-fish presence and rough numbers | Check within 24 hours. An unchecked trap releases its catch at 24 hours. Nothing dies in a trap |
| eDNA | Filter a water sample | Which species are present | Finds fish too rare to catch |

Every method feeds the survey estimate in the journal, weighted by its precision. That estimate is what the Park Service allotment is compared against.

## Fish in Minecraft

- **Entities only near the player.** Fish spawn as entities within 24 blocks, up to 6 per pool or pond, 16 in total (as in the Mobs tab); a school counts as 1. Farther away they are numbers in their reach's population.
- **Schools as one group.** A school is one group entity that moves together and draws several fish, which keeps cost low.
- **Lies come from the water arrays.** Lies and seams are computed from the flow field once a game hour per reach and cached. Fish pick lies from the cache, not by searching every tick.
- **Water levels.** Fish read water depth from the Flowing Fluids level blocks. A fish in water below its minimum depth triggers the stranded behavior.
- **Spooking** uses the same noise and sight checks as land mobs (see the Mobs tab), plus a shadow check: the angle from the sun to the fish passes through the player.
- **Visuals.** Clean, simple models with a slight sway, and a silver flash when a fish turns. Rise rings and redd gravel are small particle and texture effects.
- **GameTests:**
  - a boulder placed in a riffle creates a lie that a test trout takes within a minute
  - a 1-block perched culvert stops brook trout
  - a redd with 25% silt loses its eggs
  - trout move into a cold refuge when the water passes 22 °C
