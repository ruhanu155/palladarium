package com.fieldstation.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/** Studio package rules (Minecraft development tab -> Tests and the smoke -> ArchUnit rules). */
class ArchitectureTest {
    private static final String[] SYSTEMS = {
        "terrain", "water", "soil", "plants", "habitat", "wildlife", "fish", "tame", "weather",
        "survey", "economy", "station", "player", "story", "world", "clock"
    };
    private static final Set<String> TIME_METHODS = Set.of(
        "getGameTime", "getOverworldClockTime", "getDefaultClockTime", "clockManager",
        "getTotalTicks", "setTotalTicks", "addTicks", "setPaused", "setRate", "moveToTimeMarker"
    );
    private static JavaClasses classes;

    @BeforeAll
    static void load() {
        classes = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.fieldstation");
    }

    @Test
    void simHasNoMinecraftImports() {
        noClasses().that().resideInAPackage("com.fieldstation.sim..")
            .should().dependOnClassesThat().resideInAnyPackage("net.minecraft..", "net.neoforged..", "com.mojang..")
            .allowEmptyShould(true).check(classes);
    }

    @Test
    void systemsTalkOnlyThroughApi() {
        for (String a : SYSTEMS) {
            for (String b : SYSTEMS) {
                if (a.equals(b)) continue;
                noClasses().that().resideInAPackage("com.fieldstation." + a + "..")
                    .should().dependOnClassesThat().resideInAPackage("com.fieldstation." + b + "..")
                    .because("systems talk only through com.fieldstation.api")
                    .allowEmptyShould(true).check(classes);
            }
        }
    }

    @Test
    void onlyClientUsesClientClasses() {
        noClasses().that().resideOutsideOfPackage("com.fieldstation.client..")
            .should().dependOnClassesThat().resideInAnyPackage("net.minecraft.client..", "com.fieldstation.client..")
            .allowEmptyShould(true).check(classes);
    }

    @Test
    void onlyClockReadsWorldTime() {
        // 26.2 WorldClock / ClockManager / Level time methods (verified via javap against 26.2.0.93).
        noClasses().that().resideOutsideOfPackage("com.fieldstation.clock..")
            .should().callMethodWhere(com.tngtech.archunit.core.domain.JavaCall.Predicates.target(
                com.tngtech.archunit.base.DescribedPredicate.describe("a world time or clock method",
                    t -> TIME_METHODS.contains(t.getName()))))
            .allowEmptyShould(true).check(classes);
    }
}
