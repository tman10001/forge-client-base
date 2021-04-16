package git.yagel15637.clientbase.impl.gui;

import com.lukflug.panelstudio.CollapsibleContainer;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.hud.HUDClickGUI;
import com.lukflug.panelstudio.mc12.MinecraftHUDGUI;
import com.lukflug.panelstudio.settings.*;
import com.lukflug.panelstudio.theme.GameSenseTheme;
import com.lukflug.panelstudio.theme.MouseDescription;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;
import git.yagel15637.clientbase.ClientBase;
import git.yagel15637.clientbase.api.feature.Feature;
import git.yagel15637.clientbase.api.feature.FeatureCategory;
import git.yagel15637.clientbase.api.feature.FeatureManager;
import git.yagel15637.clientbase.api.setting.Setting;
import git.yagel15637.clientbase.impl.feature.client.GUI;

import java.awt.*;

public final class ClientGUI extends MinecraftHUDGUI {
    public static final ClientGUI INSTANCE = new ClientGUI();

    private static final int WIDTH = 100, HEIGHT = 12;

    private final HUDClickGUI hudgui;
    private final GUIInterface guiInter;
    private final Theme theme;

    private ClientGUI() {
        guiInter = new GUIInterface(true) {
            @Override
            protected String getResourcePrefix() {
                return ClientBase.MOD_ID + ":gui/";
            }

            @Override
            public void drawString(final Point pos, final String s, final Color c) {
                end();
                if (GUI.fontShadow.getValue()) mc.fontRenderer.drawStringWithShadow(s, pos.x + 2, pos.y + 2, c.getRGB());
                else mc.fontRenderer.drawString(s, pos.x + 2, pos.y + 2, c.getRGB());
                begin();
            }

            @Override
            public int getFontWidth(String s) {
                return mc.fontRenderer.getStringWidth(s) + 4;
            }

            @Override
            public int getFontHeight() {
                return mc.fontRenderer.FONT_HEIGHT + 4;
            }
        };

        hudgui = new HUDClickGUI(guiInter, new MouseDescription(new Point(0, 0)));

        theme = new GameSenseTheme(new SettingsColorScheme(
                GUI.activeColor,
                GUI.disabledColor,
                GUI.backgroundColor,
                GUI.outlineColor,
                GUI.fontColor,
                GUI.opacity
        ), HEIGHT, 2, 5);
    }

    public void init() {
        int x = 10;

        for (final FeatureCategory category : FeatureCategory.values()) {
            final DraggableContainer panel = new DraggableContainer(category.toString(),null, theme.getPanelRenderer(), new SimpleToggleable(false), new SettingsAnimation(GUI.animationSpeed), null, new Point(x,10), WIDTH) {
                @Override
                protected int getScrollHeight(int childHeight) {
                    return Math.min(childHeight, Math.max(HEIGHT * 4, ClientGUI.this.height - getPosition(guiInter).y - renderer.getHeight(open.getValue() != 0) - HEIGHT));
                }
            };

            x += 110;
            hudgui.addComponent(panel);

            for (final Feature feature : FeatureManager.INSTANCE.getFeaturesByCategory(category)) {
                final CollapsibleContainer container = new CollapsibleContainer(feature.getId(), feature.getDescription(), theme.getContainerRenderer(), new SimpleToggleable(false), new SettingsAnimation(GUI.animationSpeed), feature.enabled);
                panel.addComponent(container);

                for (final Setting<?> setting : feature.getSettings()) {
                    if (setting == feature.enabled) continue;

                    if (setting instanceof Toggleable) container.addComponent(new BooleanComponent(setting.getId(), setting.getDescription(), theme.getComponentRenderer(), (Toggleable) setting));
                    else if (setting instanceof NumberSetting) container.addComponent(new NumberComponent(setting.getId(), setting.getDescription(), theme.getComponentRenderer(), (NumberSetting) setting, ((NumberSetting) setting).getMinimumValue(), ((NumberSetting) setting).getMaximumValue()));
                    else if (setting instanceof EnumSetting) container.addComponent(new EnumComponent(setting.getId(), setting.getDescription(), theme.getComponentRenderer(), (EnumSetting) setting));
                    else if (setting instanceof ColorSetting) container.addComponent(new ColorComponent(setting.getId(), setting.getDescription(), theme.getContainerRenderer(), new SettingsAnimation(GUI.animationSpeed), theme.getComponentRenderer(), (ColorSetting) setting, true, ((ColorSetting) setting).getRainbow(), new SimpleToggleable(false)));
                    else if (setting instanceof KeybindSetting) container.addComponent(new KeybindComponent(theme.getComponentRenderer(), (KeybindSetting) setting));
                    else throw new IllegalStateException("Setting " + setting.getId() + " in feature " + feature.getId() + " is not a valid setting.");
                }
            }
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        FeatureManager.INSTANCE.getFeature(GUI.class).enabled.setValue(false);
    }

    @Override
    protected HUDClickGUI getHUDGUI() {
        return hudgui;
    }

    @Override
    protected GUIInterface getInterface() {
        return guiInter;
    }

    @Override
    protected int getScrollSpeed() {
        return GUI.scrollSpeed.getValue().intValue();
    }
}
