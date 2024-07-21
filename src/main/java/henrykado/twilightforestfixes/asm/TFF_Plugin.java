package henrykado.twilightforestfixes.asm;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.TransformerExclusions({"henrykado.twilightforestfixes"})
public class TFF_Plugin implements IFMLLoadingPlugin {
	@Override
	public String[] getASMTransformerClass() {
		return new String[] {"henrykado.twilightforestfixes.asm.TFF_ClassTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return null;
		//return TFF_DummyContainer.class.getName();
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
