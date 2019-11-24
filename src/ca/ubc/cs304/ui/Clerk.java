package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegates;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Created by JFormDesigner on Wed Nov 20 00:18:48 PST 2019
 */


/**
 * @author unknown
 */
public class Clerk extends JFrame {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static final int INVALID_INPUT = Integer.MIN_VALUE;
    private static final int EMPTY_INPUT = 0;
    private TerminalTransactionsDelegates delegate = null;

    public Clerk(){
    }
    public void showClerk(final TerminalTransactionsDelegates delegate) {
        this.delegate = delegate;
        final JFrame frame = new JFrame("Clerk Management System");
        frame.setContentPane(dialogPane);

        ((JPanel) frame.getContentPane()).setOpaque(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setSize(800, 600);
        frame.setVisible(true);


        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoleChoose rc = new RoleChoose();

                rc.showClerk(delegate);
                frame.dispose();
            }
        }
        );

        FinishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Thanks for your visit to our store");
                frame.dispose();
            }
        }
        );

        ReturnCar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,"Thanks for your visit to our store");
                    frame.dispose();
                }
            }
        );

        RentAllButton.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null,"Thanks for your visit to our store");
                frame.dispose();
             }
           }
        );

        RentEachButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  JOptionPane.showMessageDialog(null,"Thanks for your visit to our store");
                  frame.dispose();
              }
          }
        );

        ReturnAllButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 JOptionPane.showMessageDialog(null,"Thanks for your visit to our store");
                 frame.dispose();
             }
         }
        );

        ReturnEachButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   JOptionPane.showMessageDialog(null,"Thanks for your visit to our store");
                   frame.dispose();
               }
           }
        );

    }


    {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        textField11 = new JTextField();
        panel1 = new JPanel();
        label10 = new JLabel();
        textField7 = new JTextField();
        label12 = new JLabel();
        textField8 = new JTextField();
        textField9 = new JTextField();
        label16 = new JLabel();
        ReturnCar = new JButton();
        label17 = new JLabel();
        textField10 = new JTextField();
        scrollPane3 = new JScrollPane();
        list5 = new JList();
        tabbedPane2 = new JTabbedPane();
        panel4 = new JPanel();
        label8 = new JLabel();
        RentDateForAll = new JTextField();
        RentAllButton = new JButton();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        panel3 = new JPanel();
        label5 = new JLabel();
        RentDateForEach = new JTextField();
        label6 = new JLabel();
        RentCity = new JComboBox();
        label7 = new JLabel();
        RentAddr = new JTextField();
        RentEachButton = new JButton();
        list2 = new JList();
        tabbedPane3 = new JTabbedPane();
        panel6 = new JPanel();
        panel13 = new JPanel();
        label11 = new JLabel();
        ReturnDateForAll = new JTextField();
        ReturnAllButton = new JButton();
        scrollPane2 = new JScrollPane();
        list3 = new JList();
        panel14 = new JPanel();
        label13 = new JLabel();
        ReturnDate = new JTextField();
        label14 = new JLabel();
        ReturnCity = new JComboBox();
        label15 = new JLabel();
        ReturnEachButton = new JButton();
        list4 = new JList();
        ReturnAddr = new JComboBox();
        buttonBar = new JPanel();
        ReturnButton = new JButton();
        FinishButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
            .border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder
            .CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.
            awt.Font.BOLD,12),java.awt.Color.red),dialogPane. getBorder()))
            ;dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}})
            ;
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout());

                //======== tabbedPane1 ========
                {
                    tabbedPane1.setForeground(UIManager.getColor("Link.hoverForeground"));

                    //======== panel2 ========
                    {
                        panel2.setLayout(null);

                        //---- label1 ----
                        label1.setText("Confirmation Num:");
                        panel2.add(label1);
                        label1.setBounds(new Rectangle(new Point(60, 30), label1.getPreferredSize()));

                        //---- label2 ----
                        label2.setText("Type of Car:");
                        panel2.add(label2);
                        label2.setBounds(60, 65, 130, 20);
                        panel2.add(textField11);
                        textField11.setBounds(200, 25, 105, textField11.getPreferredSize().height);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel2.getComponentCount(); i++) {
                                Rectangle bounds = panel2.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel2.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel2.setMinimumSize(preferredSize);
                            panel2.setPreferredSize(preferredSize);
                        }
                    }
                    tabbedPane1.addTab("Rental", panel2);

                    //======== panel1 ========
                    {
                        panel1.setLayout(null);

                        //---- label10 ----
                        label10.setText("ReservationID:");
                        label10.setForeground(UIManager.getColor("Link.hoverForeground"));
                        panel1.add(label10);
                        label10.setBounds(60, 40, 110, 45);
                        panel1.add(textField7);
                        textField7.setBounds(170, 35, 125, 40);

                        //---- label12 ----
                        label12.setText("Odemeter:");
                        label12.setForeground(UIManager.getColor("Link.hoverForeground"));
                        panel1.add(label12);
                        label12.setBounds(60, 95, 90, 15);
                        panel1.add(textField8);
                        textField8.setBounds(170, 80, 125, 40);
                        panel1.add(textField9);
                        textField9.setBounds(170, 125, 125, 40);

                        //---- label16 ----
                        label16.setText("City:");
                        label16.setForeground(UIManager.getColor("Link.hoverForeground"));
                        panel1.add(label16);
                        label16.setBounds(60, 130, 60, 25);

                        //---- ReturnCar ----
                        ReturnCar.setText("Return The Car");
                        ReturnCar.setForeground(UIManager.getColor("Link.hoverForeground"));
                        panel1.add(ReturnCar);
                        ReturnCar.setBounds(185, 350, ReturnCar.getPreferredSize().width, 42);

                        //---- label17 ----
                        label17.setText("Date of Return:");
                        label17.setForeground(UIManager.getColor("Link.hoverForeground"));
                        panel1.add(label17);
                        label17.setBounds(60, 175, 95, 25);
                        panel1.add(textField10);
                        textField10.setBounds(170, 170, 125, 40);

                        //======== scrollPane3 ========
                        {
                            scrollPane3.setViewportView(list5);
                        }
                        panel1.add(scrollPane3);
                        scrollPane3.setBounds(395, 35, 345, 400);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel1.getComponentCount(); i++) {
                                Rectangle bounds = panel1.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel1.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel1.setMinimumSize(preferredSize);
                            panel1.setPreferredSize(preferredSize);
                        }
                    }
                    tabbedPane1.addTab("Return", panel1);

                    //======== tabbedPane2 ========
                    {
                        tabbedPane2.setForeground(UIManager.getColor("Link.hoverForeground"));

                        //======== panel4 ========
                        {
                            panel4.setLayout(null);

                            //---- label8 ----
                            label8.setText("Date:");
                            label8.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel4.add(label8);
                            label8.setBounds(385, 30, 55, 40);
                            panel4.add(RentDateForAll);
                            RentDateForAll.setBounds(440, 30, 110, 35);

                            //---- RentAllButton ----
                            RentAllButton.setText("Search");
                            RentAllButton.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel4.add(RentAllButton);
                            RentAllButton.setBounds(595, 30, 110, 35);

                            //======== scrollPane1 ========
                            {
                                scrollPane1.setViewportView(list1);
                            }
                            panel4.add(scrollPane1);
                            scrollPane1.setBounds(30, 70, 745, 375);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel4.getComponentCount(); i++) {
                                    Rectangle bounds = panel4.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel4.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel4.setMinimumSize(preferredSize);
                                panel4.setPreferredSize(preferredSize);
                            }
                        }
                        tabbedPane2.addTab("For all branchs", panel4);

                        //======== panel3 ========
                        {
                            panel3.setLayout(null);

                            //---- label5 ----
                            label5.setText("Date:");
                            label5.setFont(label5.getFont().deriveFont(Font.PLAIN));
                            label5.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel3.add(label5);
                            label5.setBounds(25, 30, 50, 30);
                            panel3.add(RentDateForEach);
                            RentDateForEach.setBounds(60, 20, 110, 40);

                            //---- label6 ----
                            label6.setText("City:");
                            label6.setFont(label6.getFont().deriveFont(Font.PLAIN));
                            label6.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel3.add(label6);
                            label6.setBounds(new Rectangle(new Point(185, 40), label6.getPreferredSize()));
                            panel3.add(RentCity);
                            RentCity.setBounds(220, 25, 130, 35);

                            //---- label7 ----
                            label7.setText("Branch's Address:");
                            label7.setFont(label7.getFont().deriveFont(Font.PLAIN));
                            label7.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel3.add(label7);
                            label7.setBounds(355, 35, 120, 25);
                            panel3.add(RentAddr);
                            RentAddr.setBounds(470, 25, 135, 40);

                            //---- RentEachButton ----
                            RentEachButton.setText("Search");
                            RentEachButton.setFont(RentEachButton.getFont().deriveFont(Font.PLAIN));
                            RentEachButton.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel3.add(RentEachButton);
                            RentEachButton.setBounds(615, 25, 95, 40);
                            panel3.add(list2);
                            list2.setBounds(10, 75, 760, 370);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel3.getComponentCount(); i++) {
                                    Rectangle bounds = panel3.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel3.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel3.setMinimumSize(preferredSize);
                                panel3.setPreferredSize(preferredSize);
                            }
                        }
                        tabbedPane2.addTab("For each branch", panel3);
                    }
                    tabbedPane1.addTab("Rental Report", tabbedPane2);

                    //======== tabbedPane3 ========
                    {
                        tabbedPane3.setForeground(UIManager.getColor("Link.hoverForeground"));

                        //======== panel6 ========
                        {
                            panel6.setLayout(null);

                            //======== panel13 ========
                            {
                                panel13.setLayout(null);

                                //---- label11 ----
                                label11.setText("Date:");
                                label11.setForeground(UIManager.getColor("Link.hoverForeground"));
                                panel13.add(label11);
                                label11.setBounds(385, 30, 55, 40);
                                panel13.add(ReturnDateForAll);
                                ReturnDateForAll.setBounds(440, 30, 110, 35);

                                //---- ReturnAllButton ----
                                ReturnAllButton.setText("Search");
                                ReturnAllButton.setForeground(UIManager.getColor("Link.hoverForeground"));
                                panel13.add(ReturnAllButton);
                                ReturnAllButton.setBounds(595, 30, 110, 35);

                                //======== scrollPane2 ========
                                {
                                    scrollPane2.setViewportView(list3);
                                }
                                panel13.add(scrollPane2);
                                scrollPane2.setBounds(30, 80, 745, 360);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel13.getComponentCount(); i++) {
                                        Rectangle bounds = panel13.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel13.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel13.setMinimumSize(preferredSize);
                                    panel13.setPreferredSize(preferredSize);
                                }
                            }
                            panel6.add(panel13);
                            panel13.setBounds(0, 0, 784, 449);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel6.getComponentCount(); i++) {
                                    Rectangle bounds = panel6.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel6.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel6.setMinimumSize(preferredSize);
                                panel6.setPreferredSize(preferredSize);
                            }
                        }
                        tabbedPane3.addTab("For all branchs", panel6);

                        //======== panel14 ========
                        {
                            panel14.setLayout(null);

                            //---- label13 ----
                            label13.setText("Date:");
                            label13.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel14.add(label13);
                            label13.setBounds(25, 15, 50, 35);
                            panel14.add(ReturnDate);
                            ReturnDate.setBounds(60, 5, 110, 40);

                            //---- label14 ----
                            label14.setText("City:");
                            label14.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel14.add(label14);
                            label14.setBounds(new Rectangle(new Point(185, 25), label14.getPreferredSize()));
                            panel14.add(ReturnCity);
                            ReturnCity.setBounds(220, 10, 125, 35);

                            //---- label15 ----
                            label15.setText("Branch's Address:");
                            label15.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel14.add(label15);
                            label15.setBounds(355, 20, 120, 25);

                            //---- ReturnEachButton ----
                            ReturnEachButton.setText("Search");
                            ReturnEachButton.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel14.add(ReturnEachButton);
                            ReturnEachButton.setBounds(615, 10, 95, 40);
                            panel14.add(list4);
                            list4.setBounds(10, 75, 760, 370);
                            panel14.add(ReturnAddr);
                            ReturnAddr.setBounds(475, 10, 125, 35);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel14.getComponentCount(); i++) {
                                    Rectangle bounds = panel14.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel14.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel14.setMinimumSize(preferredSize);
                                panel14.setPreferredSize(preferredSize);
                            }
                        }
                        tabbedPane3.addTab("For each branch", panel14);
                    }
                    tabbedPane1.addTab("Return Report", tabbedPane3);
                }
                contentPanel.add(tabbedPane1);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- ReturnButton ----
                ReturnButton.setText("Return");
                ReturnButton.setForeground(UIManager.getColor("Link.hoverForeground"));
                buttonBar.add(ReturnButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- FinishButton ----
                FinishButton.setText("Finish");
                FinishButton.setForeground(UIManager.getColor("Link.hoverForeground"));
                buttonBar.add(FinishButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField11;
    private JPanel panel1;
    private JLabel label10;
    private JTextField textField7;
    private JLabel label12;
    private JTextField textField8;
    private JTextField textField9;
    private JLabel label16;
    private JButton ReturnCar;
    private JLabel label17;
    private JTextField textField10;
    private JScrollPane scrollPane3;
    private JList list5;
    private JTabbedPane tabbedPane2;
    private JPanel panel4;
    private JLabel label8;
    private JTextField RentDateForAll;
    private JButton RentAllButton;
    private JScrollPane scrollPane1;
    private JList list1;
    private JPanel panel3;
    private JLabel label5;
    private JTextField RentDateForEach;
    private JLabel label6;
    private JComboBox RentCity;
    private JLabel label7;
    private JTextField RentAddr;
    private JButton RentEachButton;
    private JList list2;
    private JTabbedPane tabbedPane3;
    private JPanel panel6;
    private JPanel panel13;
    private JLabel label11;
    private JTextField ReturnDateForAll;
    private JButton ReturnAllButton;
    private JScrollPane scrollPane2;
    private JList list3;
    private JPanel panel14;
    private JLabel label13;
    private JTextField ReturnDate;
    private JLabel label14;
    private JComboBox ReturnCity;
    private JLabel label15;
    private JButton ReturnEachButton;
    private JList list4;
    private JComboBox ReturnAddr;
    private JPanel buttonBar;
    private JButton ReturnButton;
    private JButton FinishButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
