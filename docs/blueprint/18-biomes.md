# Biomes

## Travel and multiple plots

**Driving.** The Travel app is hidden until RV travel ships (after v1.0). Once it ships, it shows every biome and every plot the player owns. A trip takes 1–3 game days and $40–$120 in fuel, depending on distance and route. The full trip concept (pack up, drive, set up) is in the Lab and RV tab. The RV, its lab stations and everything in storage go along.

**One dimension per biome.** Each biome is its own Minecraft dimension (`fieldstation:appalachia`, `fieldstation:pnw`, and so on). The plot sits in the middle, surrounded by normal terrain of that biome. Plots bought later in the same biome sit next to the first.

**Catch-up while away.** When the player returns to a plot, the time since they left is simulated in daily steps, capped at 1 year. Used only for plots the player is not in (after v1.0). A catch-up step costs at most 35 ms and runs one day per tick. Each step covers:

- water balance: rain, the spring or creek, evaporation
- population growth and death
- plant growth and succession
- erosion at reduced detail

An arrival report sums it up: "While you were away: East pond lost 1 layer to silt. Bluebirds raised 2 broods. Stiltgrass spread over 40 m²."

**Moving animals between biomes** needs an animal transfer permit and quarantine. The game blocks releasing any species outside its real native range, and the release screen explains why.

**When.** Every biome beyond the cove comes after v1.0, The Cove.

## Southern Appalachians (start)

A 200 × 200 block cove in the Blue Ridge, about 10 acres, around 900 m up. A spring and a small trout creek run down the cove floor between steep wooded slopes. The land was logged and grazed a lifetime ago and is half grown back.

| Season | Temperature | Rain |
| --- | --- | --- |
| Spring | 5–18 °C | 0.9 events a day, 30% storms |
| Summer | 16–28 °C | 1.0 events a day, 50% storms; afternoon storms on about half of summer days |
| Fall | 5–18 °C | 0.6 a day, 20% storms |
| Winter | −8 to 6 °C | 0.5 a day, 0% storms; snow on cold days |

This table is the single source for the cove's climate. The Systems spec season table copies these values, and the weather model (Weather and climate tab) is tuned to produce them.

**Terrain.** About 25 blocks of height from the creek to the ridge tops. The slopes are steep, the cove floor is narrow and flat, and rock outcrops break the slopes.

**Ground.**

1. Dark forest topsoil, 1 block
2. Subsoil, 1 block
3. Red clay, 1–2 blocks in the coves, thin or missing on the ridges
4. Rocky colluvium (gravel and cobble), 1–2 blocks
5. Bedrock, 3–6 blocks down on slopes, at the surface on outcrops

**Water.**

- A spring of 1.1 m³ an hour.
- A permanent trout creek entering from upslope.
- Seeps along the base of the slopes.
- Summer afternoon storms.

**Mechanics that make this biome different.**

- **Steep slopes.** Stream power runs higher on the slopes. Saturated soil on a slope steeper than 1 block up per 2 across can slide in a big storm, a small landslide built on the bank collapse rule.
- **Cold water.** Brook trout and hellbenders need clean cobble streams below 20 °C. Shade from hemlocks and rhododendron keeps them cold. Silt from bare ground smothers the cobble.
- **Mast years.** Oaks drop a big acorn crop every 2–5 years. Bears, turkeys, deer and hogs follow the acorns.
- **Mist.** On cool, still mornings, fog fills the cove and the creek valley.
- **Salamanders.** The southern Appalachians hold more salamander species than anywhere else on Earth. Damp woods with logs and seeps carry many of them, including several that never need a pond.

**Starting condition.**

- An old logging road cut straight down a slope, now a deep gully dumping silt into the creek
- An old farm pond, half full of silt, behind a leaking dam with no spillway
- A perched culvert under the driveway that trout can't climb
- Hemlocks along the creek, dying from hemlock woolly adelgid
- A homestead junk pile of rusted fencing and tin (cleanup yields scrap for crafting)

**Threats.** Hemlock woolly adelgid, Japanese stiltgrass, multiflora rose, Japanese honeysuckle and Chinese privet at the road edge, feral hogs, silt from bare ground.

**Biome goals.**

- Heal the logging-road gully.
- Restore the farm pond and give it a spillway.
- Replace the culvert so trout can move upstream.
- Save the hemlocks.
- Log every species in the Appalachian roster for the biome badge.

## Pacific Northwest rainforest

A cold salmon creek through big conifers, once logged.

| Season | Temperature | Rain |
| --- | --- | --- |
| Spring | 5–15 °C | Frequent drizzle; snowmelt pulse in the creek |
| Summer | 12–26 °C | Nearly dry all season; the creek drops |
| Fall | 6–16 °C | Rain returns; salmon run |
| Winter | 0–8 °C | Drizzle most days at 2–6 mm an hour |

**Ground.**

1. Moss and duff, 1 block, holding 0.5 m of water per m
2. Topsoil, 1 block
3. Glacial till, 2–3 blocks, soaking 3 mm an hour
4. Gravel lenses
5. Basalt

**Water.** A creek enters at the upper edge instead of a spring. It is fed by snowmelt and rain and is always running.

**Salmon run.** In fall, coho swim up the creek if they can get through.

- No step more than 1 block high, or a fish ladder over it
- No old culvert in the way
- They spawn only in clean gravel, under 20% silt
- Eggs sit in the gravel all winter and die if it silts up or the water passes 16 °C
- Each redd (gravel nest) counts toward grants

**Wood in streams.** Logs anchored across or along the creek with the rock bar scour pools and hold gravel, the way real stream restoration works. Beaver dam analogs (posts woven with willow) slow the water and invite real beavers.

**Threats.** Himalayan blackberry, reed canarygrass, warm water where banks lack shade, and the old culvert blocking the run.

**Unlock.** Unlocks after v1.0. The main tab's biome table is the only unlock source.

## Tropical rainforest

A lowland forest field station beside cleared pasture.

| Season | Temperature | Rain |
| --- | --- | --- |
| Spring and summer (wet) | 24–32 °C | A storm every afternoon, 20–40 mm an hour for 1–2 hours |
| Fall and winter (dry) | 24–32 °C | Rain every 3 days |

**Ground.**

1. Leaf litter, 1 block, rotting away in 3 days
2. Thin topsoil, 1 block
3. Red laterite clay, 3–4 blocks; very poor
4. Weathered rock

Plants live off the litter, not the soil. Cleared ground loses its fertility within a season and erodes fast in the afternoon storms.

**A fourth plant layer.** Emergent trees rise above the canopy. Plants growing on branches (orchids, bromeliads) need tree trunks, not soil.

**Tiny habitats.** Bromeliad cups and tree holes hold water and are tracked as tiny habitat records. Poison frogs carry tadpoles into them one at a time. A healthy forest has hundreds of them.

**Heat.** Outside shade, energy drains 1.3× faster.

**Rules.**

- The vivarium and incubator are needed before the first visit.
- Dart frogs can never leave this biome. The transfer permit doesn't cover them, as with real export rules.

**Threats.** Chytrid (the highest risk of any biome), neighbors clearing land for pasture upstream, and escaped farmed fish.

**Unlock.** Unlocks after v1.0. The main tab's biome table is the only unlock source.

## Desert springs

A small spring oasis in a dry wash, where every liter counts.

| Season | Day | Night | Rain |
| --- | --- | --- | --- |
| Spring | 20–30 °C | 8–15 °C | Rare |
| Summer | 30–45 °C | 18–25 °C | Monsoon: 2–3 storms a season at 40–60 mm an hour for 30–60 minutes; flash floods in the wash |
| Fall | 20–30 °C | 8–15 °C | Rare |
| Winter | 12–20 °C | 0–5 °C | 1 gentle rain a season |

**Ground.**

1. Sand, 1–2 blocks, soaking very fast
2. Caliche hardpan, 1 block: soaks 0.2 mm an hour and needs the pick
3. Gravel, 2 blocks
4. Bedrock

Caliche works like clay as a pond liner, but it is hard to dig.

**Water budget.** The spring gives only 0.3 m³ an hour, and evaporation is 3× the Appalachian rate. A Water budget card on the RV computer shows each pool's inflow against its evaporation. Here, a pool too big for its spring simply dies.

**Shade.** Cottonwoods and willows over a pool cut its evaporation by 40%. They also keep it cool enough for pupfish, which need shaded pools at least 2 levels deep.

**Flash floods.** A monsoon storm sends a wall of water and debris down the wash and scours anything in its path. Crafted gabions (wire baskets filled with stone) protect pools and banks.

**Tamarisk.** Each stand drinks 10 mm of water an hour. Clearing it raises the spring's flow by up to 30%, the biggest single fix in the biome.

**Unlock.** Unlocks after v1.0. The main tab's biome table is the only unlock source. The biome goal is to keep a spring pool alive through a drought.

## Northern lakes

A glacial lake with bogs, on thin soil over granite.

| Season | Temperature | Notes |
| --- | --- | --- |
| Spring | 0–12 °C | Snowmelt flood; ice breaks up |
| Summer | 10–24 °C | Short and busy; loons nest |
| Fall | 0–12 °C | Migration; first ice |
| Winter | −25 to −5 °C | Ice grows 1 level every 2 days, up to 6 levels (as `fieldstation:ice_layer` blocks); snow 1 layer per snowy day |

**Ground.**

1. Peat, 1–4 blocks in bogs, holding 0.8 m of water per m
2. Glacial till
3. Sand and gravel ridges
4. Granite, often only 1–3 blocks down

Shallow granite limits digging, so ponds are built up as much as down.

**Bogs.** Sphagnum adds 1 peat layer per game year and keeps bog water at pH 4. Wood frogs and pitcher plants thrive there; fish can't live in it.

**Ice and thaw.** Fish and frogs need water deeper than the ice. The spring melt sends the year's largest flood through every outlet.

**Quiet shores.** Loons nest only on a lake of at least 400 m² where no player activity happens within 32 blocks for 3 days during nesting. The observation blind lets the player watch from inside that zone without counting as activity.

**Unlock.** Unlocks after v1.0. The main tab's biome table is the only unlock source.
