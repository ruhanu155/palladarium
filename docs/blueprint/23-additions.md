# Additions

These systems come after Milestone 1. Each one builds on rules that already exist (water, sediment, microhabitat tags, the lab), so the land stays one connected simulation. All of them follow the game's two rules: no combat, and mistakes hurt habitats, not the player.

| Addition | Builds on | Version |
| --- | --- | --- |
| Animals that reshape the land | Sediment, habitat scan | v0.3 |
| Habitat structures | Microhabitat tags | v0.4 |
| Water chemistry and the nutrient cycle | Sediment, lab bench | v0.2 |
| Science contracts and reputation | Journal, RV computer | v0.5 (reputation in v0.2) |
| RV living systems | RV, lab stations | v0.4 |
| Seasonal events | Weather and seasons | v0.2 |
| Land management | Succession, vanilla fire | v0.4 |
| Invasive species and disease | Succession, lab, corridors | v0.4 |
| Native plant nursery and pollinators | Plants, RV fridge | v0.4 |
| Neighbors and the watershed | Plot edges, outlet | v1.0 (agreements and downstream score only) |

*Each addition's version is in the release path in Fun, feel and finish; the Version column mirrors it*.

## Animals that reshape the land

The player is not the only one moving dirt and water. Some animals are engineers: they change terrain through the same water and sediment rules, so the land keeps changing even on days the player does nothing.

| Animal | Arrives when | What it does to the land | Player response |
| --- | --- | --- | --- |
| Beaver | A stream reach of 30+ blocks with willow or alder within 20 blocks | Builds a `beaver_dam` (sticks and mud) one layer per night until water backs up 1 block. Floods the woods upstream, which drowns trees into snags. The dam traps silt, so a beaver pond becomes a *beaver meadow* over years | Welcome it as a free wetland; cap the water height with a pond leveler pipe; wire-wrap trees you want to keep; it adopts any beaver dam analog in its reach (Mobs → Beavers and beaver dam analogs) |
| Muskrat | Ponds or marsh with cattail or rush cover | Burrows into banks and earth dams from the water side. Each burrow is a leak path: the column soaks 20× faster | Riprap or wire mesh on the dam's water face; keep cattails away from the dam |
| Burrowing crayfish | Wet meadow at moisture 5+ | Builds mud chimneys; burrows double soaking in that column, so the meadow drains a little faster | Nothing needed; logs in the journal |
| White-tailed deer | Woods edge (see *Wildlife rules*) | Columns walked 20+ times in 7 days become a packed trail. Runoff follows the trail and starts rills | Fence, plant shrubs across the line, or use the trail as your own path and build it into tread (Landscaping and terraforming → Trail craft) |
| Feral hog (invasive) | Unfenced plot edge in the Appalachians, fall acorn crop | Roots up wet meadow and pond edges in groups, turning cover into bare mud: erosion, muddy water, lost eggs. Flees from the player and never attacks | Hog panel fencing; a corral trap through an agency contract; replant the damage |

Engineer work shows on the terrain-change map in its own color, so the player can tell their own changes from the animals'.

## Habitat structures

Small builds give specific animals a place to hide, nest or bask. A structure only works on the right microhabitat tag. The journal shows which species used it.

| Structure | Built from | Must be on | Attracts | Upkeep |
| --- | --- | --- | --- | --- |
| Brush pile | Cut branches from brush clearing | Meadow or woods edge | Rabbits, wrens, sparrows | Rots down over 2 years |
| Log pile | Logs from fallen or cut trees | Woods, moisture 4+, shade | Salamanders, beetles, fungi | Keep it damp; it improves as it rots |
| Rock pile | Stone or cobble | Warm slope | Skinks, snakes, chipmunks | None |
| Snag | A girdled or drowned tree | Any | Woodpeckers, then their old holes bring bluebirds, owls and bats | Falls after about 3 years and becomes a log |
| Bluebird box | Nest box on a post | Dry meadow, 1.5 m up | Bluebirds, tree swallows | Clean out after each brood |
| Wood duck box | Nest box on a pole | Over or beside open water | Wood ducks | Fresh wood shavings each spring |
| Bat box | Box on a pole or the RV | Facing south, 4 m up, near water | Bats that hunt mosquitoes at dusk | None |
| Basking log | Anchored log | Open water in full sun | Turtles | Re-anchor after floods |
| Sandy nesting bank | Sand blocks | South-facing bank above flood level | Turtles lay eggs there | A nest cage stops raccoons |
| Fish cover | Sunk brush bundles | Open water 1 block deep or more | Fry and small fish survive better | Replace every 2 years |
| Mud puddle | Wet bare soil | Meadow in summer | Butterflies, swallows collecting mud | Refill in drought |

Structures are cheap and quick, so they give the player something to do while a pond establishes.

## Water chemistry and the nutrient cycle

Erosion doesn't only make water muddy. Soil carries phosphorus, and phosphorus feeds algae. Each habitat record tracks oxygen, nitrate, phosphate, pH, temperature and algae.

**The chain.**

1. Phosphorus comes in with eroded topsoil, fallen leaves, droppings from ducks and geese, and grey water from the RV.
2. Algae grows with phosphorus × sunlight × warmth. Reeds and underwater plants take up phosphorus first, so a planted pond starves the algae.
3. Plants and algae make oxygen by day and use it at night. Oxygen is lowest at dawn.
4. In a bloom, dawn oxygen falls below 2 mg/L. Fish and tadpoles die, and their decay releases more phosphorus. Left alone, a pond can spiral.

| Reading | Good | Stressed | Deadly |
| --- | --- | --- | --- |
| Dissolved oxygen at dawn | Above 6 mg/L | 3–6 | Below 2 |
| Nitrate | Below 5 mg/L | 5–10 | Above 10 for crayfish and eggs |
| Water temperature (cold-water species) | Below 20 °C | 20–24 | Above 24 |
| Turbidity | Below 0.2 | 0.2–0.5 | Above 0.5 for a day |

**What changes the numbers.** Cover in the catchment, a reed buffer at the inflow, shade trees on the south bank, spring water flowing through, and dredging out silt (which stores phosphorus) all help. An aerator works too, but it runs on RV power.

**How the player sees it.** A sample tested at the lab bench gives one reading. A data logger left in a pond records oxygen and temperature every hour, and the RV computer charts it, so the dawn crash shows up as a dip the player can explain.

## Invasive species and disease

With no monsters, the pressure in the game comes from life that doesn't belong. Invasives take over fastest on ground the player disturbed, so every dig carries a small risk until it is replanted.

| Threat | Where it shows up | How it spreads | How to manage it |
| --- | --- | --- | --- |
| Japanese stiltgrass | Appalachians; shaded bare ground and trails | 1 column a day along trails and bare soil | Pull or mow before seed in late summer; replant natives |
| Chinese privet | Woods edge | Birds drop seed | Cut and pull young shrubs |
| Phragmites (common reed) | Wet ground everywhere | Runners into wet columns, 1 every 7 days | Mow repeatedly, then shade it out with natives |
| Purple loosestrife | Northern wetlands | Seed along water | Pull before flowering |
| Bullfrog (outside its range) | Western biomes | Hops between ponds along wet corridors | Agency trapping contract; vernal pools that dry each summer keep them out |
| Red swamp crayfish | Southern and western ponds | Through connected water | Traps; drying out the pond |
| Chytrid fungus | Any amphibian pond | Through water links, and on nets and boots moved between ponds | Boot-wash station at the RV; swab, microscope and lab test; treatment station |
| Ranavirus | Crowded tadpole ponds in hot weather | Within one pond | Fewer tadpoles per pond; cooler, shaded water |

**Arrival.** Invasive seed lands only on bare or disturbed columns, more often near the plot edge and the road. Native cover blocks it, so replanting quickly is the main defense.

**Detection.** Invasives are logged like any species, with binoculars. Disease needs a swab tested in the lab. The RV computer shows invasive cover as a share of the plot, and grants drop slightly while it is above 10%.

## Land management

Left alone, open land turns to woods (one game year is 28 days):

| Stage | Reached after |
| --- | --- |
| Bare ground to weeds | 3 days |
| Weeds to meadow | 7 days |
| Meadow to shrubs | 14 days |
| Shrubs to young woods | 2 years |

Meadow species (bluebirds, butterflies, grassland plants) need the player to hold succession back. Each tool resets it in a different way.

| Action | Tool | Effect | Best season |
| --- | --- | --- | --- |
| Mow | Scythe, later a mower | Sets meadow back to short grass; stops shrubs | After midsummer. The scythe and mower will not cut within 3 blocks of an active ground nest; the HUD says 'Nest in the grass' |
| Clear brush | Brush cutter | Removes shrubs and saplings; gives branches for brush piles | Fall and winter |
| Girdle a tree | Hatchet | The tree dies standing and becomes a snag | Any |
| Prescribed burn | Drip torch, with a permit | Burns grass and litter; kills shrub seedlings and much of the stiltgrass seed; natives come back stronger next season | Early spring, dry days |
| Leave it | — | Succession carries on toward woods | — |

**Burning safely.** A burn needs a permit from the RV computer, wind below 15 km/h, and soil moisture of 3 or less in the burn area. It also needs firebreaks around it: strips at least 2 blocks wide that are dug, wet or freshly mowed. Fire uses vanilla fire spread, but only into dry grass and leaf litter. If it crosses a firebreak, the agency fines the player and reputation drops. A good burn is calm work, not a disaster.

## Native plant nursery and pollinators

Buying seed works early on. Growing your own is cheaper, fits the land better, and is a second income from selling plugs.

**The process.**

1. **Collect seed** in fall from mature native plants on your land, using the seed collector. Collecting elsewhere needs a permit.
2. **Cold treatment.** Many natives need winter before they sprout. Keep the seed in the RV fridge for 7 days (it uses power), or leave a seed tray outside through the winter.
3. **Sow in trays** on the nursery shelf (an awning shelf or a roof rack add-on). Trays need watering every 2 days.
4. **Plugs** are ready in 5 days. Plant them out, or sell them through the RV computer.

| Plant | Grows on | Why it matters |
| --- | --- | --- |
| Common milkweed | Dry meadow | The only food for monarch caterpillars |
| Joe-pye weed | Wet meadow | Late-summer nectar for butterflies |
| Cardinal flower | Stream banks | Hummingbirds |
| Hazel alder | Pond and stream edges | Cover for ducks; early catkins for bees; beaver food |
| Broadleaf arrowhead | Shallows | Takes up phosphorus; tubers for ducks; dragonfly perches |
| Switchgrass | Dry meadow and slopes | Deep roots stop erosion; winter cover for birds |
| Black-eyed Susan | Dry meadow | Quick color on new meadow; goldfinch seed |

**Pollinators.** Flowering columns make nectar by season. Bee and butterfly populations follow the nectar, and pollinated natives set more seed and spread on their own. Monarchs need milkweed to breed and fall nectar to migrate. A plot with both is logged as a monarch waystation.

### Seed vault (v0.4)

Bank seed from every native plant on the plot. Real seed banks keep seed dry and cold so it lives for years. Here the vault is insurance: after a fire, flood or blowdown, the player replants with the cove's own seed instead of buying it. Spare seed goes to the regional seed bank for science credits.

**How it plays.** Collect seed with the seed collector, as for the nursery. Put the packets in the vault, one drawer per species. Take packets out by hand whenever ground needs replanting. After a disturbance (an escaped burn, a dam breach, a blowdown, hog rooting), the vault's readout lists the species that grew there before and how many packets it holds of each.

| Rule | Number |
| --- | --- |
| Storage | One drawer per native species, up to 64 packets each. The vault refuses invasive seed |
| Cold | In the springhouse it needs no power (and doesn't use the springhouse's 9 slots). Anywhere else it uses 0.3 kWh a day |
| Viability | Starts at 100%. Cold, it holds. Without power, it drops 5% a season, never below 40%. Regrow and re-collect a species to refresh its drawer |
| Local seed | Vault seed came from the plot, so it germinates 15% better than bought seed of the same species. Seed packets carry an `origin` component: plot or bought |
| Collecting limit | Take seed from at most 1 plant in 5 of a species each season. The seed collector readout counts it, for example "2 of 10" |
| Cold treatment | Vault seed is stored dry, so species that need cold treatment still need it before sowing in trays (step 2 above). Sown outdoors in fall, they get winter for free |
| Regional seed bank | Ship 8 packets of one species in a drone crate for 3 SC. Three wanted species are posted each season and pay 6 SC. Up to 4 shipments a week |

| Item | Id | Get it | Price | Tier |
| --- | --- | --- | --- | --- |
| Seed vault | `fieldstation:seed_vault` | A cabinet block placed in the RV, under the awning or in the restored springhouse | $150 | Volunteer |

**Rewards.** Each species banked for the first time: +2 reputation and 3 SC. Every native species on the cove rosters banked (Plants): the cove seed bank milestone, +40 reputation and 30 SC. Replanting from the vault costs nothing; the same seed bought costs the shop price.

**Mistakes.** Past the collecting limit, the readout warns first. Collecting anyway costs −2 reputation, and that patch spreads at half rate next spring. A vault without power loses viability, never its seed.

**Hooks.** The nursery process and the seed collector (this section), germination and spreading (Plants), RV power (RV living systems), the springhouse (The cove → Restoration projects), land management and burns (this tab), dam breaches (Systems spec), the drone crate (Economy, crafting and drones).

**GameTests.** `fieldstation:nursery/vault_viability`: a vault without power for one season reads 95%; one in the springhouse reads 100%. `fieldstation:nursery/vault_local_seed`: vault seed and bought seed of one species sown on matching plots; the vault plot germinates 15% more.

## Science contracts and reputation

Grants pay for what lives on the land. Contracts pay for doing real fieldwork. The Contracts app on the RV computer posts 2–4 jobs each season (7 days) from agencies, a university, a land trust and the county, paying $50–$300.

| Example contract | Client | Task | Tools | Pay |
| --- | --- | --- | --- | --- |
| Frog call survey | State wildlife agency | Record 5 minutes at 2 ponds on 3 nights between 21:00 and 01:00 | Audio recorder | $60 |
| Stream reach map | University lab | Walk 50 m of stream; mark pools, riffles and banks | GPS, transect tape | $80 |
| eDNA check | State wildlife agency | Filter water at 3 sites and ship the filters within a day | eDNA kit, lab bench | $100 |
| Nest box monitoring | Land trust | Check boxes every 2 days for 6 days; record eggs and chicks | Notebook, nest boxes | $50 |
| Stormwater credit | County | Keep soil lost below 5 blocks this season | Terrain-change map | $150 |
| Invasive pull | Land trust | Clear phragmites on a neighbor's parcel | Mower, gloves | $120 |

**Survey tools.**

- **Camera trap:** photographs animals overnight; the player reviews photos on the computer.
- **Audio recorder:** frog and bird calls, identified on playback.
- **eDNA kit:** detects species too shy to see.
- **Quadrat frame:** counts plant cover in a 1 m square.
- **Bat detector:** turns bat calls into sound at dusk.
- **GPS and transect tape:** for maps.

**Data quality.** A late sample, a survey at the wrong hour, or a skipped quarantine pays less and costs reputation. Doing it right is the game.

**Reputation tiers.**

| Tier | Unlocks |
| --- | --- |
| Volunteer | Basic contracts, the starting shop |
| Technician | Burn permits, data logger, mini excavator at $200 a day |
| Field biologist | Handling protected species, the trailer lab |
| Station director | The cove easement; land and biomes after v1.0 |

## RV living systems

The RV runs on limited power and water. Its systems are upkeep, not survival: when something runs out, the lab and the animals in it suffer, not the player's health.

**Power.** The battery bank starts at 5 kWh.

| Source or load | Per game day |
| --- | --- |
| Roof solar | +2 to +4 kWh, depending on sun and season |
| Generator | +1 kWh per hour of running; uses fuel; loud |
| Lights, computer, fridge | −1.5 kWh |
| Incubator or vivarium | −1 kWh each |
| Pond aerator | −2 kWh |
| Seed vault, outside the springhouse | −0.3 kWh |
| Winter heater fan | −1 kWh (heat itself comes from propane) |

The generator is loud. While it runs, animals within 32 blocks hide, frogs stop calling and sightings stop. Running it at dusk ruins a frog survey.

**Water.**

- The 150 L fresh tank fills from the spring with a pump and a filter. Daily use is about 40 L, plus lab water changes.
- The 120 L grey-water tank must be emptied. Dumped into a pond, it adds phosphorus and soap and feeds algae. Dumped into a small constructed wetland (a lined reed cell the player builds), it is cleaned and the wetland becomes habitat.

**Heat.** Propane heats the RV in winter. On a cold night without heat, sleep restores half the energy and the incubator and vivarium drift off their temperature.

**Upgrades.** A second battery, more solar, bigger tanks, a roof nursery rack and the trailer lab. The trailer lab doubles lab space and needs its own power.

## Seasonal events

Each season has moments worth planning for. The forecast announces each one as far ahead as the difficulty preset allows (Standard: 2 days), so the player can prepare instead of reacting.

| Season | Event | What happens | What the player can do |
| --- | --- | --- | --- |
| Early spring | Big Night | The first warm rain after a frost: salamanders and frogs walk to breeding ponds in one night, many across the RV driveway | Carry them across (reputation), or build a small culvert under the driveway |
| Spring | Peeper chorus | Every pond with reeds calls at once | Best night for a call survey contract |
| Spring | Spring storm (every year) | 40–60 mm in a day | Check silt fences and new banks |
| Late spring | Dragonfly emergence | Larvae climb reeds at dawn and shed their skins | Collect the empty skins as journal records |
| Summer | Firefly night | Fireflies flash together over wet meadow | Photograph it; it marks wet meadow as healthy |
| Summer | Drought (one year in two, never two years running) | The spring drops to 30% for 4–7 days; ponds shrink; vernal pools dry, as they should | Move stranded tadpoles; run the aerator; shade the shallows |
| Summer | Heat wave | Water warms; dawn oxygen crashes in ponds with algae | Aerate; test at dawn |
| Late summer | Big storm (every year; the storyteller picks the day) | 100 mm of rain in a day | Check spillways and dam crests the day before |
| Fall | Migration | Warblers move through woods edge; ducks stop on ponds of 100 m² or more; monarchs need nectar | Count them for contracts; keep fall flowers |
| Fall | Leaf fall | Leaves drop into ponds and add phosphorus | Rake leaves out of small ponds |
| Winter | Snow tracks | Mammal tracks show in snow | Log hidden species from their tracks |
| Once per playthrough | Periodical cicadas | A 17-year brood comes out across the woods | 7 days of food (one summer) for every bird and fish |

The storyteller schedules these events (see *Fun, feel and finish*), spending its tension budget and respecting cooldowns, so a drought and a heat wave never land within 7 days of each other. Opportunities such as firefly nights and migrations are scheduled too, so trouble and wonder alternate.

## Neighbors and the watershed

The plot is one piece of a watershed. Water comes in across the upper edge from neighbors' land and leaves over the outlet into a creek. Both edges matter.

**What comes from upstream.**

| Neighbor | Event | Effect on your land |
| --- | --- | --- |
| Farm | Field plowed in spring | 3 days of muddy, phosphorus-rich runoff at the upper edge |
| County road | Winter road salt | Salt in spring runoff; amphibian eggs fail near the road |
| New subdivision | Construction | Silt for a season; bigger storm peaks afterward from roofs and pavement |
| Neighbor's farm pond | Dam failure in a big storm | A flood pulse through your creek |

**What the player can do.** Build a sediment basin or wetland where the water enters. Or make an agreement through the RV computer: paying for a grass buffer on the farm, for example, costs cash and reputation but reduces those events for good. Neighbor agreements cost $1,500 (farm buffer), $2,500 (road-salt swap) and $4,000 (subdivision stormwater pond), each one-time (tuning values).

**Downstream score.** Every 7 days (once a season) the water leaving the plot is scored on turbidity, phosphorus and temperature. A good score earns a stormwater credit from the county. That makes the quiet work of holding soil worth money.

**More land.** In v1.0, the Station director can place the cove conservation easement ($6,000, tuning value): it locks the cove from development and pays +150 reputation. Neighboring parcels come after v1.0; each starts in its own condition: a worn-out farm field, logged woods, or a slope cut by gullies.

**Multiplayer later.** Each player owns a parcel in one shared watershed. Upstream players' choices reach downstream players through the same water and sediment rules.
