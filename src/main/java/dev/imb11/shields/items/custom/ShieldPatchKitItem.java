package dev.imb11.shields.items.custom;

import dev.imb11.shields.items.BannerShieldItemWrapper;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.InteractionResult;

//? if <1.21.2 {
/*import net.minecraft.world.InteractionResultHolder;
*///?}

public class ShieldPatchKitItem extends Item {
    public boolean mixin$did_flip_priorities = false;

    public ShieldPatchKitItem(Properties properties) {
        super(properties);
    }

    public ItemStack getResultHolderStack(Player player, InteractionHand hand) {
        if (mixin$did_flip_priorities) {
            mixin$did_flip_priorities = false;
            InteractionHand oppositeHand = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
            return player.getItemInHand(oppositeHand);
        } else {
            return player.getItemInHand(hand);
        }
    }



    //? if <1.21.2 {
    /*@Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
    *///?} else {
    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand usedHand) {
    //?}
        ItemStack repairKitStack = player.getItemInHand(usedHand);
        if (!(level instanceof ServerLevel)) {
            //? if <1.21.2 {
            /*return InteractionResultHolder.pass(getResultHolderStack(player, usedHand));
            *///?} else {
            return InteractionResult.PASS;
            //?}
        }

        ItemStack shieldStack = canUse(player, usedHand);

        if (shieldStack != null) {
            // If the shield is already at max damage, don't use the item.
            if (shieldStack.getMaxDamage() == shieldStack.getDamageValue()) {
                //? if <1.21.2 {
                /*return InteractionResultHolder.pass(repairKitStack);
                *///?} else {
                return InteractionResult.PASS;
                //?}
            }

            // 1 minute between uses.
            //? if <1.21.2 {
            /*player.getCooldowns().addCooldown(this, 20 * 60);
            *///?} else {
            player.getCooldowns().addCooldown(repairKitStack, 20 * 60);
            //?}
            shieldStack.set(DataComponents.MAX_DAMAGE, (int) Math.floor(shieldStack.getMaxDamage() - 0.25 * shieldStack.getMaxDamage()));

            // Repair to max durability.
            shieldStack.setDamageValue(0);

            // Damage the patch kit by 1 durability.
            repairKitStack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(repairKitStack));

            player.playSound(SoundEvents.UI_LOOM_TAKE_RESULT, 1.0F, 1.0F);

            //? if <1.21.2 {
            /*return InteractionResultHolder.success(getResultHolderStack(player, usedHand));
            *///?} else {
            return InteractionResult.SUCCESS;
            //?}
        }

        //? if <1.21.2 {
        /*return InteractionResultHolder.pass(getResultHolderStack(player, usedHand));
        *///?} else {
        return InteractionResult.PASS;
        //?}
    }

    private static ItemStack canUse(Player player, InteractionHand usedHand) {
        if (usedHand == InteractionHand.MAIN_HAND) {
            // Check if shield is in off-hand, and whether shield is more than 25% damaged.
            ItemStack offhandStack = player.getOffhandItem();
            if (offhandStack.is(ConventionalItemTags.SHIELD_TOOLS)) {
                if (offhandStack.getDamageValue() > 0.25 * offhandStack.getMaxDamage()) {
                    return offhandStack;
                }
            }
        } else {
            // Check if shield is in main-hand, and whether shield is more than 25% damaged.
            ItemStack mainhandStack = player.getMainHandItem();
            if (mainhandStack.is(ConventionalItemTags.SHIELD_TOOLS)) {
                if (mainhandStack.getDamageValue() > 0.25 * mainhandStack.getMaxDamage()) {
                    return mainhandStack;
                }
            }
        }
        return null;
    }
}
