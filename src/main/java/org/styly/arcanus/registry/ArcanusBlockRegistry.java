package org.styly.arcanus.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.block.RitualBlock;

public class ArcanusBlockRegistry {
    //BLOCKS is a DeferredRegister.Blocks
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Arcanus.MODID);
    public static final DeferredBlock<Block> RITUAL_TABLE_BLOCK = BLOCKS.register("ritual_table", () -> new RitualBlock(BlockBehaviour.Properties.of().destroyTime(5f).explosionResistance(20f)));

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
