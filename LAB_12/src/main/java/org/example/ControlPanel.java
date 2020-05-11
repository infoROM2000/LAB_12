package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 50;
    JLabel clasaLabel = new JLabel("Nume clasa: ");
    JLabel textLabel = new JLabel("Text obiect: ");
    JTextField numeClasaField = new JTextField(50);
    JTextField textObiectField = new JTextField(50);
    JButton addBtn = new JButton("Adauga");
    JButton resetBtn = new JButton("Reset"); //buton pentru resetarea textului
    final static int objectW=100, objectH=100; //dimensiuni standard obiect
    int pozX=0,pozY=0;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setLayout(new GridLayout(2, 3));
        add(clasaLabel);
        add(numeClasaField);
        add(resetBtn);
        add(textLabel);
        add(textObiectField);
        add(addBtn);
        addBtn.addActionListener(this::add);
        resetBtn.addActionListener(this::reset);
    }

    private void add(ActionEvent e) {
        String text = numeClasaField.getText();
        Class clasa;
        try {
            clasa = Class.forName(text); //se incearca incarcarea dinamica a clasei
        } catch (ClassNotFoundException ex) {
            System.out.println("Nu s-a gasit clasa cu numele: " + text);
            System.err.println(ex);
            return;
        }
        String[] split = text.split("\\."); //impartim in cuvinte separate prin punct
        for (String s : split) {
            System.out.println(s);
        }
        String tip = split[2]; // al 3-lea cuvant care este tipul (dupa javax si swing)

        try {
            switch (tip) {
                case "JButton":
                    JButton b = (JButton) clasa.newInstance();
                    b.setText(textObiectField.getText());
                    b.setBounds(pozX,pozY,objectH,objectW);
                    frame.designPanel.add(b);
                    break;
                case "JLabel":
                    JLabel l = (JLabel) clasa.newInstance();
                    l.setPreferredSize(new Dimension(100,100));
                    l.setText(textObiectField.getText());
                    frame.designPanel.add(l);
                    break;
            }
        } catch (InstantiationException | IllegalAccessException instantiationException) {
            instantiationException.printStackTrace();
        }
        pozX+=objectW;
        if(pozX==W)
        {
            pozX=0;
            pozY+=objectH;
        }
        frame.designPanel.revalidate(); // necesar altfel nu se actualizeaza DesignPanelul
        frame.designPanel.repaint();
    }

    private void reset(ActionEvent e) {
        numeClasaField.setText("");
        textObiectField.setText("");
    }
}
