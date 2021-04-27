
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class JitterRendererSerializer {


    public byte[] serialize(com.fs.starfarer.renderers.JitterRenderer obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isSetSeedOnRender());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.renderers.JitterRenderer deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.renderers.JitterRenderer obj = objenesis.newInstance(com.fs.starfarer.renderers.JitterRenderer.class);
        return obj;
    }

}
