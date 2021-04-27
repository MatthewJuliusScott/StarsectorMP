
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class SmokeEmitterSerializer {


    public byte[] serialize(com.fs.starfarer.renderers.fx.SmokeEmitter obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getDuration());
            out.writeBoolean(obj.isLooping());
            out.writeInt(obj.getNumFrames());
            out.writeBoolean(obj.isReverse());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.renderers.fx.SmokeEmitter deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.renderers.fx.SmokeEmitter obj = objenesis.newInstance(com.fs.starfarer.renderers.fx.SmokeEmitter.class);
        return obj;
    }

}
