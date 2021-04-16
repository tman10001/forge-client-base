package git.yagel15637.clientbase.impl.feature.client;

import git.yagel15637.clientbase.api.feature.Feature;
import git.yagel15637.clientbase.api.feature.FeatureCategory;
import git.yagel15637.clientbase.api.setting.settings.BooleanSetting;
import git.yagel15637.clientbase.api.setting.settings.ColorSetting;
import git.yagel15637.clientbase.api.setting.settings.NumberSetting;
import git.yagel15637.clientbase.impl.gui.ClientGUI;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public final class GUI extends Feature {
    public static final BooleanSetting fontShadow = new BooleanSetting("FontShadow", "", true);
    public static final NumberSetting scrollSpeed = new NumberSetting("ScrollSpeed", "", 1.0, 10.0, 100.0);
    public static final NumberSetting animationSpeed = new NumberSetting("AnimationSpeed", "", 1.0, 10.0, 100.0);
    public static final NumberSetting opacity = new NumberSetting("Opacity", "", 0.0, 100.0, 100.0);
    public static final ColorSetting activeColor = new ColorSetting("ActiveColor", "", Color.CYAN);
    public static final ColorSetting disabledColor = new ColorSetting("DisabledColor", "", Color.BLUE);
    public static final ColorSetting backgroundColor = new ColorSetting("BackgroundColor", "", Color.DARK_GRAY);
    public static final ColorSetting outlineColor = new ColorSetting("OutlineColor", "", Color.WHITE);
    public static final ColorSetting fontColor = new ColorSetting("FontColor", "", Color.YELLOW);

    public GUI() {
        super(FeatureCategory.CLIENT);

        bind.setValue(Keyboard.KEY_P);
    }

    @Override
    protected void onEnable() {
        mc.displayGuiScreen(ClientGUI.INSTANCE);

        enabled.setValue(false);
    }
}
