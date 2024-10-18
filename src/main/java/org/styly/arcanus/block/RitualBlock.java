package org.styly.arcanus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.styly.arcanus.registry.ArcanusBlockEntityRegistry;

public class RitualBlock extends BaseEntityBlock {
    // The important part is implementing the EntityBlock interface and overriding the #newBlockEntity method.
    public static final MapCodec<RitualBlock> CODEC = simpleCodec((t) -> new RitualBlock());

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    // Constructor deferring to super.
    public RitualBlock() {
        super(Properties.ofFullCopy(Blocks.LODESTONE).noOcclusion());
    }

    // Return a new instance of our block entity here.
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RitualBlockEntity(pos, state);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack pStack, BlockState state, Level pLevel, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pos);
            if (entity instanceof RitualBlockEntity ritualTile) {
                if (player.isShiftKeyDown()) {
                    ItemStack currentPedestalItem = ritualTile.getHeldItem();
                    ItemStack handItem = player.getItemInHand(hand);

                    //Drop Current Item
                    ItemStack playerItem = currentPedestalItem.copy();
                    if (handItem.isEmpty() || handItem.getCount() == 1) {
                        player.setItemInHand(hand, playerItem);
                    } else {
                        dropItem(playerItem, player);
                    }
                    ritualTile.setHeldItem(ItemStack.EMPTY);
                    //Let clients know to update rendered item
                    pLevel.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
                } else {
                    //Crafting Logic
                    if(ritualTile.getHeldItem()!=ItemStack.EMPTY){
                        dropItem(ritualTile.getHeldItem(),player);
                        ritualTile.setHeldItem(ItemStack.EMPTY);
                        pLevel.sendBlockUpdated(pos,state,state,Block.UPDATE_CLIENTS);
                    }
                    BlockPos b0 = new BlockPos(pos.getX(),pos.getY(),pos.getZ()); //center aka result!
                    //this is faster I think?!
                    BlockPos b1 = new BlockPos(pos.getX()-4,pos.getY(),pos.getZ()-4); //t2
                    BlockPos b2 = new BlockPos(pos.getX(),pos.getY(),pos.getZ()-5); //t2
                    BlockPos b3 = new BlockPos(pos.getX()+4,pos.getY(),pos.getZ()-4); //t2

                    BlockPos b4 = new BlockPos(pos.getX()-1,pos.getY(),pos.getZ()-3);
                    BlockPos b5 = new BlockPos(pos.getX()+1,pos.getY(),pos.getZ()-3);

                    BlockPos b6 = new BlockPos(pos.getX()-3,pos.getY(),pos.getZ()-1);
                    BlockPos b7 = new BlockPos(pos.getX()+3,pos.getY(),pos.getZ()-1);

                    BlockPos b8 = new BlockPos(pos.getX()-5,pos.getY(),pos.getZ()); //t2
                    BlockPos b9 = new BlockPos(pos.getX()+5,pos.getY(),pos.getZ()); //t2

                    BlockPos b10 = new BlockPos(pos.getX()-3,pos.getY(),pos.getZ()+1);
                    BlockPos b11 = new BlockPos(pos.getX()+3,pos.getY(),pos.getZ()+1);

                    BlockPos b12 = new BlockPos(pos.getX()-1,pos.getY(),pos.getZ()+3);
                    BlockPos b13 = new BlockPos(pos.getX()+1,pos.getY(),pos.getZ()+3);

                    BlockPos b14 = new BlockPos(pos.getX()-4,pos.getY(),pos.getZ()+4); //t2
                    BlockPos b15 = new BlockPos(pos.getX(),pos.getY(),pos.getZ()+5); //t2
                    BlockPos b16 = new BlockPos(pos.getX()+4,pos.getY(),pos.getZ()+4); //t2
                }
            }
        }

        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    private void dropItem(ItemStack itemstack, Player owner) {
        if (owner instanceof ServerPlayer serverplayer) {
            ItemEntity itementity = serverplayer.drop(itemstack, false);
            if (itementity != null) {
                itementity.setNoPickUpDelay();
                itementity.setThrower(owner);
            }
        }
    }
}
