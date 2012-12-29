package com.kutkut.core;

import javax.swing.ImageIcon;

/**
 * Store constants of the chatbot.
 * Store the required constants of the program such as details,
 * version information, dialog box messages, etc.
 * 
 * @author Ashutosh Kumar Singh [me@AKSingh.net]
 * @version 1.0 2012/12/28
 */

public class Constants {
    
    public static String getVersion() {
        return VERSION;
    }
    
    public static String getTitle() {
        return TITLE;
    }
    
    public static String getName() {
        return NAME;
    }
    
    public static ImageIcon getLogo() {
        return KUTKUT_LOGO;
    }
    
    public static ImageIcon getIcon() {
        return KUTKUT_ICON;
    }
    
    public static String getTooltipPaused() {
        return TOOLTIP_PAUSED;
    }
    
    public static String getTooltipDisplay() {
        return TOOLTIP_DISPLAY;
    }
    
    public static String getTooltipInput() {
        return TOOLTIP_INPUT;
    }
    
    public static String getTooltipEnterButton() {
        return TOOLTIP_ENTER_BUTTON;
    }
    
    public static String getDeveloper() {
        return DEVELOPER;
    }
    
    public static String getDeveloperEmail() {
        return DEVELOPER_EMAIL;
    }
    
    public static String getCopyrightYear() {
        return COPYRIGHT_YEAR;
    }
    
    public static String getCopyrightText() {
        return COPYRIGHT_TEXT;
    }
    
    public static String[] getCopyrightInfo() {
        return COPYRIGHT_INFO;
    }
    
    public static String getBuild() {
        return BUILD;
    }
    
    public static String[] getMessageAbout() {
        return MESSAGE_ABOUT;
    }
    
    public static String getWebsite() {
        return WEBSITE;
    }
    
    public static String[] getMessageCommands() {
        return MESSAGE_COMMANDS;
    }
    
    public static String[] getMessageCredits() {
        return MESSAGE_CREDITS;
    }
    
    public static String[] getMessageLicense() {
        return MESSAGE_LICENSE;
    }
    
    public static String[] getBotDetailsSally() {
        return BOT_DETAILS_SALLY;
    }
    
    
    private static final String NAME = "Kutkut Chatbot";
    private static final String VERSION = "0.2";
    private static final String BUILD = "00";
    private static final String TITLE = NAME+" v"+VERSION;
    
    private static final String DEVELOPER = "Ashutosh Kumar Singh";
    private static final String DEVELOPER_EMAIL = "me@AKSingh.net";
    private static final String WEBSITE = "http://www.AKSingh.net/Kutkut";
    private static final String COPYRIGHT_YEAR = "2012";
    private static final String COPYRIGHT_TEXT = "Copyright (C)" + COPYRIGHT_YEAR + " " + DEVELOPER + " [" + DEVELOPER_EMAIL + "]";
    
    private static final String TOOLTIP_PAUSED = "Program is paused. Unpause the program to resume the conversation.";
    private static final String TOOLTIP_DISPLAY = "On-going chat conversation.";
    private static final String TOOLTIP_INPUT = "Write your message and press Enter to send.";
    private static final String TOOLTIP_ENTER_BUTTON = "Press enter to send your message.";
    
    private static final ImageIcon KUTKUT_LOGO = new ImageIcon(ClassLoader.getSystemResource("com/kutkut/icons/kutkutLogo.png"));
    private static final ImageIcon KUTKUT_ICON = new ImageIcon(ClassLoader.getSystemResource("com/kutkut/icons/kutkutIcon.png"));
    
    public static final String COPYRIGHT_INFO[] = {
        TITLE, 
        COPYRIGHT_TEXT, 
        "All Rights Reserved.", 
        "This program is free software (with exceptions to Sally bot); ", 
        "you can redistribute it and/or", 
        "modify it under the terms of the GNU General Public License", 
        "as published by the Free Software Foundation; either version 2", 
        "of the License, or (at your option) any later version."
    };
    
    private static final String MESSAGE_ABOUT[] = {
        NAME, 
        "Version " + VERSION, " ", 
        COPYRIGHT_TEXT, 
        "All Rights Reserved", " ", 
        "Homepage: " + WEBSITE, " ", 
        "License: GNU GPLv2 (or later version) with exceptions to ", 
        "Sally bot (including its AIML files).", 
        "(check License for details)", " ", 
        "What's new in this version :-", 
        "1. New graphical interface", 
        "2. Theme chooser with three in-built themes", 
        "3. Sally bot - Kutkut's own version of Alice bot", " "
    };
    
    private static final String MESSAGE_COMMANDS[] = {
        NAME, " ", 
        "These are the commands that can be passed as a message to the Kutkut's shell", 
        "and Kutkut will perform the associated operation.", 
        "Note: Shell commands start with forward slash \'/\' character.", " ", 
        "/help              -   prints this help", 
        "/exit              -   shuts down the bot server", 
        "/load filename     -   loads/reloads given filename for active bot", 
        "/unload filename   -   unloads given filename for active bot", 
        "/bots              -   lists loaded bots", 
        "/talkto botid      -   switches conversation to given bot", 
        "/who               -   prints the id of the current bot", 
        "/files             -   lists the files loaded by the current bot", 
        "/roll chatlog      -   rolls over chat log", 
        "/roll targets      -   rolls over saved targeting data", 
        "/commandables      -   lists available \"shell commandables\" (such as listeners)", " "
    };
    
    private static final String MESSAGE_CREDITS[] = {
        NAME, 
        "Kutkut Chatbot is a chatter bot program that can talk with you", 
        "like a human being using the artificial intelligence.", " ",
        "Sally bot", 
        "Kutkut Chatbot uses Sally bot to make conversations with you.", 
        "Sally bot is Kutkut's own customized version of Alice bot.", " ", 
        "Kutkut Chatbot was never possible without these wonderful software:", 
        "1. Charliebot: A chat program based on ANNA", 
        "2. ANNA: A chat program based on Program D", 
        "3. Program D: A chat program based on Alice bot", 
        "4. Alice: A wonderful AI chat program that uses AIML", " "
    };
    
    private static final String MESSAGE_LICENSE[] = {
        NAME, 
        COPYRIGHT_TEXT, 
        "All Rights Reserved", " ", 
        "This program is free software (with exceptions to Sally bot);", 
        "you can redistribute it and/or", 
        "modify it under the terms of the GNU General Public License", 
        "as published by the Free Software Foundation; either version 2", 
        "of the License, or (at your option) any later version.", 
        "This program is distributed in the hope that it will be useful,", 
        "but WITHOUT ANY WARRANTY; without even the implied warranty of", 
        "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.", 
        "See the GNU General Public License for more details.", 
        "You should have received a copy of the GNU General Public License", 
        "along with this program; if not, write to the Free Software", 
        "Foundation, Inc., 51 Franklin Street, Fifth Floor, ", 
        "Boston, MA  02110-1301, USA.", " "
    };
    
    private static final String BOT_DETAILS_SALLY[] = {
        "Sally bot",
        COPYRIGHT_TEXT, 
        "All Rights Reserved", " ", 
        "About:", 
        "Sally bot is Kutkut's own customized version of Alice bot.", 
        "Sally is young and charming girl. She has good", 
        "knowledge about computers. She love to talk and chat", 
        "with you.", " ", 
        "License:", 
        "You can ONLY use Sally bot and its files (AIMLs and XMLs)", 
        "with Kutkut Chatbot only as is provided and you can NOT, ", 
        "under any whatsoever conditions copy, edit, modify, ", 
        "distribute or use the Sally bot's files (AIMLs and XMLs)", 
        "in any other way with Kutkut Chatbot or with any other", 
        "program or software or other computer code."  
    };
}
