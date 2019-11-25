package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegates;
import ca.ubc.cs304.exceptions.InvalidDetailsException;
import ca.ubc.cs304.exceptions.VehicleUnavailableException;
import ca.ubc.cs304.model.VehicleSearchResults;
import ca.ubc.cs304.model.VehicleTypeModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
/*
 * Created by JFormDesigner on Tue Nov 19 22:58:41 PST 2019
 */

// hi frank was here


/**
 * @author unknown
 */
public class Reserve extends JFrame {
    public TerminalTransactionsDelegates delegate;

    public Reserve() {}
    public void showReserve(final TerminalTransactionsDelegates delegate){
        this.delegate = delegate;
        final JFrame frame = new JFrame("Customer Reservation System");
        frame.setContentPane(dialogPane);

        ((JPanel) frame.getContentPane()).setOpaque(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setSize(800, 600);
        frame.setVisible(true);

        personalSetup();
        reserveSetup();
        // This is the current date shown in Available cars...just show all vehicles there are

        //ActionListener

        PerSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carType = (String) PerCarTypeBox.getSelectedItem();
                Boolean hasCarType = false;
                if (carType != null && !carType.isEmpty() && !carType.equals("All")) hasCarType = true;
                String city = PerCity.getText();
                String address = PerAddress.getText();
                boolean hasLocation = false;
                if (address != null && !address.isEmpty()) hasLocation = true;
                System.out.println("Time period is not filled out: " + PerFDBox.getText());
                Date fromDate = parseDateFromString(PerFDBox.getText());
                Time fromTime = parseTimeFromString(PerFTBox.getText());
                Date toDate = parseDateFromString(PerTDBox.getText());
                Time toTime = parseTimeFromString(PerFTBox.getText());
                Boolean hasTimePeriod = false;
                if (fromDate != null && fromTime != null && toDate != null && toTime != null) hasTimePeriod = true;
                VehicleSearchResults[] searchResults = delegate.customerSearchVehicle(hasCarType, hasLocation, hasTimePeriod,
                        carType, address,city, fromDate, fromTime, toDate, toTime);
                String[] listData = new String[searchResults.length +1];
                for (int i=0; i<searchResults.length; i++ ){
                    listData[i] = String.format("%-20.15s", "" + searchResults[i].getVehicleType())+
                            String.format("%-20.15s", "" + searchResults[i].getLocation()) +
                            String.format("%-20.15s", "" + PerFDBox.getText()) +
                            String.format("%-20.15s", "" + PerTDBox.getText()) +
                            String.format("%-20.15s", "" + searchResults[i].getNumAvailable());
                    System.out.println(listData[i]);
                }
                PerList.setListData(listData);
            }
        }
        );

        reservebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carType = (String) ResTypeBox.getSelectedItem();
                String dlicense = RevDlic.getText();
                String cname = ResCname.getText();
                String phoneNum = ResCphone.getText();
                String caddress = ResCaddr.getText();
                String city = ResCity.getText();
                String branchAddress = ResBA.getText();
                Date fromDate = parseDateFromString(ResFD.getText());
                Time fromTime = parseTimeFromString(ResFT.getText());
                Date toDate = parseDateFromString(ResTD.getText());
                Time toTime = parseTimeFromString(textField12.getText());
                try {
                    int confNo = delegate.makeReservation(dlicense, cname, phoneNum, caddress, city, branchAddress, carType,
                            fromDate, fromTime, toDate, toTime);
                    JOptionPane.showMessageDialog(null,"Reservation Made! Confirmation Number is " + confNo);
                } catch (InvalidDetailsException | VehicleUnavailableException ex) {
                    JOptionPane.showMessageDialog(null,"[EXCEPTION] Reservation not made: " + ex.getMessage());
                }
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

        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoleChoose rc = new RoleChoose();
                rc.showClerk(delegate);
                frame.dispose();
            }
        }
        );

    }

    private Date parseDateFromString(String dateStr) {
        String[] parts = dateStr.split("/");
        // TODO: need to add more checks
        if (dateStr.isEmpty() || parts.length != 3) return null;
        return new Date(Integer.parseInt(parts[2])-1900, Integer.parseInt(parts[0])-1, Integer.parseInt(parts[1]));
    }

    private Time parseTimeFromString(String timeStr) {
        String[] parts = timeStr.split(":");
        // TODO: need more checks
        if (timeStr.isEmpty() || parts.length != 2) return null;
        return new Time(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), 0);
    }

    private void personalSetup() {
        // adds all possible vehicle types into the drop down window for "Reserve"
        PerCarTypeBox.addItem("All");
        VehicleTypeModel[] vehicleTypes = this.delegate.getAllVehicleTypes();
        for (VehicleTypeModel vt : vehicleTypes) {
            PerCarTypeBox.addItem(vt.getVtname());
        }
    }

    public void reserveSetup() {
        // adds all possible vehicle types into the drop down window for "Reserve"
        VehicleTypeModel[] vehicleTypes = this.delegate.getAllVehicleTypes();
        for (VehicleTypeModel vt : vehicleTypes) {
            ResTypeBox.addItem(vt.getVtname());
        }
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
        Personalized = new JPanel();
        panel12 = new JPanel();
        panel1 = new JPanel();
        label14 = new JLabel();
        PerCarTypeBox = new JComboBox();
        label13 = new JLabel();
        PerCity = new JTextField();
        label2 = new JLabel();
        PerAddress = new JTextField();
        label9 = new JLabel();
        PerFDBox = new JTextField();
        label10 = new JLabel();
        PerFTBox = new JTextField();
        label11 = new JLabel();
        PerTDBox = new JTextField();
        label12 = new JLabel();
        PerTTBox = new JTextField();
        PerSearch = new JButton();
        scrollPane3 = new JScrollPane();
        PerList = new JList();
        label21 = new JLabel();
        label22 = new JLabel();
        label23 = new JLabel();
        label24 = new JLabel();
        label25 = new JLabel();
        ResTT = new JPanel();
        panel2 = new JPanel();
        label26 = new JLabel();
        ResTypeBox = new JComboBox();
        label28 = new JLabel();
        RevDlic = new JTextField();
        label32 = new JLabel();
        ResCname = new JTextField();
        label33 = new JLabel();
        ResCphone = new JTextField();
        label34 = new JLabel();
        ResCaddr = new JTextField();
        label4 = new JLabel();
        ResCity = new JTextField();
        label3 = new JLabel();
        ResBA = new JTextField();
        label27 = new JLabel();
        ResFD = new JTextField();
        label29 = new JLabel();
        ResFT = new JTextField();
        label30 = new JLabel();
        ResTD = new JTextField();
        label31 = new JLabel();
        textField12 = new JTextField();
        reservebutton = new JButton();
        buttonBar = new JPanel();
        ReturnButton = new JButton();
        FinishButton = new JButton();
        label1 = new JLabel();

        //======== this ========
        setTitle("SuperRent");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,dialogPane. getBorder( )) )
            ; dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

                //======== tabbedPane1 ========
                {
                    tabbedPane1.setMinimumSize(new Dimension(300, 194));

                    //======== Personalized ========
                    {
                        Personalized.setLayout(new GridLayout(1, 4));

                        //======== panel12 ========
                        {
                            panel12.setLayout(null);

                            //======== panel1 ========
                            {
                                panel1.setLayout(new GridLayout(7, 2));

                                //---- label14 ----
                                label14.setText("Car Type:");
                                label14.setHorizontalAlignment(SwingConstants.CENTER);
                                panel1.add(label14);
                                panel1.add(PerCarTypeBox);

                                //---- label13 ----
                                label13.setText("City:");
                                label13.setHorizontalAlignment(SwingConstants.CENTER);
                                panel1.add(label13);
                                panel1.add(PerCity);

                                //---- label2 ----
                                label2.setText("Address:");
                                label2.setHorizontalAlignment(SwingConstants.CENTER);
                                panel1.add(label2);
                                panel1.add(PerAddress);

                                //---- label9 ----
                                label9.setText("From Date:");
                                label9.setHorizontalAlignment(SwingConstants.CENTER);
                                panel1.add(label9);
                                panel1.add(PerFDBox);

                                //---- label10 ----
                                label10.setText("From Time:");
                                label10.setHorizontalAlignment(SwingConstants.CENTER);
                                panel1.add(label10);
                                panel1.add(PerFTBox);

                                //---- label11 ----
                                label11.setText("To Date:");
                                label11.setHorizontalAlignment(SwingConstants.CENTER);
                                panel1.add(label11);
                                panel1.add(PerTDBox);

                                //---- label12 ----
                                label12.setText("To Time:");
                                label12.setHorizontalAlignment(SwingConstants.CENTER);
                                panel1.add(label12);
                                panel1.add(PerTTBox);
                            }
                            panel12.add(panel1);
                            panel1.setBounds(5, 50, 265, 300);

                            //---- PerSearch ----
                            PerSearch.setText("Search");
                            PerSearch.setForeground(Color.black);
                            panel12.add(PerSearch);
                            PerSearch.setBounds(155, 345, 85, 37);

                            //======== scrollPane3 ========
                            {
                                scrollPane3.setViewportView(PerList);
                            }
                            panel12.add(scrollPane3);
                            scrollPane3.setBounds(270, 35, 495, 385);

                            //---- label21 ----
                            label21.setText("vehicleType");
                            panel12.add(label21);
                            label21.setBounds(275, 0, 115, 35);

                            //---- label22 ----
                            label22.setText("Location");
                            panel12.add(label22);
                            label22.setBounds(395, 0, 60, 35);

                            //---- label23 ----
                            label23.setText("From");
                            panel12.add(label23);
                            label23.setBounds(new Rectangle(new Point(490, 10), label23.getPreferredSize()));

                            //---- label24 ----
                            label24.setText("To");
                            panel12.add(label24);
                            label24.setBounds(new Rectangle(new Point(555, 10), label24.getPreferredSize()));

                            //---- label25 ----
                            label25.setText("Available Number");
                            panel12.add(label25);
                            label25.setBounds(630, 10, 125, label25.getPreferredSize().height);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel12.getComponentCount(); i++) {
                                    Rectangle bounds = panel12.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel12.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel12.setMinimumSize(preferredSize);
                                panel12.setPreferredSize(preferredSize);
                            }
                        }
                        Personalized.add(panel12);
                    }
                    tabbedPane1.addTab("Personalized Search", Personalized);

                    //======== ResTT ========
                    {
                        ResTT.setLayout(null);

                        //======== panel2 ========
                        {
                            panel2.setLayout(new GridLayout(11, 2));

                            //---- label26 ----
                            label26.setText("Vehicle Type Name");
                            label26.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label26);
                            panel2.add(ResTypeBox);

                            //---- label28 ----
                            label28.setText("Your Driver's License");
                            label28.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label28);
                            panel2.add(RevDlic);

                            //---- label32 ----
                            label32.setText("Your Name");
                            label32.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label32);
                            panel2.add(ResCname);

                            //---- label33 ----
                            label33.setText("Your Phone Number");
                            label33.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label33);
                            panel2.add(ResCphone);

                            //---- label34 ----
                            label34.setText("Your Current Address");
                            label34.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label34);
                            panel2.add(ResCaddr);

                            //---- label4 ----
                            label4.setText("City");
                            label4.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label4);
                            panel2.add(ResCity);

                            //---- label3 ----
                            label3.setText("Branch Address");
                            label3.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label3);
                            panel2.add(ResBA);

                            //---- label27 ----
                            label27.setText("From Date (MM/DD/YYYY)");
                            label27.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label27);
                            panel2.add(ResFD);

                            //---- label29 ----
                            label29.setText("From Time (hh:mm)");
                            label29.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label29);
                            panel2.add(ResFT);

                            //---- label30 ----
                            label30.setText("To Date (MM/DD/YYYY)");
                            label30.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label30);
                            panel2.add(ResTD);

                            //---- label31 ----
                            label31.setText("To Time (hh:mm)");
                            label31.setHorizontalAlignment(SwingConstants.CENTER);
                            panel2.add(label31);
                            panel2.add(textField12);
                        }
                        ResTT.add(panel2);
                        panel2.setBounds(110, 20, 350, 375);

                        //---- reservebutton ----
                        reservebutton.setText("CLICK HERE TO RESERVE!");
                        ResTT.add(reservebutton);
                        reservebutton.setBounds(495, 305, 270, 75);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < ResTT.getComponentCount(); i++) {
                                Rectangle bounds = ResTT.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = ResTT.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            ResTT.setMinimumSize(preferredSize);
                            ResTT.setPreferredSize(preferredSize);
                        }
                    }
                    tabbedPane1.addTab("Reserve", ResTT);
                }
                contentPanel.add(tabbedPane1);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(null);

                //---- ReturnButton ----
                ReturnButton.setText("Return");
                buttonBar.add(ReturnButton);
                ReturnButton.setBounds(614, 37, 80, 38);

                //---- FinishButton ----
                FinishButton.setText("Finish");
                buttonBar.add(FinishButton);
                FinishButton.setBounds(699, 37, 80, 38);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < buttonBar.getComponentCount(); i++) {
                        Rectangle bounds = buttonBar.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = buttonBar.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    buttonBar.setMinimumSize(preferredSize);
                    buttonBar.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            //---- label1 ----
            label1.setText("SuperRent Reservation System");
            label1.setForeground(Color.red);
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            dialogPane.add(label1, BorderLayout.NORTH);
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
    private JPanel Personalized;
    private JPanel panel12;
    private JPanel panel1;
    private JLabel label14;
    private JComboBox PerCarTypeBox;
    private JLabel label13;
    private JTextField PerCity;
    private JLabel label2;
    private JTextField PerAddress;
    private JLabel label9;
    private JTextField PerFDBox;
    private JLabel label10;
    private JTextField PerFTBox;
    private JLabel label11;
    private JTextField PerTDBox;
    private JLabel label12;
    private JTextField PerTTBox;
    private JButton PerSearch;
    private JScrollPane scrollPane3;
    private JList PerList;
    private JLabel label21;
    private JLabel label22;
    private JLabel label23;
    private JLabel label24;
    private JLabel label25;
    private JPanel ResTT;
    private JPanel panel2;
    private JLabel label26;
    private JComboBox ResTypeBox;
    private JLabel label28;
    private JTextField RevDlic;
    private JLabel label32;
    private JTextField ResCname;
    private JLabel label33;
    private JTextField ResCphone;
    private JLabel label34;
    private JTextField ResCaddr;
    private JLabel label4;
    private JTextField ResCity;
    private JLabel label3;
    private JTextField ResBA;
    private JLabel label27;
    private JTextField ResFD;
    private JLabel label29;
    private JTextField ResFT;
    private JLabel label30;
    private JTextField ResTD;
    private JLabel label31;
    private JTextField textField12;
    private JButton reservebutton;
    private JPanel buttonBar;
    private JButton ReturnButton;
    private JButton FinishButton;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
