// Decompiled by Jad v1.5.8c. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package org.alicebot.server.core.interpreter;

import java.util.StringTokenizer;
import org.alicebot.server.core.Interpreter;
import org.alicebot.server.core.logging.Log;
import org.mozilla.javascript.Context;

public class RhinoInterpreter
    implements Interpreter
{

    public RhinoInterpreter()
    {
    }

    public String evaluate(String s)
    {
        Log.log("evaluate: \"" + s + "\"", Log.INTERPRETER);
        Context context = Context.enter();
        org.mozilla.javascript.Scriptable scriptable = context.initStandardObjects(null);
        Object obj = null;
        try
        {
            obj = context.evaluateString(scriptable, s, "<cmd>", 1, null);
        }
        catch(Exception exception)
        {
            Log.userinfo("JavaScript exception (see interpreter log).", Log.ERROR);
            Log.log(exception, Log.INTERPRETER);
            for(StringTokenizer stringtokenizer = new StringTokenizer(s, System.getProperty("line.separator")); stringtokenizer.hasMoreTokens(); Log.log(stringtokenizer.nextToken(), Log.INTERPRETER));
        }
        Context.exit();
        if(obj != null)
        {
            return obj.toString();
        } else
        {
            Log.userinfo("JavaScript returned null!", Log.INTERPRETER);
            return "";
        }
    }

    private static final String CMD = "<cmd>";
    private static final String EMPTY_STRING = "";
}
