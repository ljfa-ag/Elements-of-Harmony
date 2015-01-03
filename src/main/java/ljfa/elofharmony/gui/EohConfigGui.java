package ljfa.elofharmony.gui;

import java.util.ArrayList;
import java.util.List;

import ljfa.elofharmony.Config;
import ljfa.elofharmony.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class EohConfigGui extends GuiConfig {
    public EohConfigGui(GuiScreen parent) {
        super(parent, getConfigElements(), Reference.MODID, false, false, "Elements Of Harmony mod configuration");
    }
    
    /** Creates a list of config elements */
    public static List<IConfigElement> getConfigElements() {
        ArrayList<IConfigElement> list = new ArrayList<IConfigElement>();
        
        //Add categories to config GUI
        list.add(categoryElement(Config.CAT_GENERAL, "General", "elofharmony.configgui.ctgy.general"));
        list.add(categoryElement(Config.CAT_POISONJOKE, "Poison Joke", "elofharmony.configgui.ctgy.poisonjoke"));
        
        return list;
    }
    
    /** Creates a button linking to another screen where all options of the category are available */
    private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
        return new DummyConfigElement.DummyCategoryElement(name, tooltip_key,
                new ConfigElement(Config.conf.getCategory(category)).getChildElements());
    }
}
