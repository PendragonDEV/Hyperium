package cc.hyperium.gui.main.components;

import cc.hyperium.utils.RenderUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.function.Consumer;

/*
 * Created by Cubxity on 01/06/2018
 */
public class OverlayToggle extends OverlayComponent {
    private boolean toggle;
    private Consumer<Boolean> callback;

    public OverlayToggle(String label, boolean toggle, Consumer<Boolean> callback) {
        this.label = label;
        this.toggle = toggle;
        this.callback = callback;
    }

    @Override
    public boolean render(int mouseX, int mouseY, int overlayX, int overlayY, int w, int h, int overlayH) {
        if (!super.render(mouseX, mouseY, overlayX, overlayY, w, h, overlayH)) {
            return false;
        }
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderUtils.drawSmoothRect(overlayX + w - 30, overlayY + 5, overlayX + w - 5, overlayY + h - 5, 0xffffffff);
        RenderUtils.drawFilledCircle(toggle ? overlayX + w - 10 : overlayX + w - 25, overlayY + h / 2, 4, new Color(30, 30, 30).getRGB());
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        return true;
    }

    @Override
    public void handleMouseInput(int mouseX, int mouseY, int overlayX, int overlayY, int w, int h) {
        if(Mouse.isButtonDown(0) && mouseX >= overlayX + w - 30 && mouseX <= overlayX + w - 5 && mouseY >= overlayY + 5 && mouseY <= overlayY + h - 5){
            toggle = !toggle;
            callback.accept(toggle);
        }
    }
}
