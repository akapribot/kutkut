/**
 * Graphical Shell for Kutkut Chatbot
 * Provide the graphical interface for the chatbot.
 * 
 * @author Ashutosh Kumar Singh [me@AKSingh.net]
 * @version 1.0 2012/12/23
 */
package com.kutkut.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import org.alicebot.server.core.*;
import org.alicebot.server.core.logging.Log;
import org.alicebot.server.core.util.Shell;
import org.alicebot.server.core.util.Trace;
import org.alicebot.server.net.AliceServer;


public class GraphicalShell extends JPanel
{
    public class ConsoleInputStream extends InputStream
    {

        public void receive(String s)
        {
            content = (s + '\n').getBytes();
            mark = 0;
        }

        @Override
        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            while(mark >= content.length) 
            { 
                try
                {
                    Thread.sleep(0L);
                }
                catch(InterruptedException interruptedexception)
                {
                    return -1;
                }
            }
            if(abyte0 == null) 
            {
                throw new NullPointerException();
            }
            if(i < 0 || i > abyte0.length || j < 0 || i + j > abyte0.length || i + j < 0) {
                throw new IndexOutOfBoundsException();
            }
            if(j == 0) {
                return 0;
            }
            if(content.length == 0) {
                return -1;
            }
            int k = 1;
            abyte0[i] = content[mark++];
            for(; k < j && k < content.length; k++) {
                abyte0[i + k] = content[mark++];
            }

            return k;
        }

        @Override
        public int available()
            throws IOException
        {
            return content.length - mark - 1;
        }

        @Override
        public boolean markSupported()
        {
            return false;
        }

        @Override
        public int read()
        {
            while(mark >= content.length) { 
                try
                {
                    Thread.sleep(0L);
                }
                catch(InterruptedException interruptedexception)
                {
                    return -1;
                }
            }
            if(mark < content.length) {
                return content[mark++];
            }
            else {
                return -1;
            }
        }

        byte content[];
        private int mark;

        public ConsoleInputStream()
        {
            content = new byte[0];
            mark = 0;
        }
    }

    public class ConsolePromptStream extends OutputStream
    {

        @Override
        public void write(byte abyte0[], int i, int j)
        {
            inputPanel.setPrompt(new String(abyte0, i, j));
        }

        @Override
        public void write(int i)
        {
            inputPanel.setPrompt(String.valueOf((char)i));
        }

        public ConsolePromptStream()
        {
        }
    }

    public class ConsoleDisplayStream extends OutputStream
    {

        @Override
        public void write(byte abyte0[], int i, int j)
        {
            while(paused) { 
                try
                {
                    Thread.sleep(0L);
                }
                catch(InterruptedException interruptedexception) { }
            }
            display.append(new String(abyte0, i, j));
            display.setCaretPosition(display.getText().length());
        }

        @Override
        public void write(int i)
        {
            while(paused) { 
                try
                {
                    Thread.sleep(0L);
                }
                catch(InterruptedException interruptedexception) { }
            }
            display.append(String.valueOf((char)i));
            display.setCaretPosition(display.getText().length());
        }

        private void togglePause()
        {
            paused = !paused;
        }

        private boolean paused;


        public ConsoleDisplayStream()
        {
            paused = false;
        }
    }

    class InputPanel extends JPanel
    {
        private class InputSender
            implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                String s = actionevent.getActionCommand();
                display.append(prompt.getText() + s + GraphicalShell.LINE_SEPARATOR);
                inStream.receive(s);
                input.setText(null);
            }

            private InputSender()
            {
            }

        }


        public void setPrompt(String s)
        {
            prompt.setText(s);
            prompt.revalidate();
            input.requestFocus();
        }

        private JLabel prompt;
        private JTextField input;
        private JButton jbutton;

        public void pauseInput(boolean b) {
            if (b) {
                input.setEditable(false);
                input.setEnabled(false);
                input.setToolTipText(com.kutkut.core.Constants.getTooltipPaused());
                jbutton.setEnabled(false);
                jbutton.setToolTipText(com.kutkut.core.Constants.getTooltipPaused());
                prompt.setEnabled(false);
                prompt.setToolTipText(com.kutkut.core.Constants.getTooltipPaused());
            }
            else {
                input.setEditable(true);
                input.setEnabled(true);
                input.setToolTipText(com.kutkut.core.Constants.getTooltipInput());
                jbutton.setEnabled(true);
                jbutton.setToolTipText(com.kutkut.core.Constants.getTooltipEnterButton());
                prompt.setEnabled(true);
                prompt.setToolTipText("");
            }
        }

        public InputPanel()
        {
            setLayout(new BoxLayout(this, 0));
            
            prompt = new JLabel();
            prompt.setFont(new Font("Arial", 0, 13));
            prompt.setForeground(Color.darkGray);
            prompt.setBackground(Color.white);
            prompt.setHorizontalAlignment(2);
            prompt.setAlignmentY(0.5F);
            
            input = new JTextField();
            input.setFont(new Font("Times New Roman", 0, 15));
            input.setForeground(Color.darkGray);
            input.setMinimumSize(new Dimension(50, 24));
            input.setPreferredSize(new Dimension(200, 24));
            input.setMaximumSize(new Dimension(32767, 24));
            input.setHorizontalAlignment(2);
            input.setAlignmentY(0.5F);
            input.setToolTipText(com.kutkut.core.Constants.getTooltipInput());
            input.addActionListener(new InputSender());
            
            jbutton = new JButton("Enter");
            jbutton.setFont(new Font("Fixedsys", 0, 14));
            jbutton.setForeground(Color.darkGray);
            jbutton.setMinimumSize(new Dimension(70, 24));
            jbutton.setPreferredSize(new Dimension(70, 24));
            jbutton.setMaximumSize(new Dimension(70, 24));
            jbutton.setToolTipText(com.kutkut.core.Constants.getTooltipEnterButton());
            jbutton.addActionListener(new InputSender());
            jbutton.setAlignmentY(0.5F);
            
            add(prompt);
            add(input);
            add(jbutton);
        }
    }

/*
 * Displays the graphical window of the chatbot.
 */
    public GraphicalShell()
    {
        consoleDisplay = new ConsoleDisplayStream();
        displayStream = new PrintStream(consoleDisplay);
        promptStream = new PrintStream(new ConsolePromptStream());
        inStream = new ConsoleInputStream();
        
        setLookAndFeel(com.kutkut.core.Settings.getTheme());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        /*
         * Displays the text area to show the conversations.
         */
        display = new JTextArea(50, 100);
        display.setFont(new Font("Times New Roman", 0, 15));
        display.setLineWrap(true);
        display.setWrapStyleWord(true);
        display.setTabSize(4);
        display.setForeground(Color.white);
        display.setBackground(Color.gray);
        display.setEditable(false);
        display.setToolTipText(com.kutkut.core.Constants.getTooltipDisplay());
        display.setBorder(BorderFactory.createLoweredBevelBorder());
        
        /*
         * Displays the input panel that displays the prompt, input text area, and Enter button.
         */
        inputPanel = new InputPanel();
        inputPanel.setForeground(Color.darkGray);
        inputPanel.setFont(new Font("Arial", 0, 13));
        
        JScrollPane jscrollpane = new JScrollPane(display);
        jscrollpane.setAlignmentY(0.5F);
        add(jscrollpane);
        setBorder(BorderFactory.createLoweredBevelBorder());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(inputPanel);
        
        /*
         * Displays the menu bar, listing various options under each menu.
         */
        menuBar = new JMenuBar();
        
        /* File menu */
        JMenu jmenu = new JMenu("File");
        jmenu.setFont(new Font("Fixedsys", 0, 13));
        jmenu.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('F'));
        
        JMenuItem jmenuitem = new JMenuItem("Load AIML from URL");
        jmenuitem.setFont(new Font("Fixedsys", 0, 13));
        jmenuitem.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('U'));
        jmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                loadAIMLURLBox();
            }
        });
        
        JMenuItem jmenuitem1 = new JMenuItem("Load AIML from file");
        jmenuitem1.setFont(new Font("Fixedsys", 0, 13));
        jmenuitem1.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('A'));
        jmenuitem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                loadAIMLFilePathChooser();
            }
        });
        
        JMenuItem jmenuitem2 = new JMenuItem("Exit");
        jmenuitem2.setFont(new Font("Fixedsys", 0, 13));
        jmenuitem2.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('X'));
        jmenuitem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                shutdown();
            }
        });
        
        jmenu.add(jmenuitem);
        jmenu.add(jmenuitem1);
        jmenu.addSeparator();
        jmenu.add(jmenuitem2);
        
        /* Actions menu */
        JMenu jmenu1 = new JMenu("Bots");
        jmenu1.setFont(new Font("Fixedsys", 0, 13));
        jmenu1.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('B'));
        
        JMenuItem jmenuitem3 = new JMenuItem("Choose Bot");
        jmenuitem3.setFont(new Font("Fixedsys", 0, 13));
        jmenuitem3.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('C'));
        jmenuitem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                chooseBot();
            }
        });
        
        JMenuItem jmenuitem4 = new JMenuItem("Sally bot");
        jmenuitem4.setFont(new Font("Fixedsys", 0, 13));
        jmenuitem4.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('S'));
        jmenuitem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                showBotDetails("Sally bot", com.kutkut.core.Constants.getBotDetailsSally());
            }
        });
               
        jmenu1.add(jmenuitem3);
        jmenu1.addSeparator();
        jmenu1.add(jmenuitem4);
               
        /* Settings menu */
        JMenu jmenu3 = new JMenu("Settings");
        jmenu3.setFont(new Font("Fixedsys", 0, 13));
        jmenu3.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('S'));
        
        JCheckBoxMenuItem jcheckboxmenuitem = new JCheckBoxMenuItem("Pause");
        jcheckboxmenuitem.setFont(new Font("Fixedsys", 0, 13));
        jcheckboxmenuitem.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('P'));
        jcheckboxmenuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                togglePause();
            }
        });
        
        JMenuItem jmenuitem9 = new JMenuItem("Themes");
        jmenuitem9.setFont(new Font("Fixedsys", 0, 13));
        jmenuitem9.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('T'));
        jmenuitem9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                chooseTheme();
            }
        });
        
        jmenu3.add(jcheckboxmenuitem);
        jmenu3.addSeparator();
        jmenu3.add(jmenuitem9);
        
        /* Help menu */
        JMenu jmenu2 = new JMenu("Help");
        jmenu2.setFont(new Font("Fixedsys", 0, 13));
        jmenu2.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('H'));
        
        JMenuItem jmenuitem7 = new JMenuItem("Shell Commands");
        jmenuitem7.setFont(new Font("Fixedsys", 0, 13));
        jmenuitem7.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('S'));
        jmenuitem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                showCommandsBox();
            }
        });
        
        JMenuItem jmenuitem10 = new JMenuItem("Credits");
        jmenuitem10.setFont(new Font("Fixedsys", 0, 13));
        jmenuitem10.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('C'));
        jmenuitem10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                showCreditsBox();
            }
        });
        
        JMenuItem jmenuitem11 = new JMenuItem("License");
        jmenuitem11.setFont(new Font("Fixedsys", 0, 13));
        jmenuitem11.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('L'));
        jmenuitem11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                showLicenseBox();
            }
        });
        
        JMenuItem jmenuitem8 = new JMenuItem("About");
        jmenuitem8.setFont(new Font("Fixedsys", 0, 13));
        jmenuitem8.setMnemonic(java.awt.event.KeyEvent.getExtendedKeyCodeForChar('A'));
        jmenuitem8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent)
            {
                showAboutBox();
            }
        });
        
        jmenu2.add(jmenuitem7);
        jmenu2.addSeparator();
        jmenu2.add(jmenuitem11);
        jmenu2.add(jmenuitem10);
        jmenu2.add(jmenuitem8);
        
        menuBar.add(jmenu);
        menuBar.add(jmenu1);
        menuBar.add(jmenu3);
        menuBar.add(jmenu2);
        
        /*
         * Sets the frame (main window) of the graphical shell of the chatbot.
         */
        
        /* Dimension to get the screen's resolution (height and width) */
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame = new JFrame();
        frame.setTitle(com.kutkut.core.Constants.getTitle());
        frame.setIconImage((com.kutkut.core.Constants.getIcon()).getImage());
        frame.getContentPane().add(this);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700, 500);
        frame.setLocation((int)(dim.getWidth()/2 - 350), (int)(dim.getHeight()/2 - 250));
    }
    
    private void togglePause() {
        isPause = !isPause;
        consoleDisplay.togglePause();
                
        if (isPause) {
            inputPanel.pauseInput(true);
            display.setEnabled(false);
            display.setToolTipText(com.kutkut.core.Constants.getTooltipPaused());
        }        
        else {
            inputPanel.pauseInput(false);
            display.setEnabled(true);
            display.setToolTipText(com.kutkut.core.Constants.getTooltipDisplay());
        }        
    }
    
    private void setLookAndFeel(int theme) {
        switch (theme) {
            case 0: 
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                }
                catch (Exception exc) {
                    System.err.println("ERROR - Themes: Metal (default) theme can't be set. " + exc.getMessage());
                }
                break;
            case 1: 
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (Exception exc) {
                    System.err.println("ERROR - Themes: Motif (system's look) theme can't be set. " + exc.getMessage());
                }
                break;
            case 2:
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                }
                catch (Exception exc) {
                    System.err.println("ERROR - Themes: Nimbus theme can't be set. " + exc.getMessage());
                }
                break;
        }
    }

    public void start(String s)
    {
        Globals.load(s);
        shell = new Shell(inStream, displayStream, promptStream);
        server = new AliceServer(s, shell);
        Trace.setOut(displayStream);
        server.startup();
        shutdown();
    }

    private void shutdown()
    {
        if(server != null)
        {
            GraphicalShell _tmp = this;
            AliceServer.shutdown();
        }
        System.exit(0);
    }

    private void loadAIMLURLBox()
    {
        Object obj = JOptionPane.showInputDialog(null, "Enter the URL from which to load.", "Load AIML from URL", -1, null, null, null);
        if(obj == null)
        {
            return;
        } else
        {
            int i = Graphmaster.getTotalCategories();
            Graphmaster.load((String)obj, shell.getCurrentBotID());
            Log.userinfo((Graphmaster.getTotalCategories() - i) + " categories loaded from \"" + (String)obj + "\".", Log.LEARN);
            return;
        }
    }

    private void loadAIMLFilePathChooser()
    {
        JFileChooser jfilechooser = new JFileChooser();
        jfilechooser.setDialogTitle("Choose AIML File");
        int i = jfilechooser.showDialog(this, "Choose");
        if(i == 0)
        {
            File file = jfilechooser.getSelectedFile();
            String s = null;
            try
            {
                s = file.getCanonicalPath();
            }
            catch(IOException ioexception)
            {
                Trace.userinfo("I/O error trying to access \"" + s + "\".");
                return;
            }
            int j = Graphmaster.getTotalCategories();
            Graphmaster.load(s, shell.getCurrentBotID());
            Log.userinfo((Graphmaster.getTotalCategories() - j) + " categories loaded from \"" + s + "\".", Log.LEARN);
        }
    }

    private void chooseBot()
    {
        String as[] = (String[])Bots.getIDs().toArray(new String[0]);
        ListDialog.initialize(frame, as, "Choose Bot", "Choose the bot with whom you want to talk:");
        String s = ListDialog.showDialog(null, shell.getCurrentBotID());
        if(s != null) {
            shell.switchToBot(s);
        }
    }
    
    private void chooseTheme()
    {
        String themeNames[] = {"Simple", "System", "Nimbus"};
        int theme = -1;
        
        ListDialog.initialize(frame, themeNames, "Themes", "Choose the theme for Kutkut Chatbot:");
        String s = ListDialog.showDialog(null, themeNames[com.kutkut.core.Settings.getTheme()]);
        
        for (int i = 0; i <= 2; i++) {
         if (s.equals(themeNames[i])) {
             theme=i;
             break;
         }
        }        
        
        int oldWidth = frame.getWidth();
        int oldHeight = frame.getHeight();
        int oldX = frame.getX();
        int oldY = frame.getY();
        
        setLookAndFeel(theme);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setBounds(oldX, oldY, oldWidth, oldHeight);
    }

    private void showAboutBox()
    {
        JOptionPane.showMessageDialog(frame, ((Object) (com.kutkut.core.Constants.getMessageAbout())), "About", JOptionPane.INFORMATION_MESSAGE, com.kutkut.core.Constants.getLogo());
    }
    
    private void showCommandsBox()
    {
        JOptionPane.showMessageDialog(frame, ((Object) (com.kutkut.core.Constants.getMessageCommands())), "Commands", JOptionPane.INFORMATION_MESSAGE, com.kutkut.core.Constants.getLogo());
    }
    
    private void showCreditsBox()
    {
        JOptionPane.showMessageDialog(frame, ((Object) (com.kutkut.core.Constants.getMessageCredits())), "Credits", JOptionPane.INFORMATION_MESSAGE, com.kutkut.core.Constants.getLogo());
    }
    
    private void showLicenseBox()
    {
        JOptionPane.showMessageDialog(frame, ((Object) (com.kutkut.core.Constants.getMessageLicense())), "License", JOptionPane.INFORMATION_MESSAGE, com.kutkut.core.Constants.getLogo());
    }
    
    private void showBotDetails(String botName, Object[] objMessage) {
        JOptionPane.showMessageDialog(frame, ((Object) (objMessage)), botName+" - Details", JOptionPane.INFORMATION_MESSAGE, com.kutkut.core.Constants.getLogo());
    }

    public static void main(String args[])
    {
        String s;
        if(args.length > 0) {
            s = args[0];
        }
        else {
            s = "server.properties";
        }
        (new GraphicalShell()).start(s);
    }

    private AliceServer server;
    private Shell shell;
    private JTextArea display;
    private InputPanel inputPanel;
    private ConsoleDisplayStream consoleDisplay;
    private JFrame frame;
    private PrintStream displayStream;
    private PrintStream promptStream;
    private ConsoleInputStream inStream;
    private static JMenuBar menuBar;
    
    private static boolean isPause = false;
    
    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    
}
