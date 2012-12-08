// Decompiled by Jad v1.5.8c. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package org.alicebot.server.net.listener;

import java.util.HashMap;
import org.alicebot.server.core.Bot;
import org.alicebot.server.core.BotProcess;
import org.alicebot.server.core.util.UserError;

public abstract class AliceChatListener
    implements BotProcess
{

    public AliceChatListener(Bot bot1, String s, String as[][])
    {
        parameters = new HashMap();
        bot = bot1;
        botID = bot1.getID();
        name = s;
        for(int i = as.length; --i >= 0;)
            parameters.put(as[i][0], as[i][1]);

    }

    public void setParameter(String s, String s1)
    {
        if(!parameters.containsKey(s))
        {
            throw new UserError("Invalid parameter \"" + s + "\" for listener \"" + name + "\"");
        } else
        {
            parameters.put(s, s1);
            return;
        }
    }

    public abstract boolean checkParameters();

    protected Bot bot;
    protected String botID;
    private String name;
    protected HashMap parameters;
}
