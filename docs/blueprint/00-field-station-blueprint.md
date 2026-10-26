# Field Station Blueprint

Oct 18, 2026 · @Ruhan

Working title. Built as a mod for Minecraft: Java Edition. You live in an RV parked on wild land, run a small field lab inside it, and reshape the land into living ecosystems by hand.

Details: Systems spec for rules and numbers, Content registry for every block, item and entity, Milestone 1 plan for the first build.

Start here: Canon (one page). New ideas to pick from: Concept ideas

Living things in depth: Fish mechanics · Insects and bugs · Tame and working animals

What makes it fun, and what finished means: Fun, feel and finish

The starting location in detail: The cove (start location)

Shaping the land: Landscaping and terraforming

Weather in full: Weather and climate

Systems planned after Milestone 1: Additions

Detailed design: Lab and RV · Player and tools · Biomes · Progression and economy

Economy and rules: Economy, crafting and drones · Definitions and rules

Under discussion: Minecraft integration brainstorm · Capture and animal trade

## Concept and pillars

The game takes Mistwall's hands-on paludarium and scales it to real land. The RV is your home and field lab. The land around it is a living ecosystem you dig, flood, plant and stock yourself. Everything you change ripples through water, plants and animals.

**Pillars**

1. **Live there.** You eat, sleep, work and travel from the RV. Day, night, weather and seasons pass whether you act or not.
2. **Shape the land by hand.** Dig ponds, cut streams, build berms, lay rock and plant. Every feature is made from material you move, not placed from a menu.
3. **Water is the backbone.** Rain, springs and slope decide where water goes. Ponds fill, streams run, lakes rise and drain. Water quality decides what can live there.
4. **A real food web.** Plants, insects, fish, amphibians, birds and mammals feed on each other. Populations rise and crash for causes you can trace.
5. **The lab makes the hard things possible.** Some animals and processes need RV equipment: quarantine, incubation, tadpole rearing, water testing, treatment.
6. **Learn by observing.** Field guides, tests, trail cameras and counts reveal the system. Hidden numbers stay hidden until you measure them.
7. **Clean and flush look.** Calm, readable and fast on a laptop.

**Play pillars.** The ranked play pillars in *Fun, feel and finish* settle any conflict between these concept pillars and moment-to-moment play:

1. The land answers back.
2. Respect the player's time.
3. Play, don't follow.
4. Hands-on, never a menu press.
5. Everything readable.
6. Real detail.
7. Calm but not static.
8. Grow without an end.

## Core loops

The loops follow nature's own clocks. Short loops are hands-on work; long loops are watching your changes play out across seasons.

| Loop | Time | What you do | What it feeds |
| --- | --- | --- | --- |
| Moment | Seconds | Dig, carry, pour, plant, net, observe through binoculars | A visible change in the land or an animal |
| Daily | 1 game day | Check traps and cameras, feed animals in the lab, test a pond, tend tadpoles, sleep | Animal health, data, rest |
| Weekly | 7 game days | Build or extend a water feature, release raised animals, order supplies, survey a site | New habitat, bigger populations |
| Seasonal | A season of 7 game days (a year is 28) | Prepare for floods, drought, freezing, breeding runs and migrations | Survival through hard weather; breeding booms |
| Long term | Many seasons | Restore whole sites, earn grants and reputation, move the RV to new biomes | New biomes, species and equipment |

**Money and progress.** Healthy, diverse land earns survey grants and research payments. You also sell native plant plugs and seed, and place captive-bred animals with conservation programs for reputation and science credits. Reputation unlocks permits for rarer species and new land.

**A day at the station.**

1. Wake to the morning briefing on the field tablet.
2. Work the land by hand.
3. Handle the day's event, if the storyteller sends one: a storm warning, a raccoon at the bin, a firefly night.
4. In the evening, survey or care for the lab.
5. Sleep.

Each season ends with the Park Service season report. Each year ends with a year card and a new page in the Chronicle.

## The RV

The RV is home, office and lab in one narrow space. You walk through it in first person, the way you walked the Mistwall fish room.

&#91;embedded content: RV floor plan · cab, living space, field lab\]

The living half has the bed (sleep to skip the night and recover energy), a kitchen and the computer desk. The computer replaces Mistwall's laptop: orders, permits, grants, sales and the field journal. The lab half holds the stations below; you choose which ones fit in your space and power budget.

## Lab dependencies

The lab is what lets you work with sensitive or rare life. Each station unlocks processes the land alone can't support, and each has upkeep. The paludarium becomes the lab's vivarium: a controlled habitat for animals that are too delicate for the outside yet.

| Station | What it does | Needed for | Upkeep and failure |
| --- | --- | --- | --- |
| Water test bench | Tests ammonia, nitrate, pH, oxygen, temperature and turbidity from field samples | Knowing why a pond is failing; permits for sensitive fish | Reagents run out; samples must be fresh |
| Quarantine tanks | Holds new fish and amphibians for 3 days before release (plus a clear swab for amphibians) | Every wild-caught or shipped animal; stops disease spreading to the land | Tanks need water changes and heaters; skipped quarantine can bring chytrid or ich into a pond |
| Vivarium (paludarium) | Humid, heated enclosure with misting | Dart frogs, rare salamanders, newly metamorphosed froglets | Same care as Mistwall: misting, flies, calcium |
| Incubator | Holds eggs at set temperature and humidity | Turtle eggs, some salamander eggs, rescued clutches | Power outage or wrong humidity makes a clutch fail to develop |
| Rearing tubs | Raises tadpoles, fry and larvae safe from predators | Restocking a pond, head-starting rare species | Feeding, water quality, overcrowding |
| Culture rack | Fruit flies, springtails, isopods, worms, daphnia | Food for small amphibians, fry and lab animals | Cultures crash after 7 days unless restarted |
| Treatment station | Medicated baths and salt dips, isolation | Sick animals brought in from the field | Wrong dose costs condition; untreated disease spreads |
| Microscope | Identifies plankton, parasites and algae | Diagnosing disease and water problems | None; needs samples |

**Rule:** the lab is small. Space, power and water in the RV are limited, so you choose which stations to run. Bigger RVs and trailer add-ons raise those limits.

## The land and its water

The land is the paludarium's layered substrate at landscape scale. Each cell of ground has layers you can see when you dig. Water follows physics simple enough to run fast: rain soaks in or runs off, flows downhill, pools in low ground and drains through porous layers.

**Ground layers** (top to bottom): leaf litter, topsoil, subsoil, clay or sand, gravel, bedrock. Clay holds water and makes ponds seal. Sand and gravel drain. Bedrock stops digging.

**Water features you can build**

| Feature | How you make it | What keeps it alive | What lives there |
| --- | --- | --- | --- |
| Rain pond | Dig a basin into clay, or line it with packed clay | Rain and runoff; dries in drought | Frogs, toads, salamander larvae, dragonflies |
| Spring pond | Dig down to where a spring seeps out | Steady cold groundwater | Trout-tolerant species, watercress |
| Stream | Cut a channel downhill from a spring or pond overflow | Flow rate from its source | Crayfish, darters, insect larvae |
| Lake | Dam a valley with an earth berm and spillway | Inflow from streams; spillway keeps the dam safe | Sunfish, bass, turtles, waterfowl |
| Marsh | Flood flat ground a few inches deep, plant reeds | Seasonal high water | Wading birds, rails, nesting ducks |
| Vernal pool | Shallow basin that dries each summer | Spring rain; drying keeps fish out | Fairy shrimp, spotted salamanders |

**Tools and processes:** shovel, pick, wheelbarrow, rake, clay tamper, rock bar, pump, hose, seed spreader, planting tray. A pond is a project: survey, dig, haul spoil, line with clay, shape the edges, plant, then let rain fill it and let it settle.

**Consequences:** a dam without a spillway washes out in a storm. A pond dug into sand drains away. Bare banks erode and cloud the water. A stream cut too steep scours and carries silt downstream.

**In Minecraft:** each block is a 1 m cell, so a cut bank shows the layers as stacked blocks. Water fills in levels of an eighth of a block, so a pond rises and falls visibly. Infinite water sources are turned off on your land; the spring and the rain are the only inputs.

## Ecosystem simulation

The land runs as many small habitats (each pond, stream reach, meadow and woodland patch) linked by water and by animals that move between them. Each habitat tracks a handful of values. Animals near the player are simulated one by one; everything farther away runs as population numbers.

**Two levels of detail**

- **Near the player (within about 60 m):** individual animals with behavior, the same model as Mistwall's creatures. You can watch, net, follow and photograph them.
- **Everywhere else:** populations per habitat with births, deaths, food and migration. When you walk closer, individuals are spawned to match the numbers; when you leave, they fold back in.

**What each habitat tracks**

| Value | Rises with | Falls with |
| --- | --- | --- |
| Water quality (oxygen, nutrients, clarity) | Plants, flow, shade, filter feeders | Runoff, silt, rotting matter, overstocking, heat |
| Plant cover and biomass | Light, nutrients, water depth that fits the plant | Grazing, shade, flooding, drought, frost |
| Insect and plankton food base | Plant cover, leaf litter, warm water | Fish predation, pollution, cold |
| Each animal population | Food, cover, breeding sites, connections to other habitats | Predators, disease, starvation, habitat drying or freezing |

**Food web:** plants and algae → insects, plankton and snails → small fish, tadpoles and salamanders → larger fish, frogs, snakes, turtles, wading birds → top predators such as herons, otters and raccoons. Add fish to a vernal pool and the salamanders vanish; remove predators and grazers strip the plants. These chains are the lessons the game teaches.

**Seasons and weather:** rain fills ponds and floods streams. Drought shrinks water and concentrates waste. Frost kills plants and stops breeding. Spring triggers migrations and breeding runs. Each biome has its own calendar.

**Succession:** bare ground turns to weeds in 3 days, meadow in 7, shrubs in 14 more, and young woods by year 2 (see Land management in Additions), unless you mow, graze or burn it. Ponds slowly fill with silt and plants and become marsh unless you dredge them.

## Animals

Animals come to you in three ways, and each has a hands-on process. Most wild animals arrive on their own once the habitat is right. That is the main reward for good building.

| How they arrive | Process | Example |
| --- | --- | --- |
| Colonize on their own | Build the right habitat, connect it to existing ones, wait for the season | Dragonflies find a new pond in days; frogs arrive in the first spring; herons come once fish are established |
| Relocate from your land | Find them, catch with net or by hand, carry in a bucket or cup, release | Moving tadpoles from a drying puddle to a permanent pond |
| Buy or rescue through the lab | Order or receive, quarantine in the RV, acclimate, release at the right site and season | Restocking native fish; head-starting turtle hatchlings from the incubator |

**Care is mostly habitat care.** You don't feed wild animals daily. You keep water clean, cover planted, and food webs intact. Lab animals are different: they need daily feeding and cleaning, as in Mistwall.

**Observation tools:** binoculars, a trail camera, minnow traps, a dip net, call recordings at night, and a field journal that logs every species you confirm. Counting species is how you earn grants.

**Behavior stays species-true:** frogs call and spawn after warm rain, salamanders migrate on wet nights, fish defend nests, turtles bask and lay eggs in sandy banks, herons hunt shallows, raccoons raid unguarded nests. Each species has a behavior checklist the tester agents verify.

**In Minecraft:** vanilla frogs, tadpoles, turtles and fish give a head start, but their spawning is replaced by pond populations. Hostile mobs never spawn, on your land or anywhere else, so nights are for frog calls, not zombies.

## Biomes

The RV is how you travel between biomes. You start in one, earn reputation, then drive to new land. Each biome changes the climate, the soil, the water features that work, and the species list. Your lab travels with you, so animals can move between biomes only with permits and quarantine.

| Biome | Climate and soil | Water features that suit it | Signature species | Unlock |
| --- | --- | --- | --- | --- |
| Southern Appalachians (start) | Four seasons; steep coves; rocky soil over clay; springs, seeps and summer storms | Spring-fed cove ponds, cold mountain streams, vernal pools, beaver dams | Hellbender, brook trout, spotted and dusky salamanders, spring peeper, crayfish, box turtle, black bear, heron, white-tailed deer | Start |
| Pacific Northwest rainforest | Mild and wet, deep moss, gravel streams | Spring-fed gravel streams, cold lakes | Rough-skinned newt, coho salmon, beaver, banana slug, ferns and mosses | Restore 3 habitats in the start biome |
| Tropical rainforest | Hot, humid, no winter, daily rain | Rain pools, slow blackwater streams, bromeliad cups | Dart frogs, cichlids, caimans, orchids, bromeliads | Lab vivarium and incubator; permits |
| Desert springs | Hot days, cold nights, flash floods, rare water | Spring oases, shaded pools, wash channels | Pupfish, desert tortoise, red-spotted toad, cottonwoods | Water management mastery: keep a spring pool alive through a drought |
| Northern lakes | Long winters, ice cover, short summers | Glacial lakes, bogs, cold rivers | Loons, pike, wood frogs that freeze solid, moose, sphagnum bogs | Late game |

Each biome is a separate plot of land with its own save. Returning to an earlier biome shows how your ecosystems changed while you were away.

## What carries over from Mistwall

The browser prototypes (Mistwall and the Field Station base) become the design reference. Their rules and numbers are ported to Java; the rendering is replaced by Minecraft.

| Prototype piece | Becomes in the mod |
| --- | --- |
| Layered ground grid (topsoil, subsoil, clay, gravel) | Custom soil blocks stacked by world generation |
| Rain, soaking, saturation and runoff math | Finite water levels plus a soil-moisture value per block column |
| Spring source | A spring block that emits water levels on a fixed rate |
| Pond detection and pond populations | An hourly scan of connected water, saved with the world |
| Creature behavior (shoaling, hunting, molting, breeding) | Entity AI goals on each new animal |
| Shovel, tamper, seed bag, sample bottle | Items with right-click and hold actions |
| Computer shop, journal and goals | A computer block with a screen; goals become advancements |
| Lab bench water test | A test bench block that reads a filled sample bottle |
| Mistwall paludarium | The vivarium block in the RV lab |

## Engine: Minecraft

The game is a mod for Minecraft: Java Edition. Minecraft already provides a block world at 1 m per block, first-person movement, inventory, beds, day and night, weather and biomes. The mod adds the systems that turn the land into a working ecosystem.

**Target.** Java Edition 26.x. The current release is 26.3, from 15 September 2026. Versions are now numbered by year and drop, with roughly quarterly drops ([version history](https://www.gameserverkings.com/knowledge-base/minecraft/updating-your-server-version/)). Each milestone pins one version. Milestone 1 pins 26.2: the newest version with a stable NeoForge release (26.2.0.88) and NeoForge builds of Flowing Fluids (1.0.7) and GeckoLib (5.5.6). NeoForge for 26.3 is still beta. Move to 26.3 once Flowing Fluids supports it.

**Loader.** Decided: NeoForge, single loader. It matches the studio's mod standard and Year of Ice, so both mods share conventions, tests and tooling. Toolchain: Java 25, Gradle 9.1 or later, ModDevGradle, NeoForge 26.2.0.88, Mojang names with no remapping. (This was Fabric until October 19; it switched before any code existed) ([loader comparison](https://www.icedfoxstudios.com/tutorials/fabric-vs-neoforge-minecraft-modding-2026/)).

**Engineering spec:** Minecraft development (pin, package map, naming, vanilla replacement, tests, performance, saves, modpack).

**Ready for development:** the checklist at the end of Milestone 1 plan (studio review of October 19: 105 problems fixed, 4 decisions open).

| System | Minecraft building block | How the mod changes it |
| --- | --- | --- |
| Ground layers | New blocks: topsoil, subsoil, red clay and gravel over stone, layered by world generation | One block is one 1 m cell, the same grid the prototype uses |
| Water | Finite water measured in levels, 8 per block. [Flowing Fluids](https://www.curseforge.com/minecraft/mc-mods/flowing-fluids) (LGPLv3; Fabric and NeoForge) is the base or the reference | Adds soil soaking, saturation, springs and evaporation on top |
| Digging and hauling | Shovel and wheelbarrow items; breaking blocks yields soil, clay and gravel | Clay you dig is what seals a pond |
| Ponds and habitats | A server-side scan of connected water blocks every in-game hour, stored in the world save | Same pond detection as the prototype |
| Wildlife | Vanilla frogs and tadpoles reused; new entities for deer, crayfish, herons, dragonflies and native fish, animated with a library such as GeckoLib (confirm 26.x support) | Spawns follow pond populations, not vanilla spawn rules |
| Plants | New grasses, rushes and sedges that spread by moisture | Bare ground goes through succession |
| RV and lab | The RV is a prebuilt structure; lab stations are blocks with their own screens: test bench, quarantine tank, incubator, culture rack, vivarium | Driving to a new biome moves you and the RV to that biome's world |
| Computer | A block with a screen for shop, permits, grants and the field journal | Orders arrive by delivery drone |
| Seasons and weather | Vanilla rain and thunder plus a season cycle | Seasons drive temperature, growth and breeding |
| Sleep and time | Vanilla beds and day cycle | Sleeping skips the night, as in the prototype |
| Testing | Minecraft's GameTest framework on a headless server | Tester agents run scripted tests per system |

**Look.** Blocks already give the clean, flush look. A custom resource pack with flat, soft textures keeps the land calm and readable. Shaders are optional.

**The browser prototype stays** as the design sandbox. Water and population numbers are tuned there in seconds, then the math is ported to the mod.

## Roadmap

Milestone 1 proves the new heart of the game: digging a pond by hand and watching life arrive. The lab and other biomes build on that.

&#91;embedded content: roadmap · 4 milestones, 3 gates\]

The same agent studio from the Mistwall blueprint runs each milestone: a director and lead agent with check-ins, builders per system, and tester agents that guard each gate. Builders work in a Gradle mod project; testers run GameTest suites on a headless server. Mod builds need access to the Minecraft and NeoForge Maven repositories. Builds run on your Mac (Apple Silicon runs Minecraft and Gradle natively). The code lives in a git repository on GitHub, and GitHub Actions builds it and runs the GameTests on every push.

**Release path.** The roadmap diagram's milestones map onto these versions. Every release must pass the GameTests and the stranger test. This table is a summary; release contents live in Fun, feel and finish → Path to a finished game.

| Version | What it adds |
| --- | --- |
| Test slice | Proves the risky technical steps |
| v0.1 | Milestone 1 |
| v0.2 | Field tablet, morning briefing, storyteller, surveys, reputation points and tiers |
| v0.3 | Hemlock Run comes alive |
| v0.4 | Every cove species, the lab, breeding phase 1, science credits and the Research exchange, crafting, the Contracts app with its Orders tab, the field dog |
| v0.5 | The Contracts tab, named animals, the Chronicle, year cards; goats, the pack mule and honeybees |
| v1.0 The Cove | The finished game |

Other biomes and RV travel come after v1.0.

## Open decisions

- [ ] Name of the game. "Field Station" is a working title.
- [x] Size and place of the first plot: decided. 200 × 200 blocks (about 10 acres) in a Southern Appalachian cove.
- [ ] Does the RV move on the map, or only between biomes?
- [x] Food and sleep: decided. A player option: off, comfort (default) or real. Nothing in any mode hurts the player (Player and tools → Food and sleep)
- [x] Selling animals: phase 1 approved. Captive-bred animals are placed with programs for reputation and science credits, with no cash. Cash sales (phases 2 and 3) are decided after playtests.
- [x] Players: decided. Solo only for now; a shared watershed for multiplayer is a later idea.
- [x] Journal numbers: decided. The journal shows the player's survey estimates next to the Park Service allotment for the area, never the simulation's true numbers.
- [x] Villages: decided. No villages or village systems for now.
- [x] Pace: decided. Growth and erosion stay fast, so change shows within a season.
- [x] Moving the RV: decided. It stays parked while you work a plot and drives only between locations (concept in the Lab and RV tab).
- [x] Loader: decided. NeoForge on Minecraft 26.2 (switched from Fabric to match the studio standard).
- [ ] Use Flowing Fluids as a dependency, or write our own finite water from its ideas.
- [x] Vanilla survival: hostile mobs are off everywhere (decided). Also decided: no vanilla hunger; items come from a mix of crafting, drone-delivered purchases and earned reward drops.
- [x] Where builds run: decided. Your Mac, with the code in git on GitHub.

## Sources

- [Minecraft versions and the 2026 numbering change](https://www.gameserverkings.com/knowledge-base/minecraft/updating-your-server-version/)
- [Fabric vs NeoForge 2026](https://www.icedfoxstudios.com/tutorials/fabric-vs-neoforge-minecraft-modding-2026/)
- [Flowing Fluids on CurseForge](https://www.curseforge.com/minecraft/mc-mods/flowing-fluids)
