import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {
    double resultat;
    String operator;
    private JButton[] b;
    private JLabel l;
    private JPanel containerPane;
    private JPanel bottomPaneLeft;
    private JPanel bottomPaneRight;

    Frame() {
        resultat = 0;
        setLayout(new FlowLayout());
        setSize(250, 300);
        String[] tab = {"=", ".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "C", "+", "-", "*", "/"};
        this.b = new JButton[tab.length];
        for (int i = 0; i < 17; i++) {
            b[i] = new JButton(tab[i]);
            b[i].setPreferredSize(new Dimension(50, 40));
        }


        l = new JLabel("");
        l.setBorder(BorderFactory.createLineBorder(Color.black));
        containerPane = new JPanel();
        bottomPaneLeft = new JPanel();
        bottomPaneRight = new JPanel();
        l.setPreferredSize(new Dimension(220, 20));
        l.setHorizontalAlignment(JLabel.RIGHT);
        bottomPaneLeft.setPreferredSize(new Dimension(165, 225));
        bottomPaneRight.setPreferredSize(new Dimension(55, 225));

        for (int i = 11; i >= 0; i--) {
            bottomPaneLeft.add(b[i]);
        }

        for (int i = 12; i < 17; i++) {
            bottomPaneRight.add(b[i]);
        }

        containerPane.add(bottomPaneLeft);
        containerPane.add(bottomPaneRight);

        add(l);
        add(containerPane);
        ActionListener[] al = new ActionListener[17];
        for (int i = 0; i < 17; i++) {
            final int j = i;
            if (i == 12) {
                al[i] = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        l.setText("");
                        resultat = 0;
                        l.setForeground(Color.black);
                    }
                };
            } else {
                al[i] = new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        String s1 = b[j].getText();
                        String s = l.getText();
                        if (s != "") {

                            if (s1.equals("+") || s1.equals("-") || s1.equals("*") || s1.equals("/")) {
                                try {

                                    double num1 = Double.valueOf(s);
                                    resultat = resultat + num1;
                                    l.setText(l.getText() + s1);
                                }catch(NumberFormatException e1) {
                                    l.setText("Error");
                                    l.setForeground(Color.red);
                                }

                            } else if (s1.equals("=")) {
                                if (s.indexOf("+") != -1) {
                                    double num2 = Double.parseDouble(s.substring(s.indexOf('+') + 1, s.length()));
                                    resultat = resultat + num2;
                                    l.setText(resultat + "");
                                } else if (s.indexOf("-") != -1) {
                                    double num2 = Double.parseDouble(s.substring(s.indexOf('-') + 1, s.length()));
                                    resultat = resultat - num2;
                                    l.setText(resultat + "");
                                } else if (s.indexOf("*") != -1) {
                                    double num2 = Double.parseDouble(s.substring(s.indexOf('*') + 1, s.length()));
                                    resultat = resultat * num2;
                                    l.setText(resultat + "");
                                } else if (s.indexOf("/") != -1) {
                                    double num2 = Double.parseDouble(s.substring(s.indexOf('/') + 1, s.length()));
                                    try{
                                    if(num2==0){
                                        resultat=0;
                                      throw new ArithmeticException() ;
                                    }

                                        resultat = resultat / num2;
                                        l.setText(resultat + "");
                                    }catch(ArithmeticException e1){
                                        l.setText("Error");
                                        l.setForeground(Color.red);
                                    }

                                }else if(s.equals(".")){
                                    l.setText("0.");
                                }


                            }
                            else {
                                l.setText(l.getText() + s1);
                            }
                        } else {
                           if (s1.equals("+") || s1.equals("-") || s1.equals("*") || s1.equals("/") ){
                                    l.setText("Error");
                                    l.setForeground(Color.red);
                                }else l.setText(l.getText() + s1);
                        }


                    }};
            }

            b[i].addActionListener(al[i]);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
