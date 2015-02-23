package ljfa.elofharmony.gui;

import java.util.ArrayList;
import java.util.List;

import ljfa.elofharmony.Config;
import ljfa.elofharmony.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class EohConfigGui extends GuiConfig {
    public EohConfigGui(GuiScreen parent) {
        super(parent, getConfigElements(), Reference.MODID, false, false, "Elements Of Harmony mod configuration");
    }
    
    /** Compiles a list of config elements */
    public static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        
        for(String name: Config.conf.getCategoryNames())
            list.add(new ConfigElement<ConfigCategory>(Config.conf.getCategory(name)));
        
        return list;
    }
}
