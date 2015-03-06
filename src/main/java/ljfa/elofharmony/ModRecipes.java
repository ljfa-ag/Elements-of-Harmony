package ljfa.elofharmony;

import ljfa.elofharmony.blocks.ModBlocks;
import ljfa.elofharmony.items.ItemResource.ResourceType;
import ljfa.elofharmony.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {
    public static void addOredict() {
        OreDictionary.registerOre("itemFeather", Items.feather);
        OreDictionary.registerOre("itemFeather", new ItemStack(ModItems.resource, 1, ResourceType.YELLOW_FEATHER.ordinal()));
        
        OreDictionary.registerOre("plankWood", ModBlocks.planks_flutter);
        OreDictionary.registerOre("logWood", ModBlocks.log_flutter);
        OreDictionary.registerOre("treeLeaves", ModBlocks.leaves_flutter);
        OreDictionary.registerOre("treeSapling", ModBlocks.sapling_flutter);
        OreDictionary.registerOre("slabWood", ModBlocks.slab_flutter);
        OreDictionary.registerOre("stairsWood", ModBlocks.stairs_flutter);
    }
    
    public static void addRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planks_flutter, 4), ModBlocks.log_flutter);
        GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.sapling_flutter, "FYF", "PSP", "JYJ",
                'J', ModBlocks.poisonjoke, 'P', "dyePink", 'Y', "dyeYellow", 'S', "treeSapling", 'F', "itemFeather").setMirrored(false));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.sapling_flutter, "FPF", "YSY", "JPJ",
                'J', ModBlocks.poisonjoke, 'P', "dyePink", 'Y', "dyeYellow", 'S', "treeSapling", 'F', "itemFeather").setMirrored(false));
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.slab_flutter, 6), "PPP",
                'P', ModBlocks.planks_flutter);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.stairs_flutter, 6), "P  ", "PP ", "PPP",
                'P', ModBlocks.planks_flutter);
        GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.ritual_table, "OYP", "###", "LWV",
                'O', "dyeOrange", 'Y', "dyeYellow", 'P', "dyePink", 'L', "dyeLightBlue",
                'W', "dyeWhite", 'V', "dyePurple", '#', ModBlocks.planks_flutter).setMirrored(false));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.twilicane, " gG", " gg", "g  ",
                'g', "nuggetGold", 'G', "ingotGold"));
    }
    
    public static void addSmelting() {
        GameRegistry.addSmelting(ModBlocks.log_flutter, new ItemStack(Items.coal, 1, 1), 0.15F);
    }
}
