package org.styly.arcanus.block;

import com.mojang.serialization.MapCodec;
import io.redspace.ironsspellbooks.block.pedestal.PedestalTile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.styly.arcanus.recipe.RitualRecipe;
import org.styly.arcanus.recipe.RitualRecipeInput;
import org.styly.arcanus.registry.ArcanusRecipes;
import org.styly.arcanus.registry.ModItems;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

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
                if (!player.isShiftKeyDown()) {
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


                    //Place a singular new Item
                    currentPedestalItem = handItem.copy();
                    if (!currentPedestalItem.isEmpty()) {
                        currentPedestalItem.setCount(1);
                        ritualTile.setHeldItem(currentPedestalItem);
                        handItem.shrink(1);
                    }
                    //Let clients know to update rendered item
                    pLevel.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
                } else {
                    //Crafting Logic

                    BlockPos b0 = new BlockPos(pos.getX(), pos.getY(), pos.getZ()); //center aka result!
                    //Tier 2
                    BlockPos b1 = new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() - 4); //t2
                    BlockPos b2 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 5); //t2
                    BlockPos b3 = new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() - 4); //t2

                    BlockPos b8 = new BlockPos(pos.getX() - 5, pos.getY(), pos.getZ()); //t2
                    BlockPos b9 = new BlockPos(pos.getX() + 5, pos.getY(), pos.getZ()); //t2

                    BlockPos b14 = new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() + 4); //t2
                    BlockPos b15 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 5); //t2
                    BlockPos b16 = new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() + 4); //t2
                    //Tier 1
                    BlockPos b4 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 3);
                    BlockPos b5 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 3);

                    BlockPos b6 = new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 1);
                    BlockPos b7 = new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 1);


                    BlockPos b10 = new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 1);
                    BlockPos b11 = new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 1);

                    BlockPos b12 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 3);
                    BlockPos b13 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 3);


                    //level check
                    int level = getLevel(pLevel, pos);
                    if (level > 0) {
                        ArrayList<ItemStack> inputs = new ArrayList<ItemStack>(); // I think it makes it faster
                        for (int i = 0; i <= 16; i++) {
                            //populate Arraylist cause java
                            inputs.add(new ItemStack(ModItems.AIR.get()));
                        }

                        if (level == 1) {
                            //set inputs according to table
                            inputs.set(4, getTileItem(pLevel, b4));
                            inputs.set(5, getTileItem(pLevel, b5));
                            inputs.set(6, getTileItem(pLevel, b6));
                            inputs.set(7, getTileItem(pLevel, b7));

                            inputs.set(10, getTileItem(pLevel, b10));
                            inputs.set(11, getTileItem(pLevel, b11));
                            inputs.set(12, getTileItem(pLevel, b12));
                            inputs.set(13, getTileItem(pLevel, b13));
                        } else if (level == 2) {
                            //t2
                            inputs.set(1, getTileItem(pLevel, b1));
                            inputs.set(2, getTileItem(pLevel, b2));
                            inputs.set(3, getTileItem(pLevel, b3));

                            inputs.set(8, getTileItem(pLevel, b8));
                            inputs.set(9, getTileItem(pLevel, b9));

                            inputs.set(14, getTileItem(pLevel, b14));
                            inputs.set(15, getTileItem(pLevel, b15));
                            inputs.set(16, getTileItem(pLevel, b16));
                            // t1
                            inputs.set(4, getTileItem(pLevel, b4));
                            inputs.set(5, getTileItem(pLevel, b5));
                            inputs.set(6, getTileItem(pLevel, b6));
                            inputs.set(7, getTileItem(pLevel, b7));

                            inputs.set(10, getTileItem(pLevel, b10));
                            inputs.set(11, getTileItem(pLevel, b11));
                            inputs.set(12, getTileItem(pLevel, b12));
                            inputs.set(13, getTileItem(pLevel, b13));
                        }
                        for (int i = 0; i < inputs.size(); i++) {
                            if (inputs.get(i).is(ItemStack.EMPTY.getItem())) {
                                inputs.set(i, new ItemStack(ModItems.AIR.get()));
                            }
                        }
                        RitualRecipeInput recipeInput = new RitualRecipeInput(inputs);
                        RecipeManager recipes = pLevel.getRecipeManager();
                        Optional<RecipeHolder<RitualRecipe>> optional = recipes.getRecipeFor(
                                // The recipe type.
                                ArcanusRecipes.RITUAL.get(),
                                recipeInput,
                                pLevel
                        );
                        ItemStack result = optional
                                .map(RecipeHolder::value)
                                .map(e -> e.assemble(recipeInput, pLevel.registryAccess()))
                                .orElse(ItemStack.EMPTY);

                        if (result != ItemStack.EMPTY) {
                            ritualTile.setHeldItem(result);
                            clearPedestals(pLevel, pos);
                            spawnLightning(pLevel, pos);
                        }

                    }


                }
            }
        }

        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    public static void clearPedestals(Level pLevel, BlockPos pos) {
        int level = getLevel(pLevel, pos);
        ArrayList<BlockPos> poss = new ArrayList<>();
        if (level == 1) {
            poss.add(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 3));
            poss.add(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 3));

            poss.add(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 1));
            poss.add(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 1));


            poss.add(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 1));
            poss.add(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 1));

            poss.add(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 3));
            poss.add(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 3));
        } else if (level == 2) {
            poss.add(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 3));
            poss.add(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 3));

            poss.add(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 1));
            poss.add(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 1));


            poss.add(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 1));
            poss.add(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 1));

            poss.add(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 3));
            poss.add(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 3));

            poss.add(new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() - 4)); //t2
            poss.add(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 5)); //t2
            poss.add(new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() - 4)); //t2

            poss.add(new BlockPos(pos.getX() - 5, pos.getY(), pos.getZ())); //t2
            poss.add(new BlockPos(pos.getX() + 5, pos.getY(), pos.getZ())); //t2

            poss.add(new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() + 4)); //t2
            poss.add(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 5)); //t2
            poss.add(new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() + 4)); //t2


        }
        for (BlockPos blockPos : poss) {
            Objects.requireNonNull((PedestalTile) pLevel.getBlockEntity(blockPos)).setHeldItem(ItemStack.EMPTY);
        }
    }

    public static void spawnLightning(Level pLevel, BlockPos pos) {
        int level = getLevel(pLevel, pos);
        ArrayList<BlockPos> poss = new ArrayList<>();
        poss.add(pos);
        if (level == 1) {
            poss.add(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 3));
            poss.add(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 3));

            poss.add(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 1));
            poss.add(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 1));


            poss.add(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 1));
            poss.add(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 1));

            poss.add(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 3));
            poss.add(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 3));
        } else if (level == 2) {
            poss.add(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 3));
            poss.add(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 3));

            poss.add(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 1));
            poss.add(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 1));


            poss.add(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 1));
            poss.add(new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 1));

            poss.add(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 3));
            poss.add(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 3));

            poss.add(new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() - 4)); //t2
            poss.add(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 5)); //t2
            poss.add(new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() - 4)); //t2

            poss.add(new BlockPos(pos.getX() - 5, pos.getY(), pos.getZ())); //t2
            poss.add(new BlockPos(pos.getX() + 5, pos.getY(), pos.getZ())); //t2

            poss.add(new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() + 4)); //t2
            poss.add(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 5)); //t2
            poss.add(new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() + 4)); //t2


        }
        for (BlockPos blockPos : poss) {
            LightningBolt entity = EntityType.LIGHTNING_BOLT.create(pLevel);
            assert entity != null;
            entity.setPos(new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
            entity.setDamage(50);
            entity.setVisualOnly(true);
            pLevel.addFreshEntity(entity);

        }
    }

    public static boolean IsValid(Level pLevel, @NotNull BlockPos pos) {
        BlockPos b0 = new BlockPos(pos.getX(), pos.getY(), pos.getZ()); //center aka result!

        BlockPos b4 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 3);
        BlockPos b5 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 3);

        BlockPos b6 = new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 1);
        BlockPos b7 = new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 1);


        BlockPos b10 = new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 1);
        BlockPos b11 = new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 1);

        BlockPos b12 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 3);
        BlockPos b13 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 3);


        return BlockInstanceOf(pLevel, b4) && BlockInstanceOf(pLevel, b5) && BlockInstanceOf(pLevel, b6) && BlockInstanceOf(pLevel, b7) && BlockInstanceOf(pLevel, b10) && BlockInstanceOf(pLevel, b11) && BlockInstanceOf(pLevel, b12) && BlockInstanceOf(pLevel, b13);


    }

    public static int getLevel(Level pLevel, BlockPos pos) {
        boolean minLevel = IsValid(pLevel, pos);

        BlockPos b1 = new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() - 4); //t2
        BlockPos b2 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 5); //t2
        BlockPos b3 = new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() - 4); //t2

        BlockPos b8 = new BlockPos(pos.getX() - 5, pos.getY(), pos.getZ()); //t2
        BlockPos b9 = new BlockPos(pos.getX() + 5, pos.getY(), pos.getZ()); //t2

        BlockPos b14 = new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() + 4); //t2
        BlockPos b15 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 5); //t2
        BlockPos b16 = new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() + 4); //t2

        if (
                BlockInstanceOf(pLevel, b1) &&
                        BlockInstanceOf(pLevel, b2) &&
                        BlockInstanceOf(pLevel, b3) &&
                        BlockInstanceOf(pLevel, b8) &&
                        BlockInstanceOf(pLevel, b9) &&
                        BlockInstanceOf(pLevel, b14) &&
                        BlockInstanceOf(pLevel, b15) &&
                        BlockInstanceOf(pLevel, b16) &&
                        minLevel
        ) {
            return 2;
        } else if (minLevel) {
            return 1;
        } else return 0;
    }

    public static boolean BlockInstanceOf(Level pLevel, BlockPos pos) {
        return pLevel.getBlockEntity(pos) instanceof PedestalTile;
    }

    public static ItemStack getTileItem(Level pLevel, BlockPos pos) {
        return ((PedestalTile) Objects.requireNonNull(pLevel.getBlockEntity(pos))).getHeldItem();
    }

    ;

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
