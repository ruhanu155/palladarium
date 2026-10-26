# Weather and climate

Weather is the cove's heartbeat. It fills ponds and cuts gullies, wakes frogs and grounds the drone, decides when salamanders walk and when trout crowd into cold water. Almost every system reads it. This tab defines the weather itself. Per-biome climate averages live in the Biomes tab, the single source; the Systems spec season table copies the cove's values, and this model is tuned to produce them.

**Design goals.**

- **Readable.** The player can see weather coming: clouds build, wind shifts, the barometer falls.
- **Real.** Patterns follow how mountain weather actually behaves.
- **Fair.** Forecasts are honest about uncertainty, and big weather is always telegraphed (the storyteller's rules).
- **Fast.** Weather changes within a game day, so there is always a reason to plan.

## The weather model

**Weather states.** At any moment, the cove is in one state:

| State | Rain | Wind | Notes |
| --- | --- | --- | --- |
| Clear | None | Calm to light | Big day-night temperature swing |
| Partly cloudy | None | Light | The most common summer state |
| Overcast | None | Light | Small temperature swing |
| Fog | None | Calm | Mornings; see mountain weather |
| Drizzle | 2–5 mm an hour | Light | Long and soaking; good for amphibians |
| Rain | 6–12 mm an hour | Moderate | Steady runoff |
| Thunderstorm | 12–28 mm an hour, in bursts | Gusty | Lightning; flash rises in the Run |
| Hail (inside a Thunderstorm) | Hailstones 6–44 mm in short bursts, on top of the storm's rain | Gusty | Not a separate state. Spring and summer only: 20% of thunderstorms drop pea hail (6 mm), 5% penny (19 mm), 1% quarter (25 mm) or larger. Visual only; it damages nothing. Stones lie on surfaces for 2 game hours (see Storm spotter) |
| Snow | Snow layers instead of rain | Light to moderate | Below 0 °C |
| Freezing rain | Ice on everything | Light | Rare; ice storm |
| Windy and clear | None | Strong | After a cold front |

**Fronts.** Weather mostly changes when a front passes.

| Front | Sequence | Lasts |
| --- | --- | --- |
| Cold front | Clouds thicken from the west → a line of storms or rain → wind shifts to the northwest → clear, cooler and windy | Half a day of rain; 1–2 cool, clear days after |
| Warm front | High thin clouds → overcast → long drizzle or rain → warm and humid | 1 day or more of wet weather |
| High pressure | Settled, clear or partly cloudy | 2–4 days |

A new front arrives every 2–4 game days, more often in spring and less often in midsummer and early fall.

**Daily cycle.** Temperature follows a curve, coldest just before dawn and warmest mid-afternoon. Clear skies make a bigger swing, and clouds a smaller one. Summer afternoons build pop-up storms even without a front.

**Seasons and months.** Each 7-day season stands for the meteorological months: spring is March–May, summer June–August, fall September–November and winter December–February. Real dates such as the meteor showers map onto game days by their place in those months.

**How the next state is chosen.** Each game hour, the model checks the current front and the time of day, then picks the next state from a table of chances for that season. Fronts set the big picture, and the hourly step adds variety within it.

## Mountain weather in the cove

The Blue Ridge makes its own weather. These effects are what make the cove feel like a mountain place.

| Effect | What happens | In the game |
| --- | --- | --- |
| Upslope showers | Moist air pushed up the ridge cools and rains on the slopes | Rain falls 20% heavier on the upper slopes and Raven Rock than on the cove floor |
| Pop-up storms | Summer heat lifts air into afternoon thunderstorms | About half of summer days bring a storm between 14:00 and 19:00 |
| Cold-air drainage | On clear, calm nights, cold air slides downhill and pools on the cove floor | The cove floor and Miller Pond run 3–5 °C colder than the slopes by dawn; frost forms there first |
| Valley fog | That pooled cold air fills with fog by morning | Fog fills the cove floor and lifts by mid-morning; the ridge sits above it in sun |
| Blackberry winter | A cold snap in late spring, when blackberries bloom | Once in most years: 2–3 cold nights after spring warmth, frost on the cove floor, insects and frogs go quiet |
| Late-summer tropical remnants | The leftovers of tropical storms drift inland | Every year in late summer (the storyteller picks the day): 1–2 days of very heavy rain, up to 100 mm in a day. This is the year's big storm in Extremes and its biggest flood test |
| Ice storms | Rain falls through freezing air near the ground | Rare in winter: ice coats every branch; trees and limbs come down |
| Ridge wind | Wind is stronger on top than in the cove | Wind 1.5× on the ridge and Raven Rock; the cove floor stays sheltered |

The terrain map has a **microclimate layer**: the cold pockets, warm slopes, foggy floor and windy ridge, built from these effects.

## Weather variables

| Variable | Range | Used by |
| --- | --- | --- |
| Air temperature | −15 to 33 °C in the cove | Everything living; snow vs rain; frost |
| Humidity and dew point | 30–100% | Fog, dew on grass at dawn, how fast things dry, amphibian activity |
| Cloud cover | 0–100% | Sun on solar panels and plants, the day-night swing, reptiles basking |
| Rain or snow rate | 0–28 mm an hour | Water and erosion (Systems spec) |
| Wind speed and direction | 0–60 km/h | Smell (Mobs tab), insects, burn permits, evaporation, the drone, tree falls |
| Air pressure | Falling, steady or rising | The barometer's storm warning: a fast fall means a front within half a day |
| Sunshine | 0–1 | Solar power, evaporation, plant growth, water temperature |
| Soil frost | Depth in layers | Frost heave (Systems spec); frozen ground soaks nothing, so winter rain runs off |
| Snowpack | Snow layers, 0–8 per column | Melts by warmth points; the spring melt pulse |
| Lightning | Strikes per storm | Rarely splits a tall tree into a snag; can start a small fire only in a dry spell |

**Snow and melt.** Snow piles in layers like the vanilla snow layer. It drifts deeper on the downwind side of the ridge and in hollows. It melts 1 layer per day for every 4 °C above freezing, faster in sun and in rain. Each melted layer becomes water in the soil or as runoff, so a warm rain on deep snow is the winter's flood risk.

## Year types and extremes

**Year types.** At the start of each game year, the model picks a type. The type shifts the chances in every season, so no two years play the same.

| Year type | Chance | Feels like |
| --- | --- | --- |
| Normal | The rest, 5 parts | The averages in the Biomes tab |
| Wet year | The rest, 2 parts | More fronts and storms; ponds full; gullies tested; amphibians boom |
| Dry year | After a year that wasn't Dry, the next year is Dry with a 50% chance. After a Dry year, the next year is never Dry | Longer high-pressure spells; a drought spell (4–7 days) in summer; the Run runs low and warm |
| Wild year | 10%; a Wild year can't also be Dry | Bigger swings: an early warm spell, a hard late frost, a remnant tropical storm |

Each year, Wild is rolled first at 10%. If the year isn't Wild, Dry is rolled next by the drought rule. Any year that is neither Wild nor Dry is Normal or Wet, split 5 to 2 (about 71% Normal, 29% Wet).

The Park Service's season report names the year type afterward, and the year card records it.

**Extremes.** Each is rare and always telegraphed. The storyteller paces them with its tension budget.

| Extreme | How often | What it does |
| --- | --- | --- |
| 100 mm storm | Every year in late summer; the storyteller picks the day | 100 mm in a day: floods, dam tests, gully cutting |
| Spring storm | Every year in spring | 40–60 mm in a day; tests silt fences and new banks |
| Drought spell (4–7 days) | One year in two, never two years running; always in a dry year | Spring flow down to 30%; ponds shrink; heat stress |
| Heat wave (3+ days above 30 °C) | 1 in 3 years | Oxygen crashes; trout to refuges |
| Ice storm | 1 in 4 years, winter | Limbs and trees down; wood falls into the Run |
| Hard late frost | 1 in 3 years | Kills new leaves and early flowers; a setback for the nursery |
| Windstorm | Rare | Shallow-rooted trees on wet soil blow over, opening gaps |
| Deep snow (6+ layers) | 1 in 3 years | Tracks everywhere; a big spring melt pulse |

## Forecasting

The forecast is honest: it gives chances, not promises, and it gets less sure the further out it looks.

| Lead time | What the forecast says | How often it's right |
| --- | --- | --- |
| Next 6 hours | The state, temperature and rain amount | About 90% |
| Tomorrow | The main state, the high and low, the chance of rain or storms | About 75% |
| 2–3 days | The fronts coming, as ranges ("storms likely Thursday, 20–60 mm") | About 60% |
| Days 4–5 | A trend only; needs the weather station | About 45% |

When a forecast misses, it misses the way real ones do: a storm arrives earlier or later, or rain comes in lighter or heavier, but a big storm is never invented from nothing.

**Instruments improve the forecast and teach reading the sky.**

| Instrument | Get it | What it adds |
| --- | --- | --- |
| Weather radio in the RV | Start | The basic forecast and storm warnings |
| Thermometer (max-min) | $10 | The night's low and day's high at that spot; shows cold-air pooling |
| Rain gauge | Craft | Rain that fell here in 24 hours |
| Barometer | $35 | A falling-pressure warning about half a day before a front |
| Rain gauge network | 3+ gauges | Local rain amounts; forecasts of how much rain falls where get better |
| Weather station | Earned (Station director kit) | Forecast out to 5 days; local wind and humidity; accuracy +10% at every lead time |

**Reading the sky.** Even without instruments, the player can learn the tells:

- High thin clouds mean a warm front within a day.
- Building towers on a summer afternoon mean storms by evening.
- A sudden cool gust and a dark western sky mean a cold front within the hour.

### Storm spotter

**Version:** v0.2.

**How it plays.** When a storm crosses the cove, the weather radio asks for spotter reports. The player steps onto the RV porch, reads the rain gauge, looks at the hail on the rail and checks the staff gauge on the Run, then files a short report from the tablet's weather app. The weather office checks it against its radar. Good reports earn reputation and sharpen the forecast for 7 days.

**When reports are wanted.** From the hour the radio calls "Spotter reports wanted" (the start of any Thunderstorm, or Rain above 8 mm an hour) until that state ends. At most 1 report per game hour and 3 per storm. The porch (within 6 blocks of the RV door, under the awning) counts as shelter, so filing there is never outdoor work during lightning.

**What a report holds.** Any 2 or all 3 of these fields:

| Field | How the player reads it | Good when |
| --- | --- | --- |
| Rain rate | Rain gauge: mm in the last game hour. Without a gauge, pick light, moderate or heavy by eye | Within ±25% of the true rate, or the right band by eye (light under 6, moderate 6–12, heavy over 12 mm an hour) |
| Hail size | Hailstones bounce on the porch rail and lie there for 2 game hours. Compare: none, pea (6 mm), penny (19 mm), quarter (25 mm), golf ball (44 mm) | The right size class; a hail pad shows the exact size |
| Creek level | The staff gauge in the Run, read from the bank or with binoculars up to 24 blocks | Within 1 water level (1/8 block) of the true level |

**Hail.** Only in Thunderstorms in spring and summer: 20% of storms drop pea hail, 5% penny and 1% quarter or larger. Hail is visual and damages nothing. Quarter size, 25 mm, is the real US threshold for severe hail.

**Rewards.** A good report: +2 reputation, capped at +10 a season from spotting. A good report of quarter-size hail or a flash rise of 4+ levels: +5 reputation instead, plus 3 SC from v0.4. Each storm with at least one good report adds a spotter bonus: +5% forecast accuracy at every lead time for 7 days, stacking to +10%, and the next Large weather event is telegraphed 6 game hours earlier. The briefing says "Your reports improved this week's forecast."

**Mistakes.** A field outside its tolerance makes the report "doesn't match radar": −1 reputation, and that hour's slot is used. Walking to the gauge in a flash rise is the player's choice; wading the Run moves them downstream as in Flash rises. Nothing worse happens.

**Items.**

| Item | Id | Price | Tier | Use |
| --- | --- | --- | --- | --- |
| Staff gauge | `fieldstation:staff_gauge` | $25 | Volunteer | Block set in a pool of the Run, marked in water levels |
| Hail pad | `fieldstation:hail_pad` | $8 for 4 | Volunteer | Foam pad on a post; after a storm it shows the largest hailstone exactly |
| Rain gauge | `fieldstation:rain_gauge` (the existing crafted gauge) | Craft | Volunteer | Rain in the last game hour and the last 24 hours |

Real basis: volunteer weather networks such as CoCoRaHS use standard rain gauges and foam hail pads, and storm spotters size hail against coins and balls.

**Hooks.** The weather model's true rain rate, hail and water levels; the forecast accuracy table above; the rain gauge network; Flash rises; the lightning rule; the morning briefing (Opportunity: "Storms this afternoon: spotter reports wanted"); reputation.

**GameTests.**

- `fieldstation:weather/spotter_report_scored_against_truth`: in a forced 20 mm-an-hour storm, a report of 18 mm with the right creek level is good (+2 reputation); a report of 10 mm is rejected (−1 reputation).
- `fieldstation:weather/spotter_bonus_lasts_7_days`: after one good report, forecast accuracy at every lead time is 5 points higher for 7 days, then back to base.

## What weather changes

| System | How weather affects it |
| --- | --- |
| Water | Rain, snowmelt and evaporation set every water level (Systems spec). Storms raise the Run within the hour, and it falls back over a day |
| Soil | Rain saturates slopes, which can then slide (Landscaping tab). Frozen ground soaks nothing. Frost heave loosens packed blocks |
| Erosion | Storms do most of the year's erosion. Snowmelt is the spring's big pulse |
| Plants | Growth needs warmth and sun. Frost kills seedlings and new leaves. Drought stresses shallow roots. Wind and ice bring trees down |
| Animals | Rain brings amphibians out; heat sends everything to shade; cold sends reptiles under; fog and wind change senses (Mobs tab) |
| Fish | Water temperature follows the air with a lag; storms muddy the Run and push fish to cover (Fish mechanics tab) |
| Insects | Warmth points set emergence; rain and wind ground flyers (Insects and bugs tab) |
| Water chemistry | Heat and calm nights cause oxygen crashes; storms wash in silt and phosphorus |
| The player | Cold and wet raise energy use. Fog shortens sight. Storms slow outdoor work |
| RV power | Sun drives solar; cold raises heating needs |
| The drone | Grounded in thunderstorms and wind above 40 km/h |
| Work rules | No in-stream work while the Run is high; burns only on dry, still days; pond work best in late summer's low water |
| Events | The storyteller schedules weather events and ties others to the weather: Big Night on the first warm rain, firefly nights on warm still evenings, hatches on warm evenings |

## How weather looks and sounds

**Looks.**

- Skies stay clean: soft gradients, simple cloud layers, no screen-wide noise.
- Rain is light, thin streaks you can see through. Its density rises with intensity but never hides the ground at working distance.
- Fog lies in the cove as a soft layer you can climb out of, with the ridge clear above.
- Frost whitens the grass on the cove floor at dawn and melts as the sun reaches each spot.
- Snow covers the ground in layers. Tracks show in it.
- Ice glazes branches after an ice storm and rims the edges of still water.
- After a storm clears in the late afternoon, a rainbow can show over the cove.

All of this follows the readability rules in *Fun, feel and finish*: the player, the held tool and the water level stay readable in every weather.

**Sounds.**

- Rain on leaves, on the RV roof, and on the pond, each with its own sound.
- Wind in the trees, louder on the ridge.
- Thunder after lightning, delayed by distance. Counting the seconds tells how far away the storm is, about 3 seconds per kilometer, as in real life.
- Dripping branches after rain.
- The Run gets louder as it rises.
- In fog and snow, sound is muffled.

**HUD.** The weather icon shows the state, temperature, and a small wind arrow. The tablet's weather app shows the hourly forecast, the fronts on a simple map, and the barometer trend.

## The night sky

**Version:** v0.4, with Dark sky in the Insects and bugs tab.

The night sky is part of the weather. Clouds, fog and the moon decide what shows; the dark-sky score (Insects and bugs) decides how much.

**Moon.** Vanilla's 8-phase cycle, one phase per game night (phase 0 full, phase 4 new), read through the clock layer.

**Milky Way.** It shows from 22:00 to 04:00 when cloud cover is 20% or less, the moon phase is 3, 4 or 5, and the dark-sky score is 70 or more. Its opacity is (score − 70) ÷ 20, capped at 1, so it is full at 90 and above. In summer it is brightest, with the galactic core low in the south, as seen from 35° N. In winter it is a fainter band high overhead. Fog on the cove floor hides it; from Raven Rock, above the fog, it shows.

**Meteor showers.** Two a year, on the real showers' calendar:

| Shower | Real peak | Game nights | Game rate at peak | Best hours |
| --- | --- | --- | --- | --- |
| Perseids | August 12–13; about 100 an hour under ideal skies | Summer day 6 (peak); days 5 and 7 at half rate | About 16 per game hour (one every 3 real seconds) | Half rate 22:00–00:00, full 00:00–04:00; radiant in the northeast |
| Geminids | December 13–14; about 120 an hour under ideal skies | Winter day 2 (peak); days 1 and 3 at half rate | About 20 per game hour (one every 2.5 real seconds) | Full 21:00–04:00; radiant high in the east |

A game hour is 50 real seconds, so the real hourly rate is shown at about one sixth. Every clear night also has 2 sporadic meteors per game hour.

Shown rate = peak rate × hour factor × (1 − cloud cover) × sky factor. The sky factor is 1.0 at a dark-sky score of 90+, 0.6 at 70–89, 0.3 at 40–69 and 0.1 below 40. The weather app lists a shower 2 days ahead with its cloud forecast, and the briefing offers it as an Opportunity.

**Counting.** Lying on the star blanket (Insects and bugs), the player taps use on each meteor. A count within ±20% of the server's tally for that hour is logged in the journal. 10 or more counted in one night logs a "meteor night" field moment, and the first Milky Way seen logs one too. A counted peak night pays +3 reputation and 2 SC.

**In Minecraft.** The dimension's custom sky rendering for `fieldstation:appalachia` (DimensionSpecialEffects or its 26.2 replacement; check at M0) draws the Milky Way as a band texture at the opacity above, and meteors as short streak quads. The server rolls each meteor's time and track from the day seed and sends the next hour's list, so the client shows exactly what the server counts. Cost: one small list per game hour, under 0.01 ms.

**GameTests.**

- `fieldstation:weather/meteor_rate_follows_clouds_and_score`: on the Perseid peak night, 00:00–04:00, with a fixed seed and a score of 95, 50–80 meteors fall at 0% cloud and none at 100% cloud.
- `fieldstation:weather/milky_way_needs_dark_clear_new_moon`: the band shows at moon phase 4, 10% cloud and a score of 92; it is hidden at phase 0, at 40% cloud, or at a score of 60.

## Weather gear and safety

| Gear | Price | Effect |
| --- | --- | --- |
| Rain jacket | $40 | Stays dry in rain: no wet-and-cold energy penalty |
| Winter jacket | $50 | No extra energy drain below 0 °C (Player and tools tab) |
| Waders | $60 | Dry feet in water; full speed wading |
| Headlamp | $25 | Light in fog and at night; red mode for wildlife |
| Ice cleats | $20 | Full speed on ice and frozen slopes |

**Wet and cold.** Being wet raises energy use outdoors: ×1.25 when wet, ×1.5 when wet and below 5 °C. Drying off in the RV takes a game hour.

**Lightning.** Lightning never strikes the player; the canon allows no damage except falls and drowning. During a nearby thunderstorm:

- the HUD shows "Lightning close: shelter in the RV"
- outdoor work costs double energy
- the drone stays grounded

Players learn to shelter without being punished.

**Flash rises.** In heavy storms, the Run can rise 4–6 levels within an hour. Wading in it then moves the player a few blocks downstream. Nothing is dropped. The HUD warns first. Nothing worse happens.

## Weather in Minecraft

- **Our model drives vanilla's visuals.** The vanilla weather cycle is turned off (game rule `minecraft:advance_weather` false; check the exact 26.2 name at M0). The model sets vanilla's rain and thunder levels each game hour, so vanilla rain, thunder and snow visuals show our weather.
- **One weather per plot.** Each biome dimension has its own model and state, synced to the client.
- **Fog** is drawn as a height-limited layer that follows the microclimate map, so it fills the cove floor and leaves the ridge clear.
- **Snow and ice are real blocks.** The model places vanilla snow layers per column and melts them by the snowpack rules. Freezing turns the top water levels of still columns into `fieldstation:ice_layer` blocks with the same level count; thaw reverses it, and water is conserved. Flowing water never freezes.
- **Lightning** uses the vanilla bolt for the flash and thunder. Fire is off except during a dry spell, when a strike can light a small, slow ground fire the player can put out.
- **Wind** shows as drifting particles, swaying leaves and grass, and the HUD arrow.
- **Frost and dew** are a texture tint on grass blocks, by column temperature and humidity.
- **Cost.** The model is one small state machine per plot, stepped once a game hour, plus per-column temperature from the microclimate map. Together they cost under 0.05 ms averaged; stepped once a game hour.

**GameTests.**

- A cold front produces rain, then a wind shift to the northwest, then cooler, clearer weather.
- On a clear, calm night, the cove floor ends at least 3 °C colder than the upper slope.
- 4 snow layers melt in about one game day at 16 °C.
- A forecast for tomorrow matches the actual weather at least 70% of the time over 100 simulated days.
- The drone refuses to fly in a thunderstorm.
