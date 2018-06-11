package gott.ssm.events;

import baubles.api.BaublesApi;
import gott.ssm.proxy.CommonProxy;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Objects;

@Mod.EventBusSubscriber
public class DeathEvent {

    //on death, heal/teleport
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getSource().canHarmInCreative()) return;
        if (!(event.getEntityLiving() instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();


        int slot = BaublesApi.isBaubleEquipped(player, CommonProxy.amulet_soul);
        if (slot != 0) return;
        //if (slot <= -1) return;

        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP) event.getEntityLiving();
            StatBase stat = StatList.getObjectUseStats(CommonProxy.amulet_soul);
            ItemStack totem = new ItemStack(CommonProxy.amulet_soul);

            playerMP.addStat(Objects.requireNonNull(stat));
            CriteriaTriggers.USED_TOTEM.trigger(playerMP, totem);
        }

        //////////////////////////////
        ItemStack amuletNBT = BaublesApi.getBaublesHandler(player).getStackInSlot(0);
        NBTTagCompound amuletTag = amuletNBT.getTagCompound();
        @Nullable
        String tagMatch = amuletTag.getString("owner");

        if(tagMatch == player.getName()){
            World worldIn = player.world;
            ////apply save-life countermeasures
            player.setHealth(2.0F);
            player.clearActivePotions();
            player.world.setEntityState(event.getEntityLiving(), (byte) 35);
            SoulShift.SoulShift(worldIn, player);
            event.setCanceled(true);

        }
        //////////////////////////////



    }

}

