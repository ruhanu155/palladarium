# Capture and animal trade

**Status: phase 1 approved (October 19, 2026). Phases 2 and 3 are decided after playtests.**

Capture is a science tool. The player catches animals to identify, measure, tag, move, head-start, or found a breeding group, and almost always lets them go. Only animals bred in captivity, with a recorded family line, can ever be sold, and only to licensed buyers. Wild-caught animals can never be sold. This mirrors real conservation rules and keeps the game from rewarding emptying the land.

**Options considered for selling.**

| Option | How it works | For | Against |
| --- | --- | --- | --- |
| A. Captive-bred only, licensed buyers (proposed) | Breed animals in the lab from a few founders; sell offspring with a recorded lineage to zoos, agencies, researchers, schools and the legal pet trade | Real-world accurate; a deep mid-game system; rewards good husbandry | The most to build |
| B. No selling, placements only | Raised animals are donated to programs for reputation and science credits, never cash | Simple; clean message | Loses a money stream and some motivation |
| C. Open market with legal checks | Any legal species can be sold, with permit checks | Easy to understand | Pushes players to strip their own land; unrealistic |

Phase 1 (v0.4) is Option B. Option A is phases 2–3, decided after playtests.

## The pitch

**The fantasy.** You start by saving one pond. A year later, the Park Service is asking your RV lab for brook trout to restock a creek two valleys over. A school's classroom is raising tadpoles from your F1 green frogs. A zoo wants a pair from your best breeding line. The field station grows from a restoration project into a small conservation program, and the animals you raised turn up on other land.

**Why it fits.**

- It turns lab skills into outcomes beyond your own plot.
- It gives the mid game a long-term goal: build a healthy breeding line.
- It reuses systems already planned: lab stations, the stress timer, condition, genetic diversity, the drone, the Orders tab and permits.
- New parts are small: a breeding tank, the studbook screen, a live-animal carrier and new order types.

**What makes it fun.**

- **Husbandry puzzles.** Work out each species' breeding cue: a rain chamber for frogs, a cool winter rest for salamanders, a light timer for fish.
- **Pairing.** The studbook turns choosing pairs into a light puzzle to keep diversity high.
- **Visible impact.** A "Released elsewhere" page on the computer shows where your animals went and how they are doing.
- **Prestige.** Late-game partnerships, such as a hellbender head-start program, earn no money but carry the most reputation in the game. The hellbender head-start release contract opens when the stream health score holds at 7 or more for 4 straight seasons, and it brings a second adult to the Run.

**Money.** Phase 1 earns no cash. If phase 2 is approved, breeding should bring in about 15–25% of mid-game income. That is meaningful but never the main source, because grants for wild populations stay bigger.

**Risks and how the design handles them.**

| Risk | Answer |
| --- | --- |
| It feels like pet farming | Conservation buyers come first; sales are capped by orders; wild animals are never sold |
| Players strip their own land | Founder take comes only from the surplus above the Park Service allotment; grants reward wild populations |
| Too much to build at once | Roll it out in phases (below) |
| A muddled message | Every sale screen states the animal's lineage and where it goes |

**Phased rollout.**

| Phase | What ships | Money from animals |
| --- | --- | --- |
| 1 (v0.4) | Option B: breed and place animals with programs for reputation and science credits | None |
| 2 (after playtests) | Sales to conservation and research buyers | Yes, capped |
| 3 (tropical biome) | The licensed pet trade, for captive-bred tropical species only | Yes, capped |

**The ask.** Phase 1 is approved and goes into v0.4. Its playtests answer whether phases 2 and 3 are worth building.

## Capture methods

| Method | Tool | Catches | How it plays | Needs |
| --- | --- | --- | --- | --- |
| Dip net | Dip net | Tadpoles, larvae, small fish, insects, crayfish | Sneak close and time the swing while the animal is still | Collecting permit |
| Hand capture | Gloves (wet for amphibians) | Frogs, salamanders, turtles, snakes | Sneak within 1 block of a resting animal | Collecting permit |
| Seine net | Seine and 2 stakes | Schools of small fish | Stake one end, then walk the other through a pool | Collecting permit |
| Minnow trap | Minnow trap (bought) | Small fish, newts, tadpoles | Set underwater; check within 24 hours | Collecting permit |
| Hoop trap | Hoop trap (bought) | Turtles | Set in open water with a float so turtles can breathe; check daily | Collecting permit |
| Cover boards | Crafted boards | Salamanders, snakes | Lay boards and lift them every few days with the brush | None |
| Drift fence and pitfalls | Fence and buckets (bought) | Salamanders and toads on the move | A low fence steers walking animals into buckets; best on Big Night; check every morning | Collecting permit |
| Live box trap | Box trap (bought) | Raccoons, rabbits | Only for tagging or collaring on an agency contract | Agency contract |
| Mist net | Mist net | Birds | Only with a banding permit; check every 2 game hours (about 100 seconds) | Field biologist and banding permit |
| Rock flip and snorkel | Mask | Hellbenders | Only on an agency survey contract | Agency contract |

**Traps must be checked.** An animal left past its check time loses 10 condition per check period. At twice the time, the trap door springs, the animal escapes at condition 40, and the player loses 5 reputation and the data. Players set only as many traps as they can tend.

## The capture process

1. **Find.** Spot the animal with binoculars, sounds, cover boards or a survey. Wariness decides how close you can get.
2. **Approach.** Sneak, wade slowly, or set a trap where the animal moves.
3. **Catch.** Use the method from the table above. A missed swing startles the animal and raises its wariness for a while.
4. **Handle.** The stress timer starts: 60 seconds in a hand or net, 5 minutes in a bucket or cup. Wet gloves for amphibians.
5. **Process.** A quick work-up screen opens. Confirm the species, then measure length, weight, sex and condition. Optionally, mark the animal with a tag. Each step takes a few seconds of the timer.
6. **Decide.**
   - Release on the spot (the default).
   - Move it to another of your habitats that suits it.
   - Take it to the lab for quarantine, head-starting or as a breeding founder (with a permit).

The work-up data goes into the journal, so even a quick catch-and-release improves survey quality, contract grades and science credits.

## Mark and recapture

Tagging turns catching into counting. Real biologists estimate populations this way.

**Tags.** A small tag (a PIT tag, or a colored elastomer mark for salamanders) gives the animal a permanent ID. The tag reader shows that ID when the animal is caught again.

**The estimate.**

1. Catch and tag a group of animals: M tagged.
2. Release them and wait a day or more.
3. Catch again: C caught, of which R carry tags.
4. The journal estimates the population as N ≈ M × C ÷ R and shows a likely range around it.

More effort gives a tighter range.

**Decided:** the journal shows *estimated* populations from the player's own surveys, next to the Park Service allotment for the area. The simulation's true numbers stay hidden except in Sandbox mode. See *Surveys and Park Service allotments* in the Systems spec.

### Tags that travel

**Version:** v0.5.

**How it plays.** Animals tagged on the plot leave, and sometimes a report comes back. A monarch tagged on Bench Field in fall turns up in Mexico that winter. A banded waterthrush comes back to the same reach next spring. A PIT-tagged brook trout is read by an antenna downstream after the culvert is fixed. Reports arrive as postcards in the RV computer's Returns inbox. A map in the journal draws a line from the cove to where each animal went.

**In the sim.** No entity ever leaves the plot. Each habitat record keeps `tagged_count` per species next to N. When the population sim moves animals out (migration, dispersal or emigration), the number of tagged animals that leave is rolled from the share tagged: each leaver is tagged with chance `tagged_count / N`. Each tagged leaver then rolls once for a report on its species' `report` rule.

| Tag | Species | Item, price, tier | Report rule | Real basis |
| --- | --- | --- | --- | --- |
| Wing sticker | Monarch | `fieldstation:monarch_tag`, $10 for 25, Volunteer (collecting permit) | 1 in 100 tagged fall migrants is "recovered" at a Michoacán overwintering site; the postcard arrives on winter day 2–6 with the distance, about 2,500 km | Volunteer tagging programs recover roughly 1 tag in 100 |
| Leg band | Louisiana waterthrush; other banded songbirds | `fieldstation:bird_band`, $10 for 20, Field biologist (banding permit) | Returns to the same reach next spring with a 0.5 chance (resighted on the next survey visit there); 1 in 300 is reported from the wintering grounds in Central America or the Caribbean | Adult waterthrushes are faithful to their breeding stream |
| PIT tag | Brook trout, hellbender (on contract) | `fieldstation:pit_tag` (existing) | A tagged fish that moves off the plot is read by the Park Service antenna 2 km downstream with a 0.6 chance; trout can only pass once the culvert is fixed | PIT antennas read tags as fish swim past |
| Satellite tag | Deer, box turtle | Satellite tag set (Research exchange, 60 SC) | Always reports: the last ping before it left the plot | Existing item |

Elastomer marks on salamanders stay local; they never produce reports.

**Rules.**

- Tagging is a step in the capture work-up (the process above) and needs that tag's permit.
- The work-up records the tag number with length, weight, sex and condition. A record missing any of those is incomplete.
- Handling a monarch for more than 10 seconds of the timer lowers its condition by 5 and halves its report chance. It never dies.
- The tag reader beeps before tagging an animal that already carries a tag, and no tag is used.
- Each report is a postcard: tag number, where and when it was tagged, where it turned up, the distance, and a line of real context.

**Rewards** (also in Progression and economy). Each report: $10 data fee and +2 reputation. A monarch recovery: $25, +5 reputation and 3 SC. A banded bird returning to its reach: +2 reputation and 1 SC. The year card gets a Returns line (Fun, feel and finish).

**Mistakes.** An incomplete record still produces reports, but the postcard says "We couldn't match your record" and pays nothing (it costs data). Over-handling costs condition and report chance. Nothing costs items or lives.

**Hooks.** The population sim's out-moves; the capture work-up; monarch tagging (Insects and bugs); the banding permit; PIT tags and the culvert project (Fish mechanics); the Satellite tag set; the RV computer; the journal map; the year card.

**GameTests.**

- `fieldstation:capture/tag_report_rolls_on_emigration`: with a fixed seed, 1,000 tagged monarchs leave in fall; 4–16 recoveries arrive on winter days 2–6, and no entity crosses the plot edge.
- `fieldstation:capture/incomplete_record_pays_nothing`: a waterthrush banded with no weight recorded returns; the postcard arrives and pays no money, reputation or SC.

## Permits and animal status

Every animal the player holds carries a status that can't be changed. The status decides what the player may do with it.

| Status | Where it came from | Allowed | Not allowed |
| --- | --- | --- | --- |
| Wild | Caught on the player's land | Process and release; move between the player's own habitats; hold up to 3 days | Selling, ever. Keeping or breeding only as a Founder under a propagation permit |
| Wild, protected | Hellbender, box turtle and other listed species | Handling only on an agency contract | Keeping, moving or selling |
| Head-start | Eggs or young taken into the lab under a permit | Rear and release on site | Selling |
| Founder | Wild adult kept for breeding under a propagation permit | Breed; return to the wild later | Selling, ever (its F1 offspring can be placed or sold) |
| Captive-bred (F1, F2…) | Born in the lab | Breed, release with a permit, sell to licensed buyers | Release outside its native range |
| Bought stock | From a licensed breeder in the shop | Same as captive-bred | — |

**Permits.**

| Permit | Cost | Tier | Lets the player |
| --- | --- | --- | --- |
| Scientific collecting | $25 a year | Volunteer | Catch, process, tag, move and briefly hold wild animals |
| Captive propagation | $100 a year | Technician | Keep founders and breed them |
| Wildlife dealer license | $250 a year | Field biologist | Sell captive-bred animals to licensed buyers |
| Transfer | $50 per group | Technician | Move animals between plots or biomes, within native range |
| Banding | Earned | Field biologist | Mist-net and band birds |

## Captive breeding

**Getting founders.**

- **From your land:** up to 2 pairs per species per year. Allowed only from the surveyed surplus above the low end of the Park Service allotment, and only when the population's diversity is 0.7 or more, so small populations are protected automatically. With the propagation permit, the Park Service runs the diversity test for you ($20 per population); with a PCR kit you test it yourself.
- **From licensed breeders:** bought in the shop (Technician tier).

**Where they breed.**

| Setup | For | Notes |
| --- | --- | --- |
| Rearing tubs | Tadpoles and larvae | Already in the lab |
| Breeding tank | Fish, newts | A new lab station, $160 |
| Vivarium | Tree frogs, dart frogs | Field biologist; cove tree frogs now, dart frogs in the tropical biome later |
| Breeding pen | Frogs and salamanders in their own pond | Crafted from planks and mesh and set inside a real pond; cheap, but exposed to the weather |

**Breeding cues.** Animals breed only when their real cues are copied.

| Cue | For | How the player gives it |
| --- | --- | --- |
| Rain chamber | Frogs | Misting plus a temperature drop at night |
| Cool rest | Salamanders and turtles | 4 days in a cool box (the fridge or an unheated trailer) in winter |
| Day length | Fish | A light timer on the tank |

Condition must be 70 or more.

**The studbook.** Every captive animal gets a record:

- ID, species, sex, parents
- generation (F1, F2…) and the founders' origin site
- diversity score

The studbook screen suggests pairings that keep diversity high. Breeding relatives lowers it, and low-diversity animals sell for less and breed worse.

**Cost.** Breeding takes station space, power, food from the culture rack, and daily care. In phase 1 it pays in reputation and science credits.

## Selling (phase 2 and later, not approved)

Sales happen through the Orders tab of the Contracts app on the RV computer and need the wildlife dealer license. In phase 1 the same buyers take placements instead, for reputation and science credits.

| Buyer | Wants | Pays | Per season | Phase 1 placements per season |
| --- | --- | --- | --- | --- |
| Zoo or aquarium program | F1 or later, diversity 0.8+, condition 80+ | The highest price | 1–2 orders | 1–2 |
| Agency restoration program | Healthy juveniles of a native species, to release at other sites | Good price plus reputation | 2–3 orders | 2–3 |
| Research lab | Small groups of common species; tadpoles; cultures | Moderate | 3–4 orders | 3–4 |
| School program | Egg or tadpole kits for classrooms | Low cash, high reputation | 2 orders | 2 |
| Licensed pet trade | Only species on the legal list for captive-bred sale, never protected species (for example, dart frogs bred in the tropical biome) | Moderate | Steady but capped | — (phase 3 only) |

**Price.**

price = base price × condition factor (0.5–1.2) × diversity factor (0.7–1.1) × stage factor (juveniles 1.0, adults 1.3) × demand (by season)

For example, a pair of F1 green frogs in top condition might sell for about $40 to a research lab, and a group of 10 brook trout juveniles about $150 to an agency program.

**Shipping.** Sold animals travel in a live-animal carrier by drone. The drone only flies them between 5 and 30 °C, and the carrier pauses the stress timer for 2 hours. Buyers rate each delivery, and that welfare rating moves future prices up or down.

## Keeping it honest

- **Status is permanent.** An animal's status and origin are written when it is first caught or born and can never be edited. A wild-caught animal can never become "captive-bred". Its offspring born in the lab are F1 captive-bred, as in real life.
- **Founder limits** protect the land: 2 pairs per species per year, only from large, healthy populations.
- **Audits.** The agency biologist checks the studbook on visits. A mismatch, such as an animal missing from the record, suspends the dealer license for a season and costs reputation.
- **Welfare counts.** Escapes from unchecked traps, stress in transit and neglect cost reputation and lower buyer ratings.
- **Sales are capped** by orders per season. Breeding is a side income and never the main one.
- **No sale ever earns grants.** Grants come only from wild populations on the land, so there is no reason to empty a pond to stock the lab.

## How it works in Minecraft

- **Caught animals are items.** Like vanilla's bucket of axolotl, a caught animal is an item that carries its data as item components: species, ID, measurements, status, lineage, and the stress timer. Releasing it spawns the entity, or adds it to the right population if the player is far from others.
- **Traps are block entities.** Each one runs its own check timer and holds whatever walks or swims in, drawn from the habitat's population at that spot.
- **The work-up screen** is a small panel that opens on capture, in the same clean style as the other screens.
- **The studbook** lives in the `fieldstation:station` SavedData on the `fieldstation:appalachia` level. The animals in lab stations are entries in it, not live entities, until the player opens the station's view.
- **Carriers** are items with a temperature check before the drone takes off.
- **The cover-board lift** reuses the vanilla brush's slow-sweep action, so lifting a board feels careful.
