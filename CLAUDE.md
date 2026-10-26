# Field Station: agent guide

Read first, in this order. Do not read the whole blueprint.
1. `docs/blueprint/README.md`: the tab index.
2. The Canon tab: the pillars and fixed facts. It wins over every other tab.
3. The Minecraft development tab: pin, package map, naming, vanilla replacement, tests, performance budget, saves.
4. Only the tabs your task touches.

Hard rules (a PR that breaks one is sent back):
- No hostile mobs, no combat. No player action ever kills an animal. Animals in care never die.
- Systems talk only through `com.fieldstation.api`. `com.fieldstation.sim` has no Minecraft imports. Server decides, client shows.
- Only `com.fieldstation.clock` touches world time. All durations are in game days: a season is 7 days, a year is 28.
- Ids are `fieldstation:<snake_case>`. Data lives in `data/fieldstation/<kind>/<id>.json` with snake_case keys and unit suffixes.
- At most 8 mixins. The performance budget is mod time ≤ 13 ms average and ≤ 20 ms peak, and 20 TPS.
- Branch `dev/<department>/<topic>`, open a PR, never push to `main`. `./gradlew smoke` must print SMOKE PASSED.
- Ruhan decides; the studio recommends. Development starts only when Ruhan says go.
