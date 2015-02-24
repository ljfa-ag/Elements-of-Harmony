package ljfa.elofharmony.render.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelRitualTable extends ModelBase {
    public ModelRitualTable() {
        renderer = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
        renderer.addBox(0.0f, 0.0f, 0.0f, 14, 5, 14, 0.0f);
    }
    
    public void renderAll() {
        renderer.render(1/14.0f);
    }
    
    private ModelRenderer renderer;
}
