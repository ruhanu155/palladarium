# Fun, feel and finish

The rest of the blueprint says what the game simulates. This tab says what makes it fun to play, and when it counts as finished. It draws on Ruhan's other projects: Year of Ice (the first-person survival mod), Shivaa, Hashfire, Emberveil, and the studio's lessons file.

## Lessons applied

| From | Lesson | Here |
| --- | --- | --- |
| Year of Ice | The world hands out goals each morning from its own state; none are required | The morning briefing |
| Year of Ice | Every activity pays twice; nothing becomes obsolete | Payoff table below |
| Year of Ice | No chore over 5 real minutes without a faster way within reach | Time-lapse work and crews |
| Year of Ice | Mistakes cost hours, not the save; risky actions pay now and bill later | Delayed consequences in the storyteller |
| Year of Ice | A handheld device shows honest numbers and never cheers | The field tablet and the HUD tone |
| Year of Ice | Animals near camp habituate and can be named | Named animals |
| Shivaa | Invitations, not gates; the player can ignore any goal and still progress | Goals are optional; no forced story |
| Shivaa v0.2 feedback | "Boring, one dimensional, just a menu press" | Every verb is hands-on with feel; events push back |
| Systems research | A storyteller with a tension budget and variety cooldowns | The storyteller |
| Shivaa | A chronicle that retells history | The Chronicle and the year card |
| Hashfire | Clear visual hierarchy; key things readable at a glance; a clear moment when you earn something | Readability rules; reward moments |
| Emberveil playtest | "Can't see my character", "confused by the start", weak control hints | Control hints, the first-5-minutes test, free roam |
| Studio lessons | Start from a one-page canon; a huge spec up front was an expensive mistake | A one-page canon becomes the north star; this blueprint is the reference |
| Studio lessons | A tester scores a frozen build; a drop blocks release | The playtest gate |

## Pillars for play

Ranked. When two ideas conflict, the higher pillar wins.

1. **The land answers back.** Every action leaves a visible mark, and the land responds within a day or a storm, never "eventually".
2. **Respect the player's time.** Growth and erosion are fast. Long chores have a faster way. No waiting with nothing to do.
3. **Play, don't follow.** Goals are invitations. The player can ignore any of them and still make progress.
4. **Hands-on, never a menu press.** Digging, catching, sorting and planting are things you do with your hands in the world, with feel.
5. **Everything readable.** Honest numbers, clear tells, nothing hidden that the player is punished for not knowing.
6. **Real detail.** Real species, real behavior, real restoration practice.
7. **Calm, but not static.** No combat, but storms, floods, disease and nuisances push back, always telegraphed and always recoverable.
8. **Grow without an end.** Small wins feel big. There is always a next project, but a clear moment when the cove is restored.

## Morning briefing

When the player wakes, the field tablet shows a short briefing built from what is actually happening on the land. It is a set of suggestions, never a quest log.

**Format.**

- One line of weather.
- Up to 3 suggestions, each with why it matters and a marker the player can toggle on.
- One line of overnight news.

**Example.**

> Clear, 14 °C, storm tomorrow afternoon.
>
> 1. Miller Pond is 1 level below the dam crest. A storm tomorrow could overtop it.
> 2. Your survey of Hemlock Run is 12 days old; a fresh kick-net sample would raise the trout grant.
> 3. Warm, still night tonight: good conditions for fireflies. Overnight: a raccoon opened the bin at the RV.

**How suggestions are picked.** Candidates come from the world: alerts, stale surveys, upcoming events, contracts near their deadline, unfinished project steps, new discoveries nearby. Each gets a score for urgency, value and how recently that type was suggested. The top 3 are shown, never more than one of the same type.

**Rules.**

- Every suggestion can be ignored with no penalty except the natural consequence.
- Suggestions name a cause and a place, never a reward amount.
- The briefing can be turned off in Settings.

**Suggestion types.**

| Type | Comes from | Example | Urgency |
| --- | --- | --- | --- |
| Alert | Water near a dam crest, an oxygen crash, an unchecked trap, a sick animal | "West pool is 1 level below its crest; storm tomorrow" | High |
| Upkeep | A stale survey, lab care due, new plantings needing water | "The Run's survey is 12 days old" | Medium |
| Opportunity | An event tonight, a rare species nearby, a mast year | "Warm, still night: fireflies likely" | Medium; expires |
| Project | The next step of an open project | "The gully's next check dam goes at the second bend" | Low |
| Contract | A deadline within 2 days | "Frog call survey due tomorrow night" | High near the deadline |
| Discovery | A clue near where the player has been | "Saw-toothed leaves on the west slope" | Low |
| Learn | An unused tool that fits a current problem | "Try the storm preview before digging below the pond" | Low; shown at most once every 7 days |

## The storyteller

Without combat, the game needs another source of tension. A storyteller paces events so there is always something pushing back, but never a pile-up.

**Tension budget.** The storyteller holds a budget that refills a little each day. Events spend it. Bigger events need more budget, so after a big flood the next days are quiet. The difficulty preset sets the refill rate.

| Size | Examples | Telegraph | Window to act |
| --- | --- | --- | --- |
| Small | Raccoon at the bin; deer in the nursery; a muddy day after rain | Tracks, sounds, a briefing note | Hours |
| Medium | Drought spell (4–7 days); duckweed bloom; a stiltgrass flare-up; jumping worms found | Forecast; journal trend lines | A day or two |
| Large | 100 mm storm; heat wave crashing pond oxygen; disease in a frog population | 1–2 days of forecast and briefing warnings | 1–2 days |
| Opportunity | Firefly night; hawk migration; a rare species passing through; a mast year; a contract with a bonus | Forecast or a sighting | One night to 7 days |

**Rules.**

- Every event is telegraphed, has more than one fix, and can be recovered from.
- After an event, its type cools down so the same kind doesn't repeat back to back.
- Opportunities count as events too, so the rhythm alternates trouble and wonder, not trouble only.
- Events come from the simulation where possible. The storyteller only nudges timing and picks among things the land could really do.

**Delayed consequences.** Some choices pay now and bill later, so the player learns cause and effect:

| Choice | Bill arrives |
| --- | --- |
| Bare soil left unprotected | Muddy creek and a lower stream health score at the next storm |
| Skipped quarantine | Sick frogs 3–5 days later |
| Mulch from an unknown source | Jumping worms found the next season |
| Loose fill in a dam | Sagging and a leak 3 days later |
| Mowing in spring | No bluebird brood that summer |

**Event catalog for the cove.**

| Event | Size | Telegraph | Fixes | If ignored |
| --- | --- | --- | --- | --- |
| Raccoon at the bin | Small | Tracks, rattling at night | Latch or bear-proof the bin | Supplies lost |
| Bear at the feeders (failed mast year) | Small | Torn bark, scat, a briefing note | Bear-proof bin; take feeders in | Feeders and bin wrecked; the bear keeps coming |
| Deer in the nursery | Small | Hoof prints, nibbled tips | Fence or tree guards | Plugs eaten |
| Muddy creek day | Small | Rain overnight | Check silt fences and gully dams | Stream health score dips |
| Stiltgrass seedlings at the road edge | Small | A briefing note in late spring | Pull or mulch | It spreads along the drive |
| Drought spell (4–7 days) | Medium | Forecast 2 days ahead | Shade pools, aerate, rescue tadpoles | Pools dry; tadpoles die |
| Duckweed bloom | Medium | Green film on Miller Pond | Skim it; plant a buffer; cut nutrients | Oxygen crash at dawn |
| Jumping worms found | Medium | Grainy bare soil under new mulch | Remove the source; use only certified mulch | Leaf litter vanishes in that patch |
| Adelgid flare on the hemlocks | Medium | White wool on twigs | Treat or release beetles | Hemlocks decline; the Run warms |
| Hogs rooting the Seep Line | Medium | Torn-up mud, tracks | Fence; a trapping contract | Seep habitat torn up; silt |
| 100 mm storm | Large | Forecast 2 days ahead, rising wind | Check crests and spillways; silt fences; move gear | Dam breach, gully cuts, silt in the Run |
| Heat wave | Large | Forecast; warm nights | Dawn oxygen tests, aerate, shade | Fish and tadpole die-off |
| Chytrid in Miller Pond | Large | Sluggish frogs, a lab result | Swab, quarantine, boot wash | Frog numbers crash |
| Ice storm | Large | Forecast in winter | Clear the drive; leave fallen wood in the Run as habitat | Trees down; the drive blocked |
| Synchronous firefly night | Opportunity | Warm, still evening in late spring | Turn lights off; survey | Missed until next year |
| Big Night | Opportunity | First warm rain after frost | Help salamanders cross the drive; count them | Some are lost on the road |
| Hawk migration day | Opportunity | Clear, northwest wind in fall | Count from Raven Rock | Missed |
| Brook trout spawning | Opportunity | Cold fall nights | Count redds; keep out of the Run | Missed count |
| Rare visitor (otter, elk, bobcat) | Opportunity | A briefing hint, tracks | Camera trap, photo | Missed |
| Mast year | Opportunity | Heavy green acorns in late summer | Plan for more deer browse and bears; survey squirrels and mice | Surprise browsing |
| Mayfly hatch | Opportunity | Warm evening over clean riffles | Watch trout rise; bat detector | Missed |
| BioBlitz | Opportunity | Posted on the orders board 7 days ahead; briefing reminders | Flag stations a day ahead; staff one yourself | The count runs with fewer stations and a lower total |
| Meteor shower night | Opportunity | Weather app 2 days ahead, with the cloud forecast | Lights off; star blanket; climb above the fog to Raven Rock | Missed until the next shower |
| Spotter call | Opportunity | Weather radio: "Spotter reports wanted" | Read the gauges and file a report from the porch | No report; the forecast stays at base accuracy |

### BioBlitz

**Version:** v0.5. It unlocks at Technician.

**How it plays.** Once a year, on the first day of summer, the cove holds a 24-game-hour count of everything on the plot. The player sets up stations ahead of time: a moth sheet, a kick-net, a camera trap, a call point. Volunteers arrive by van, staff the stations, add their counts and leave when it ends. They are visitors like the guided-walk groups, not a village. The species total goes on the year card next to last year's.

**Rules.**

- It is posted on the orders board 7 days ahead and runs from 06:00 on summer day 1 to 06:00 on summer day 2 (20 real minutes).
- A station is a BioBlitz flag next to a placed survey tool, set at least 1 game day before. Types: moth sheet, kick-net reach, camera trap, call point (audio recorder), sweep-net transect, cover-board line, quadrat plot. Up to 6 stations, 8 at Field biologist.
- One volunteer per flagged station arrives by van at the RV at 06:00 and leaves at 06:00 the next day. Volunteers are visitor entities outside the wildlife cap, at most 8, and walk only on paths and mown ground.
- Each game hour, each staffed station rolls against every species its method can detect within 24 blocks. The chance per roll is that method's per-visit detect chance × 0.5. Night methods roll only from 20:00 to 05:00. In a Thunderstorm, volunteers shelter and roll nothing that hour.
- The player's own finds that day count, and so do camera traps and loggers already running.
- The total is the number of distinct species found plot-wide in the 24 hours. A species found only by a volunteer is logged with the volunteer's photo, and it can confirm an open mystery card.
- The storyteller holds back Large events for 2 days before and during the count.

**Rewards.** Holding it: +10 reputation and $100 from the land trust. A record total (higher than every earlier year): +20 reputation, 5 SC and $150 more. New species found that day pay their normal first-sighting bonuses.

**Mistakes.** A badly placed station (a moth sheet within 32 blocks of a bright light, a kick-net on a dry reach) finds little, and its volunteer says why in the log. The cost is a lower total. Skipping the BioBlitz costs only that year's number.

**Items.** BioBlitz flag (`fieldstation:bioblitz_flag`): $5 for 6, Technician. The volunteers and the van are not items.

**Hooks.** Survey methods and detect chances (Systems spec); visitors (guided walks); mystery records; first-sighting bonuses; the storyteller; the year card.

**GameTests.**

- `fieldstation:events/bioblitz_runs_24_hours`: with 4 flagged stations, 4 volunteers arrive at 06:00 on summer day 1, all leave by 06:00 on day 2, and the total is written to the year card.
- `fieldstation:events/bioblitz_record_pays_once`: a total above every earlier year pays the record reward once; a total equal to the record pays only the holding reward.

## Feel

Every verb gets feedback that makes it satisfying, while staying calm.

| Verb | Feedback |
| --- | --- |
| Dig | A different sound and crumb particle per material; the cut face shows its layers; the shovel readout counts down to the plan target |
| Pack | A dull thud that turns to a firm knock as the block packs; a short dust puff |
| Pour or pump water | Water spreads level by level; the sound rises as a basin fills |
| Plant | Soil settles around the plant; a small leaf pops; the seed readout says good, fair or poor |
| Catch | A brief timing ring on the net; a splash on a miss; on a catch, the animal is shown in the hand with its name |
| Kick-net sort | Bugs wriggle in the tray; each correct sort clicks into its group; the score fills like a gauge |
| First sighting | Time slows briefly; the species name and a short line appear; the call plays; a photo slot opens in the journal |
| Drone delivery | A distant hum, then the drone lowers the crate with a soft thunk |
| Reward crate | The lid lifts and items rise one at a time with a soft chime; the cash or credits line ticks up |
| Storm | Wind rises a few minutes before; rain sound swells; the creek gets louder as it runs higher |

**Tone.** The HUD and tablet use honest numbers and plain words, and never cheer. Big moments are marked by quiet: the music stops and the animal's own sound carries the moment. Small wins should feel big because the land is quiet around them.

### Sketchbook

**Version:** v0.3.

**How it plays.** Hold still near an animal with the sketchbook open, and a pencil sketch fills in on the page, stroke by stroke. Move too much or spook the animal and the sketch is spoiled. A finished sketch logs the animal the way a photo does, and if the animal is doing something special, the sketch catches that field moment too.

**Rules.**

- Hold use with the sketchbook while an animal is within 24 blocks, in clear sight, and inside the center 30° of view.
- Fill time is the species' `sketch_time_s`: 10 s for still animals (the hellbender under its rock, a basking turtle, a perched owl), 20 s for most, 30 s for busy ones (dragonflies, songbirds). Habituated animals (wariness at 50% or less) take 25% less time.
- The sketch fills only while the player moves less than 0.5 blocks and the animal stays in view. Looking away pauses it for up to 3 s; longer spoils it.
- If the animal flees (Mobs tab: seen moving, or a noise), the sketch is spoiled. Sketching from closer than 4 blocks raises the flee chance, as in the Mobs sight rules.
- A spoiled page is erased, never used up. The book holds 64 sketches, and old ones can be erased.
- A finished sketch logs the species like a binocular sighting, fills the journal's photo slot in pencil if it is empty, and logs any field moment playing during the sketch.
- Sketches log presence only. They don't update survey estimates or count for photo contracts. They do confirm mystery records (v0.4).

**Feel.** A soft pencil scratch. Strokes trace the outline first, then the shading. The finished page settles with a paper sound. No music.

**Rewards.** First sketch of a species: +1 reputation. Sketch sets: 10 species, +10 reputation; 25 species, +20 reputation and a framed sketch for the RV wall (`fieldstation:framed_sketch`, earned). Each season the land trust newsletter buys one sketch the player picks, for $20.

**Mistakes.** A spoiled sketch costs only the time spent. Chasing an animal to sketch it resets its wariness.

**Hooks.** Mobs wariness, habituation and sight cones; the camera and the journal photo slot; field moments; mystery records; the year card.

**GameTests.**

- `fieldstation:feel/sketch_fills_while_still`: a still green frog 8 blocks away; the sketch finishes in 20 s, logs the species and fills the empty photo slot.
- `fieldstation:feel/sketch_spoils_on_flee`: the frog flees at 12 s; the sketch is spoiled, the page is free again, and nothing is logged.

## Named animals, the Chronicle and the year card

**Named animals.** Some animals become individuals the player follows for years:

- **The hellbender** under the Big Rock, from the start.
- **Any long-lived animal seen 3 times**, such as a box turtle, the heron or a big brook trout, gets a tag in the journal. The player can name it, or the game offers a plain name based on where it lives ("the Run heron").
- **Tagged animals** from mark and recapture.

Named animals keep their own page: sightings, a photo history, their territory on the map. When one dies, the cause is recorded. A long-lived box turtle found on day 1 may still be there in year 10.

**The Chronicle.** The journal keeps a plain timeline of the cove's history, written automatically from events:

> Year 1, summer: the storm that broke Miller Pond's old dam. Year 2, spring: first spotted salamander eggs in the new vernal pool. Year 3, fall: brook trout spawn below the old culvert for the first time.

The player can pin entries and add notes.

**The year card.** At the end of each game year, a single card sums it up:

- soil held
- species returned
- the stream health score's trend
- the coldest night
- the biggest trout measured
- the named animals still around
- the BioBlitz species total, next to last year's
- Returns: tagged animals reported from away, with the farthest one
- Cited: how many times the cove's papers were cited this year
- one old-timer story from that year, quoted (from v0.5; The cove → The old-timer)
- one photo or sketch the player picked

The cards collect in the journal, so the cove's story is told in a stack of them.

## Every activity pays twice

| Activity | Pays in | And also in |
| --- | --- | --- |
| Clearing brush | Materials for piles and wattle | Meadow and young woods habitat |
| Dredging Miller Pond | A deeper, clearer pond | Topsoil for Bench Field |
| Kick-net sampling | The stream health score | A bigger grant, science credits, contract grades |
| Building check dams | A healing gully | Cleaner creek, more stoneflies, more trout |
| Felling a tree | Logs for structures | Light for the meadow edge, a log for salamanders |
| Cutting reeds in winter | Bee hotel stems | Fresh reed growth in spring |
| Collecting seed | Plants for restoration | Sales and orders |
| Photographing | Journal pages | Contracts; named-animal histories |
| Night surveys | Data for grants | Firefly, owl and salamander moments |

Upgrades always have a target, and nothing becomes obsolete. The hand shovel stays useful for fine work after the mini excavator arrives, and the spade outlives both.

## Respect the player's time

- **Time-lapse work.** Holding the use button on a repeated job, such as digging a long trench to plan, tamping a dam layer or planting a row, runs it as a time-lapse. Game time passes faster while energy and materials are spent normally.
- **The 5-minute rule.** No chore takes more than 5 real minutes without a faster way within reach: a better tool, a rented machine, or a volunteer crew posted on the computer.
- **Crews.** A job list from plan mode can be handed to a volunteer crew for a day. They do the simple steps (hauling, planting, pulling weeds) while the player does the skilled ones.
- **Short walks.** The plot is 200 blocks across, the tablet works anywhere, and the drone delivers to field beacons.

## The field tablet

The player carries a field tablet, the handheld version of the RV computer. Its apps:

- morning briefing
- weather
- map with markers
- journal
- alerts
- shop orders to the nearest beacon
- the plan overlay

Lab work, reports, permits and the Research exchange still need the RV, so going home has a purpose.

## Readability and controls

**Visual hierarchy.**

- The land sits in soft, low-saturation colors.
- Animals, plants you can act on, and placed items stand out in brighter colors with at least 3:1 contrast against what's behind them.
- Check in grayscale: if something important disappears, fix its value, not just its hue.
- Each screen uses 3–4 tones plus 1 accent.
- Clean skies; no screen-wide particles; mist and rain stay light enough to see through.

**What the player must always be able to see.**

- Their own position and the held tool.
- The crosshair readout.
- The water level where they're working.
- Any animal they are tracking.

**Controls.**

- Keycap-style hints appear next to the crosshair the first few times a tool is used, then fade.
- A controls overlay opens on H.
- Every hold action can be set to toggle.

**Guidance.**

- First-time hints are contextual and skippable.
- A marker toward a briefing suggestion appears only if the player turns it on.
- Free roam is always allowed: nothing locks the player into a step.

## Definition of done and the playtest gate

**The stranger test.** A build is done when a new player, in their first 15 minutes, can:

1. read every frame: where they are, what they hold, what the water is doing
2. understand without help what they could do next (the briefing and the land itself tell them)
3. change the land with their own hands and see it respond within the session
4. meet at least one animal because of something they built or did
5. want to keep playing
6. hit no errors, freezes or confusing dead ends

**The playtest gate.**

- A tester agent plays a frozen build from a fresh world with real input only, for the first 15 minutes and then 7 full game days (one season) at speed.
- It opens every screenshot and describes what a stranger would see.
- It scores Readability, Controls, Feel, Calm and Fun from 1 to 10, and ranks findings as blocker, major or minor.
- A release ships only with no blockers, and only if no score dropped from the last build.
- Screenshots must include day, night, rain, the RV inside, and a dug pond.

**Process rules carried over from the studio.**

- One focused goal per round.
- Targeted edits, never whole-file rewrites.
- Saves stay compatible.
- Nothing ships without the GameTests passing.
- Any problem that cost an hour or more becomes a written lesson.
- Ruhan decides; the studio recommends.

## Path to a finished game

The finished goal is **v1.0, The Cove**: one complete, polished location that a player can restore from a damaged cove to a healthy one, with an ending moment and an open future. Other biomes, travel and selling animals for cash are expansions after v1.0.

| Version | What's in it | It is done when |
| --- | --- | --- |
| Test slice | The 5 risky tech steps (see the Minecraft integration brainstorm) | All 5 pass on the Mac |
| v0.1 (Milestone 1) | The cove's land, soil layers, water, spring, erosion, digging and packing, Miller Pond area, dragonflies, green frogs, tadpoles, the RV with bed, computer and test bench, journal, grants, no hostile mobs | The Milestone 1 gate tests pass, plus the stranger test |
| v0.2 | Field tablet, morning briefing, the storyteller, surveys and Park Service allotments, reputation points and tiers (shop and recipes unlock by tier), plan mode and storm preview, water chemistry and the nutrient cycle, seasonal events, the food and sleep option, the compost bin, the storm spotter | The stranger test scores at least 7 for Fun |
| v0.3 | Hemlock Run fully alive: fish, stream insects, the stream health score, the hellbender, culvert and gully projects; animals that reshape the land; beaver dam analogs, the sketchbook, spring cleanup day | A full game year plays with no blockers |
| v0.4 | Every cove species, plants and invasives; lab stations; captive breeding phase 1; science credits and the Research exchange; crafting; the Contracts app with only its Orders tab; the field dog; habitat structures, RV living systems, land management, the native plant nursery and pollinators; the first wave of the Upgrades catalog; mystery records, trail craft, dark sky, the heirloom orchard, the seed vault | All cove projects can be finished |
| v0.5 | Contracts (the Contracts tab joins the Contracts app), named animals, the Chronicle, year cards; tags that travel, the BioBlitz, the cove paper, the old-timer; goats, the pack mule and honeybees | Two full game years with no blockers; scores hold |
| v1.0 The Cove | Polish, balance, art and sound passes; Sandbox and difficulty presets; neighbor agreements, the downstream score and the cove easement; the full Upgrades catalog (machines, buildings, vehicles); the restoration finale | See below |

**What "finished" means for v1.0.**

- Every project in the cove can be completed.
- Every species in the cove can be found and logged.
- The cove can be restored from start to end in about 6–8 game years (roughly 30–40 hours) at Standard difficulty.
- No blocker bugs. Every tester score is at least 7. Fun is at least 8.
- Saves made in v0.5 still load.

**The restoration finale.** When, for one full season, every native species on the cove list is rated within or above its Park Service allotment in the season report, using survey estimates no older than 14 days, with invasive cover under 5% and a stream health score of 7 or more on every reach, the Park Service designates the cove a protected reference site. Park Service survey contracts, camera traps and the field dog count as surveys. The year card for that year is special: a slow flight over the cove at dawn, with the hellbender's rock, the trout redds, the meadow and the named animals. Then the game continues, with new parcels, contracts and the biomes beyond.

**Next step.** Write the one-page canon for Field Station, as the studio lessons recommend, then start the test slice.
