package gott.ssm.proxy;

import gott.ssm.items.AmuletSoul;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public abstract class CommonProxy {

	/**
	 * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
	 */
	public static AmuletSoul amulet_soul;

	public void preInit()
	{
		amulet_soul = (AmuletSoul) (new AmuletSoul().setUnlocalizedName("amulet_soul"));
		amulet_soul.setRegistryName("amulet_soul");
		ForgeRegistries.ITEMS.register(amulet_soul);

	}

	/**
	 * Do your mod setup. Build whatever data structures you care about. Register recipes,
	 * send FMLInterModComms messages to other mods.
	 */
	public void init()
	{

	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	public void postInit()
	{

	}

	abstract public boolean isDedicatedServer();
}