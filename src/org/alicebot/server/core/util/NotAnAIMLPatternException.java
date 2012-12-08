// Decompiled by Jad v1.5.8c. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package org.alicebot.server.core.util;


public class NotAnAIMLPatternException extends Exception
{

    public NotAnAIMLPatternException(String s, String s1)
    {
        super(s);
        pattern = s1;
    }

    public String getMessage()
    {
        return "Not an AIML pattern: \"" + pattern + "\" - " + super.getMessage();
    }

    private String pattern;
    private static final String MSG_PART_ONE = "Not an AIML pattern: \"";
    private static final String MSG_PART_TWO = "\" - ";
}
