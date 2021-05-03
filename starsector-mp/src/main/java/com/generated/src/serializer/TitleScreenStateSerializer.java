
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class TitleScreenStateSerializer {


    public byte[] serialize(com.fs.starfarer.title.TitleScreenState obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isLauncherMode());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getID().writeObject();
        return obj.getDialogType().writeObject();
        return obj.getNextState().writeObject();
    }

    public com.fs.starfarer.title.TitleScreenState deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.title.TitleScreenState obj = objenesis.newInstance(com.fs.starfarer.title.TitleScreenState.class);
        return obj;
    }

}
