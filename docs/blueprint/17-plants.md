# Plants

Plants are the base of every system: they hold soil, use water, feed and shelter mobs, and decide how the land looks. They grow over time from seed, plugs or saplings. Nothing appears full-grown.

| Group | Where it grows | Examples |
| --- | --- | --- |
| Meadow grasses and wildflowers | Open, sunny ground | Switchgrass, milkweed, black-eyed Susan |
| Wetland edge plants | Wet soil and water 0–3 levels deep | Soft rush, cattail, bur-reed |
| Aquatic plants | Underwater, floating, or rooted with floating leaves | Coontail, duckweed, water lily |
| Shrubs | Woods edge, stream banks, wet meadow | Hazel alder, elderberry, spicebush |
| Trees | Anywhere they can root | Black willow, red maple, white oak |
| Ground layer | Shade under trees | Ferns, mosses, fungi, leaf litter |

**Rules every plant follows.**

- It needs the right moisture, light and soil, and grows only in its seasons.
- It competes for its column with other plants of its layer.
- It feeds back into water, soil and mobs. Plants are never decoration only.

## Growth

Each plant has a range it tolerates on four needs:

| Need | Bands |
| --- | --- |
| Moisture | Dry (soil moisture 0–2), moist (2–4), wet (4–6), saturated (6–7), or standing water by depth in levels |
| Light | Full sun, part shade, shade (set by the canopy above) |
| Soil | Topsoil, silt, subsoil, clay, gravel, sand; silt and topsoil are the most fertile |
| Season | Growth ×1.0 in spring, ×1.2 in summer, ×0.5 in fall, none in winter (as in the Systems spec) |

**Stages.** Seed → seedling → young → mature → flowering and seeding → dormant in winter. Perennials regrow from their roots each spring. Annuals die in winter and come back only from seed.

**Health.** Each plant has health from 0 to 100.

| Cause | Loss |
| --- | --- |
| Each moisture band outside its range | −5 a day |
| Too much shade for its light need | −3 a day |
| Drowning: under more water than its own depth range for 48 hours (land plants have a range of 0) | Dies |
| Grazing or browsing | Each deer removes 3 unguarded seedling or sapling columns a night within woods edge; tree guards and fences stop it |
| Trampling, or packed soil | Seedlings only |
| Frost | Seedlings die; in spring, other plants lose 20 health and skip flowering that season |

A plant at low health stops flowering. At 0 it dies and leaves bare ground.

**How fast they grow.**

| Plant type | Time to full size |
| --- | --- |
| Meadow grass | Full cover in 48 hours |
| Soft rush | 72 hours |
| Wildflowers | Flower in 4 days |
| Shrubs | 10 days |
| Trees | Sapling to young in 1 season, mature in 2 years, old after 6 |

## Spreading, seeds and competition

Plants spread by themselves. The player's job is to tip that in the right direction.

| Way it spreads | Reach | Plants |
| --- | --- | --- |
| Seed dropped nearby | 2 blocks | Grasses, most wildflowers |
| Seed on the wind | Up to 16 blocks downwind | Milkweed, cattail, goldenrod |
| Seed carried by birds | Dropped under perches: snags, fences, nest-box posts | Elderberry, dogwood, blackberry, redcedar |
| Nuts buried by squirrels | Up to 20 blocks from the parent tree | Oaks |
| Runners and rhizomes | 1 column every 5 days into a suitable neighbor | Soft rush, cattail, phragmites |

Bird-carried seed means a snag left in a meadow grows a patch of berry shrubs under it within a season.

**Seed bank.** Seeds stay alive in topsoil for 2 years. Digging up topsoil moves its seed bank with it, so dumped topsoil sprouts whatever grew where it came from, invasives included.

**Germination** needs bare or thinly covered soil, the right moisture, and spring or early summer. Sun-loving seeds can't sprout under a canopy.

**Competition.** Each column holds up to three layers of plants: ground, shrub and canopy. It can also hold one wetland edge plant or one aquatic plant.

- **Between layers:** a taller layer shades the ones below. Under full canopy, meadow plants grow at 20% and give way to ferns and woodland plants.
- **Within a layer:** the best-matched plant takes the column. Its score is moisture fit × light fit × soil fit.
- **Invasives** score ×1.3 on disturbed soil. That is why they win on fresh digging unless natives are established first.

## Trees and the canopy

Trees grow slowly but change the most.

| Stage | Size | Reached | What changes |
| --- | --- | --- | --- |
| Sapling | 1 block | Planted or sprouted | Deer browse it; a tree guard protects it |
| Young | About 4 blocks | 1 season | Shades a 1-block radius |
| Mature | 8–14 blocks | 2 years | Shades a 3-block radius; drops leaf litter; seeds |
| Old | Full size | 6 years | Hollows form, so woodpeckers, owls and bats move in |

**What a tree does to its surroundings.**

- **Shade:** cuts light under its crown, which turns meadow into woodland floor. Shading a stream keeps it cool (see the water temperature rule in The cove), which cold-water species need.
- **Leaf litter:** in fall, covers the ground under the crown (woodland floor habitat). Leaves falling into ponds add phosphorus.
- **Roots:** lower erodibility to ×0.15 within 2 blocks, so trees hold banks.
- **Water use:** a mature tree draws 4 mm an hour of soil water within 2 blocks in summer. Woods dry the ground; clearing them raises the water table.

**Flood tolerance** (how long a tree survives with its roots underwater):

| Tree | Survives |
| --- | --- |
| Black willow | All season |
| Red maple | 14 days |
| Black cherry | 3 days |
| White oak | 7 days |
| Eastern white pine | 3 days |

After that the tree dies standing and becomes a snag. A snag falls after 3 years and becomes a log.

**Storms and cutting.** In big storms, trees on saturated soil can blow over, opening a sunny gap where meadow plants come back. The player can fell trees on their own land with a saw. Felling yields logs for log piles and opens the canopy.

## Aquatic plants

Water plants are what keep a pond clear. Each kind lives in its own depth band.

| Kind | Depth (levels) | Examples | Role |
| --- | --- | --- | --- |
| Wetland edge plants | 0–2 (cattail 0–3) | Soft rush, broadleaf arrowhead, bur-reed, cattail | Edge cover, frog calling sites, take up phosphorus |
| Floating-leaved plants | 3–12 | American white water lily | Shade the water, cooling it and starving algae |
| Submerged plants | 2–16, if light reaches the bottom | Coontail, pondweed | Make oxygen by day; cover for fry and tadpoles |
| Free-floating plants | Surface | Duckweed | Fast on still, nutrient-rich water |

**Light underwater.** The light reaching a depth is exp(−depth × (0.3 + 3 × turbidity)). Muddy water kills submerged plants, and algae take their place. Erosion, water chemistry and plants all connect here.

**Duckweed.** In summer, on still water with high phosphorus, duckweed doubles every 2 days. Full cover blocks light, so submerged plants die and oxygen drops. Flowing water, low nutrients, or skimming it with the net keep it in check. It is also a sign the pond has too many nutrients.

**Nutrients and oxygen.** Edge and submerged plants take up phosphorus first, before algae can. That is the main reason a planted pond stays clear. Submerged plants add oxygen by day and use it at night.

**Compost (replaces bone meal).** There is no bone meal. Instead, the compost bin (`fieldstation:compost_bin`, built from 6 planks at the awning workbench, Start) takes mowed grass, leaf litter, native plant cuttings and kitchen scraps. Invasive cuttings go in a bag to the drone, never the bin, because their seeds survive. A full bin makes 8 compost in 3 days, or 2 days if you turn it with the shovel once.

One use of `fieldstation:compost` on a dry soil column raises nutrients on a 3 × 3 patch for 7 days. Plants there grow 1.5× faster and seedlings survive 20% more often. It can't be used on water or within 4 blocks of a stream or pond, because the runoff feeds algae; the readout says why. Two bought boosters work the same way:

- **Worm castings:** $12 a bag, Volunteer. 2× growth for 7 days with no runoff.
- **Mycorrhizal inoculant:** $20, Technician. Tree and shrub seedlings survive 30% more often for their first year.

**Arrival and winter.** Ducks carry duckweed and seeds between ponds, or the player plants plugs and weighted pots. In winter, edge plants die back to their roots and submerged plants go dormant under the ice.

## What plants do for the world

| Effect | How it works | Feeds into |
| --- | --- | --- |
| Hold soil | Erosion ×0.1 under grass, ×0.2 under shrubs, ×0.05 under reeds, ×0.15 near tree roots | Sediment |
| Use water | 1.6 mm/h × (0.3 + cover) for ground plants; 4 mm/h near mature trees | Water |
| Take up nutrients | Edge and aquatic plants take phosphorus before algae | Water chemistry |
| Shade | Cools ground and water; turns meadow into woodland floor | Microclimate, cold-water species |
| Food | Seeds, nectar, berries, leaves to browse, acorns | Mob food base |
| Cover and nest sites | Reeds, shrubs, tall grass, tree hollows | Mob cover need, nesting |
| Host plants | Some insects can only breed on one plant | Butterflies |
| Fuel | Dry grass burns hot; green wetland plants barely burn | Prescribed burns |

**Plants that specific mobs need.**

| Plant | Needed by |
| --- | --- |
| Common milkweed | Monarch caterpillars |
| Spicebush | Spicebush swallowtail caterpillars |
| Soft rush, cattail | Red-winged blackbirds, calling frogs |
| Hazel alder | Woodcock (wet alder thickets), early bees (catkins) |
| Elderberry, blackberry | Songbirds, foxes |
| White oak acorns | Deer, squirrels, wood ducks |
| Willow, alder | Beavers |

**Mobs acting on plants.**

- Deer browse shrubs and seedlings; heavy browsing stops woods from regrowing.
- Geese graze bank grass down to bare soil.
- Birds plant berry shrubs under perches, and squirrels plant oaks.
- Pollinators raise seed set by 1.5×.
- Beavers cut willow and alder.

## Southern Appalachian forest roster (start biome)

| Plant | Id | Group | Moisture | Light | What it does |
| --- | --- | --- | --- | --- | --- |
| Broomsedge | `broomsedge` | Meadow | Dry | Sun | First native grass on worn-out ground |
| Little bluestem | `little_bluestem` | Meadow | Dry | Sun | Short clump grass; turns copper in fall; good fuel for burns |
| Switchgrass | `switchgrass` | Meadow | Moist to wet | Sun | Deep roots hold slopes; winter cover for birds |
| Common milkweed | `common_milkweed` | Meadow | Dry to moist | Sun | Monarch host; seeds on the wind |
| Black-eyed Susan | `black_eyed_susan` | Meadow | Dry to moist | Sun | Quick color; goldfinch seed |
| Goldenrod | `goldenrod` | Meadow | Dry to moist | Sun | Fall nectar for bees and monarchs; spreads fast |
| Wild bergamot | wild\_bergamot | Meadow | Dry to moist | Sun | Nectar for bees, butterflies and hummingbirds |
| Joe-pye weed | `joe_pye_weed` | Wet meadow | Wet | Sun to part shade | Late nectar for butterflies |
| Tussock sedge | `tussock_sedge` | Wetland edge | Saturated, 0–1 level | Sun | Mounds that frogs and rails hide in |
| Soft rush | `soft_rush` | Wetland edge | 0–2 levels | Sun | Frog cover; filters muddy water |
| Common cattail | `cattail` | Wetland edge | 0–3 levels | Sun | Spreads hard; blackbird nests; muskrat food |
| Broadleaf arrowhead | broadleaf\_arrowhead | Wetland edge | 0–2 levels | Sun | Takes up phosphorus; tubers feed ducks and muskrats; dragonfly perches |
| Jewelweed | jewelweed | Wetland edge | Wet | Part shade to shade | Seep and stream-edge annual; hummingbird nectar |
| Bur-reed | bur\_reed | Wetland edge | 0–2 levels | Sun to part shade | Edges of streams and ponds; seeds feed ducks |
| Cardinal flower | `cardinal_flower` | Wetland edge | Wet | Part shade | Hummingbirds |
| American white water lily | `water_lily` | Floating-leaved | 3–12 levels | Sun | Shades the pond; frogs sit on pads |
| Coontail | `coontail` | Submerged | 2–16 levels | Clear water | Oxygen; fry cover |
| Pondweed | `pondweed` | Submerged | 2–12 levels | Clear water | Duck food; oxygen |
| Duckweed | `duckweed` | Free-floating | Surface | Sun | Shows too many nutrients; can take over |
| Blackberry | `blackberry` | Shrub | Dry to moist | Sun | First shrub on cleared land; berries for birds |
| Elderberry | `elderberry` | Shrub | Moist to wet | Sun | Berries for birds; spread by them |
| Silky dogwood | `silky_dogwood` | Shrub | Wet | Sun to part shade | Holds stream banks; berries |
| Hazel alder | hazel\_alder | Shrub | Wet to saturated | Sun to part shade | Holds stream banks; fixes nitrogen; beaver food; woodcock cover |
| Spicebush | `spicebush` | Shrub | Moist | Shade | Swallowtail host; woods understory |
| Black willow | `black_willow` | Tree | Wet | Sun | Survives flooding; beaver food; fast |
| River birch | `river_birch` | Tree | Wet | Sun | Stream banks; peeling bark |
| Red maple | `red_maple` | Tree | Moist to wet | Sun to shade | Red in fall; common everywhere |
| Black cherry | black\_cherry | Tree | Moist | Sun | Fast pioneer on old fields; fruit for birds and bears; caterpillar host |
| Tulip poplar | `tulip_poplar` | Tree | Moist | Sun | Tall and straight; fills canopy gaps |
| White oak | `white_oak` | Tree | Dry to moist | Sun to part shade | Acorns feed half the woods; slow |
| Eastern white pine | eastern\_white\_pine | Tree | Dry to moist | Sun | Fast on old fields and dry ridges; can't handle flooding |
| Eastern redcedar | `eastern_redcedar` | Tree | Dry | Sun | Bird-planted along fences; winter cover |
| Christmas fern | `christmas_fern` | Ground layer | Moist | Shade | Evergreen woodland floor |
| Mosses | `moss` | Ground layer | Moist to wet | Shade | Stream rocks and logs; holds moisture |

The invasives in the Additions tab (stiltgrass, privet, phragmites) follow these same plant rules, plus their bonus on disturbed soil.

**Appalachian plants.** These join the roster above on the slopes, coves and ridges.

| Plant | Id | Group | Moisture | Light | What it does |
| --- | --- | --- | --- | --- | --- |
| Eastern hemlock | `eastern_hemlock` | Tree | Moist | Shade | Shades and cools trout streams. The hemlock woolly adelgid kills it within a few years unless treated: release predator beetles (permit) or treat each tree. Infested hemlocks lose 8 health per season (about 3 game years from full health to death). They start at 40–90 health, so the first one dies in summer of year 1. Infestation spreads to 2 hemlocks within 16 blocks each season. A treatment kit stops the loss on one tree for 5 game years. A beetle release (permit) halves the spread plot-wide after 2 years and stops it after 4 |
| American chestnut | `american_chestnut` | Tree | Dry to moist | Sun | Once a quarter of these forests; blight kills young trees. Blight-resistant restoration seedlings come from the Research exchange |
| Yellow birch | `yellow_birch` | Tree | Moist | Part shade | Grows on rotting logs and boulders in coves |
| Rosebay rhododendron | `rhododendron` | Shrub | Moist | Shade | Dense thickets along streams; cover; keeps water cool |
| Mountain laurel | `mountain_laurel` | Shrub | Dry to moist | Part shade | Ridge shrub; bees |
| Ramps | `ramps` | Ground layer | Moist | Shade | Spring wild onion. Slow to spread, so over-harvesting wipes out a patch |
| Large-flowered trillium | `trillium` | Ground layer | Moist | Shade | Spring wildflower; ants spread its seed |
| Galax | `galax` | Ground layer | Moist | Shade | Evergreen ground cover on slopes |
| Cinnamon fern | `cinnamon_fern` | Ground layer | Wet | Part shade | Seeps and wet edges |
| Multiflora rose (invasive) | `multiflora_rose` | Shrub | Any | Sun to part shade | Thorny thickets spread by birds |
| Tall fescue (invasive) | tall\_fescue | Meadow | Dry to moist | Sun | Bench Field start state; dense pasture grass that crowds out natives |
| Japanese stiltgrass (invasive) | japanese\_stiltgrass | Ground layer | Moist | Sun to shade | Annual grass along the old road edge; spreads on disturbed soil and on boots |
| Chinese privet (invasive) | chinese\_privet | Shrub | Moist to wet | Sun to shade | Thickets on the old road edge and stream banks; spread by birds; goats eat it |
| Japanese honeysuckle (invasive) | japanese\_honeysuckle | Vine | Any | Sun to part shade | Smothers shrubs along the old road edge; goats eat it |
| Tree-of-heaven (invasive) | tree\_of\_heaven | Tree | Dry to moist | Sun | Fast at the road edge; root sprouts; spotted lanternfly host |
| Mockernut hickory | mockernut\_hickory | Tree | Dry to moist | Sun to part shade | West slope woods with the oaks; nuts for squirrels, chipmunks and bears |
| Chestnut oak | chestnut\_oak | Tree | Dry | Sun to part shade | Rocky upper slopes and ridges; big acorn crops in mast years |
| Northern red oak | northern\_red\_oak | Tree | Moist | Sun to part shade | Fast-growing oak of the coves; acorns |
| Serviceberry | serviceberry | Tree | Dry to moist | Sun to part shade | White bloom in early spring; berries for birds in early summer |
| Common blue violet | common\_blue\_violet | Ground layer | Moist | Part shade to shade | Diana fritillary host plant |
| Sassafras | sassafras | Tree | Dry to moist | Sun | Old-field pioneer; spicebush swallowtail host |
| Old apple tree | old\_apple | Tree | Moist | Sun | One authored tree at the Homeplace; fall fruit for bears, deer and foxes; does not spread; its winter scion wood starts the heirloom orchard |

## Heirloom orchard (v0.4)

The old homeplace apple is the start. The Southern Appalachians once grew well over a thousand named apple varieties. Each was kept alive by grafting, because an apple grown from seed is never the same as its parent. The player can bring some of them back.

**How it plays.** In winter, cut scion wood from a dormant apple tree with the loppers. At the awning workbench, graft it onto a rootstock with the grafting knife: cut both pieces, fit them so their green cambium lines meet, and wrap the joint. Heel the grafted tree in for 7 days, then plant it out in spring. Prune each tree once every winter. Pick the apples in fall, or leave some for the wildlife.

| Step | Rule |
| --- | --- |
| Scion wood | Winter only, from a mature apple tree; up to 4 per tree per winter. Keeps 14 days in the fridge or springhouse, 3 days elsewhere; after that it is dry |
| Graft | At the workbench, from winter day 5 to spring day 2. The fit sets how fast it takes: good 3 days, fair 5, poor 7. A dry scion, or a graft outside the window, takes 10 days and starts at 60 health. Grafts never fail outright |
| Rootstock | Seedling (a standard tree, as old orchards used): first fruit in its third fall, then lives on as an old tree. Semi-dwarf MM111: first fruit in its second fall; a smaller crown |
| Spacing | Standard trees 6 blocks apart, semi-dwarf 4 |
| Pollination | Apples need a second variety blooming nearby. Full crop only if another apple variety is within 32 blocks and the native bee index is 30 or more; otherwise 25% |
| Pruning | One loppers use on each mature tree each winter. Each missed winter cuts the crop by 25%, down to 25%. Each pruning wins back 25% |
| Crop | A mature tree gives 8–16 apples in its variety's season; an old tree 16–24 |

Other varieties come as scion wood ordered from the shop in winter.

| Variety | Origin | Ripens | Notes |
| --- | --- | --- | --- |
| Homeplace apple | The authored `old_apple` | Late fall | Unknown until the old-timer names it: a Limbertwig, a late keeper |
| Horse | North Carolina | Summer | Early cooking apple |
| Magnum Bonum | North Carolina | Early fall | Sweet eating apple |
| Grimes Golden | West Virginia | Fall | Yellow; a good pollinator for the others |
| Virginia Beauty | Virginia | Late fall | Dark red and sweet |
| Black Twig | Tennessee | Late fall | Keeps into spring |
| Yates | Georgia | Late fall | Small; keeps all winter |

| Item | Id | Get it | Price or recipe | Tier |
| --- | --- | --- | --- | --- |
| Apple rootstock | `fieldstation:apple_rootstock` (component `rootstock`: seedling or mm111) | Buy | $6 | Volunteer |
| Scion wood | `fieldstation:scion_wood` (component `variety`) | Cut with the loppers, or order in winter | Free, or $8 | Volunteer |
| Grafted apple | `fieldstation:grafted_apple` (components `variety`, `rootstock`, `take_days`) | Graft at the workbench | 1 rootstock, 1 scion wood | Volunteer |
| Apple tree (block) | `fieldstation:apple_tree` | Planted from a grafted apple. Variety lives in the plants SavedData; stage templates as for other trees | — | — |
| Heirloom apple | `fieldstation:heirloom_apple` (component `variety`) | Picked by hand | — | — |
| Grafting knife, loppers | See Player and tools | Buy | $25, $30 | Volunteer |

**Wildlife.** Spring blossoms are nectar columns for bees (Additions → Pollinators). Apples left past their variety's season drop as windfalls for bears, deer, foxes and box turtles. Four or more fruiting trees count as a fall food source for the mammals (The cove → Mast years). A bear may climb a loaded tree and break a limb: −10 health and half that year's crop. Picking in time, or leaving windfalls under it, prevents that. Deer browse young trees unless guarded.

**Food and sale.** An apple is half a meal and +5 energy, like the other land snacks, in the food and sleep option. Apples never spoil. They sell only through Orders, like other goods, for example "20 heirloom apples for the farmers' market" listed at $2 each, or "6 Magnum Bonum scions for a heritage orchard" at $8 each.

**Rewards.** The first graft that takes: +10 reputation and 5 SC. First fruit of each variety: +3 reputation, 3 SC and a journal page. Six or more varieties bearing at once: the heritage orchard milestone, +20 reputation and 10 SC.

**Mistakes.** A dry scion or a late graft is slower and weaker. A missed pruning or a lone variety means a smaller crop. A broken limb costs health. No tree or item is lost.

**Hooks.** Trees and the canopy (this tab), pollinators and the bee index (Additions), mammals and mast years (The cove), food and sleep (Player and tools), Orders (Economy, crafting and drones), the fridge and the springhouse (Lab and RV, The cove), the old-timer (The cove).

**GameTests.** `fieldstation:orchard/graft_take_time`: a good fit takes in 3 days; a dry scion outside the window takes 10 and starts at 60 health. `fieldstation:orchard/pollination`: a lone variety sets 25% of its crop; with a second variety within 32 blocks and a bee index of 30, it sets a full crop.

## Other biomes

Each biome gets a full roster when it is built. These are the plants that define each one.

| Biome | Plant | What it does |
| --- | --- | --- |
| Pacific Northwest | Western redcedar | Huge, shade-making; cool streams |
| Pacific Northwest | Douglas fir | Main canopy tree |
| Pacific Northwest | Red alder | First tree on bare ground; enriches the soil; beaver food |
| Pacific Northwest | Skunk cabbage | Wet seeps; first green in spring |
| Pacific Northwest | Sword fern, salmonberry | Forest floor and stream banks |
| Tropical rainforest | Bromeliads | Their leaf cups hold water where poison frogs raise tadpoles |
| Tropical rainforest | Heliconia | Hummingbird flowers in gaps |
| Tropical rainforest | Cecropia | Fast tree that fills gaps; ants live inside |
| Tropical rainforest | Kapok tree | Emergent giant over the canopy |
| Tropical rainforest | Orchids, mosses | Grow on branches, not soil |
| Desert springs | Fremont cottonwood, Goodding's willow | Shade the spring; cool the water for pupfish |
| Desert springs | Saltgrass | Holds wet, salty soil around springs |
| Desert springs | Mesquite | Deep roots; shade and pods for wildlife |
| Desert springs | Tamarisk (invasive) | Drinks a spring dry; the biome's main enemy |
| Northern lakes | Sphagnum moss | Builds bogs and makes the water acidic |
| Northern lakes | Black spruce, tamarack | Bog trees; tamarack drops its needles in fall |
| Northern lakes | Wild rice | Shallow lakes; food for ducks and the player's journal |
| Northern lakes | Pitcher plant | Catches insects in poor bog soil |
| Northern lakes | Blueberry | Bears and birds; bog edges |

## Minecraft implementation

**Blocks and data.**

- Each species is a block with a `stage` property (0–4) for what you see.
- Cover, stage, health and seed bank live in the `fieldstation:plants` level SavedData (per column, owned by the plants package), not in block states. That keeps block states small.
- Water plants are waterlogged blocks. The water lily is a pad block like the vanilla lily pad.
- Duckweed is a thin overlay on the water surface that never blocks Flowing Fluids.

**Ticks.** Mod plants don't use vanilla random ticks. A plant system runs every 1,000 ticks (one game hour), taking chunks in turn, with a budget of 0.5 ms a tick. Only a visible stage change causes a block update.

**On the plot, vanilla plants are replaced.**

- World generation places mod species.
- Vanilla tall grass and flowers aren't generated.
- Grass cover is the `seeded=grass` state on topsoil plus `meadow_grass` blocks.
- Off the plot, vanilla plants stay.

**Trees.**

- Each species has one structure template per stage (sapling, young, mature, old), with small random variation.
- A tree advances a stage when the space it needs is free.
- Leaf blocks have a `season` property. They swap texture from green to the species' fall color, then to bare twigs in winter, instead of being removed.

**Seasonal color.** Grass and leaf tint follow the season through a color provider, so the whole plot shifts from spring green to fall color to winter brown.

**Items.** A seed packet item stores its species as an item component. Plugs and saplings are their own items. All are bought from the shop or collected with the seed collector.

**Scale.** 40,000 columns × up to 3 layers is about 120,000 plant entries in arrays, which is small. Plant cover feeds into the sediment, water and microhabitat systems directly from those arrays.
