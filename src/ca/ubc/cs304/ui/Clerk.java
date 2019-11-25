package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegates;
import ca.ubc.cs304.exceptions.InvalidDetailsException;
import ca.ubc.cs304.exceptions.InvalidReservationException;
import ca.ubc.cs304.model.*;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
/*
 * Created by JFormDesigner on Wed Nov 20 00:18:48 PST 2019
 */



/**
 * @author unknown
 */
public class Clerk extends JFrame {
    public TerminalTransactionsDelegates delegate = null;
    public Clerk(){}

    private Date parseDateFromString(String dateStr) {
        String[] parts = dateStr.split("/");
        // TODO: need to add more checks
        if (dateStr.isEmpty() || parts.length != 3) return null;
        return new Date(Integer.parseInt(parts[2])+1900, Integer.parseInt(parts[0])-1, Integer.parseInt(parts[1]));
    }

    public void showClerk(final TerminalTransactionsDelegates delegate){
        this.delegate = delegate;
        final JFrame frame = new JFrame("Clerk Management System");
        frame.setContentPane(dialogPane);

        ((JPanel) frame.getContentPane()).setOpaque(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setSize(800, 600);
        frame.setVisible(true);

        RentSetUp();
        ReturnSetUp();




        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);

        RentIDBox.addItem(a.get(0));
        RentIDBox.addItem(a.get(1));




        ////Return show available ID
        ////Show all rented ID
        //rentednotreturned() return arraylist
        //
//        ArrayList<Integer> rentedlist = rentednotreturned();
//        for (int i = 0; i <rentedlist.size(); i++){
//            RentIDBox.addItem(rentedlist.get(i)));
//        }



        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoleChoose rc =new RoleChoose();
                rc.showClerk(delegate);
                frame.dispose();
            }
        }
        );

        FinishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Exception:
                JOptionPane.showMessageDialog(null,"Thanks for your visit to our store");
                frame.dispose();
            }
        }
        );

        ReturnCar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ReturnResult rr = new ReturnResult();
                    int rentId = (int) RentIDBox.getSelectedItem();
                    String dateofreturn = ReturnDate.getText();
                    if (dateofreturn.length()==8){
                        int date = Integer.parseInt(dateofreturn);
                        rr = delegate.returnVehicle(rentId,date);
                        ReturnCN.setText(Integer.toString(rr.getConfNum()));
                        ReturnRentD.setText(rr.getFromDate().toString());
                        ReturnReturnD.setText(rr.getReturnDate().toString());
                        ReturnRate.setText(Integer.toString(rr.getdRate()));
                        ReturnDur.setText(Integer.toString(rr.getDaysRent()));
                        ReturnTP.setText(Integer.toString(rr.getPrice()));
                    }
                    else{
                        //JOptionPane.showMessageDialog(null,"ENTER VALID TIME PERIOD! (e.g. 20191100)");
                    }
                }

            }
        );


        RentAllButton.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) {

                int num;
                VehicleRented[] vr = null;
                TotalCatModel[] tcm = null;
                TotalBranchModel[] tbm = null;

                String[] listData = null;
                String[] listCat = null;
                String[] listTbm = null;
                String sdate =  RentDateForAll.getText();
                if (sdate.length()==8){

                    num= delegate.totalRental(parseDateFromString(sdate));
                    totalRentNum.setText("Total Number of Rental on this date: " + num);

                    vr = delegate.getAllRental(Integer.parseInt(sdate));
                    listData = new String[vr.length+1];
                    listData[0] = String.format("%-20.15s", "Vehicle Type")+
                            String.format("%-4.4s", " " + "Make") +
                            String.format("%-20.15s", "" + "Model")
                            + String.format("%-20.15s", "" + "Vehicle license");

                    for (int i = 1; i < vr.length; i++) {
                        VehicleRented r = vr[i];
                        listData[i] = String.format("%-20.15s", "" + r.getVtname())+
                                String.format("%-4.4s", " " + r.getMake()) +
                                String.format("%-20.15s", "" + r.getModel())
                                + String.format("%-20.15s", "" + r.getVlicense())
                        ;
                    }
                    RentalDetailAllList.setListData(listData);

                    tcm = delegate.totalCatgeory(parseDateFromString(sdate));
                    listCat = new String[tcm.length+1];
                    listCat[0] = String.format("%-20.15s", "Vehicle Type")+
                            String.format("%-20.15s", " " + "COUNT");
                    for (int i = 1; i < tcm.length; i++) {
                        TotalCatModel r = tcm[i];
                        listData[i] = String.format("%-20.15s", "" + r.getVtname())+
                                String.format("%-20.15s", " " + r.getCount())
                        ;
                    }
                    RentalTypeAllList.setListData(listCat);

                    tbm = delegate.totalBranch(parseDateFromString(sdate));
                    listTbm = new String[tbm.length+1];
                    listTbm[0] = String.format("%-20.15s", "City")+
                            String.format("%-20.15s", "Location")+
                            String.format("%-20.15s", " " + "COUNT");
                    for (int i = 1; i < tbm.length; i++) {
                        TotalBranchModel r = tbm[i];
                        listData[i] = String.format("%-20.15s", "" + r.getCity())+
                                String.format("%-20.15s", "" + r.getLocation())+
                                String.format("%-20.15s", " " + r.getCount())
                        ;
                    }
                    RentalBraAllList.setListData(listCat);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Date is not Valid");
                }
             }
           }
        );

        RentEachButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  String sdate = RentDateForEach.getText();
                  BranchCat[] bc = null;
                  VehicleRented[] vr = null;

                  String[] listbc = null;
                  String[] listvr = null;

                  String city = (String) RentCity.getText();
                  String location = (String) RentAddr.getText();
                  if (location != "" && city !="" && sdate.length()==8){

                      //int date = Integer.parseInt(sdate);
                      int datenum= delegate.totalRental(parseDateFromString(sdate));
                      int num = delegate.totalBranches(parseDateFromString(sdate),city,location);
                      braRentNum.setText("Total Number of Rental for this branch on this date: "+num);

                      bc = delegate.getBranchCategory(parseDateFromString(sdate), city, location);
                      listbc = new String[bc.length+1];
                      listbc[0] = String.format("%-20.15s", "Vehicle Type")+
                              String.format("%-20.15s", " " + "COUNT");
                      for (int i = 1; i < bc.length; i++) {
                          BranchCat r = bc[i];
                          listbc[i] = String.format("%-20.15s", "" + r.getVtname())+
                                  String.format("%-20.15s", "" + r.getCount())
                          ;
                      }
                      RentalTypeEachList.setListData(listbc);

                      vr = delegate.getAllBranchRental(parseDateFromString(sdate), city, location);
                      listvr = new String[vr.length+1];
                      listvr[0] = String.format("%-20.15s", "Vehicle Type")+
                              String.format("%-4.4s"," " + "Make")+
                              String.format("%-20.15s", "Model")+
                              String.format("%-20.15s", " " + "Vlicense");
                      for (int i = 1; i < bc.length; i++) {
                          VehicleRented r = vr[i];
                          listbc[i] = String.format("%-20.15s", "" + r.getVtname())+
                                  String.format("%-4.4s", " " + r.getMake())+
                                  String.format("%-20.15s", "" + r.getModel())+
                                  String.format("%-20.15s", "" + r.getVlicense());
                      }
                      RentalDetailEachList.setListData(listbc);

                  }
                  else
                      JOptionPane.showMessageDialog(null, "Not Valid, Try again");

              }
          }
        );

        ReturnAllButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 String sdate =  RentDateForAll.getText();
                 if (sdate.length()==8){
                     Date date = parseDateFromString(sdate);
                     Pair<Integer, Integer> pair = delegate.totalRevenue(date);
                     totalReturnNum.setText("Total Number of Return on this date:" + pair.getKey());
                     totalReturnRev.setText("Total Revenue on this date:" + pair.getValue());

                     VehicleRented[] vr = delegate.getReturn(date);
                     String[] listvr = new String[vr.length+1];

                     listvr[0] =String.format("%-20.15s", "Vehicle Type")+
                             String.format("%-4.4s"," " + "Make")+
                             String.format("%-20.15s", "Model")+
                             String.format("%-20.15s", " " + "Vlicense");
                     ;

                     for (int i = 1; i < vr.length; i++) {
                         VehicleRented r = vr[i];
                         listvr[i] = String.format("%-20.15s", "" + r.getVtname())+
                                 String.format("%-4.4s", " " + r.getMake())+
                                 String.format("%-20.15s", "" + r.getModel())+
                                 String.format("%-20.15s", "" + r.getVlicense());
                     }

                     ReturnDetailAllList.setListData(listvr);


                     RevenueCat[] rc;
                     rc = delegate.revenueCat(date);
                     String[] listrc = new String[rc.length+1];

                     listrc[0] = String.format("%-20.15s", "Vehicle Type")+
                             String.format("%-20.15s", " " + "COUNT")+
                             String.format("%-20.15s", " " + "Revenue")
                     ;

                     for (int i = 1; i < rc.length; i++) {
                         RevenueCat r = rc[i];
                         listrc[i] = String.format("%-20.15s", "" + r.getVtname())+
                                 String.format("%-20.15s", "" + r.getCount())+
                                 String.format("%-20.15s", " " + r.getRevenue())
                         ;
                     }
                     ReturnTypeAllList.setListData(listrc);

                     RevenueBranch[] rb;
                     rb = delegate.revenueBranch(date);
                     String[] listrb = new String[rb.length+1];

                     listrb[0] = String.format("%-20.15s", "City")+
                             String.format("%-20.15s", " " + "Location")+
                             String.format("%-20.15s", " " + "COUNT")+
                             String.format("%-20.15s", " " + "Revenue")
                     ;

                     for (int i = 1; i < rc.length; i++) {
                         RevenueBranch r = rb[i];
                         listrb[i] = String.format("%-20.15s", "" + r.getCity())+
                                 String.format("%-20.15s", "" + r.getLocation())+
                                 String.format("%-20.15s", "" + r.getCount())+
                                 String.format("%-20.15s", " " + r.getRevenue())
                         ;
                     }
                     RentalBraAllList2.setListData(listrb);
                 }
                 else {
                     JOptionPane.showMessageDialog(null, "Date is not Valid");
                 }
             }


         }
        );

        ReturnEachButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   String sdate = ReturnDateEach.getText();
                   Date date = parseDateFromString(sdate);
                   String city = (String) ReturnCity.getText();
                   String addr = (String)ReturnAddr.getText();

                   if (sdate.length()==9 && city !="" && addr != ""){
                       VehicleRented[] vr = delegate.getReturnBranch(date,city,addr);
                       String[] listvr = new String[vr.length+1];

                       listvr[0] = String.format("%-20.15s", "Vehicle Type")+
                               String.format("%-4.4s"," " + "Make")+
                               String.format("%-20.15s", "Model")+
                               String.format("%-20.15s", " " + "Vlicense");
                       ;

                       for (int i = 1; i < vr.length; i++) {
                           VehicleRented r = vr[i];
                           listvr[i] = String.format("%-20.15s", "" + r.getVtname())+
                                   String.format("%-4.4s", " " + r.getMake())+
                                   String.format("%-20.15s", "" + r.getModel())+
                                   String.format("%-20.15s", "" + r.getVlicense());
                       }

                       ReturnDetailAllList2.setListData(listvr);

                       RevenueBranch rb = delegate.getRevenueBranch(date, city, addr);
                       braReturnNum.setText("Total Number of Return for This Branch on This Date: "+ rb.getCount());
                       braReturnRev.setText("Total Revenue for This Branch: " + rb.getRevenue());

                       RevenueBranchCat[] rbc = delegate.getRevenueBranchCat(date, city, addr);
                       String[] listrbc = new String[rbc.length+1];
                       listrbc[0] = String.format("%-20.15s", "Vehicle Type")+
                               String.format("%-20.15s", " " + "COUNT")+
                               String.format("%-20.15s", " " + "Revenue")
                       ;

                       for (int i = 1; i < rbc.length; i++) {
                           RevenueBranchCat r = rbc[i];
                           listrbc[i] = String.format("%-20.15s", "" + r.getVtname())+
                                   String.format("%-20.15s", "" + r.getCount())+
                                   String.format("%-20.15s", " " + r.getRevenue())
                           ;
                       }
                       ReturnTypeAllList2.setListData(listrbc);

                   }
                   else{
                       JOptionPane.showMessageDialog(null,"Thanks for your visit to our store");
                   }

               }
           }
        );

        rentbutton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                RentalReceipt receipt = null;
                                                String confNo = ResConf.getText();
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
                                                String cardName = cardNameTextField.getText();
                                                String cardNo = cardNoTextField.getText();
                                                Date expDate = parseDateFromString(expDateTextField.getText());
                                                try {
                                                    if (confNo != null || !confNo.isEmpty())
                                                        receipt = delegate.makeRental(Integer.parseInt(confNo), city, branchAddress, cardName, cardNo, expDate);
                                                    else
                                                        receipt = delegate.makeRental(dlicense, cname, phoneNum, caddress, city, branchAddress, carType, fromDate, fromTime, toDate, toTime, cardName, cardNo, expDate);
                                                } catch (InvalidReservationException | InvalidDetailsException ex) {
                                                    JOptionPane.showMessageDialog(null,"[EXCEPTION] Rental not made: " + ex.getMessage());
                                                }
                                                if (receipt != null) {
                                                    String receiptString = "Rental Receipt:\n\nRental ID: " + receipt.getRid()
                                                        + "\nBranch Address: " + receipt.getLocation()
                                                        + "\nVehicle Type: " + receipt.getVtname()
                                                        + "\n for Time Period" + ResFD.getText() + " to " + ResTD.getText();
                                                    JOptionPane.showMessageDialog(null,receiptString); }

                                           }
                                        }
        );

    }


    {
        initComponents();
    }

    private Date parseDateFromString(String dateStr) {
        String[] parts = dateStr.split("/");
        // TODO: need to add more checks
        if (parts.length == 2) return new Date(Integer.parseInt(parts[1])+1900, Integer.parseInt(parts[0])-1, 0);
        if (dateStr.isEmpty() || parts.length != 3) return null;
        return new Date(Integer.parseInt(parts[2])+1900, Integer.parseInt(parts[0])-1, Integer.parseInt(parts[1]));
    }

    private Time parseTimeFromString(String timeStr) {
        String[] parts = timeStr.split(":");
        // TODO: need more checks
        if (timeStr.isEmpty() || parts.length != 2) return null;
        return new Time(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), 0);
    }

    public void RentSetUp() {
        // adds all possible vehicle types into the drop down window for "Reserve"
        VehicleTypeModel[] vehicleTypes = this.delegate.getAllVehicleTypes();
        for (VehicleTypeModel vt : vehicleTypes) {
            ResTypeBox.addItem(vt.getVtname());
        }
    }

    public void ReturnSetUp(){
        RentalModel[] rm = delegate.rentedNotReturned();
        for (int i = 0; i<rm.length;i++){
            RentIDBox.addItem(rm[i].getRid());
        }
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        ResTT = new JPanel();
        rentbutton = new JButton();
        panel7 = new JPanel();
        label1 = new JLabel();
        ResConf = new JTextField();
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
        label2 = new JLabel();
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
        panel1 = new JPanel();
        label10 = new JLabel();
        ReturnDate = new JTextField();
        ReturnCar = new JButton();
        label17 = new JLabel();
        RentIDBox = new JComboBox();
        panel5 = new JPanel();
        label9 = new JLabel();
        ReturnCN = new JLabel();
        label4 = new JLabel();
        ReturnRentD = new JLabel();
        label12 = new JLabel();
        ReturnReturnD = new JLabel();
        label20 = new JLabel();
        ReturnRate = new JLabel();
        label21 = new JLabel();
        ReturnDur = new JLabel();
        label23 = new JLabel();
        ReturnTP = new JLabel();
        label24 = new JLabel();
        tabbedPane2 = new JTabbedPane();
        panel4 = new JPanel();
        label8 = new JLabel();
        RentDateForAll = new JTextField();
        RentAllButton = new JButton();
        tabbedPane4 = new JTabbedPane();
        scrollPane1 = new JScrollPane();
        RentalDetailAllList = new JList();
        scrollPane5 = new JScrollPane();
        RentalTypeAllList = new JList();
        scrollPane6 = new JScrollPane();
        RentalBraAllList = new JList();
        totalRentNum = new JLabel();
        panel3 = new JPanel();
        label5 = new JLabel();
        RentDateForEach = new JTextField();
        label6 = new JLabel();
        label7 = new JLabel();
        RentAddr = new JTextField();
        RentEachButton = new JButton();
        tabbedPane5 = new JTabbedPane();
        scrollPane3 = new JScrollPane();
        RentalDetailEachList = new JList();
        scrollPane7 = new JScrollPane();
        RentalTypeEachList = new JList();
        braRentNum = new JLabel();
        RentCity = new JTextField();
        TotalReturnNum = new JTabbedPane();
        panel6 = new JPanel();
        panel13 = new JPanel();
        label11 = new JLabel();
        ReturnDateForAll = new JTextField();
        ReturnAllButton = new JButton();
        tabbedPane6 = new JTabbedPane();
        scrollPane2 = new JScrollPane();
        ReturnDetailAllList = new JList();
        scrollPane9 = new JScrollPane();
        ReturnTypeAllList = new JList();
        scrollPane10 = new JScrollPane();
        RentalBraAllList2 = new JList();
        totalReturnNum = new JLabel();
        totalReturnRev = new JLabel();
        panel14 = new JPanel();
        label13 = new JLabel();
        ReturnDateEach = new JTextField();
        label14 = new JLabel();
        label15 = new JLabel();
        ReturnEachButton = new JButton();
        tabbedPane7 = new JTabbedPane();
        scrollPane4 = new JScrollPane();
        ReturnDetailAllList2 = new JList();
        scrollPane11 = new JScrollPane();
        ReturnTypeAllList2 = new JList();
        braReturnNum = new JLabel();
        braReturnRev = new JLabel();
        ReturnCity = new JTextField();
        ReturnAddr = new JTextField();
        buttonBar = new JPanel();
        ReturnButton = new JButton();
        FinishButton = new JButton();
        cardNameLabel = new JLabel();
        cardNoLabel = new JLabel();
        expDateLabel = new JLabel();
        cardNameTextField = new JTextField();
        cardNoTextField = new JTextField();
        expDateTextField = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout());

                //======== tabbedPane1 ========
                {
                    tabbedPane1.setForeground(UIManager.getColor("Link.hoverForeground"));

                    {
                        panel7.setLayout(new GridLayout(6, 2));

                        //----- label26 -----
                        label26.setText("Which table do you want to view into?");
                        label26.setHorizontalAlignment(SwingConstants.CENTER);
                        panel7.add(label26);

                    }
                    tabbedPane1.addTab("View Data Manipulations", panel7);

                    //======== panel2 ========
                    {
                        panel2.setLayout(null);

                        //======== ResTT ========
                        {
                            ResTT.setLayout(null);

                            //---- reservebutton ----
                            rentbutton.setText("CLICK HERE TO RENT!");
                            ResTT.add(rentbutton);
                            rentbutton.setBounds(495, 305, 270, 75);

                            //======== panel7 ========
                            {
                                panel7.setLayout(new GridLayout(15, 2));

                                //---- label1 ----
                                label1.setText("Confirmation Number");
                                label1.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label1);
                                panel7.add(ResConf);

                                //---- label26 ----
                                label26.setText("Vehicle Type Name");
                                label26.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label26);
                                panel7.add(ResTypeBox);

                                //---- label28 ----
                                label28.setText("Your Driver's License");
                                label28.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label28);
                                panel7.add(RevDlic);

                                //---- label32 ----
                                label32.setText("Your Name");
                                label32.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label32);
                                panel7.add(ResCname);

                                //---- label33 ----
                                label33.setText("Your Phone Number");
                                label33.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label33);
                                panel7.add(ResCphone);

                                //---- label34 ----
                                label34.setText("Your Current Address");
                                label34.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label34);
                                panel7.add(ResCaddr);

                                //---- label2 ----
                                label2.setText("City");
                                label2.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label2);
                                panel7.add(ResCity);

                                //---- label3 ----
                                label3.setText("Branch Address");
                                label3.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label3);
                                panel7.add(ResBA);

                                //---- label27 ----
                                label27.setText("From Date (MM/DD/YYYY)");
                                label27.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label27);
                                panel7.add(ResFD);

                                //---- label29 ----
                                label29.setText("From Time (hh:mm)");
                                label29.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label29);
                                panel7.add(ResFT);

                                //---- label30 ----
                                label30.setText("To Date (MM/DD/YYYY)");
                                label30.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label30);
                                panel7.add(ResTD);

                                //---- label31 ----
                                label31.setText("To Time (hh:mm)");
                                label31.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(label31);
                                panel7.add(textField12);

                                cardNameLabel.setText("Card Name");
                                cardNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(cardNameLabel);
                                panel7.add(cardNameTextField);

                                cardNoLabel.setText("Card Number");
                                cardNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(cardNoLabel);
                                panel7.add(cardNoTextField);

                                expDateLabel.setText("Exp Date (MM/YYYY)");
                                expDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                panel7.add(expDateLabel);
                                panel7.add(expDateTextField);
                            }
                            ResTT.add(panel7);
                            panel7.setBounds(60, 25, 350, 375);

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
                        panel2.add(ResTT);
                        ResTT.setBounds(10, 20, 779, 414);

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
                        label10.setText("Rent ID:");
                        label10.setForeground(UIManager.getColor("Link.hoverForeground"));
                        label10.setHorizontalAlignment(SwingConstants.CENTER);
                        panel1.add(label10);
                        label10.setBounds(50, 100, 110, 45);
                        panel1.add(ReturnDate);
                        ReturnDate.setBounds(170, 165, 125, 40);

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
                        panel1.add(RentIDBox);
                        RentIDBox.setBounds(175, 105, 115, 35);

                        //======== panel5 ========
                        {
                            panel5.setLayout(new GridLayout(6, 0));

                            //---- label9 ----
                            label9.setText("Comfirmation Number:");
                            panel5.add(label9);
                            panel5.add(ReturnCN);

                            //---- label4 ----
                            label4.setText("Rental Date:");
                            panel5.add(label4);
                            panel5.add(ReturnRentD);

                            //---- label12 ----
                            label12.setText("Return Date:");
                            panel5.add(label12);
                            panel5.add(ReturnReturnD);

                            //---- label20 ----
                            label20.setText("Daily Rate:");
                            panel5.add(label20);
                            panel5.add(ReturnRate);

                            //---- label21 ----
                            label21.setText("Duration:");
                            panel5.add(label21);
                            panel5.add(ReturnDur);

                            //---- label23 ----
                            label23.setText("Total Price:");
                            panel5.add(label23);
                            panel5.add(ReturnTP);
                        }
                        panel1.add(panel5);
                        panel5.setBounds(450, 60, 310, 390);

                        //---- label24 ----
                        label24.setText("Receipt");
                        label24.setHorizontalAlignment(SwingConstants.CENTER);
                        label24.setForeground(Color.pink);
                        panel1.add(label24);
                        label24.setBounds(505, 25, 220, 40);

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
                            RentAllButton.setBounds(645, 30, 110, 35);

                            //======== tabbedPane4 ========
                            {

                                //======== scrollPane1 ========
                                {
                                    scrollPane1.setViewportView(RentalDetailAllList);
                                }
                                tabbedPane4.addTab("Detail of All Rentals On The Day", scrollPane1);

                                //======== scrollPane5 ========
                                {
                                    scrollPane5.setViewportView(RentalTypeAllList);
                                }
                                tabbedPane4.addTab("Group By Category", scrollPane5);

                                //======== scrollPane6 ========
                                {
                                    scrollPane6.setViewportView(RentalBraAllList);
                                }
                                tabbedPane4.addTab("Group By Branch", scrollPane6);
                            }
                            panel4.add(tabbedPane4);
                            tabbedPane4.setBounds(25, 70, 740, 370);

                            //---- totalRentNum ----
                            totalRentNum.setForeground(UIManager.getColor("Button.default.endBackground"));
                            panel4.add(totalRentNum);
                            totalRentNum.setBounds(25, 25, 305, 30);

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
                            label5.setBounds(25, 15, 50, 30);
                            panel3.add(RentDateForEach);
                            RentDateForEach.setBounds(60, 5, 110, 40);

                            //---- label6 ----
                            label6.setText("City:");
                            label6.setFont(label6.getFont().deriveFont(Font.PLAIN));
                            label6.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel3.add(label6);
                            label6.setBounds(new Rectangle(new Point(185, 25), label6.getPreferredSize()));

                            //---- label7 ----
                            label7.setText("Branch's Address:");
                            label7.setFont(label7.getFont().deriveFont(Font.PLAIN));
                            label7.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel3.add(label7);
                            label7.setBounds(355, 20, 120, 25);
                            panel3.add(RentAddr);
                            RentAddr.setBounds(470, 10, 135, 40);

                            //---- RentEachButton ----
                            RentEachButton.setText("Search");
                            RentEachButton.setFont(RentEachButton.getFont().deriveFont(Font.PLAIN));
                            RentEachButton.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel3.add(RentEachButton);
                            RentEachButton.setBounds(615, 10, 95, 40);

                            //======== tabbedPane5 ========
                            {

                                //======== scrollPane3 ========
                                {
                                    scrollPane3.setViewportView(RentalDetailEachList);
                                }
                                tabbedPane5.addTab("Detail of All Rentals On The Day", scrollPane3);

                                //======== scrollPane7 ========
                                {
                                    scrollPane7.setViewportView(RentalTypeEachList);
                                }
                                tabbedPane5.addTab("Group By Category", scrollPane7);
                            }
                            panel3.add(tabbedPane5);
                            tabbedPane5.setBounds(0, 105, 785, 310);

                            //---- braRentNum ----
                            braRentNum.setForeground(UIManager.getColor("Button.default.endBackground"));
                            panel3.add(braRentNum);
                            braRentNum.setBounds(10, 50, 420, 30);
                            panel3.add(RentCity);
                            RentCity.setBounds(230, 10, 120, 32);

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

                    //======== TotalReturnNum ========
                    {
                        TotalReturnNum.setForeground(UIManager.getColor("Link.hoverForeground"));

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

                                //======== tabbedPane6 ========
                                {

                                    //======== scrollPane2 ========
                                    {
                                        scrollPane2.setViewportView(ReturnDetailAllList);
                                    }
                                    tabbedPane6.addTab("Detail of All Rentals On The Day", scrollPane2);

                                    //======== scrollPane9 ========
                                    {
                                        scrollPane9.setViewportView(ReturnTypeAllList);
                                    }
                                    tabbedPane6.addTab("Group By Category", scrollPane9);

                                    //======== scrollPane10 ========
                                    {
                                        scrollPane10.setViewportView(RentalBraAllList2);
                                    }
                                    tabbedPane6.addTab("Group By Branch", scrollPane10);
                                }
                                panel13.add(tabbedPane6);
                                tabbedPane6.setBounds(0, 65, 765, 380);

                                //---- totalReturnNum ----
                                totalReturnNum.setForeground(UIManager.getColor("Button.default.endBackground"));
                                panel13.add(totalReturnNum);
                                totalReturnNum.setBounds(20, 0, 330, 30);

                                //---- totalReturnRev ----
                                totalReturnRev.setForeground(UIManager.getColor("Button.default.endBackground"));
                                panel13.add(totalReturnRev);
                                totalReturnRev.setBounds(20, 35, 200, 25);

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
                        TotalReturnNum.addTab("For all branchs", panel6);

                        //======== panel14 ========
                        {
                            panel14.setLayout(null);

                            //---- label13 ----
                            label13.setText("Date:");
                            label13.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel14.add(label13);
                            label13.setBounds(25, 15, 50, 35);
                            panel14.add(ReturnDateEach);
                            ReturnDateEach.setBounds(60, 5, 110, 40);

                            //---- label14 ----
                            label14.setText("City:");
                            label14.setForeground(UIManager.getColor("Link.hoverForeground"));
                            panel14.add(label14);
                            label14.setBounds(new Rectangle(new Point(185, 25), label14.getPreferredSize()));

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

                            //======== tabbedPane7 ========
                            {

                                //======== scrollPane4 ========
                                {
                                    scrollPane4.setViewportView(ReturnDetailAllList2);
                                }
                                tabbedPane7.addTab("Detail of All Rentals On The Day", scrollPane4);

                                //======== scrollPane11 ========
                                {
                                    scrollPane11.setViewportView(ReturnTypeAllList2);
                                }
                                tabbedPane7.addTab("Group By Category", scrollPane11);
                            }
                            panel14.add(tabbedPane7);
                            tabbedPane7.setBounds(5, 65, 780, 370);

                            //---- braReturnNum ----
                            braReturnNum.setForeground(UIManager.getColor("Button.default.endBackground"));
                            panel14.add(braReturnNum);
                            braReturnNum.setBounds(15, 45, 455, 25);

                            //---- braReturnRev ----
                            braReturnRev.setForeground(UIManager.getColor("Button.default.endBackground"));
                            panel14.add(braReturnRev);
                            braReturnRev.setBounds(485, 45, 215, 25);
                            panel14.add(ReturnCity);
                            ReturnCity.setBounds(225, 10, 105, 35);
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
                        TotalReturnNum.addTab("For each branch", panel14);
                    }
                    tabbedPane1.addTab("Return Report", TotalReturnNum);
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
    private JPanel ResTT;
    private JButton rentbutton;
    private JPanel panel7;
    private JLabel label1;
    private JTextField ResConf;
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
    private JLabel label2;
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
    private JPanel panel1;
    private JLabel label10;
    private JTextField ReturnDate;
    private JButton ReturnCar;
    private JLabel label17;
    private JComboBox RentIDBox;
    private JPanel panel5;
    private JLabel label9;
    private JLabel ReturnCN;
    private JLabel label4;
    private JLabel ReturnRentD;
    private JLabel label12;
    private JLabel ReturnReturnD;
    private JLabel label20;
    private JLabel ReturnRate;
    private JLabel label21;
    private JLabel ReturnDur;
    private JLabel label23;
    private JLabel ReturnTP;
    private JLabel label24;
    private JTabbedPane tabbedPane2;
    private JPanel panel4;
    private JLabel label8;
    private JTextField RentDateForAll;
    private JButton RentAllButton;
    private JTabbedPane tabbedPane4;
    private JScrollPane scrollPane1;
    private JList RentalDetailAllList;
    private JScrollPane scrollPane5;
    private JList RentalTypeAllList;
    private JScrollPane scrollPane6;
    private JList RentalBraAllList;
    private JLabel totalRentNum;
    private JPanel panel3;
    private JLabel label5;
    private JTextField RentDateForEach;
    private JLabel label6;
    private JLabel label7;
    private JTextField RentAddr;
    private JButton RentEachButton;
    private JTabbedPane tabbedPane5;
    private JScrollPane scrollPane3;
    private JList RentalDetailEachList;
    private JScrollPane scrollPane7;
    private JList RentalTypeEachList;
    private JLabel braRentNum;
    private JTextField RentCity;
    private JTabbedPane TotalReturnNum;
    private JPanel panel6;
    private JPanel panel13;
    private JLabel label11;
    private JTextField ReturnDateForAll;
    private JButton ReturnAllButton;
    private JTabbedPane tabbedPane6;
    private JScrollPane scrollPane2;
    private JList ReturnDetailAllList;
    private JScrollPane scrollPane9;
    private JList ReturnTypeAllList;
    private JScrollPane scrollPane10;
    private JList RentalBraAllList2;
    private JLabel totalReturnNum;
    private JLabel totalReturnRev;
    private JPanel panel14;
    private JLabel label13;
    private JTextField ReturnDateEach;
    private JLabel label14;
    private JLabel label15;
    private JButton ReturnEachButton;
    private JTabbedPane tabbedPane7;
    private JScrollPane scrollPane4;
    private JList ReturnDetailAllList2;
    private JScrollPane scrollPane11;
    private JList ReturnTypeAllList2;
    private JLabel braReturnNum;
    private JLabel braReturnRev;
    private JTextField ReturnCity;
    private JTextField ReturnAddr;
    private JPanel buttonBar;
    private JButton ReturnButton;
    private JButton FinishButton;
    private JLabel cardNameLabel;
    private JLabel cardNoLabel;
    private JLabel expDateLabel;
    private JTextField cardNameTextField;
    private JTextField cardNoTextField;
    private JTextField expDateTextField = new JTextField();
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
