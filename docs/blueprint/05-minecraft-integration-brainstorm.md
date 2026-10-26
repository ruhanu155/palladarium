# Minecraft integration brainstorm

**Status: most tensions are decided (see the Status column); the rest are still brainstorm.**

The blueprint now describes many systems. This page works out how they fit inside Minecraft before any code is written. Each tension lists options and a *lean*, the option that looks best today. Nothing here is final until a test slice proves it and you agree. Decisions then move to the open decisions on the main tab.

## Tensions and options

| # | Tension | Options | Lean | Status |
| --- | --- | --- | --- | --- |
| 1 | **The plot must keep living when its chunks unload.** Minecraft only ticks chunks near players; our ponds, erosion and populations must run while the player is in the RV or far away | A. Force-load all \~170 plot chunks. B. Keep all simulation in our own arrays, independent of chunks, and write block changes only to loaded chunks, queuing the rest. C. Simulate only near the player | **B**, with A as a fallback for small servers. The arrays are the truth; the world is the view | Decided (Minecraft development) |
| 2 | **Who owns the water.** Flowing Fluids moves water in the world; our model needs soaking, springs and evaporation per column | A. Flowing Fluids handles everything; we add soaking on top. B. Our column model is the truth on the plot and writes levels as Flowing Fluids blocks; Flowing Fluids runs everywhere else and handles buckets. C. Fork Flowing Fluids | **Split by chunk state.** Flowing Fluids moves water in loaded chunks; our column model moves it in unloaded chunks; soaking, springs, rain, evaporation and outlets always run on the arrays. Full rule in Systems spec → Water ownership | Decided: water ownership rule; test slice step 4 confirms |
| 3 | **Smooth soil vs whole blocks.** 8-layer blocks look smooth but touch placing, walking, mob paths and lighting | A. Whole blocks only. B. Layers on every block. C. Layers only on the top block of each column | **C**: smooth surfaces and cheap below ground | Decided (Minecraft development) |
| 4 | **Thousands of animals vs entity cost** | Populations as numbers plus a few entities near the player (as designed) vs real entities everywhere | **As designed.** Caps and folding keep it cheap | Decided (Minecraft development) |
| 5 | **Speeding up time** | A. Our simulation takes bigger steps and the clock jumps; the vanilla tick rate stays. B. Vanilla `/tick sprint` speeds everything | **A.** Sprint runs every entity faster, and our steps are cheaper | Decided (Minecraft development) |
| 6 | **Biomes and travel** | A. One dimension per biome. B. One huge world; drive the RV between regions. C. Separate saves | **A.** Clean climates and seasons per biome; driving is a short travel scene | Decided (Minecraft development) |
| 7 | **Seasons** | Our own season clock and color provider vs a seasons mod | **Our own.** A seasons mod would fight our climate | Decided (Minecraft development) |
| 8 | **Terrain on the plot** | A. A custom generator shapes the plot from the prototype's land function and blends into vanilla terrain at the edges. B. A hand-built structure placed in a vanilla world | **A.** It can vary per seed and per biome | Decided: A |
| 9 | **Screens** | Build our own on the 26.2 retained-mode GUI, or use a UI library | **Our own, small:** a few reusable panels (card, table, chart, button) used by every app | Decided (Minecraft development) |
| 10 | **Multiplayer money** | Shared station cash and reputation vs per-player | **Decided: solo only for now; multiplayer comes later** | Decided: solo only |
| 11 | **Animals the player holds** | Entities in the lab vs data records | **Records** in the studbook, shown as entities only while a station is open | Lean |
| 12 | **Performance on a Mac** | The 200 × 200 plot is 40,000 columns | Keep the budgets in the Systems spec and measure on your Mac in the test slice | Ongoing: test slice step 5 and the Milestone 1 benchmark gate |

## How the systems connect

Each package owns its SavedData and changes another package's data only through its service in `com.fieldstation.api`. Changes are announced through a small set of events. That keeps each system testable on its own and lets agents build them in parallel.

**Data each package owns (layout in Content registry → Saved data).**

- **Per column:** height of each soil layer, water level, soil moisture, wear and load, plant layers, microhabitat tag, packed and dug flags.
- **Habitat records:** area, depth, age, chemistry, populations, condition, diversity.
- **Station:** cash, reputation, science credits, permits, journal, studbook, orders and contracts.

**Events** (moved to Minecraft development → API events and services; these tables are kept for history).

| Event | Raised by | Used by |
| --- | --- | --- |
| Column changed | Player tools, sediment, plants | Water, microhabitat scan, terrain map, world writer |
| Habitat formed, changed or lost | Habitat scan | Ecology, journal, alerts, goals |
| Population changed | Ecology | Entity spawner, grants, journal |
| Species arrived or lost | Ecology | Journal, grants, advancements |
| Animal caught or released | Capture | Ecology, studbook, journal |
| Weather and season changed | Weather | Water, plants, ecology, HUD |
| Purchase or reward | Economy | Drone, inventory, goals |
| Day began | Clock | Grants, contracts, orders, autosave |

| More events | Raised by | Used by |
| --- | --- | --- |
| Event scheduled | Storyteller | Weather, ecology, briefing, alerts |
| Briefing built | Briefing | Field tablet |
| Animal named | Named-animal tracker | Journal, Chronicle, entity spawner |
| Chronicle entry | Chronicle writer | Journal |
| Year ended | Clock | Year card builder, Park Service report |

**One rule:** only the world writer touches Minecraft blocks. Every system changes the arrays, and the writer turns those changes into block updates in batches, for loaded chunks only (tension 1, option B). The writer's rules are in Systems spec → World writer.

## Vanilla pieces worth reusing

Using what Minecraft already has makes the mod feel native and saves work.

| Vanilla piece | Becomes |
| --- | --- |
| Spyglass | The base for binoculars (zoom is already built in) |
| Brush (the archaeology tool) | Lifting cover boards and flipping rocks slowly and carefully |
| Bucket of axolotl or fish | The pattern for carrying caught animals with their data |
| Composter | The base for the compost bin (outputs topsoil instead of bone meal) |
| Bundle | Sample kits and seed packet pouches |
| Map item | Field maps with the terrain-change layer drawn on them |
| Advancements | Goals and the biome badges |
| Recipe book | Shows only unlocked recipes |
| Frogs, tadpoles, turtles, salmon, foxes, bees, bats, rabbits, wolves | Visual and AI bases for our species, with every vanilla target and attack goal removed; tadpoles are our own entity on the vanilla model |
| Firefly bush particles | Firefly nights over wet meadow |
| Villager AI with trading off | NPC visitors |
| GameTest framework | All gate tests |
| Beds, weather | Sleep, rain and thunder visuals; the clock package sets the dimension's clock |

## A test slice that proves the risky parts first

Before Milestone 1 tasks 4–8 start, one small build (Milestone 1 task 1b) tests the riskiest tensions on your Mac. It takes about 3 agent days.

| Step | Proves | Pass when |
| --- | --- | --- |
| 1. Empty NeoForge 26.2 mod with Flowing Fluids, in the GitHub repo, built on the Mac and in Actions | Toolchain | Both builds pass; the game launches |
| 2. A 64 × 64 test plot from the land function, with layered top blocks | Tensions 3 and 8 | Terrain looks smooth; walking and placing work. A 3-layer bed under 4 levels of water shows no air gap, and moisture changes cause no block updates |
| 3. Column arrays and the world writer; fly 300 blocks away and come back | Tension 1 | Water and plants kept changing while away; blocks match the arrays. No column differs from the arrays after 3 unload and reload cycles. The writer never takes more than 2 ms in a tick |
| 4. Water bridge: Flowing Fluids in loaded chunks, our column model in unloaded ones; buckets still work | Tension 2 | Water conserved within 0.5%; no fighting between the two. Pass also needs: water conserved within 0.5% across a chunk unload and reload, and no Flowing Fluids mixin or config hack needed. If the Flowing Fluids public API can't do this, list the mixin it needs against the 8-mixin budget |
| 5. A storm on bare ground at 20× speed | Tension 5 and performance | A gully forms; mod time under 13 ms on average and under 20 ms at peak (spark) |

If any step fails, its tension goes back to the brainstorm with the evidence before Milestone 1 continues.

## Questions for Ruhan

1. **Multiplayer money:** answered. Solo only for now.
2. **Population numbers:** answered. The journal shows survey estimates and the Park Service allotment (see *Surveys and Park Service allotments* in the Systems spec).
3. **Selling animals:** Decided: phase 1 approved (v0.4); later phases open. The pitch is in the Capture and animal trade tab.
4. **Moving the RV:** Decided: deferred until the cove is done.
5. **Off-plot land:** answered. No villages for now; the surroundings are wild.
6. **Realism versus pace:** answered. Keep growth and erosion fast.
