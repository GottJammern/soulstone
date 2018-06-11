package gott.ssm.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import baubles.api.render.IRenderBauble;
import gott.ssm.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static baubles.api.BaubleType.*;

public class AmuletSoul extends Item  implements IBauble , IRenderBauble {

    private BaubleType type = AMULET;

	public AmuletSoul()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.MISC);   // the item will appear on the Miscellaneous tab in creative
	}

	public BaubleType getBaubleType(ItemStack itemStack) {return AMULET;}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
        if(!worldIn.isRemote) {
            //Check Tags and bind to player
            ItemStack stack = playerIn.getHeldItem(handIn);
            NBTTagCompound tags = stack.getTagCompound();
            if (tags == null) {
                stack.setTagCompound(new NBTTagCompound());
                tags = stack.getTagCompound();
                tags.setString("owner", playerIn.getName());
                playerIn.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1, 1);
            }

            if(playerIn.isSneaking() == true) {
                //Equip as amulet bauble
                IBaublesItemHandler handler = BaublesApi.getBaublesHandler(playerIn);
                for (int slot : type.getValidSlots()) {
                    ItemStack remainder = handler.insertItem(slot, stack.copy(), true);
                    if (remainder.getCount() < stack.getCount()) {
                        playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0F, 1.0F);
                        handler.insertItem(slot, stack.copy(), false);
                        stack.setCount(remainder.getCount());
                        return super.onItemRightClick(worldIn, playerIn, handIn);
                    }
                }
            }
        }
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}


	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag advanced)
	{
		NBTTagCompound tags = stack.getTagCompound();
		if(tags == null)
		{
			tooltip.add(TextFormatting.RED + "Unbound");
			tooltip.add(TextFormatting.RED + "" + TextFormatting.ITALIC + "Right click to bind your soul.");
            tooltip.add(TextFormatting.BLUE + "" + TextFormatting.ITALIC + "Shift-Right click to equip.");

		}else
		{
			tooltip.add(TextFormatting.GREEN + "Bound to " + tags.getString("owner"));
            tooltip.add(TextFormatting.BLUE + "" + TextFormatting.ITALIC + "Shift-Right click to equip.");
		}
	}
	@Override
    @SideOnly(Side.CLIENT)
    public void onPlayerBaubleRender(ItemStack stack, EntityPlayer playerIn, RenderType renderType, float partialTicks) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        ItemStack totem = new ItemStack(CommonProxy.amulet_soul);

        if (type == HEAD && renderType != RenderType.HEAD) return;
        if (type != HEAD && renderType != RenderType.BODY) return;

        switch (type) { // TODO Rendering states
            case HEAD:
                break;
            case AMULET:
                boolean chest = !playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty();
                IRenderBauble.Helper.rotateIfSneaking(playerIn);
                GlStateManager.scale(0.25, 0.25, 0.25);
                GlStateManager.rotate(180, 0, 0, 1);
                GlStateManager.translate(0.0, -0.5, chest ? -0.8 : -0.5);
                mc.getRenderItem().renderItem(totem, ItemCameraTransforms.TransformType.NONE);
            case RING:
                break;
            case BELT:
                break;
            case TRINKET:
                break;
            case BODY:
            case CHARM:
                break;
        }
    }

}//END CLASS//

