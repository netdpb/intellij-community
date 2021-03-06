package com.intellij.openapi.editor.markup;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class DefaultLineMarkerRenderer implements LineMarkerRendererEx {
  private final TextAttributesKey myAttributesKey;
  private final int myThickness;
  private final int myDeepness;
  private final Position myPosition;

  private final Color myColor;

  public DefaultLineMarkerRenderer(TextAttributesKey myAttributesKey) {
    this(myAttributesKey, 1, 0, Position.RIGHT);
  }

  public DefaultLineMarkerRenderer(TextAttributesKey myAttributesKey, int thickness) {
    this(myAttributesKey, thickness, 0, Position.RIGHT);
  }

  public DefaultLineMarkerRenderer(TextAttributesKey attributesKey, int thickness, int deepness, Position position) {
    myAttributesKey = attributesKey;
    myThickness = thickness;
    myDeepness = deepness;
    myPosition = position;

    EditorColorsScheme scheme = EditorColorsManager.getInstance().getGlobalScheme();
    TextAttributes attributes = scheme.getAttributes(myAttributesKey);
    Color color = attributes.getBackgroundColor();
    color = color != null ? color : attributes.getForegroundColor();

    if (color != null) {
      myColor = ColorUtil.isDark(scheme.getDefaultBackground()) ? ColorUtil.shift(color, 1.5d) : color.darker();
    } else {
      myColor = null;
    }
  }

  @Override
  public void paint(@NotNull Editor editor, @NotNull Graphics g, @NotNull Rectangle r) {
    if (myColor == null) return;

    g.setColor(myColor);
    g.fillRect(r.x, r.y, myThickness, r.height);
    g.fillRect(r.x + myThickness, r.y, myDeepness, myThickness);
    g.fillRect(r.x + myThickness, r.y + r.height - myThickness, myDeepness, myThickness);
  }

  public TextAttributesKey getAttributesKey() {
    return myAttributesKey;
  }

  public int getDeepness() {
    return myDeepness;
  }

  public int getThickness() {
    return myThickness;
  }

  @Override
  public @NotNull Position getPosition() {
    return myPosition;
  }
}
