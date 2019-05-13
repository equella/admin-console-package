/**
 * Copyright <2019>, The Apereo Foundation
 *
 * This project includes software developed by The Apereo Foundation.
 * http://www.apereo.org/
 *
 * Licensed under the Apache License, Version 2.0, (the "License"); you may not
 * use this software except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apereo.openequella.adminconsole.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

public class ComponentHelper {
  public static void centreOnScreen(Window window) {
    Window owner = window.getOwner();

    // If the window has an owner, use the same graphics configuration so it
    // will
    // open on the same screen. Otherwise, grab the mouse pointer and work
    // from there.
    GraphicsConfiguration gc = owner != null ? owner.getGraphicsConfiguration()
        : MouseInfo.getPointerInfo().getDevice().getDefaultConfiguration();

    if (gc != null) {
      window.setBounds(centre(getUsableScreenBounds(gc), window.getBounds()));
    } else {
      // Fall-back to letting Java do the work
      window.setLocationRelativeTo(null);
    }
  }

  public static Rectangle centre(Rectangle parent, Rectangle child) {
    child.x = (parent.width / 2) + parent.x - (child.width / 2);
    child.y = (parent.height / 2) + parent.y - (child.height / 2);

    return child;
  }

  public static Rectangle getUsableScreenBounds(GraphicsConfiguration gc) {
    Rectangle screen = gc.getBounds();
    Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
    screen.x += insets.left;
    screen.y += insets.top;
    screen.width -= insets.left + insets.right;
    screen.height -= insets.top + insets.bottom;
    return screen;
  }

  public static void ensureMinimumSize(Component c, Dimension d) {
    ensureMinimumSize(c, d.width, d.height);
  }

  public static void ensureMinimumSize(Component c, int width, int height) {
    Dimension size = c.getSize();

    if (size.width < width) {
      size.width = width;
    }

    if (size.height < height) {
      size.height = height;
    }

    c.setSize(size);
  }
}