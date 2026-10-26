# Mobs

Every living thing in the game, by type. No mob is hostile, and none can be hurt by the player. Every wild mob near the player stands for a population in a habitat record, so what you see is what the land supports.

| Category | What they are | Examples | Count in the start biome |
| --- | --- | --- | --- |
| Wildlife | Land and water animals that come on their own | Frogs, turtles, deer, herons | Count kept in the Content registry |
| Aquatic | Fish, tadpoles and crayfish living in ponds and streams | Bluegill, bass, crayfish | Count kept in the Content registry |
| Ambient swarms | Insects drawn as particles, not entities | Fireflies, mosquitoes, bees | 5 |
| Nuisance | Animals that cause trouble without combat | Raccoon, Canada goose | 7 |
| Lab animals | Wild or bought animals kept in the RV lab | Froglets, turtle hatchlings, dart frogs | Any species, by permit |
| People | NPCs who visit for deliveries, contracts and agreements | Delivery driver, biologist | 5 |

**Rules every mob follows.**

- No combat, no drops, no damage from the player.
- They spawn only where their microhabitat exists (see *How the landscape drives the world* in the Systems spec).
- They behave like the real species: right hours, right calls, right food.
- They notice the player and get calmer over time if the player is calm.

## Behavior framework

Every wildlife mob is built from the same set of behaviors (AI goals), each with its own priority. A species is mostly a choice of goals and numbers.

| Goal | What it does | Used by |
| --- | --- | --- |
| Flee | Runs, flies or dives away from the player or a predator | All wildlife |
| Hide | Goes to cover (reeds, water, burrow, log pile) and waits | Frogs, crayfish, salamanders, rabbits |
| Forage | Searches its microhabitat for food | All |
| Hunt | Stalks and strikes smaller mobs | Heron, kingfisher, bass, snakes, otter |
| Drink | Walks to water at set times | Deer, foxes, birds |
| Bask | Sits in sun on logs, rocks or banks | Turtles, snakes, skinks, dragonflies |
| Call | Plays its call on a schedule; others answer | Frogs, birds, owls |
| Nest and breed | Builds a nest, lays eggs, guards them | Birds, turtles, bluegill, frogs |
| School or flock | Moves with others of its kind | Fish, tadpoles, ducks, swallows |
| Migrate | Arrives or leaves in a set season | Birds, monarchs, salamanders on Big Night |
| Rest | Sleeps out of sight in its active-time off hours | All |

**Senses and wariness.** Each mob notices the player inside a range: 12 blocks for most animals, 24 for birds and deer, 6 for fish (only if the player is at the water's edge). Sneaking halves the range. Running, splashing or the RV generator doubles it. When it notices the player, a mob flees if its *wariness* is high, or watches and carries on if it is low.

**Habituation.** Mobs that see the player moving calmly day after day get less wary, down to 30% of their starting value. Chasing them or catching one resets that species' wariness in that habitat. Quiet players get the best views and photos, which rewards patience over rushing.

**Daily schedule.** Each species has active hours: dawn and dusk (deer, heron), day (dragonflies, turtles, songbirds), night (frogs calling, owls, bats, raccoons) or all day (fish). Outside them, it rests out of sight. Rain brings amphibians out at any hour.

**Food web.** Hunters really catch prey mobs near the player, and the same rules run as numbers far from the player. A heron eats about one frog or fish a day, bass eat tadpoles, and a kingfisher takes small fish. The player sees the food web happen instead of reading about it.

## Needs and condition

Every mob, and every population as an average, has a **condition** from 0 to 100. Needs that are met raise it slowly. Needs that are not met lower it.

| Need | Who | Met when | When it fails |
| --- | --- | --- | --- |
| Food | All | The habitat's food base (insects, plankton, plants, prey) covers what the population eats each day | Condition −5 a day; the population shrinks toward what the food supports |
| Skin moisture | Amphibians | Within 3 blocks of moisture 4+ ground, water or rain | Condition −10 an hour; they move toward water or die |
| Drinking water | Land mammals and birds | Water inside their home range once a day | They leave the plot |
| Temperature | Cold-blooded animals | Each species' data file sets its own band (`temp_active_c`, `temp_stress_c`). Defaults: salamanders 5–20 °C, stress 25; frogs 12–30 °C, stress 32; reptiles 18–32 °C, stress 35. Below the band: slow; below 5 °C: torpid and hidden | Above `temp_stress_c` they seek shade or water; above 35 °C, −5 an hour |
| Cover | All prey species | At least 20% of the home range has cover (reeds, shrubs, logs, deep water) | Predation chance doubles and wariness rises |
| Oxygen | Fish, tadpoles, larvae | Dawn oxygen above the species' `oxygen_min_mg_l` (brook trout 7, sculpin and dace 5, bluegill and bass 3, tadpoles 3) | −5 an hour from 2–3 mg/L; below 2, 10% die each hour |
| Clean water | Crayfish, eggs, larvae | Turbidity and nitrate inside the limits in the Additions tab | Eggs fail; adults leave or die |

**What condition changes.**

| Condition | Label in the journal | Effect |
| --- | --- | --- |
| 70–100 | Thriving | Full breeding; normal speed; quick to flee |
| 40–70 | Stressed | Half breeding; more time spent foraging |
| 1–40 | Weak | No breeding; slower; caught by predators more often |
| 0 | — | Dies; the cause is logged (wild animals only). An animal in care never dies: below 20 condition, the state wildlife vet collects it, or its owner program takes it back, and you lose 10 reputation |

Looking at a mob through binoculars shows its condition label. The journal page for each population shows the average condition and the need that is dragging it down, so the player always knows what to fix.

## Life cycle and breeding

Every species moves through stages, and each stage has its own needs. Times are game time: one year is 28 days, one season 7.

| Species | Breeds when | Young per breeding | Stages | Becomes adult | Lifespan |
| --- | --- | --- | --- | --- | --- |
| Green frog | Spring to summer, water above 18 °C | 40 eggs per female (25% become froglets) | Eggs 2 days → tadpoles 4 days → froglet | 1 year | 4 years |
| Spring peeper | Early spring, after warm rain on nights above 5 °C | 15 eggs | Eggs 1 day → tadpoles 3 days → froglet | Next spring | 3 years |
| Spotted salamander | Big Night | 1 egg mass of 50 | Eggs 3 days → larvae through midsummer (the pool must last that long) → juvenile | 2 years | 20 years |
| Painted turtle | Late spring; nests on sandy banks | 6 eggs | Eggs 10 days → hatchling | 3 years | 30+ years |
| Bluegill | Summer; males guard round nests | 30 fry survive per nest | Eggs 1 day → fry, guarded 2 days | 1 year | 6 years |
| Common green darner | Summer | Eggs laid in plants | Larva underwater for 1 year → emerges late spring | Right away | One summer as an adult |
| Eastern bluebird | Spring and summer, 2 broods | 4–5 eggs | Eggs 2 days → nestlings 3 days → fledgling | Next spring | 6 years |
| White-tailed deer | Fawns in early summer | 1–2 | Fawn hides alone for 1 season | 1 year | 8 years |

**Pulse model numbers.** Each species' data file sets these fields for the pulse model in Wildlife rules (Systems spec). Starting values:

| Species | young\_per\_female | young\_survival\_fraction | years\_to\_adult | adult\_survival\_per\_year |
| --- | --- | --- | --- | --- |
| Green frog | 40 | 0.25 | 1 | 0.6 |
| Spring peeper | 15 | 0.2 | 1 | 0.5 |
| Wood frog | 30 | 0.1 | 1 | 0.5 |
| Spotted salamander | 50 | 0.08 | 2 | 0.85 |
| Red-backed salamander | 8 | 0.4 | 2 | 0.8 |
| Dusky salamander | 15 | 0.2 | 1 | 0.7 |
| Brook trout | 100 | 0.04 | 1 | 0.5 |
| Painted turtle | 6 | 0.15 | 3 | 0.9 |
| Box turtle | 4 | 0.1 | 3 | 0.97 |
| Eastern bluebird | 9 (2 broods) | 0.5 | 1 | 0.5 |

**Breeding triggers** are tied to real cues: warm rain and water temperature for amphibians, day length for birds, water temperature for fish. A pond that warms early gets the first frogs.

**Parental care.** Bluegill guard nests and chase off small fish. Bluebirds feed nestlings, so they need insects within 50 m. Deer leave fawns hidden in tall meadow. Mowing within 3 blocks of a hidden fawn stops the mower; the fawn bolts to the woods edge and the HUD says 'Fawn in the grass'. Mowing that meadow in early summer costs 1 reputation. The scythe and mower will not cut within 3 blocks of an active ground nest; the HUD says 'Nest in the grass'.

**Individuals and populations.** Mobs near the player carry their own age, sex and condition. When they fold back into the population count, those numbers average back in. The population model is the *pulse model in Wildlife rules (Systems spec)*.

## Movement, territory and winter

**Home ranges.** Each mob stays within its range of a home habitat. Ranges explain where to protect land, not just water.

| Species | Home range |
| --- | --- |
| Green frog | 20 blocks around its pond |
| Spotted salamander | Up to 150 blocks into the woods from its breeding pool, so the woods around a vernal pool matter as much as the pool |
| Painted turtle | 100 blocks; nests up to 50 blocks from water |
| Eastern bluebird | 50 blocks around its box |
| White-tailed deer | The whole plot and beyond |
| Great blue heron | Visits from off the plot |

**Dispersal.** Each summer, 10% of juveniles leave home to look for new habitat. That is how a new pond gets colonized from the ponds you already have, not only from off the plot.

| Mover | How far | How |
| --- | --- | --- |
| Dragonflies | 500 blocks | By air; they find new ponds first |
| Frogs | 30 blocks | Only over wet ground (corridors) |
| Salamanders | 200 blocks | Only through woods |
| Turtles | 100 blocks | Overland, any cover |

**Territories.** Some species won't crowd, so habitat shape sets their numbers, not only habitat size.

- Green frog males need one calling site per 6 blocks of shoreline. A wiggly shore holds more frogs than a round pond of the same area.
- Bluebird pairs need boxes at least 50 blocks apart. Boxes placed closer get tree swallows instead.
- One red-winged blackbird male per 50 m² of reed bed.
- One bluegill nest per 2 m² of shallow, gravelly bottom.

**Winter.** Each species needs a place to get through winter. If none exists, it leaves or dies.

| Species | Overwinters in |
| --- | --- |
| Frogs, painted turtles | Mud at the bottom of ponds at least 1 block deep that don't freeze solid |
| Salamanders, box turtles | Underground or under leaf litter in woods |
| Bats | Big brown bats: the old chimney or the RV awning. Eastern red bats: deep leaf litter in the woods. Tri-colored bats: leave for caves off the plot each fall and return in spring if summer roosts remain. Bat boxes are summer roosts only |
| Dragonfly larvae | Ponds that don't dry out |
| Most songbirds | They migrate and return in spring |
| Deer | Woods with shrubs to browse |

## Population health and death

**Genetic diversity.** Each population has a diversity value from 0 to 1.

- It drops 2% a season when the population stays below 10 and no others reach it through a corridor. Applies only to species flagged `isolated` in their data file (pond amphibians, fish above the barrier). Birds, mammals and insects are exempt.
- Arrivals through dispersal raise it, and so does releasing lab-raised stock from another population (with a permit).
- Below 0.5, breeding success falls 30% and the population gets sick more easily.
- A DNA swab tested in the lab shows the value on the species' journal page.

This makes linking ponds matter, not just building them.

**Disease.** A population has an infected share.

- It spreads faster when a habitat is crowded.
- It moves between habitats through water links, corridors and the player's unwashed nets and boots.
- Infected mobs show signs the player can learn to spot: sluggish movement, skin patches, reddened legs.
- The lab confirms it, and the treatment station or drying out a fish pond clears it. (See *Invasive species and disease* in the Additions tab.)

**Causes of death.** Mobs only die from the simulation, never from the player. Player actions can lower habitat quality, which changes the simulation's numbers, but no individual animal ever dies from a direct player action.

| Cause | Typical trigger |
| --- | --- |
| Predation | Low cover; a heron or bass nearby |
| Starvation | Food base below demand |
| Drying | A pond or pool dries before the young finish growing |
| Freezing | No proper overwintering site |
| Heat | Shallow, unshaded water above 35 °C |
| Low oxygen | Algae bloom; dawn crash |
| Disease | Chytrid, ranavirus |
| Old age | Past its lifespan |
| Road | Crossing the driveway on Big Night |

Every death is counted by cause. Each population's journal page has a chart of deaths by cause per season, so the player learns what is actually killing their animals and can fix that one thing.

## Named animals

Long-lived individuals become characters the player follows (see *Fun, feel and finish*).

**Who qualifies.** Any long-lived species (turtles, hellbenders, herons, large trout, owls, bears) seen 3 times in its home habitat. Also every tagged animal, and the Big Rock hellbender from day 1.

**The same animal every time.** A named animal keeps one permanent ID. When the player comes near its home, that same individual spawns instead of a random one, with its size, marks and favorite perch. It never folds into the anonymous count.

**Habits.** It keeps a home spot (a perch, a rock, a basking log) and a daily schedule, so players learn where and when to find it.

**Protection.** Named animals can be caught for tagging and health checks, but never taken as breeding founders or moved off their home habitat.

**Fate.** When a named animal dies, the cause comes from the same death rules as any other mob. The Chronicle records it, and the journal page keeps its history.

## Perception

Every wildlife mob notices the player through four senses. Each sense has its own range, set per species in its data file.

**Sight.** A cone in front of the animal. Prey animals with eyes on the sides of their head (deer, rabbits, most birds) see almost all the way around. Predators (owls, herons, snakes) see a narrower cone but farther. Sight needs a clear line through the blocks, and plants block it, so approaching behind shrubs works. Movement is seen at full range; a still, crouched player is seen at half range.

**Hearing.** Every player action makes a *noise* with a radius:

| Action | Noise radius in blocks |
| --- | --- |
| Sneaking | 2 |
| Walking | 4 |
| Walking on built trail tread (Landscaping and terraforming → Trail craft) | 3 |
| Running | 10 |
| Wading | 6 |
| Digging or raking | 12 |
| Tamping | 16 |
| Drone landing | 16 |
| Generator, mini excavator | 32 |

An animal inside a noise radius hears it unless rain or the creek masks it. Rain halves all noise; the Run masks noise within 6 blocks of it.

**Smell.** Mammals (deer, bear, fox) smell the player downwind out to 3× their sight range. The weather already tracks wind direction, so approaching a deer with the wind in your face lets you get close. The HUD's weather icon shows the wind direction.

**Vibration.** Fish and frogs feel footsteps on soft banks within 4 blocks. Sneaking cancels it.

## The reaction ladder

When a mob notices the player, it climbs a ladder of reactions. Each step has a visible tell, so the player learns to read animals and back off in time.

| Step | Tell | What the player can do |
| --- | --- | --- |
| Unaware | Feeding, calling, normal movement | Keep approaching slowly |
| Alert | Head up and still; ears forward (mammals); a frog stops calling; a bird's alarm chip; a deer stamps or flags its tail | Stop and crouch. If nothing else happens for 5 seconds, it drops back to unaware |
| Flee | Runs, flies, dives or hops to its preferred cover | Nothing; let it go |
| Hide | Out of sight in cover for 1–5 minutes (by species) | Stay still and quiet nearby; it may come back |
| Return | Eases back out and resumes what it was doing | Watch from where you are |

Wariness (habituation) decides how fast a mob climbs. A calm-habituated animal can go from alert straight back to unaware.

**Alarm spreads.** One animal's alarm call or flight alerts others of its kind within 16 blocks, and some other species too: squirrels and birds react to each other's alarms. A frog chorus falls silent within 20 blocks of one frog's alarm, and starts again from the edges inward a minute later.

## Movement by body type

| Body type | Examples | How it moves |
| --- | --- | --- |
| Walkers | Deer, bear, fox, turtles, salamanders | Walks the terrain; deer cross water up to 2 levels deep and jump 1 block; turtles and salamanders climb half-block steps only |
| Hoppers | Frogs, toads, rabbits | Short hops; frogs leap toward water when alarmed, then swim to the bottom |
| Swimmers | Fish, tadpoles, newts, hellbenders | See the Fish mechanics tab |
| Fliers | Birds, bats, dragonflies, butterflies | Fly between perches. Perches are tree branches, snags, posts, boxes, reed tops and the RV roof. Each species has preferred perch heights. Dragonflies fly patrol loops over open water |
| Climbers | Squirrels, rat snakes, tree frogs | Move up tree trunks and along branches |
| Burrowers | Moles, woodchucks, burrowing crayfish | Mostly unseen; their mounds and burrows appear and change |

**Paths follow cover.** Prey animals choose paths that stay near cover. Each column's path cost goes up with openness and down with cover. A deer crossing Bench Field takes the hedgerow, and a salamander crossing to the vernal pool takes the leafy route. Hedgerows, brush piles and corridors change real movement, not just a number.

## Predators and prey near the player

Predation runs as numbers everywhere (Wildlife rules). Near the player, some of it plays out as visible hunts:

- **When.** Each minute, a predator entity with suitable prey within its hunting range rolls a chance to hunt, based on how hungry it is and the time of day.
- **Odds.** Success depends on cover: prey in thick cover or deep water escapes more often. Typical odds are a heron strike 1 in 3, a hawk stoop 1 in 4, a bass lunge 1 in 2.
- **What the player sees.** A short, clear sequence: the stalk, the strike, then either the catch or the escape. A catch shows only a splash, a puff of feathers or a ripple, never anything graphic.
- **Afterward.** The prey is removed from its population, and the predator rests.
- **The player can't step in,** but a hunt seen near the player is often a field moment.

These visible hunts are why cover matters in-game. A pond with a reed edge shows herons missing; a bare pond shows them succeeding.

## Weather and groups

**Weather.**

| Weather | What wildlife does |
| --- | --- |
| Warm rain, especially at night | Amphibians come out everywhere; worms surface; birds shelter |
| Heavy storm | Everything shelters; bats and birds stay in |
| Wind above 25 km/h | Insects stay low, so swallows and bats feed low; deer more nervous (they can't hear well) |
| Cold below 5 °C | Reptiles and amphibians hide; birds flock and feed hard |
| Heat above 30 °C | Midday rest in shade; activity shifts to dawn and dusk; trout to refuges |
| Fog | Birds quieter; sight ranges halve, for animals and player alike |

**Groups.**

| Group | Species | Behavior |
| --- | --- | --- |
| Herd | Deer | Usually one adult watches while others feed; if it flees, all flee |
| Flock | Turkeys, swallows, chickadees | Feed together; mixed winter flocks of chickadees, titmice and woodpeckers move through the woods as one |
| School | Dace, bluegill | See the Fish mechanics tab |
| Chorus | Frogs, katydids | Call in sync and answer each other; go silent together |
| Colony | Chimney swifts, bats | Leave and return together at dusk and dawn |

## Animation, sound and detail levels

**States.** Every mob animates the same set of states: idle, move, forage, alert, flee, hide, call, rest, plus species extras such as a heron strike, a turtle retracting into its shell, or a trout rising. Each state has a sound set: calls for idle and call, alarm sounds for alert and flee, nothing while hiding.

**Detail by distance.**

| Distance from player | Detail |
| --- | --- |
| Within 16 blocks | Full animation, every behavior goal, all sounds |
| 16–48 blocks | Simple animation; goals checked twice a second; calls only |
| Beyond 48 blocks | Not spawned; part of the population numbers |

**Readability.** Animals use slightly brighter, more saturated colors than the land around them, and a small color pop on the alert tell, so the player can read a deer's raised head or a frog's freeze at a glance. This follows the readability rules in *Fun, feel and finish*.

## Handling

The player can't hurt any mob. Attacking does nothing except startle the animal, and there are no weapons. Interaction is watching, catching, moving and photographing.

| Action | Tool | How it works |
| --- | --- | --- |
| Net | Dip net | Sneak within 3 blocks and time the swing while the mob is still. Works on fish, tadpoles, frogs, insects and crayfish |
| Catch by hand | Gloves (wet gloves for amphibians) | Sneak within 1 block of a resting turtle, frog or salamander. Without wet gloves, amphibians lose health from dry skin (never below 10; see Stress timer) |
| Carry | Bucket (water animals) or ventilated cup (insects, small frogs) | One animal per container, vanilla bucket style; holding it starts the stress timer |
| Release | Right-click water or ground | Released into the wrong microhabitat, the animal moves to the nearest suitable column within 32 blocks, or the release is refused and the HUD says why. It never dies from a release |
| Lab intake | Quarantine tank | Carrying an animal into the RV moves it into a tank and stops the stress timer |
| Photograph | Camera | Frame the mob and hold still for a sharp shot. Photos fill journal pages and some contracts ask for them |

**Stress timer.** A caught animal has 60 seconds in a hand or net, or 5 minutes in a bucket or cup, before it starts losing health. Heat, dry gloves and rough carrying shorten it. Releasing an animal before the timer runs out costs nothing. Health never drops below 10 while handled. At 10, the animal wriggles free and returns to the nearest suitable column; its condition drops 10 and the journal notes the stress.

**Protected species** (marked in the journal) can only be handled with a permit from the *Field biologist* tier. Without one, the net and gloves won't work on them, and the HUD says why.

## Southern Appalachian forest roster (start biome)

| Mob | Id | Lives on | Active | Behavior | Interaction |
| --- | --- | --- | --- | --- | --- |
| Spring peeper | `spring_peeper` | Reed bed, woods near ponds | Night, early spring | Tiny; loud chorus; climbs reeds | Call survey; hard to spot |
| Green frog | `green_frog` | Shallows, reed bed | Day and night | Sits on banks; jumps in with a squeak | Net or hand |
| American bullfrog | `bullfrog` | Open water 1 block deep or more | Night | Eats small frogs; deep call | Net; big and strong |
| American toad | `american_toad` | Meadow, garden, woods | Night, after rain | Walks more than it hops; breeds in shallow pools | Hand; harmless |
| Spotted salamander | `spotted_salamander` | Woods with log piles; vernal pools to breed | Rainy nights | Hides under logs; Big Night migration | Hand with wet gloves |
| Red-spotted newt | `red_spotted_newt` | Ponds; red juveniles walk the woods | Day | Juveniles roam land for years, then go back to water | Net |
| Tadpoles | `tadpole` (own entity; vanilla tadpole model as a visual base only) | Shallows | Always | Schools; grows legs | Net |
| Eastern box turtle | `box_turtle` | Woods edge, wet meadow | Day, after rain | Slow; eats berries and mushrooms; closes its shell | Hand; protected |
| Painted turtle | `painted_turtle` | Open water with basking logs | Day | Basks in rows; slides off when approached | Net or hand |
| Northern water snake | `water_snake` | Pond and stream edges | Day | Hunts fish and frogs; basks on brush | Watch only |
| Bluegill | `bluegill` | Open water | Always | Guards round nests in the shallows in summer | Net |
| Largemouth bass | `largemouth_bass` | Ponds of 100 m² or more | Always | Top fish predator; eats tadpoles | Net; can be stocked with a permit |
| Creek chub | `creek_chub` | Spring-fed streams | Always | Schools in pools; builds gravel mounds | Net |
| Crayfish | `crayfish` | Spring-fed gravel | Night | Walks backward under stones | Net or hand |
| Common green darner | `green_darner` | Open water | Day | Patrols; hovers; lays eggs in plants | Net; photograph |
| Ebony jewelwing | `ebony_jewelwing` | Shaded streams | Day | Black wings; flutters along the water | Photograph |
| Monarch butterfly | `monarch` | Meadow with milkweed | Day, summer to fall | Lays eggs on milkweed; migrates in fall | Photograph; count |
| Great blue heron | `great_blue_heron` | Shallows | Dawn | Wades and strikes; flies off at 16 blocks | Watch |
| Belted kingfisher | `belted_kingfisher` | Streams and ponds with perches | Day | Rattling call; dives for small fish | Watch |
| Wood duck | `wood_duck` | Ponds near woods | Dawn and dusk | Nests in tree holes or boxes | Nest box monitoring |
| Red-winged blackbird | `red_winged_blackbird` | Reed bed | Day, spring | Males sing from reed tops | Watch; count |
| Eastern bluebird | `eastern_bluebird` | Dry meadow | Day | Perches low and drops onto insects | Nest box |
| Pileated woodpecker | `pileated_woodpecker` | Woods with snags | Day | Carves big holes; loud drumming | Watch |
| Barred owl | `barred_owl` | Woods near water | Night | "Who cooks for you" call; hunts frogs and mice | Call survey |
| Big brown bat | `big_brown_bat` | Over ponds; roosts in bat boxes | Dusk | Hunts insects over water | Bat detector |
| White-tailed deer | `white_tailed_deer` | Woods edge | Dawn and dusk | Herd; drinks at ponds; makes trails | Watch; camera trap |
| River otter | `river_otter` | Connected streams and ponds | Dawn and dusk | Plays; eats fish and crayfish | Watch; rare |
| Beaver | `beaver` | Streams with willow or alder | Night | Builds dams (see *Additions*) | Watch; camera trap |
| Muskrat | `muskrat` | Marsh, cattail ponds | Dusk | Burrows in banks; builds lodges | Watch |
| Eastern cottontail | `cottontail` | Meadow, brush piles | Dawn and dusk | Freezes, then bolts | Watch |
| Gray fox | `gray_fox` | Woods | Night | Climbs trees; hunts mice | Camera trap |

**Ambient (particle swarms, not entities):** fireflies, mosquitoes, midges, bees, mayfly hatches.

**Appalachian species.** These join the roster above. Warm-water species such as bullfrogs, bass and bluegill live only in the old farm pond and other warm, low ponds.

| Mob | Id | Lives on | Active | Behavior | Interaction |
| --- | --- | --- | --- | --- | --- |
| Eastern hellbender | `hellbender` | Cold, clear streams with large flat rocks | Night | North America's largest salamander; lives under one rock for years; needs clean cobble and cold water; no on-plot breeding is modeled; exempt from the diversity rule | Agency contracts only; protected |
| Brook trout | `brook_trout` | Cold spring-fed streams below 20 °C | Always | Holds in pools; spawns on clean gravel in fall | Net; needs stream access past the culvert |
| Dusky salamander (Desmognathus) | `dusky_salamander` | Seeps and stream edges | Night and wet days | Hides under rocks at the water's edge | Hand with wet gloves |
| Red salamander | `red_salamander` | Springs and seeps | Night | Bright red; larvae live in spring water for years | Hand with wet gloves |
| Eastern red-backed salamander | `red_backed_salamander` | Woods floor under logs | Night | Lives entirely on land and lays eggs in damp logs; no pond needed | Cover boards |
| Black bear | `black_bear` | Woods and berry patches | Dawn and dusk | Shy; eats berries and acorns; opens unsecured bins and feeders | Watch from far off; bear-proof bin |
| Wild turkey | `wild_turkey` | Woods edge, meadow | Day | Flocks scratch leaf litter for acorns | Watch; camera trap |
| Ruffed grouse | `ruffed_grouse` | Young, thick regrowth | Day | Males drum on logs in spring | Needs young woods, kept by land management |
| Louisiana waterthrush | `waterthrush` | Clean rocky streams | Day, spring to summer | Bobs its tail; nests only where stream insects are healthy | Count; a sign of clean water |
| Mottled sculpin | `mottled_sculpin` | Riffles under clean cobble | Night | Sits still on the bottom and darts between stones; the male guards eggs under a rock in spring | Kick net; rock flip |
| Blacknose dace | `blacknose_dace` | Riffles and runs | Day | Small schools; the first fish into a restored reach | Seine; minnow trap |
| Rainbow trout (non-native) | `rainbow_trout` | Downstream of the plot; the lower Run if the culvert is open with no barrier | Always | Spawns in spring; crowds out brook trout | Count at the outlet |
| Wood frog | `wood_frog` | Woods; breeds in the vernal pool | Early spring nights | Calls like quacking ducks; freezes solid in winter and thaws | Hand with wet gloves |
| Common snapping turtle | `snapping_turtle` | Miller Pond mud | Day and night | Rarely basks; ambushes from the mud | Lift from behind with gloves (see Nuisance mobs) |
| Eastern garter snake | `garter_snake` | Field and pond edge | Day | Hunts frogs and worms | Cover boards; watch |
| Northern ring-necked snake | `ring_necked_snake` | Woods, under logs and boards | Night | Small and secretive | Found only by lifting cover |
| Eastern rat snake | `eastern_rat_snake` | Woods and Homeplace | Day | Climbs trees and walls; eats eggs and mice | Watch; baffles on box poles |
| Timber rattlesnake | `timber_rattlesnake` | Raven Rock; dens in its cracks | Day, spring to fall | Lies still for days; rattles and holds still within 3 blocks; never strikes | Watch from more than 3 blocks; protected |
| Five-lined skink | `five_lined_skink` | Logs and rock piles in sun | Day | Young have bright blue tails | Hand; photograph |
| Eastern fence lizard | `fence_lizard` | Raven Rock, sunny logs | Day | Does push-ups to show off | Photograph |
| American woodcock | `american_woodcock` | Field edge beside wet young woods | Dusk, spring | Sky dance over the field edge | Count displaying males |
| Chimney swift | `chimney_swift` | The old chimney | Day, spring to fall | Colony pours into the chimney at dusk | Roost count |
| Eastern phoebe | `eastern_phoebe` | Ledges at the Homeplace, culvert and awning | Day, spring to fall | Wags its tail; returns to the same nest each spring | Watch; nest check |
| Carolina wren | `carolina_wren` | Homeplace brush and woodpiles | Day, all year | Loud song from cover; nests in crevices | Watch |
| Red-tailed hawk | `red_tailed_hawk` | Raven Rock and the ridge sky | Day | Soars; stoops on rabbits and squirrels | Watch; hawk count |
| Common raven | `common_raven` | Raven Rock | Day | Croaks; tumbles in the air | Watch |
| Bobcat | `bobcat` | Rocky slopes | Dusk and night | Rarely seen; hunts rabbits and squirrels | Camera trap |
| Mink | `mink` | Hemlock Run banks | Night | Hunts fish and crayfish along the banks | Camera trap; tracks |
| Woodchuck | `woodchuck` | Bench Field edge | Day | Digs burrows that other animals reuse | Watch |
| Eastern chipmunk | `eastern_chipmunk` | Woods and stone walls | Day | Buries nuts; chips an alarm | Watch |
| Gray squirrel | `gray_squirrel` | Woods | Day | Buries acorns and hickory nuts, planting trees | Watch (see Nuisance mobs) |
| Southern flying squirrel | `southern_flying_squirrel` | Old woods with cavities | Night | Glides between trees | Thermal camera; night feeder |
| Raccoon | `raccoon` | Everywhere near water | Night | Raids nests and bins | Camera trap (see Nuisance mobs) |
| Virginia opossum | `virginia_opossum` | Everywhere near water | Night | Plays dead when startled; raids nests | Camera trap |
| Eastern red bat | `eastern_red_bat` | Over the pond and the Run; roosts in leaves | Dusk | Hunts insects; winters in deep leaf litter | Bat detector |
| Tri-colored bat | `tricolored_bat` | Over the pond and the Run | Dusk | Small; hit hard by white-nose syndrome; needs mayfly hatches | Bat detector |
| Feral hog (invasive) | `feral_hog` | Wet areas; oak woods in mast years | Night | Roots up seeps and stream banks | As in the Additions tab |
| Canada goose | `canada_goose` | Miller Pond banks | Day | Grazes banks bare; hisses near its nest | See Nuisance mobs |

**Ambient birds.** Songbirds without rows (wood thrush, warblers, ovenbird, towhee, field sparrow, indigo bunting, green heron, turkey vulture, chickadee, titmouse, tree swallow, killdeer) are sound and particle "ambient birds" driven by habitat numbers, not entities.

**Held for later biomes.** Elk (`elk`) needs large meadows, which the cove plot does not have, so it is not on the start roster.

## Other biome rosters

Each biome gets its own full roster at the level of detail above when it is built. These are the core mobs that define each one.

**Pacific Northwest rainforest**

| Mob | Lives on | Behavior | Interaction |
| --- | --- | --- | --- |
| Rough-skinned newt | Ponds, wet woods | Walks openly by day; toxic, so nothing eats it | Hand with wet gloves |
| Northern red-legged frog | Cold ponds | Breeds in winter rain | Net; protected |
| Pacific chorus frog | Any wet ground | Loud "ribbit" chorus | Call survey |
| Coho salmon | Gravel streams | Swims upstream in fall; spawns in gravel; dies after | Watch; count redds (nests) |
| Cutthroat trout | Cold streams | Holds behind rocks | Net; needs cold, clear water |
| American dipper | Fast streams | Walks underwater for insects; bobs on rocks | Watch |
| Banana slug | Moss, logs | Slow; eats fungi and leaves | Hand |
| Roosevelt elk | Meadow and woods | Big herds; bugles in fall | Watch from far off |

**Tropical rainforest**

| Mob | Lives on | Behavior | Interaction |
| --- | --- | --- | --- |
| Strawberry poison frog | Forest floor, bromeliads | Carries tadpoles to bromeliad cups | Vivarium only; permit |
| Red-eyed tree frog | Leaves over ponds | Lays eggs on leaves above water | Hand with wet gloves |
| Glass frog | Leaves over streams | See-through belly; guards eggs | Photograph |
| Spectacled caiman | Slow streams | Basks; shy; stays away from the player | Watch only |
| Convict cichlid | Streams | Pairs guard fry fiercely | Net |
| Blue morpho | Forest edge | Flashing blue wings | Photograph |
| Keel-billed toucan | Canopy | Eats fruit | Watch |
| Mantled howler monkey | Canopy | Roars at dawn | Watch; call survey |

**Desert springs**

| Mob | Lives on | Behavior | Interaction |
| --- | --- | --- | --- |
| Desert pupfish | Spring pools | Males turn blue and guard spots | Net; protected; the biome's key species |
| Desert tortoise | Desert washes | Digs burrows; drinks after rain | Watch only; protected |
| Red-spotted toad | Wash pools | Breeds in rain pools | Hand |
| Couch's spadefoot | Flash-flood pools | Comes out only after summer storms; tadpoles finish in 8 days | Watch the race against drying |
| Vermilion flycatcher | Cottonwoods by water | Bright red; catches insects in the air | Watch |
| Gambel's quail | Brush | Coveys run across open ground | Watch |
| Coyote | Everywhere at night | Howls; shy; drinks at springs | Camera trap |

**Northern lakes**

| Mob | Lives on | Behavior | Interaction |
| --- | --- | --- | --- |
| Common loon | Large clear lakes | Dives for fish; haunting call | Watch; needs a quiet shore to nest |
| Wood frog | Woods, vernal pools | Freezes solid in winter and thaws | Hand |
| Northern pike | Weedy lakes | Ambushes from weeds | Net |
| Moose | Bogs and lakeshores | Feeds on water plants | Watch from far off |
| Snowshoe hare | Woods | Turns white in winter | Camera trap |
| Sandhill crane | Bogs, meadows | Dancing displays in spring | Watch |
| Gray wolf | Whole area, rare | Shy; never approaches the player; heard more than seen | Tracks and howls only |

## Nuisance mobs

Nuisance mobs are trouble without combat. They never deal damage; they cost time, energy, eggs or plants. The player handles them by planning ahead, not by fighting.

| Mob | What it does | How to handle it |
| --- | --- | --- |
| Raccoon | At night, digs up turtle nests, raids bird boxes and opens unsecured bins at the RV | Nest cages, baffles on box poles, a latched bin. A camera trap shows who did it |
| Canada goose | Grazes pond banks bare (erosion), adds droppings (phosphorus) and hisses, spreads its wings and walks at you if you go near its nest, stopping 3 blocks away; no contact or push | Tall plants along the bank (geese avoid cover they can't see over); give the nest room in spring |
| Snapping turtle | Hauled out on land, if you try to lift it from the front, it hisses and the pickup fails (HUD: 'Lift it from behind'); nothing is dropped | Lift it from behind with gloves, or leave it; it is a sign of a healthy pond |
| Mosquito swarm | Rises from still water without predators in warm weather. Salamander larvae, tadpoles, dragonfly larvae and small fish each cut it by half; bats and swallows overhead cut it again. Standing in a full swarm uses energy 1.5× faster. A vernal pool with salamander larvae never swarms | Bats, dragonflies and small fish eat them; keep water moving; bat boxes |
| White-tailed deer | Eats nursery trays and fresh plugs | Fence the nursery; plant out at once |
| Beaver | Cuts down trees you wanted to keep | Wire guards on trunks |
| Gray squirrel | Steals seed from trays and feeders | Covered trays |

Nuisance mobs keep the land from being a static garden. Each one rewards an informed fix rather than reflexes.

**Beavers and beaver dam analogs.** A beaver whose home range holds a beaver dam analog (Landscaping and terraforming → Beaver dam analogs) adopts it instead of starting a new dam. It patches storm damage itself (+25 condition a night), so the player no longer has to. It raises the crest 1 layer a night until water backs up 1 block, and on the Run it plugs the fish notch within 3 nights. The adopted structure becomes a `beaver_dam` and follows the beaver rules in Additions. Trout still pass it on days with 40 mm of rain or more, when water spills over the top, as they pass real beaver dams at high flow. A beaver's work never costs reputation.

## People

A few human NPCs make the station feel connected to the world. They come and go on schedules and never stay overnight.

| Person | Id | Comes when | What they do |
| --- | --- | --- | --- |
| Delivery driver | `delivery_driver` | Right after a shop order | Comes only for oversized orders (mini excavator, plate compactor, trailer lab). Everything else arrives by drone |
| Agency biologist | `agency_biologist` | Once a season, or after a fine or a protected-species report | Walks the plot with you, checks permits and gives a short review that changes reputation |
| University researcher | `researcher` | When a university contract starts | Sets out gear, asks for samples and pays on handoff |
| Neighbor farmer | `neighbor_farmer` | When an upstream event happens or an agreement is offered | Talks at the fence line; agrees to buffer strips for cash or reputation |
| Volunteer crew | `volunteer` | Big Night, a BioBlitz, a planting day, spring cleanup day (The cove), or an invasive pull you post on the computer | 2–4 helpers carry salamanders, plant plugs or pull weeds for a few hours |
| Old-timer (Arlo Haskett, fictional) | `old_timer` | Day 3 of each season, 10:00–12:00, from v0.5 | Walks up to 3 stops and tells one story at each; no quests, trades or needs (The cove → The old-timer) |

People speak through short dialogue panels in the same clean style as the screens. They are built on vanilla villager AI with trading turned off.

**Volunteers (`fieldstation:volunteer`).** One visitor entity serves the volunteer crew and the BioBlitz volunteers, and guided-walk visitors can reuse it later. At most 8 are on the plot at once, counted outside the 64-entity wildlife cap. They walk only on paths, built trail tread and mown ground. They arrive and leave by van at the RV. They have no combat, no trading and no needs.

## Minecraft implementation

**Entity classes.** One base class, `FieldMob`, holds the shared goals, senses, wariness, schedule and handling. Each species is a data file (JSON) that sets its goals, ranges, active hours, size, sounds and microhabitat tags. New species are added through data, without new code. Only unusual behavior (beaver dam building, salmon spawning runs) gets its own Java.

**Mystery and sketch fields.** Each species data file also carries `mystery` (bool, default false), `detect_chance_per_visit` (0–1), `clue_methods` (a list of clue method ids) and `sketch_time_s` (the sketchbook fill time, 10–30). They are set for the 11 mystery species in Systems spec → Mystery records: `mink`, `tricolored_bat`, `southern_flying_squirrel`, `bobcat`, `ring_necked_snake`, `timber_rattlesnake`, `ruffed_grouse`, `american_woodcock`, `barred_owl`, `red_salamander`, `mottled_sculpin`.

**Vanilla mobs reused.** Vanilla mobs are used where they fit: frog (as a base for green frog visuals), turtle (painted turtle swap), salmon, rabbit, fox, bee, bat and wolf. The vanilla tadpole model is a visual base only for our own `fieldstation:tadpole`. All of them get the FieldMob goals added and their vanilla spawn entries removed from the plot. All vanilla target and attack goals are removed. Predation runs only through the Hunt goal.

**Spawning.** No mob spawns from vanilla rules on the plot. The ecology system spawns a mob near the player only when its habitat record has a population and the column's microhabitat tag matches. When the player leaves, the mob folds back into the population count. Off the plot, in normal biomes, vanilla passive animals spawn as usual, and hostile mobs never do.

**Caps.**

| Limit | Value |
| --- | --- |
| Wildlife, fish and insect entities near the player at once | 64 |
| Per species, per habitat, near the player | 4 |
| Fish per pool or pond near the player | 6 |
| Fish in total near the player | 16 (a school counts as 1) |
| People at once | 3 |

**Particle swarms.** Fireflies, mosquitoes, midges, bees and mayfly hatches are particle effects driven by population numbers, not entities. That keeps hundreds of insects cheap to draw and run.

**Models and sound.** Models are simple and clean in the vanilla block style, animated with GeckoLib (in the stack). Each species has a recorded call and an alarm sound, and the ambient soundscape mixes the calls of mobs nearby.

**No drops and no damage.** Mobs have no loot tables and take no damage from the player. They can only die from the simulation itself: starvation, predation, drying, heat, disease or old age. A death shows in the journal with its cause, never as a body to loot.
