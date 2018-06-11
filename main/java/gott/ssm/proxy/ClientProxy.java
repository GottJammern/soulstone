package gott.ssm.proxy;

import gott.ssm.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.client.model.ModelLoader;

/**
 * ClientProxy is used to set up the mod and start it running on normal minecraft.  It contains all the code that should run on the
 *   client side only.
 *   For more background information see here http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html
 */
public class ClientProxy extends CommonProxy
{

	/**
	 * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
	 */
	public void preInit()
	{
		super.preInit();
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("ssm:amulet_soul", "inventory");
        final int DEFAULT_ITEM_SUBTYPE = 0;
        ModelLoader.setCustomModelResourceLocation(CommonProxy.amulet_soul, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
	}

	/**
	 * Do your mod setup. Build whatever data structures you care about. Register recipes,
	 * send FMLInterModComms messages to other mods.
	 */
	public void init()
	{
		super.init();

	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	public void postInit()
	{
		super.postInit();
	}


	@Override
	public boolean isDedicatedServer() {return false;}

}
