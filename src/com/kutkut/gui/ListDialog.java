/**
 * Show a list dialog for Kutkut's graphical shell.
 * Dialog for listing various options, like list of available bots, etc.
 * 
 * @author Ashutosh Kumar Singh (me@AKSingh.net)
 * @version 1.0 2012/12/23
 */

package com.kutkut.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ListDialog extends JDialog
{

    public static void initialize(Component component, String as[], String s, String s1)
    {
        Frame frame = JOptionPane.getFrameForComponent(component);
        dialog = new ListDialog(frame, as, s, s1);
    }

    public static String showDialog(Component component, String s)
    {
        if(dialog != null)
        {
            dialog.setValue(s);
            dialog.setLocationRelativeTo(component);
            dialog.setVisible(true);
        } else
        {
            System.err.println("ERROR - ListDialog: ListDialog requires you to call initialize before calling showDialog.");
        }
        return value;
    }

    private void setValue(String s)
    {
        value = s;
        list.setSelectedValue(value, true);
    }

    private ListDialog(Frame frame, Object aobj[], String s, String s1)
    {
        super(frame, s, true);
        JButton jbutton = new JButton("Cancel");
        final JButton setButton = new JButton("Save");
        jbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                ListDialog.dialog.setVisible(false);
            }

        });
        setButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                ListDialog.value = (String)list.getSelectedValue();
                ListDialog.dialog.setVisible(false);
            }

        });
        getRootPane().setDefaultButton(setButton);
        list = new JList(aobj);
        list.setSelectionMode(1);
        list.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseevent)
            {
                if(mouseevent.getClickCount() == 2) 
                {
                    setButton.doClick();
                }
            }

        });
        JScrollPane jscrollpane = new JScrollPane(list);
        jscrollpane.setPreferredSize(new Dimension(250, 80));
        jscrollpane.setMinimumSize(new Dimension(250, 80));
        jscrollpane.setAlignmentX(0.0F);
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BoxLayout(jpanel, 1));
        JLabel jlabel = new JLabel(s1);
        jlabel.setLabelFor(list);
        jpanel.add(jlabel);
        jpanel.add(Box.createRigidArea(new Dimension(0, 5)));
        jpanel.add(jscrollpane);
        jpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel jpanel1 = new JPanel();
        jpanel1.setLayout(new BoxLayout(jpanel1, 0));
        jpanel1.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        jpanel1.add(Box.createHorizontalGlue());
        jpanel1.add(setButton);
        jpanel1.add(Box.createRigidArea(new Dimension(10, 0)));
        jpanel1.add(jbutton);
        Container container = getContentPane();
        container.add(jpanel, "Center");
        container.add(jpanel1, "South");
        pack();
    }

    
    private static ListDialog dialog;
    private static String value = "";
    private JList list;
    
}
