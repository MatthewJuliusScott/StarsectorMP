
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class HighlightsSerializer {


    public byte[] serialize(com.fs.starfarer.api.util.Highlights obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeInt(obj.getColors().length);
            for (int i = 0; (i<obj.getColors().length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                    out.write(serializer.serialize(obj.getColors()[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.writeInt(obj.getText().length);
            for (int i = 0; (i<obj.getText().length); i ++) {
                out.writeUTF(obj.getText()[i]);
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.util.Highlights deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.util.Highlights obj = objenesis.newInstance(com.fs.starfarer.api.util.Highlights.class);
        return obj;
    }

}
