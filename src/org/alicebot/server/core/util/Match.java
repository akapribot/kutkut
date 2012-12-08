// Decompiled by Jad v1.5.8c. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package org.alicebot.server.core.util;

import java.util.ArrayList;
import org.alicebot.server.core.node.Nodemapper;

// Referenced classes of package org.alicebot.server.core.util:
//            InputNormalizer

public class Match
{

    public Match()
    {
        inputStars = new ArrayList();
        thatStars = new ArrayList();
        topicStars = new ArrayList();
        path = "";
    }

    public void pushInputStar(String s)
    {
        inputStars.add(0, s);
    }

    public void pushThatStar(String s)
    {
        thatStars.add(0, s);
    }

    public void pushTopicStar(String s)
    {
        topicStars.add(0, s);
    }

    public void setPattern(String s)
    {
        pattern = s;
    }

    public void setThat(String s)
    {
        that = s;
    }

    public void setTopic(String s)
    {
        topic = s;
    }

    public void setBotID(String s)
    {
        botid = s;
    }

    public void setNodemapper(Nodemapper nodemapper1)
    {
        nodemapper = nodemapper1;
    }

    public String getPattern()
    {
        return pattern;
    }

    public String getThat()
    {
        return that;
    }

    public String getTopic()
    {
        return topic;
    }

    public String getPath()
    {
        return InputNormalizer.patternFit(pattern) + " : " + InputNormalizer.patternFit(that) + " : " + InputNormalizer.patternFit(topic) + " : " + botid;
    }

    public Nodemapper getNodemapper()
    {
        return nodemapper;
    }

    public String getTemplate()
    {
        return (String)nodemapper.get("<template>");
    }

    public String getFileName()
    {
        return (String)nodemapper.get("<filename>");
    }

    public ArrayList getInputStars()
    {
        return inputStars;
    }

    public ArrayList getThatStars()
    {
        return thatStars;
    }

    public ArrayList getTopicStars()
    {
        return topicStars;
    }

    private ArrayList inputStars;
    private ArrayList thatStars;
    private ArrayList topicStars;
    private String pattern;
    private String that;
    private String topic;
    private String botid;
    private static final String EMPTY_STRING = "";
    private String path;
    private Nodemapper nodemapper;
    public static final String SPACE = " ";
    private static final String SPACED_PATH_SEPARATOR = " : ";
}
