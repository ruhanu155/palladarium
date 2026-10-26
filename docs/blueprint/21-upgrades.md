# Upgrades

Where the money goes once the basics are bought. Upgrades make land work faster, bigger or nicer to live with. They never restore the land for you: machines move dirt, but you still choose where. All prices are tuning values.

## How upgrades work

- **Buying.** Upgrades are bought on the RV computer under Shop → Upgrades, and each is unlocked by the tier shown.
- **Delivery.** Small items come by drone. Kits, machines and vehicles come by truck to the drone pad the next morning.
- **Building.** Buildings come as kits. Clear and level a site with the shovel, place the kit, then hold use on it. It builds as a time-lapse at normal energy cost: 1 game hour for small kits, 3 for large ones. A kit can be picked up and moved once.
- **Not required.** No upgrade is needed to finish v1.0, and a player who buys none can still reach the finale.
- **Money pace.** About $3,000 of this catalog fits years 1–2, about $15,000 years 3–5, and the big machines and buildings years 5–8. The whole catalog costs about $50,000. A typical player owns half of it by the finale, and the rest gives play after the finale a goal.
- **Elsewhere.** The RV's own upgrades (solar, battery, tanks, nursery rack, trailer lab, larger RV) stay in Lab and RV → Upgrades. Neighbor agreements and the cove easement are in Additions and Economy.

## Tools

| Upgrade | Price | Tier | Version | Effect |
| --- | --- | --- | --- | --- |
| Pro shovel | $90 | Volunteer | v0.4 | Energy per dig 0.25 → 0.15; digs 2 layers in one use in soft soil |
| Garden cart | $180 | Volunteer | v0.4 | Hauls 24 soil layers a trip; slow on slopes steeper than 2 blocks |
| Brush mower | $900 | Technician | v0.4 | Mows a 3-wide strip; stops near fawns and nests like the scythe |
| Power wheelbarrow | $1,200 | Technician | v0.4 | Hauls 48 layers, climbs slopes, no energy per trip; charges at the RV (0.5 kWh a day) |
| Plate compactor (owned) | $1,600 | Technician | v1.0 | Packs a 3 × 3 area per use; no more rentals |
| Skid steer (owned) | $9,000 | Station director | v1.0 | Parked at the drone pad; no daily rental; same rules as the rented one |
| Mini excavator (owned) | $12,000 | Station director | v1.0 | Parked at the drone pad; no daily rental; same speed and rules as the rented one |

## Water and power

| Upgrade | Price | Tier | Version | Effect |
| --- | --- | --- | --- | --- |
| Rain barrels (set of 4) | $160 | Volunteer | v0.4 | Catch 800 L of roof rain for the nursery and new plantings, so you don't haul water |
| Spring box and line | $450 | Technician | v0.4 | Pipes water from Laurel Spring to the RV. Takes 5% of the spring's flow, which the spring sim subtracts |
| Cistern | $600 | Technician | v0.4 | Stores 3,000 L from the RV and shed roofs; tops up the lab's fresh tank for free |
| Micro-hydro turbine | $2,500 | Station director | v1.0 | +3 kWh a day from Hemlock Run. Needs a fish screen and a 4-block bypass so fish still pass; installing it runs a stream health check |

## Station buildings

| Upgrade | Price | Tier | Version | Effect |
| --- | --- | --- | --- | --- |
| Boardwalk (per 4-block segment) | $40 | Volunteer | v0.4 | Walk over wet meadow and seeps with no trampling or compaction |
| Outdoor kitchen and fire ring | $350 | Volunteer | v0.4 | Meals cooked here give Well fed for 8 hours instead of 6; a place to watch the field at dusk |
| Wildlife blind | $400 | Volunteer | v0.4 | Animals within 24 blocks don't notice you inside; call and sight surveys from it take half the time |
| Footbridge | $350 | Technician | v0.4 | Cross the Run without wading; nothing touches the stream bed |
| Equipment shed | $900 | Volunteer | v0.4 | +54 storage and a tool wall; its roof feeds the rain barrels and cistern |
| Potting shed and greenhouse | $2,200 | Technician | v0.4 | 32 seed trays; plugs grow in winter; plug time 5 → 3 days |
| Stream gauge with telemetry | $700 | Technician | v0.4 | Live Hemlock Run flow and temperature on the tablet; the storm preview warns 2 game hours earlier |
| Observation tower | $1,800 | Field biologist | v1.0 | 12 blocks tall at the Bench Field edge; a whole-plot bird survey in one visit |
| Field classroom pavilion | $3,000 | Field biologist | v1.0 | School orders pay 25% more; school groups visit on the first day of each season |

## Drones

| Upgrade | Price | Tier | Version | Effect |
| --- | --- | --- | --- | --- |
| Heavy-lift frame | $600 | Technician | v0.4 | Carries 2 crates a trip instead of 1 |
| Rain-rated drone | $900 | Field biologist | v0.4 | Flies in rain; still grounded in storms |
| Second delivery drone | $1,500 | Field biologist | v1.0 | Two deliveries at once |

## Getting around

| Upgrade | Price | Tier | Version | Effect |
| --- | --- | --- | --- | --- |
| E-bike with trailer | $900 | Volunteer | v0.4 | Twice walking speed on paths and the old road; carries 2 soil bags |
| UTV and dump trailer | $6,500 | Field biologist | v1.0 | Fast across the plot; the trailer hauls 96 layers. Driving the same off-trail column 3 times compacts it, so trail layout matters |

## Comfort and look

| Upgrade | Price | Tier | Version | Effect |
| --- | --- | --- | --- | --- |
| Hammock | $60 | Volunteer | v0.4 | A 2-hour nap restores 25 instead of 20 |
| String lights, rugs, curtains, paint sets | $20–120 each | Volunteer | v0.4 | Cosmetic. Outdoor lights come only in warm white, so they don't draw insects |
| Porch furniture and planters | $40–200 each | Volunteer | v0.4 | Cosmetic; planters take native plugs |
| Hot shower | $400 | Technician | v0.4 | The first sleep after a day in the field restores 14 an hour instead of 12 |
| Record player and field recordings | $150 | Volunteer | v1.0 | Plays the cove's own calls from your sound recordings |
| Screened porch | $800 | Technician | v1.0 | Sit out on summer nights with no mosquito drain; frog calls heard from it count toward the call survey |

Item and block ids follow the names in snake\_case (for example `pro_shovel`, `garden_cart`, `equipment_shed_kit`, `utv`). They are added to the Content registry when each one's version is planned.
