package gott.ssm;

import gott.ssm.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MC_VERSIONS)
public class Main {

	@Instance
	public static Main instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println("ssm loaded");

		proxy.preInit();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();


	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
