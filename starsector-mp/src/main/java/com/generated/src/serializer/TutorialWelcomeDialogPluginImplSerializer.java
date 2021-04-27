
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class TutorialWelcomeDialogPluginImplSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.tutorial.TutorialWelcomeDialogPluginImpl obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.tutorial.TutorialWelcomeDialogPluginImpl deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.tutorial.TutorialWelcomeDialogPluginImpl obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.tutorial.TutorialWelcomeDialogPluginImpl.class);
        return obj;
    }

}
