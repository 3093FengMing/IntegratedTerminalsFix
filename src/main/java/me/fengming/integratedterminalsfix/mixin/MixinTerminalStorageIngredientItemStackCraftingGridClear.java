package me.fengming.integratedterminalsfix.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import org.cyclops.integratedterminals.core.terminalstorage.TerminalStorageTabIngredientComponentItemStackCraftingCommon;
import org.cyclops.integratedterminals.core.terminalstorage.TerminalStorageTabIngredientComponentServer;
import org.cyclops.integratedterminals.network.packet.TerminalStorageIngredientItemStackCraftingGridClear;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TerminalStorageIngredientItemStackCraftingGridClear.class)
public class MixinTerminalStorageIngredientItemStackCraftingGridClear {
    /**
     * @author 3093FengMing
     * @reason Fix items dupe bug
     */
    @Overwrite(remap = false)
    public static void clearGrid(TerminalStorageTabIngredientComponentItemStackCraftingCommon tabCommon, TerminalStorageTabIngredientComponentServer<ItemStack, Integer> tabServer, int channel, boolean toStorage, PlayerEntity player) {
        tabCommon.getInventoryCraftResult().setInventorySlotContents(0, ItemStack.EMPTY);
        CraftingInventory inventoryCrafting = tabCommon.getInventoryCrafting();

        for (int i = 0; i < inventoryCrafting.getSizeInventory(); ++i) {
            ItemStack itemStack = inventoryCrafting.removeStackFromSlot(i);
            if (!itemStack.isEmpty()) {
                if (toStorage) {
                    ItemStack remainder = tabServer.getIngredientNetwork().getChannel(channel).insert(itemStack, false);
                    inventoryCrafting.setInventorySlotContents(i, remainder);
                } else {
                    player.inventory.placeItemBackInInventory(player.world, itemStack);
                    inventoryCrafting.setInventorySlotContents(i, ItemStack.EMPTY);
                }
            }
        }
    }
}
