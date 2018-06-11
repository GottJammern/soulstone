package gott.ssm.events;

import gott.ssm.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class SoulShift
{

    public static void SoulShift (World worldIn, EntityPlayer playerIn){
        int curDim = playerIn.dimension;

        if (!worldIn.isRemote) {
            //In Overworld
            if (curDim == 0) {

                playerIn.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150));
                playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 150, 1));

                BlockPos pos;
                if (playerIn.getBedLocation() != null) {
                    if (playerIn.getBedSpawnLocation(worldIn, playerIn.getBedLocation(), true) != null) {
                        pos = playerIn.getBedSpawnLocation(worldIn, playerIn.getBedLocation(), true);
                        playerIn.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 1);

                    } else { // Bed spawn location Invalid
                        playerIn.sendMessage(new TextComponentTranslation(Reference.MOD_ID + ".msg.noBed"));
                        pos = playerIn.world.getSpawnPoint();
                        playerIn.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 1);
                    }

                } else { // No bed found
                    pos = playerIn.world.getSpawnPoint();
                    playerIn.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 1);
                }
            }
            ///If not in overworld
            if (curDim != 0){
                playerIn.sendMessage(new TextComponentTranslation("The Soulstone cannot Warp you in this dimension!"));

                playerIn.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 50));
                playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 50, 1));
            }



        }

    }

}///END CLASS///