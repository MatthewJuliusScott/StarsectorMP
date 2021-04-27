
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class FaderUtilSerializer {


    public byte[] serialize(com.fs.starfarer.api.util.FaderUtil obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isBounceDown());
            out.writeBoolean(obj.isBounceUp());
            out.writeFloat(obj.getBrightness());
            out.writeFloat(obj.getDurationIn());
            out.writeFloat(obj.getDurationOut());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.util.FaderUtil deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.util.FaderUtil obj = objenesis.newInstance(com.fs.starfarer.api.util.FaderUtil.class);
        return obj;
    }

}
