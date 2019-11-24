package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegates;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Created by JFormDesigner on Fri Nov 22 17:00:27 PST 2019
 */


/**
 * @author unknown
 */
public class RoleChoose extends JFrame {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static final int INVALID_INPUT = Integer.MIN_VALUE;
    private static final int EMPTY_INPUT = 0;
    private TerminalTransactionsDelegates delegate = null;

    public RoleChoose(){
    }

    public void showClerk(final TerminalTransactionsDelegates delegate) {
        this.delegate = delegate;
        final JFrame frame = new JFrame("CHOOSE ROLE");
        frame.setContentPane(dialogPane);

        ((JPanel) frame.getContentPane()).setOpaque(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
//        frame.setSize(600, 400);
        frame.setVisible(true);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CusButton.isSelected()){
                    Reserve r = new Reserve();
                    r.showReserve(delegate);
                    frame.dispose();
                }
                else if (ClerkButton.isSelected()){
                    Clerk c = new Clerk();
                    c.showClerk(delegate);
                    frame.dispose();
                }
            }
        });



    }



    {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        button1 = new JButton();
        CusButton = new JRadioButton();
        ClerkButton = new JRadioButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- button1 ----
                button1.setText("Choose");
                contentPanel.add(button1);
                button1.setBounds(new Rectangle(new Point(285, 190), button1.getPreferredSize()));

                //---- CusButton ----
                CusButton.setText("Customer");
                contentPanel.add(CusButton);
                CusButton.setBounds(new Rectangle(new Point(100, 95), CusButton.getPreferredSize()));

                //---- ClerkButton ----
                ClerkButton.setText("Clerk");
                contentPanel.add(ClerkButton);
                ClerkButton.setBounds(new Rectangle(new Point(185, 95), ClerkButton.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
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
    private JButton button1;
    private JRadioButton CusButton;
    private JRadioButton ClerkButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
