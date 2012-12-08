// Decompiled by Jad v1.5.8c. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package org.alicebot.server.core.util;


public class StringTriple
{

    public StringTriple(String s, String s1, String s2)
    {
        first = s;
        second = s1;
        third = s2;
    }

    public String getFirst()
    {
        return first;
    }

    public String getSecond()
    {
        return second;
    }

    public String getThird()
    {
        return third;
    }

    public void setFirst(String s)
    {
        first = s;
    }

    public void setSecond(String s)
    {
        second = s;
    }

    public void setThird(String s)
    {
        third = s;
    }

    public boolean equals(Object obj)
    {
        StringTriple stringtriple;
        try
        {
            stringtriple = (StringTriple)obj;
        }
        catch(ClassCastException classcastexception)
        {
            return false;
        }
        return stringtriple.getFirst().equals(first) & stringtriple.getSecond().equals(second) & stringtriple.getThird().equals(third);
    }

    private String first;
    private String second;
    private String third;
}
