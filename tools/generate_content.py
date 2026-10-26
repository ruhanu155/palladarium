import json
import os
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
RES_DATA = ROOT / "src/main/resources/data/fieldstation"
RES_ASSETS = ROOT / "src/main/resources/assets/fieldstation"
JAVA_ROOT = ROOT / "src/main/java/com/fieldstation"
TEST_ROOT = ROOT / "src/test/java/com/fieldstation"

def write_json(path: Path, obj):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2) + "\n", encoding="utf-8")

def write_text(path: Path, text: str):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(text.strip() + "\n", encoding="utf-8")

# ---------------------------------------------------------------------------
# 1. CONTENT REGISTRY LISTS (Blocks, Items, Species, Plants, Mysteries, Shop)
# ---------------------------------------------------------------------------
LAYERED_BLOCKS = [
    ("topsoil", "Topsoil", "minecraft:block/dirt"),
    ("subsoil", "Subsoil", "minecraft:block/coarse_dirt"),
    ("red_clay", "Red Clay", "minecraft:block/terracotta"),
    ("gravel_layer", "Gravel Layer", "minecraft:block/gravel"),
    ("silt", "Silt", "minecraft:block/mud"),
    ("mud", "Mud", "minecraft:block/mud"),
    ("trail_tread", "Trail Tread", "minecraft:block/dirt_path_top"),
    ("ice_layer", "Ice Layer", "minecraft:block/ice"),
]

SIMPLE_BLOCKS = [
    ("clay_film", "Clay Film", "minecraft:block/clay"),
    ("spring_head", "Spring Head", "minecraft:block/mossy_cobblestone"),
    ("soft_rush", "Soft Rush", "minecraft:block/seagrass"),
    ("meadow_grass", "Meadow Grass", "minecraft:block/short_grass"),
    ("native_shrub", "Native Shrub", "minecraft:block/azalea_leaves"),
    ("silt_fence", "Silt Fence", "minecraft:block/oak_planks"),
    ("coir_log", "Coir Log", "minecraft:block/hay_block_side"),
    ("bluebird_box", "Bluebird Box", "minecraft:block/oak_planks"),
    ("wood_duck_box", "Wood Duck Box", "minecraft:block/spruce_planks"),
    ("bat_box", "Bat Box", "minecraft:block/dark_oak_planks"),
    ("rv_bed", "RV Bed", "minecraft:block/white_wool"),
    ("rv_computer", "RV Computer", "minecraft:block/iron_block"),
    ("lab_bench", "Lab Bench", "minecraft:block/quartz_block_side"),
    ("rv_door", "RV Door", "minecraft:block/iron_door_top"),
    ("drone_pad", "Drone Pad", "minecraft:block/Smooth_stone"),
    ("field_beacon", "Field Beacon", "minecraft:block/copper_block"),
    ("beaver_dam_analog", "Beaver Dam Analog", "minecraft:block/oak_log"),
    ("apple_tree", "Old Apple Tree", "minecraft:block/oak_leaves"),
    ("seed_vault", "Seed Vault", "minecraft:block/barrel_side"),
    ("compost_bin", "Compost Bin", "minecraft:block/composter_side"),
]

COVE_PLANTS = [
    ("meadow_grass", "Meadow Grass", "ground", [1, 6], 48, 0.90),
    ("soft_rush", "Soft Rush", "aquatic", [4, 7], 72, 0.95),
    ("native_shrub", "Native Shrub", "shrub", [2, 5], 240, 0.80),
    ("eastern_white_pine", "Eastern White Pine", "canopy", [1, 5], 480, 0.85),
    ("black_cherry", "Black Cherry", "canopy", [2, 5], 480, 0.85),
    ("wild_bergamot", "Wild Bergamot", "ground", [2, 5], 96, 0.85),
    ("broadleaf_arrowhead", "Broadleaf Arrowhead", "aquatic", [5, 7], 96, 0.90),
    ("jewelweed", "Jewelweed", "edge", [4, 7], 72, 0.85),
    ("bur_reed", "Bur-Reed", "aquatic", [5, 7], 96, 0.92),
    ("hazel_alder", "Hazel Alder", "shrub", [4, 7], 240, 0.88),
    ("tall_fescue", "Tall Fescue", "ground", [1, 5], 48, 0.85),
    ("japanese_stiltgrass", "Japanese Stiltgrass", "ground", [2, 6], 36, 0.60),
    ("chinese_privet", "Chinese Privet", "shrub", [2, 6], 180, 0.65),
    ("japanese_honeysuckle", "Japanese Honeysuckle", "ground", [2, 6], 48, 0.65),
    ("tree_of_heaven", "Tree of Heaven", "canopy", [1, 5], 300, 0.60),
    ("mockernut_hickory", "Mockernut Hickory", "canopy", [1, 4], 480, 0.85),
    ("chestnut_oak", "Chestnut Oak", "canopy", [1, 4], 480, 0.85),
    ("northern_red_oak", "Northern Red Oak", "canopy", [2, 5], 480, 0.85),
    ("serviceberry", "Serviceberry", "shrub", [2, 5], 240, 0.82),
    ("common_blue_violet", "Common Blue Violet", "ground", [3, 6], 48, 0.80),
    ("sassafras", "Sassafras", "canopy", [1, 5], 360, 0.82),
    ("old_apple", "Old Apple", "canopy", [2, 5], 360, 0.82),
]

ITEMS = [
    ("field_shovel", "Field Shovel", 1, "minecraft:item/iron_shovel"),
    ("soil_bag", "Soil Bag", 1, "minecraft:item/bundle"),
    ("wheelbarrow", "Wheelbarrow", 1, "minecraft:item/minecart"),
    ("hand_tamper", "Hand Tamper", 1, "minecraft:item/stone_pickaxe"),
    ("seed_pouch", "Seed Pouch", 1, "minecraft:item/bundle"),
    ("binoculars", "Binoculars", 1, "minecraft:item/spyglass"),
    ("sample_bottle", "Sample Bottle", 16, "minecraft:item/glass_bottle"),
    ("test_reagent", "Test Reagent", 64, "minecraft:item/sugar"),
    ("field_journal", "Field Journal", 1, "minecraft:item/writable_book"),
    ("field_tablet", "Field Tablet", 1, "minecraft:item/filled_map"),
    ("rental_key", "Rental Key", 1, "minecraft:item/tripwire_hook"),
    ("reed_plug", "Reed Plug", 64, "minecraft:item/seagrass"),
    ("field_bucket", "Field Bucket", 1, "minecraft:item/water_bucket"),
    ("survey_stake", "Survey Stake", 16, "minecraft:item/stick"),
    ("benchmark_stake", "Benchmark Stake", 1, "minecraft:item/blaze_rod"),
    ("planting_trowel", "Planting Trowel", 1, "minecraft:item/iron_hoe"),
    ("work_gloves", "Work Gloves", 1, "minecraft:item/leather"),
    ("wading_gloves", "Wading Gloves", 1, "minecraft:item/rabbit_hide"),
    ("compost", "Compost", 16, "minecraft:item/bone_meal"),
    ("worm_castings", "Worm Castings", 16, "minecraft:item/brown_dye"),
    ("mycorrhizal_inoculant", "Mycorrhizal Inoculant", 16, "minecraft:item/glowstone_dust"),
    ("grocery_box", "Grocery Box", 16, "minecraft:item/chest_minecart"),
    ("sandwich", "Field Sandwich", 16, "minecraft:item/bread"),
    ("camp_stew", "Camp Stew", 16, "minecraft:item/mushroom_stew"),
    ("coffee", "Thermos Coffee", 16, "minecraft:item/honey_bottle"),
    ("blackberries", "Wild Blackberries", 16, "minecraft:item/sweet_berries"),
    ("ramps", "Wild Ramps", 16, "minecraft:item/kelp"),
    ("chanterelles", "Chanterelles", 16, "minecraft:item/brown_mushroom"),
    ("kick_net", "Kick Net", 1, "minecraft:item/fishing_rod"),
    ("sorting_tray", "Sorting Tray", 1, "minecraft:item/bowl"),
    ("cover_board", "Cover Board", 16, "minecraft:item/oak_trapdoor"),
    ("tag_reader", "PIT Tag Reader", 1, "minecraft:item/recovery_compass"),
    ("sketchbook", "Field Sketchbook", 1, "minecraft:item/book"),
    ("loppers", "Loppers", 1, "minecraft:item/shears"),
    ("post_driver", "Post Driver", 1, "minecraft:item/iron_axe"),
    ("fencing_pliers", "Fencing Pliers", 1, "minecraft:item/flint_and_steel"),
    ("mcleod", "McLeod Trail Tool", 1, "minecraft:item/golden_hoe"),
    ("clinometer", "Clinometer", 1, "minecraft:item/compass"),
    ("grafting_knife", "Grafting Knife", 1, "minecraft:item/iron_sword"),
    ("edna_kit", "eDNA Filter Kit", 16, "minecraft:item/glass_bottle"),
    ("data_logger", "Data Logger", 16, "minecraft:item/clock"),
]

# Species: id, name, sci, microhabitats, min_age_h, min_area, min_reed, chance_h, k_mult, grant, bonus, mystery
SPECIES = [
    ("green_darner", "Green Darner", "Anax junius", ["open_water"], 24, 4, 0.0, 0.05, 0.30, 6, 25, False),
    ("green_frog", "Green Frog", "Lithobates clamitans", ["shallows", "reed_bed"], 72, 6, 0.06, 0.02, 0.20, 6, 30, False),
    ("tadpole", "Green Frog Tadpole", "Lithobates clamitans", ["shallows"], 72, 6, 0.06, 0.04, 0.80, 6, 20, False),
    ("bullfrog", "American Bullfrog", "Lithobates catesbeianus", ["open_water"], 168, 40, 0.0, 0.01, 0.05, 6, 40, False),
    ("crayfish", "Appalachian Brook Crayfish", "Cambarus bartonii", ["open_water", "shallows"], 48, 4, 0.0, 0.03, 0.40, 6, 35, False),
    ("great_blue_heron", "Great Blue Heron", "Ardea herodias", ["shallows"], 72, 15, 0.0, 0.04, 0.05, 6, 50, False),
    ("white_tailed_deer", "White-Tailed Deer", "Odocoileus virginianus", ["woods_edge", "wet_meadow"], 24, 20, 0.0, 0.04, 0.05, 6, 40, False),
    ("eastern_bluebird", "Eastern Bluebird", "Sialia sialis", ["dry_meadow", "wet_meadow"], 24, 20, 0.0, 0.04, 0.05, 6, 35, False),
    ("spotted_salamander", "Spotted Salamander", "Ambystoma maculatum", ["shallows", "woods"], 48, 12, 0.0, 0.04, 0.60, 6, 55, False),
    ("wood_frog", "Wood Frog", "Lithobates sylvaticus", ["shallows", "woods"], 24, 12, 0.0, 0.04, 0.30, 6, 45, False),
    ("spring_peeper", "Spring Peeper", "Pseudacris crucifer", ["shallows", "reed_bed"], 24, 10, 0.05, 0.02, 0.50, 6, 30, False),
    ("red_spotted_newt", "Red-Spotted Newt", "Notophthalmus viridescens", ["open_water", "shallows"], 168, 20, 0.05, 0.01, 0.15, 6, 40, False),
    ("american_toad", "American Toad", "Anaxyrus americanus", ["shallows", "bare_ground"], 24, 4, 0.0, 0.02, 0.25, 6, 25, False),
    ("red_backed_salamander", "Red-Backed Salamander", "Plethodon cinereus", ["woods"], 24, 10, 0.0, 0.05, 0.30, 6, 30, False),
    ("dusky_salamander", "Northern Dusky Salamander", "Desmognathus fuscus", ["shallows", "woods_edge"], 24, 6, 0.0, 0.03, 0.40, 6, 35, False),
    ("five_lined_skink", "Common Five-Lined Skink", "Plestiodon fasciatus", ["warm_slope"], 24, 4, 0.0, 0.02, 0.20, 6, 35, False),
    ("fence_lizard", "Eastern Fence Lizard", "Sceloporus undulatus", ["warm_slope"], 24, 4, 0.0, 0.02, 0.20, 6, 35, False),
    ("box_turtle", "Eastern Box Turtle", "Terrapene carolina", ["woods_edge"], 48, 10, 0.0, 0.02, 0.10, 6, 50, False),
    ("painted_turtle", "Eastern Painted Turtle", "Chrysemys picta", ["open_water", "shallows"], 72, 20, 0.0, 0.02, 0.15, 6, 40, False),
    ("american_woodcock", "American Woodcock", "Scolopax minor", ["wet_meadow", "woods_edge"], 48, 20, 0.0, 0.02, 0.05, 6, 65, True),
    ("ruffed_grouse", "Ruffed Grouse", "Bonasa umbellus", ["woods", "woods_edge"], 48, 20, 0.0, 0.02, 0.05, 6, 65, True),
    ("louisiana_waterthrush", "Louisiana Waterthrush", "Parkesia motacilla", ["shallows", "woods_edge"], 48, 10, 0.0, 0.03, 0.10, 6, 55, False),
    ("wood_duck", "Wood Duck", "Aix sponsa", ["open_water", "shallows"], 48, 20, 0.0, 0.05, 0.10, 6, 50, False),
    ("red_winged_blackbird", "Red-Winged Blackbird", "Agelaius phoeniceus", ["reed_bed"], 24, 10, 0.10, 0.03, 0.15, 6, 30, False),
    ("chimney_swift", "Chimney Swift", "Chaetura pelagica", ["dry_meadow"], 24, 10, 0.0, 0.05, 0.20, 6, 40, False),
    ("eastern_red_bat", "Eastern Red Bat", "Lasiurus borealis", ["open_water", "woods_edge"], 24, 10, 0.0, 0.02, 0.20, 6, 45, False),
    ("tricolored_bat", "Tricolored Bat", "Perimyotis subflavus", ["open_water", "woods_edge"], 48, 10, 0.0, 0.02, 0.15, 6, 70, True),
    ("monarch", "Monarch Butterfly", "Danaus plexippus", ["dry_meadow", "wet_meadow"], 24, 10, 0.0, 0.04, 0.30, 6, 35, False),
    ("diana_fritillary", "Diana Fritillary", "Speyeria diana", ["woods_edge", "wet_meadow"], 48, 15, 0.0, 0.02, 0.15, 6, 60, False),
    ("brook_trout", "Brook Trout", "Salvelinus fontinalis", ["open_water", "shallows"], 48, 8, 0.0, 0.04, 0.40, 6, 55, False),
    ("blacknose_dace", "Eastern Blacknose Dace", "Rhinichthys atratulus", ["shallows"], 24, 6, 0.0, 0.05, 0.50, 6, 30, False),
    ("mottled_sculpin", "Mottled Sculpin", "Cottus bairdii", ["shallows", "gravel_bar"], 48, 6, 0.0, 0.03, 0.35, 6, 60, True),
    ("beaver", "North American Beaver", "Castor canadensis", ["open_water", "reed_bed"], 96, 30, 0.05, 0.02, 0.05, 6, 75, False),
    ("muskrat", "Muskrat", "Ondatra zibethicus", ["open_water", "reed_bed"], 72, 20, 0.10, 0.02, 0.10, 6, 45, False),
    ("killdeer", "Killdeer", "Charadrius vociferus", ["gravel_bar", "bare_ground"], 24, 8, 0.0, 0.03, 0.10, 6, 35, False),
    ("tree_swallow", "Tree Swallow", "Tachycineta bicolor", ["open_water", "wet_meadow"], 24, 15, 0.0, 0.05, 0.15, 6, 35, False),
    ("canada_goose", "Canada Goose", "Branta canadensis", ["open_water", "dry_meadow"], 48, 25, 0.0, 0.02, 0.05, 6, 30, False),
    ("mink", "American Mink", "Neogale vison", ["shallows", "woods_edge"], 72, 10, 0.0, 0.02, 0.10, 6, 70, True),
    ("southern_flying_squirrel", "Southern Flying Squirrel", "Glaucomys volans", ["woods"], 48, 15, 0.0, 0.02, 0.15, 6, 65, True),
    ("bobcat", "Bobcat", "Lynx rufus", ["woods", "woods_edge"], 96, 20, 0.0, 0.01, 0.05, 6, 80, True),
    ("ring_necked_snake", "Ring-Necked Snake", "Diadophis punctatus", ["woods", "warm_slope"], 48, 8, 0.0, 0.02, 0.15, 6, 55, True),
    ("timber_rattlesnake", "Timber Rattlesnake", "Crotalus horridus", ["warm_slope", "woods"], 96, 12, 0.0, 0.01, 0.08, 6, 80, True),
    ("barred_owl", "Barred Owl", "Strix varia", ["woods", "woods_edge"], 72, 15, 0.0, 0.02, 0.08, 6, 70, True),
    ("red_salamander", "Northern Red Salamander", "Pseudotriton ruber", ["shallows", "woods"], 48, 6, 0.0, 0.02, 0.25, 6, 65, True),
]

MYSTERIES = [
    ("mink", "mammal", "track", ["camera_trap", "night_sighting"], ["long_tailed_weasel", "river_otter", "raccoon"]),
    ("tricolored_bat", "bat", "call", ["bat_detector", "dusk_sighting"], ["little_brown_bat", "northern_long_eared_bat", "eastern_red_bat"]),
    ("southern_flying_squirrel", "mammal", "sign", ["camera_trap", "thermal_camera"], ["gray_squirrel", "eastern_chipmunk", "white_footed_mouse"]),
    ("bobcat", "mammal", "track", ["camera_trap"], ["gray_fox", "coyote", "house_cat"]),
    ("ring_necked_snake", "reptile", "shed", ["cover_board"], ["worm_snake", "garter_snake", "red_bellied_snake"]),
    ("timber_rattlesnake", "reptile", "shed", ["day_sighting", "camera_trap"], ["eastern_rat_snake", "northern_water_snake", "copperhead"]),
    ("ruffed_grouse", "bird", "call", ["audio_recorder", "sighting"], ["pileated_woodpecker", "wild_turkey", "american_woodcock"]),
    ("american_woodcock", "bird", "sign", ["dusk_sighting", "audio_recorder"], ["wilsons_snipe", "killdeer", "american_robin"]),
    ("barred_owl", "bird", "feather", ["audio_recorder", "camera_trap"], ["great_horned_owl", "eastern_screech_owl", "red_tailed_hawk"]),
    ("red_salamander", "amphibian", "edna", ["night_sighting", "rock_flip"], ["spotted_salamander", "dusky_salamander", "red_backed_salamander"]),
    ("mottled_sculpin", "fish", "edna", ["kick_net", "rock_flip"], ["blacknose_dace", "brook_trout", "fantail_darter"]),
]

SHOP_ITEMS = [
    ("grass_seed", "fieldstation:seed_pouch", 40, 20, "starter", "seed", "drone"),
    ("reed_plug", "fieldstation:reed_plug", 20, 35, "starter", "plant", "drone"),
    ("sample_bottle", "fieldstation:sample_bottle", 3, 10, "starter", "lab", "drone"),
    ("test_reagent", "fieldstation:test_reagent", 10, 25, "starter", "lab", "drone"),
    ("bluebird_box", "fieldstation:bluebird_box", 1, 30, "starter", "habitat", "drone"),
    ("wood_duck_box", "fieldstation:wood_duck_box", 1, 45, "starter", "habitat", "drone"),
    ("bat_box", "fieldstation:bat_box", 1, 35, "starter", "habitat", "drone"),
    ("wheelbarrow", "fieldstation:wheelbarrow", 1, 80, "starter", "tool", "drone"),
    ("hand_tamper", "fieldstation:hand_tamper", 1, 40, "starter", "tool", "drone"),
    ("gravel_pack", "fieldstation:gravel_layer", 16, 30, "starter", "material", "drone"),
    ("mini_excavator_rental", "fieldstation:rental_key", 1, 250, "technician", "rental", "truck"),
]

def generate_resources():
    lang = {
        "itemGroup.fieldstation": "Field Station",
        "fieldstation.ui.tablet.survey_title": "Field Station Survey & Telemetry",
        "fieldstation.ui.computer.title": "RV Field Station Terminal",
        "fieldstation.ui.lab.title": "RV Water Quality Lab Bench",
        "fieldstation.ui.journal.title": "Appalachian Cove Field Journal",
    }

    # Blockstates & Models for Layered Soil Blocks
    for bid, name, tex in LAYERED_BLOCKS:
        lang[f"block.fieldstation.{bid}"] = name
        lang[f"item.fieldstation.{bid}"] = name
        variants = {}
        for layer in range(1, 9):
            model_name = f"fieldstation:block/{bid}_height{layer * 2}"
            for packed in ["false", "true"]:
                if bid == "topsoil":
                    for seeded in ["none", "grass", "reed"]:
                        variants[f"layers={layer},packed={packed},seeded={seeded}"] = {"model": model_name}
                else:
                    variants[f"layers={layer},packed={packed}"] = {"model": model_name}
            write_json(RES_ASSETS / f"models/block/{bid}_height{layer * 2}.json", {
                "parent": "minecraft:block/cube_all" if layer == 8 else f"minecraft:block/snow_height{layer * 2}",
                "textures": {"all": tex, "particle": tex, "texture": tex}
            })
        write_json(RES_ASSETS / f"blockstates/{bid}.json", {"variants": variants})
        write_json(RES_ASSETS / f"models/item/{bid}.json", {"parent": f"fieldstation:block/{bid}_height16"})
        write_json(RES_ASSETS / f"items/{bid}.json", {"model": {"type": "minecraft:model", "model": f"fieldstation:item/{bid}"}})

    # Simple Blocks
    for bid, name, tex in SIMPLE_BLOCKS:
        lang[f"block.fieldstation.{bid}"] = name
        lang[f"item.fieldstation.{bid}"] = name
        write_json(RES_ASSETS / f"blockstates/{bid}.json", {
            "variants": {"": {"model": f"fieldstation:block/{bid}"}}
        })
        write_json(RES_ASSETS / f"models/block/{bid}.json", {
            "parent": "minecraft:block/cube_all",
            "textures": {"all": tex}
        })
        write_json(RES_ASSETS / f"models/item/{bid}.json", {"parent": f"fieldstation:block/{bid}"})
        write_json(RES_ASSETS / f"items/{bid}.json", {"model": {"type": "minecraft:model", "model": f"fieldstation:item/{bid}"}})

    # Items
    for iid, name, stack, tex in ITEMS:
        lang[f"item.fieldstation.{iid}"] = name
        write_json(RES_ASSETS / f"models/item/{iid}.json", {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": tex}
        })
        write_json(RES_ASSETS / f"items/{iid}.json", {"model": {"type": "minecraft:model", "model": f"fieldstation:item/{iid}"}})

    # Species JSONs
    for sid, name, sci, tags, min_age, min_area, min_reed, chance, k_mult, grant, bonus, is_mys in SPECIES:
        lang[f"entity.fieldstation.{sid}"] = name
        write_json(RES_DATA / f"species/{sid}.json", {
            "scientific_name": sci,
            "category": "wildlife",
            "native_regions": ["fieldstation:appalachia"],
            "microhabitats": tags,
            "active_hours": "day_night",
            "goals": ["flee", "hide", "forage", "call", "breed"],
            "senses": {"range_blocks": 12, "wariness": 0.8},
            "needs": {"skin_moisture": True, "temp_active_c": [8, 30], "temp_stress_c": 32, "cover_min_fraction": 0.1},
            "arrival": {
                "min_habitat_age_hours": min_age,
                "min_area_blocks": min_area,
                "min_reed_fraction": min_reed,
                "chance_per_hour": chance
            },
            "breeding": {
                "breeding_window": "spring",
                "trigger": "warm_water",
                "water_temp_min_c": 16,
                "young_per_female": 40 if "frog" in sid or "tadpole" in sid else 6,
                "young_survival_fraction": 0.25,
                "years_to_adult": 1,
                "adult_survival_per_year": 0.7
            },
            "carrying_capacity_per_area": k_mult,
            "isolated": True,
            "lifespan_years": 4,
            "grant_dollars_per_day": grant,
            "first_sighting_bonus_dollars": bonus,
            "protected": False,
            "mystery": is_mys,
            "detect_chance_per_visit": 0.10 if is_mys else 0.75,
            "clue_methods": ["camera_trap", "audio_recorder", "edna"],
            "sketch_time_s": 5,
            "sounds": {
                "call": f"fieldstation:{sid}.call",
                "alarm": f"fieldstation:{sid}.alarm"
            }
        })

    # Plant JSONs
    for pid, name, layer, moist, gh, cover in COVE_PLANTS:
        write_json(RES_DATA / f"plant/{pid}.json", {
            "id": f"fieldstation:{pid}",
            "group": layer,
            "layer": layer,
            "moisture": moist,
            "light": [4, 15],
            "soils": ["fieldstation:topsoil", "fieldstation:silt"],
            "depth_levels": [0, 2] if layer == "aquatic" else [0, 0],
            "growth_hours": gh,
            "spread_method": "rhizome" if layer == "aquatic" else "seed",
            "spread_reach_blocks": 2,
            "seed_season": "fall",
            "erosion_cover_fraction": cover,
            "water_use_l_per_day": 2,
            "phosphorus_uptake_g_per_day": 1,
            "flood_tolerance_hours": 168 if layer == "aquatic" else 48,
            "native_regions": ["fieldstation:appalachia"],
            "invasive": pid in ("japanese_stiltgrass", "chinese_privet", "japanese_honeysuckle", "tree_of_heaven")
        })

    # Mystery JSONs
    for mid, grp, kind, methods, decoys in MYSTERIES:
        write_json(RES_DATA / f"mystery/{mid}.json", {
            "species": f"fieldstation:{mid}",
            "group": grp,
            "opening_clue": {
                "kind": kind,
                "text_key": f"fieldstation.mystery.{mid}.opening"
            },
            "clue_methods": methods,
            "clues": [
                {"reveals": "season", "rules_out": decoys[0]},
                {"reveals": "microhabitat", "rules_out": decoys[1]},
                {"reveals": "active_hours", "rules_out": decoys[2]}
            ],
            "decoys": decoys,
            "confirm_methods": ["binoculars", "camera_trap", "capture", "sketch"]
        })

    # Shop Item JSONs
    for sid, item_id, count, price, tier, cat, deliv in SHOP_ITEMS:
        write_json(RES_DATA / f"shop_item/{sid}.json", {
            "item": item_id,
            "count": count,
            "price_dollars": price,
            "tier": tier,
            "category": cat,
            "delivery": deliv,
            "seasonal_price_multiplier": 1.0
        })

    # Entity Type Tags
    write_json(RES_DATA / "tags/entity_type/allowed_entities.json", {
        "replace": False,
        "values": [
            "minecraft:frog",
            "minecraft:tadpole",
            "minecraft:turtle",
            "minecraft:salmon",
            "minecraft:cod",
            "minecraft:bat",
            "minecraft:bee",
            "minecraft:rabbit",
            "minecraft:fox",
            "minecraft:wolf",
            "minecraft:goat",
            "minecraft:mule",
            "minecraft:villager",
            "minecraft:allay"
        ]
    })
    write_json(RES_DATA / "tags/entity_type/amphibians.json", {
        "replace": False,
        "values": ["minecraft:frog", "minecraft:tadpole"]
    })

    # Advancements
    advs = [
        ("root", "Parked", "Enter the Appalachian cove field station"),
        ("first_pond", "It holds!", "A pond you dug lasts 3 days"),
        ("journal_4", "Field notes", "4 species logged in the journal"),
        ("first_tadpoles", "Next generation", "Tadpoles appear in a pond you made"),
        ("spring_fed", "Living water", "Crayfish in a spring-fed pond"),
        ("big_water", "Big water", "A pond of 40 m2, 1 block deep"),
        ("no_soil_lost", "Holding ground", "A whole season with under 1 block of soil lost"),
        ("corridor", "Wet road", "Frogs move between two of your ponds"),
        ("oxbow", "Cut off", "A meander forms an oxbow pond"),
        ("year_one", "First year card", "Complete your first 28-day year in the cove"),
        ("old_friends", "Old friends", "5 named animals alive at once"),
        ("stream_health", "Clean water", "Reach a stream health score of 8 on Hemlock Run"),
        ("reference_site", "Reference site", "Every native species on the cove list rated within or above its allotment"),
    ]
    for aid, title, desc in advs:
        lang[f"advancement.fieldstation.{aid}.title"] = title
        lang[f"advancement.fieldstation.{aid}.description"] = desc

    write_json(RES_ASSETS / "lang/en_us.json", lang)
    print(f"Generated {len(SPECIES)} species, {len(COVE_PLANTS)} plants, {len(MYSTERIES)} mysteries, {len(SHOP_ITEMS)} shop items.")

if __name__ == "__main__":
    generate_resources()
