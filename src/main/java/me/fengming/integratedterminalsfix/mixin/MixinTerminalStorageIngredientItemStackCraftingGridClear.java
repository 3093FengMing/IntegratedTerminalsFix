package me.fengming.integratedterminalsfix.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.cyclops.integratedterminals.core.terminalstorage.TerminalStorageTabIngredientComponentItemStackCraftingCommon;
import org.cyclops.integratedterminals.core.terminalstorage.TerminalStorageTabIngredientComponentServer;
import org.cyclops.integratedterminals.network.packet.TerminalStorageIngredientItemStackCraftingGridClear;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TerminalStorageIngredientItemStackCraftingGridClear.class)
public class MixinTerminalStorageIngredientItemStackCraftingGridClear {
    @Inject(method = "clearGrid", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;placeItemBackInInventory(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void inject_clearGrid_fixDupeItems(TerminalStorageTabIngredientComponentItemStackCraftingCommon tabCommon, TerminalStorageTabIngredientComponentServer<ItemStack, Integer> tabServer, int channel, boolean toStorage, PlayerEntity player, CallbackInfo ci, CraftingInventory inventoryCrafting, int i, ItemStack itemStack, ItemStack remainder) {
        inventoryCrafting.setInventorySlotContents(i, ItemStack.EMPTY);
    }
}
