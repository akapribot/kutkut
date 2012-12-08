// Decompiled by Jad v1.5.8c. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package org.alicebot.server.core.parser;

import org.alicebot.server.core.Bot;
import org.alicebot.server.core.processor.loadtime.StartupElementProcessorRegistry;

// Referenced classes of package org.alicebot.server.core.parser:
//            GenericParser

public class StartupFileParser extends GenericParser
{

    public StartupFileParser()
    {
        super.processorRegistry = StartupElementProcessorRegistry.getSelf();
    }

    public void setCurrentBot(Bot bot)
    {
        currentBot = bot;
    }

    public Bot getCurrentBot()
    {
        return currentBot;
    }

    private Bot currentBot;
}
