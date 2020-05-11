package org.example;

import javax.swing.*;
import java.awt.*;

public class DesignPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;

    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setLayout(null); // altfel din cate am observat ramane relativa (similar cu Justify pe centru)
    }
}
