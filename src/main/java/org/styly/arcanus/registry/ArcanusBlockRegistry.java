package org.styly.arcanus.registry;

import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.styly.arcanus.Arcanus;
import org.styly.arcanus.block.RitualBlock;

public class ArcanusBlockRegistry {
    //BLOCKS is a DeferredRegister.Blocks
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Arcanus.MODID);
    public static final DeferredHolder<Block,Block> RITUAL_TABLE_BLOCK = BLOCKS.register("ritual_table", RitualBlock::new);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
