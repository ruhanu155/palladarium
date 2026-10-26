# Field Station

A calm, non-combat NeoForge mod for Minecraft Java 26.2. You park a field station RV in a damaged 200×200 Southern Appalachian cove and bring it back to life: one pond, one creek, one species at a time.

**Status:** ready for development (M0 scaffold). No game systems are written yet.

- **Design:** `docs/blueprint/` is an export of the Claude Docs "Field Station Blueprint" (the live doc is the source of truth). Start with `docs/blueprint/README.md`, then the canon tab and the Minecraft development tab.
- **Mod id:** `fieldstation`. **Packages:** `com.fieldstation.<system>`.

## Toolchain (pinned in `gradle.properties`)
| Piece | Version |
| --- | --- |
| Minecraft | 26.2 |
| NeoForge | 26.2.0.93 |
| Java | 25 (Temurin) |
| Gradle | 9.8.2 (wrapper) |
| ModDevGradle | 2.0.148 |
| Flowing Fluids | 1.0.7 (NeoForge, 26.2), required |
| GeckoLib | 5.5.6 (NeoForge, 26.2), required |
| JUnit / ArchUnit | 6.1.3 / 1.5.1 |

The pin moves only after a milestone ships, on a branch named `upgrade/mc-<version>`. NeoForge 26.3 exists, but Flowing Fluids has no 26.3 build yet.

## Build on the Mac
1. Install a Java 25 JDK (for example `brew install --cask temurin@25`).
2. `./gradlew build` builds the mod, runs the unit and ArchUnit tests and runs contentLint.
3. `./gradlew runClient` opens Minecraft with the mod, Flowing Fluids and GeckoLib.
4. `./gradlew smoke` runs the full studio check and prints `SMOKE PASSED`.

CI (`.github/workflows/build.yml`) runs the same on every pull request.

## Rules for working here
- Work on `dev/<department>/<topic>` branches and open a pull request. Never push to `main`.
- Each system owns its package and its saved data. Systems talk only through `com.fieldstation.api` (ArchUnit enforces this).
- Server decides, client shows: client code lives only in `com.fieldstation.client`.
- Only `com.fieldstation.clock` reads or writes world time.
- Data goes in `data/fieldstation/<kind>/<id>.json`, with a singular kind and snake_case keys with unit suffixes. `contentLint` enforces this.
- At most 8 mixins, each with a test.
- Never rename an id after the first beta; retire it with `addAlias`.
- Versions are `MAJOR.MINOR.PATCH+mc<version>`. Changelog entries come from `Changelog:` commit trailers.

## Layout
```
src/main/java/com/fieldstation/   package map (empty packages with their ownership notes)
src/main/templates/META-INF/      neoforge.mods.toml template
src/main/resources/data/fieldstation/   data files (test_instance/ for GameTests)
src/generated/resources/          datagen output, checked in
src/test/java/                    JUnit and ArchUnit
tools/sim/                        headless balance simulator (empty)
fixtures/saves/                   one save per release, for migration tests
pack/                             packwiz modpack (set up at v0.1)
docs/blueprint/                   design export
```
