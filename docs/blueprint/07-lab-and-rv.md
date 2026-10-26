# Lab and RV

## RV layout

The RV is a prebuilt structure 3 blocks wide and 10 long inside. It follows the floor plan on the main tab: cab, living space, field lab.

| Zone | Length | What's there |
| --- | --- | --- |
| Cab | 2 blocks | Driver's seat, passenger seat, dash with the weather radio |
| Living | 4 blocks | Bed, kitchen counter with sink, computer desk, cabinet, fridge |
| Lab | 4 blocks | 4 station slots along the walls, lab sink, equipment locker |

**Storage.**

| Container | Slots | Holds |
| --- | --- | --- |
| Cabinet | 27 | Anything |
| Equipment locker | 18 | Tools only; tools stored here are repaired overnight |
| Fridge | 9 | Samples (fresh for 24 hours instead of 6), seed in cold treatment, live food cultures |

**Station slots.** Each lab station takes 1 slot. Large stations (vivarium, trailer-only gear) take 2. The test bench and lab sink are fixed; 4 slots are free. The trailer lab adds 6.

**Outside.**

- **Door step and awning.** The awning holds the workbench, where habitat structures are assembled from collected logs, stones and branches.
- **D**rone pad. Shop deliveries and reward crates land here.
- **Hookup panel.** Hose to the spring pump, grey-water valve, generator socket.
- **Roof ladder.** Up to the solar panels and the nursery rack.
- **Boot-wash station.** Next to the door once bought; washing takes 2 seconds and clears disease from boots and nets.
- **Outdoor light.** Over the door. It ships cool white and unshielded, which costs 20 on the dark-sky score and pulls insects from 32 blocks. A light shield, a warm bulb and the motion switch lower that (Insects and bugs → Dark sky).
- **Window blinds.** Use a window to open or close the blinds. At night, open blinds with the interior light on cost 4 on the dark-sky score.

## Computer apps

The RV computer is the player's office. Each app is a card on one home screen.

| App | Shows | Actions |
| --- | --- | --- |
| Shop | Catalog by category, unlocked by tier | Buy; the drone drops the crate on the pad or at a field beacon within seconds |
| Cash and grants | Balance, the grant paid each morning per species, income and spending by week | View the history |
| Contracts | Two tabs. Orders (from v0.4): capped buy orders for plugs, seed, crafted items and honey. Contracts (from v0.5): 2–4 open jobs each season, their deadlines and pay, and jobs in progress | Accept, abandon, hand in data |
| Permits | Permits you hold and ones you can apply for (burn, protected species, animal transfer, stocking) | Apply; most need a tier and a fee |
| Pond monitor | Every habitat record: area, depth, age, water quality, populations, condition; alerts | Open a habitat's chart; set alert levels |
| Terrain map | Plot top-down: cut and fill in the last 7 days, soil lost, turbidity hot spots, planned digs | Switch layers; mark a dig plan |
| Weather | Today plus a 3-day forecast; upcoming events (Big Night, storms, drought) | None; it is a warning system |
| Field journal | The same journal as the handheld item | Read; compare seasons |
| Reports | Finished lab tests and the Park Service season report | File each for $15 and reputation |
| Paper (from v0.5) | Topics with 2+ years of data, their data score, published papers and citations (Progression and economy → The cove paper) | Pick 3 charts and a claim; write (2 game hours, time-lapse); submit |
| Returns (from v0.5) | Postcards for tagged animals reported from away, and a map line from the cove to each (Capture and animal trade → Tags that travel) | Read; open the tagged animal's record |
| Neighbors | Upstream parcels, their events and possible agreements | Offer agreements |
| Travel | Hidden until RV travel ships (after v1.0) | — |
| Settings | Time speed, difficulty, accessibility | Change them |

Alerts appear as small notices on screen even when the player is outside: a pond near its dam crest, an incubator off temperature, or a contract due tomorrow.

**Field tablet.** The handheld version of the computer carries these apps:

- morning briefing
- weather
- map with markers
- field journal, including the Chronicle and year cards
- alerts
- shop, delivering to the nearest field beacon
- plan overlay

These stay in the RV: lab work, reports, permits, the Research exchange, contract hand-in, and the studbook.

## Lab stations

Every station is a block with its own screen. Times are game time.

| Station | Price and tier | Power per day | Capacity | Process | Failure |
| --- | --- | --- | --- | --- | --- |
| Water test bench | Starts in the RV | 0.2 kWh | 1 sample at a time | Insert a bottle and choose tests (nitrate, phosphate, pH, turbidity, oxygen). Each test uses 1 reagent and takes 30 minutes | Samples older than 6 hours (24 in the fridge) read "stale"; oxygen must be tested within 2 hours |
| Microscope | $150, Volunteer | 0.1 kWh | 1 slide | Place a drop from a sample. Identifies algae, plankton and parasites in 15 minutes | None |
| Quarantine tank | $120, Volunteer | 0.4 kWh | 1 group of up to 6 small animals | Holds new or rescued animals for 3 days. Change the water every 2 days | Missed water changes raise ammonia and lower condition; skipped quarantine can carry disease out |
| Rearing tubs | $90, Volunteer | 0.2 kWh | 30 tadpoles or fry | Feed daily from the culture rack. 80% reach release size, against 12% in the wild | Over 30 animals brings disease; missed feeding stunts growth |
| Culture rack | $60, Volunteer | 0.1 kWh | 4 cultures | Fruit flies, springtails, daphnia, worms. Each makes 1 food unit a day | A culture crashes after 7 days unless split; splitting 1 makes 2 |
| Incubator | $200, Technician | 1 kWh | 2 clutches | Set temperature and humidity. Turtle eggs hatch in 10 days. Like real painted turtles, the temperature sets the sex: below 27 °C males, above 30 °C females | A power outage over 6 hours or humidity below 70% makes the clutch fail to develop. The eggs are lost and the journal notes why |
| Treatment station | $180, Technician | 0.2 kWh | 1 animal | Salt dip (10 minutes) or a medicated bath. Set the dose by body weight on a slider | Too low a dose doesn't cure; too high drops condition by 40 and needs 2 days of recovery in the tank |
| Data logger dock | $120 plus $60 per logger, Technician | 0.1 kWh | 4 loggers | Reads the hourly oxygen and temperature log from loggers left in ponds | A logger left over 14 days runs out of battery |
| PCR kit | $400, Field biologist | 0.3 kWh | 8 swabs or filters per run | Tests for chytrid and ranavirus, reads eDNA filters, and gives a population's genetic diversity. Each run takes 2 hours | A contaminated run (dirty tools) gives false results |
| Vivarium (2 slots) | $350, Field biologist | 1 kWh | 1 species group | The Mistwall paludarium: misting twice a day from a reservoir, 22–26 °C, 80–100% humidity, feeding every 2 days | An empty reservoir or a power cut dries the frogs out: condition falls 10 an hour. Below 20, the state wildlife vet collects them (see Animals in care) |

## Lab animal care and release

Lab animals are the hands-on side of the game. Unlike wild animals, they depend on the player every day.

**Daily care** for each enclosure:

| Task | Effect |
| --- | --- |
| Feed (1 food unit per 5 small animals) | Keeps condition up |
| Water change | Resets ammonia, which rises by 1 a day per animal |
| Clean | Lowers disease risk |
| Check temperature and humidity | Shown as green, yellow or red on the station |

**Care round.** Holding use on the lab sink runs feed, water change and clean on every enclosure as a time-lapse, at normal food and water cost. Checks still need a look.

Each enclosure has a care score. It feeds the animals' condition (see the Mobs tab), so neglect shows up exactly as it does outdoors.

**Animals in care.** An animal in the lab never dies. Neglect lowers its condition. Below 20 condition, the state wildlife vet collects it for rehab, you lose 10 reputation and the data, and the enclosure sits empty.

**Eggs.** A clutch can fail to develop (power cut, wrong humidity or temperature, a rough road). The eggs are lost and the journal notes why. This is the one natural loss in the lab.

**Release criteria.** The release button lights up only when all of these are met:

| Check | Rule |
| --- | --- |
| Quarantine | At least 3 days, plus a clear swab for amphibians |
| Condition | 70 or more |
| Stage | Froglet or juvenile; turtles at one season old |
| Season | Not winter |
| Site | A habitat whose microhabitat suits the species |
| Permit | Required for protected species |

**Head-starting** pays off because young animals are safest in the lab. Released frogs reach adulthood 6× more often than wild-hatched ones, and turtles 3×.

**Rescues** come from events, and each teaches a real rule:

| Rescue | What to do |
| --- | --- |
| Tadpoles in a drying puddle during a drought | Net them, rear them, release them |
| Turtle on the driveway | Carry it across in the direction it was heading |
| Injured box turtle | Treatment station, then release where it was found |
| "Orphaned" fawn | Leave it. Its mother is nearby. Picking it up costs reputation |

**Selling animals.** Only captive-bred animals can be sold, and only with a permit, such as dart frogs bred in the vivarium in the tropical biome. Wild-caught animals can never be sold. Phase 1 is approved: captive-bred animals are placed with programs for reputation and science credits. Cash sales come later if playtests support them (see the Capture and animal trade tab).

## Sleep and time

**Clock.** One game hour is 1,000 ticks (50 seconds at normal speed), the same as vanilla. A day is 24 hours, a season 7 days, a year 28 days.

**Speed settings** (Settings app or a hotkey):

| Speed | One game hour takes | Use |
| --- | --- | --- |
| 1× | 50 seconds | Working and watching |
| 5× | 10 seconds | Waiting for a pond to fill |
| 20× | 2.5 seconds | Waiting out a dry spell |
| 60× | Under 1 second | Waiting for a season to turn |

Speed-up runs the simulation in larger steps rather than more ticks, so it costs no extra performance. Nearby mobs fold into their populations while the speed is above 5× and come back when it returns to normal. Speed drops to 1× automatically for alerts and forecast events.

**Sleep.** The bed works from 20:00. Sleep skips to 06:00 and restores 12 energy an hour, as in the prototype. A 2-hour nap is allowed in the day and restores 20. Sleeping without heat on a frozen night halves the energy restored.

**Waking.** The morning briefing opens on the tablet as the player wakes. On the last night of winter, the year card shows first, then the briefing for the new year.

**Multiplayer.** Later only; the game is solo for now. When it comes, there is one shared clock. Speed-up and sleep need every player to agree, through a vote or by everyone being in bed, the same rule as vanilla sleep.

**Saving.** The world autosaves every game morning and on exit, with the vanilla save system.

## Upgrades

| Upgrade | Price | Tier | Effect |
| --- | --- | --- | --- |
| Extra solar panel (up to 2) | $250 each | Volunteer | +1 to +2 kWh a day each |
| Second battery | $400 | Technician | Storage 5 → 10 kWh |
| Generator fuel | $15 for 10 hours | Volunteer | Backup power; loud |
| Larger fresh tank | $150 | Volunteer | 150 → 300 L |
| Larger grey tank | $120 | Volunteer | 120 → 240 L |
| Awning workbench | Included | Start | Builds habitat structures from collected materials. Also grafting (scion onto rootstock), BDA posts and step stones |
| Roof nursery rack | $150 | Volunteer | 8 seed trays |
| Boot-wash station | $40 | Volunteer | Stops disease spreading on gear |
| Waders | $60 | Volunteer | Walk in water up to 4 levels deep at full speed |
| Trailer lab | $1,500 | Field biologist | +6 station slots, its own 5 kWh battery and 2 solar panels |
| Larger RV | $4,000 | Station director | +2 station slots, +27 storage, a reading nook (replaces the co-op bed) |

More upgrades, from owned machines to sheds, drones and comfort, are in Upgrades.

Prices are tuned against the income targets in the Progression and economy tab.

## Taking the RV to a new location

**Status: parked for later. Nothing here is built until the Appalachian cove is fully designed.** The RV stays parked while you work a plot. It moves only when the station goes somewhere new: another biome, a newly bought parcel, or a short field-camp job. Every trip has three parts: pack up, drive, set up.

**Options considered.**

| Option | What the player does | For | Against |
| --- | --- | --- | --- |
| A. Map travel | Pick a destination; a summary screen covers the trip | Cheap to build | Feels like a menu, not a road trip |
| B. Map plus a short drive (lean) | Pick a route, then actually drive the RV along a scenic road with a few stops; can skip to fast travel | A real sense of journey for moderate cost | Needs a vehicle and a road scene |
| C. Open-world driving | Drive across one continuous world between biomes | The most immersive | Far too big for Minecraft chunk loading and our budget |

### 1. Packing up

A *Trip prep* checklist on the computer takes about 1 game hour to work through. Skipping steps has consequences.

| Step | Why it matters |
| --- | --- |
| Pull every trap on the plot | Traps left set are sprung by the Park Service; −5 reputation each |
| Move lab animals into carriers or powered travel tanks | The stress timer runs during the drive unless they're in travel tanks |
| Finish or hand off eggs mid-incubation | Partner programs can hold them; otherwise a rough road can make the clutch fail to develop, and the journal notes why |
| Lock each station for travel | Unlocked stations spill items on the road |
| Empty the grey tank, fill the fresh tank | Needed on the road and at the next site |
| Check fuel against the route | Running dry means a tow and a fee |
| Hitch the trailer lab, if owned | It comes along with its stations |

The plot you leave goes into catch-up mode, as described in the Biomes tab.

### 2. On the road

**Routes.** The travel map offers two ways to each destination:

- **Highway:** fastest and cheapest.
- **Scenic byway:** slower, with more stops, photos and journal entries.

Mountain grades burn more fuel. Storms slow the trip. Winter can close high passes, forcing a detour or a wait.

**Driving.** The drive is a short scene, about 5–10 minutes of real time per trip, on a road that changes from the starting biome to the destination biome. The controls are simple: throttle, brake, steer within the lane. A *skip to arrival* button is always there.

**Stops along the way.**

| Stop | What you do there |
| --- | --- |
| Fuel and supply station | Buy fuel and groceries; empty tanks; refill water |
| Campground | Sleep overnight in the RV on longer trips |
| Scenic overlook | Photos for the journal; sometimes a rare species sighting |
| Bridge or roadside pullout | Quick roadside contracts: sample a stream, count birds |
| Park Service checkpoint | Animal carriers and transfer permits are checked |

**Road events.** Each is small and has a clear right choice:

- a turtle crossing the road: stop and carry it across
- a road closure: take the detour
- a flat tire: use the repair kit, or wait for roadside help for a fee
- a storm: pull over until it passes

### 3. Setting up

**Choosing a campsite.** On arrival, a site picker shows the possible RV pads on the plot. Each one is rated on:

- flatness
- sun for the solar panels
- distance to water (the pump hose reaches 20 blocks)
- road access
- how close it sits to wildlife habitat

A pad next to the best pond is convenient, but the generator and the drone pad will scare that pond's wildlife. The choice is a real trade-off, and it is fixed until the next trip.

**Setup tasks.**

1. Level the RV on its jacks.
2. Hook the hose to the nearest spring or creek.
3. Angle the solar panels.
4. Place the drone pad.
5. Unlock the stations.
6. Settle lab animals: release them if this is their native range, or keep them.

### Where trips can go

| Destination | Stay | Notes |
| --- | --- | --- |
| Another biome plot | As long as you like | The plots described in the Biomes tab |
| A newly bought parcel | As long as you like | Next to an existing plot |
| Field camp (idea) | About 7 game days | A small 64 × 64 site with one job: a hellbender survey on a river, a salmon count, a burned forest recovery. Pays well; earns science credits |

### In Minecraft

- **Packing the RV.** The parked RV is a block structure. When you pack up, it is saved as a structure template that keeps every station and its contents. At the new site, it is placed on the chosen pad. The old spot becomes an empty pad.
- **The drive.** The road is its own dimension: chains of pre-built road segments, about 2,000 blocks per route, with the scenery blending from biome to biome. Distance is compressed. Top speed stays near 12 blocks a second so chunks load smoothly.
- **The vehicle.** During the drive, the RV is a vehicle entity with an exterior model and the player in the driver's seat. It follows the road with lane steering instead of free physics.
- **When it's built.** Travel arrives with the second biome, after Milestone 1. Field camps come after that.
