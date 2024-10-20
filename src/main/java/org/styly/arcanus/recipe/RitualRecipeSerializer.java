package org.styly.arcanus.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// The generic parameter is our recipe class.
// Note: This assumes that simple RightClickBlockRecipe#getInputState, #getInputItem and #getResult getters
// are available, which were omitted from the code above.
public class RitualRecipeSerializer implements RecipeSerializer<RitualRecipe> {

    public static StreamCodec<RegistryFriendlyByteBuf, List<ItemStack>> ItemStacksCodec =
            StreamCodec.of(
                    // Encoder: Serializes the list to the buffer.
                    (buffer, itemStacks) -> {
                        buffer.writeVarInt(itemStacks.size());  // Write the size of the list
                        for (ItemStack itemStack : itemStacks) {
                            ItemStack.STREAM_CODEC.encode(buffer, itemStack);  // Serialize each ItemStack
                        }
                    },
                    // Decoder: Deserializes the list from the buffer.
                    buffer -> {
                        int size = buffer.readVarInt();  // Read the size of the list
                        List<ItemStack> itemStacks = new ArrayList<>(size);  // Create a list with the correct size
                        for (int i = 0; i < size; i++) {
                            itemStacks.add(ItemStack.STREAM_CODEC.decode(buffer));  // Deserialize each ItemStack
                        }
                        return itemStacks;
                    }
            );

    public static final MapCodec<RitualRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            ItemStack.CODEC.listOf(1, 17).fieldOf("ingredients").forGetter(RitualRecipe::getInputItems),
            ItemStack.CODEC.fieldOf("result").forGetter(RitualRecipe::getResult)
    ).apply(inst, RitualRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, RitualRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ItemStacksCodec, RitualRecipe::getInputItems,
                    ItemStack.STREAM_CODEC, RitualRecipe::getResult,
                    RitualRecipe::new
            );

    // Return our map codec.
    @Override
    public @NotNull MapCodec<RitualRecipe> codec() {
        return CODEC;
    }

    // Return our stream codec.
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, RitualRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
