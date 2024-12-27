package net.anavm.blockmod.item.custom;

import net.anavm.blockmod.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties){
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context){
        if(!context.getLevel().isClientSide()){
            BlockPos pClicked = context.getClickedPos();
            Player player = context.getPlayer();
            for(int i = 0; i < pClicked.getY(); i++){
                BlockState s = context.getLevel().getBlockState(pClicked.below(i));
                if(!isValuableBlock(s)){
                    assert player != null;
                    player.sendSystemMessage(Component.literal("No valuables found"));

                }
                else{
                    assert player != null;
                    outputValuableCoordinates(pClicked.below(i), player, s.getBlock());
                    break;
                }
            }
        }
        context.getItemInHand().hurtAndBreak(1, Objects.requireNonNull(context.getPlayer()), player -> player.broadcastBreakEvent(
                player.getUsedItemHand()
        ));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced){
        pTooltipComponents.add(Component.translatable("tooltip.blockmod.metal_detector.tooltip"));
        super.appendHoverText(stack, level, pTooltipComponents, pIsAdvanced);
    }
    private void outputValuableCoordinates(BlockPos pos, Player player, Block block){
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) +
                " at " + "(" + pos.getX() + ", " + pos.getY() + "," + pos.getZ() + ")"));
    }
    private boolean isValuableBlock(BlockState state){
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
