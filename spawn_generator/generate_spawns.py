import json
from pathlib import Path

CONFIG_FILE = Path(__file__).parent / "spawn_config.json"
OUTPUT_DIR = (
    Path(__file__).parent.parent
    / "src"
    / "main"
    / "resources"
    / "data"
    / "futuaimod"
    / "forge"
    / "biome_modifier"
)

MOD_ID = "futuaimod"


def load_config():
    with CONFIG_FILE.open("r", encoding="utf-8") as file:
        return json.load(file)


def normalize_biomes(biomes):
    if len(biomes) == 1:
        return biomes[0]

    return biomes


def generate_spawn_file(mob_id, config, multiplier):
    weight = max(1, round(config["weight"] * multiplier))
    biomes = normalize_biomes(config["biomes"])

    spawn_data = {
        "type": "forge:add_spawns",
        "biomes": biomes,
        "spawners": [
            {
                "type": f"{MOD_ID}:{mob_id}",
                "weight": weight,
                "minCount": config["min_count"],
                "maxCount": config["max_count"]
            }
        ]
    }

    spawn_cost = config.get("spawn_cost")
    if spawn_cost:
        spawn_data["spawn_cost"] = {
            "charge": spawn_cost["charge"],
            "energy_budget": spawn_cost["energy_budget"]
        }

    output_file = OUTPUT_DIR / f"{mob_id}_spawn.json"

    with output_file.open("w", encoding="utf-8") as file:
        json.dump(spawn_data, file, indent=2, ensure_ascii=False)
        file.write("\n")

def generate_spawn_cost_file(mob_id, config):
    spawn_cost = config.get("spawn_cost")

    if not spawn_cost:
        return

    biomes = normalize_biomes(config["biomes"])

    spawn_cost_data = {
        "type": "forge:add_spawn_costs",
        "biomes": biomes,
        "entity_types": f"{MOD_ID}:{mob_id}",
        "spawn_cost": {
            "charge": spawn_cost["charge"],
            "energy_budget": spawn_cost["energy_budget"]
        }
    }

    output_file = OUTPUT_DIR / f"{mob_id}_spawn_cost.json"

    with output_file.open("w", encoding="utf-8") as file:
        json.dump(spawn_cost_data, file, indent=2, ensure_ascii=False)
        file.write("\n")

    print(
        f"Generated: {output_file} "
        f"(charge={spawn_cost['charge']}, "
        f"energy_budget={spawn_cost['energy_budget']})"
    )


def main():
    config = load_config()

    multiplier = config.get("spawn_weight_multiplier", 1.0)
    mobs = config.get("mobs", {})

    OUTPUT_DIR.mkdir(parents=True, exist_ok=True)

    print(f"Spawn weight multiplier: {multiplier}")
    print(f"Mobs: {len(mobs)}")
    print()

    for mob_id, mob_config in mobs.items():
        generate_spawn_file(mob_id, mob_config, multiplier)
        #generate_spawn_cost_file(mob_id, mob_config)

    print()
    print("Spawn files generated successfully.")


if __name__ == "__main__":
    main()

