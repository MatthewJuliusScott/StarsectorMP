
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class SpriteIdSerializer {


    public byte[] serialize(com.fs.starfarer.api.SpriteId obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getCategory());
            out.writeUTF(obj.getKey());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.SpriteId deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.SpriteId obj = objenesis.newInstance(com.fs.starfarer.api.SpriteId.class);
        return obj;
    }

}
