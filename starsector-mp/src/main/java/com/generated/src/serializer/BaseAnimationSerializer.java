
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BaseAnimationSerializer {


    public byte[] serialize(com.fs.graphics.anim.BaseAnimation obj)
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

    public com.fs.graphics.anim.BaseAnimation deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.graphics.anim.BaseAnimation obj = objenesis.newInstance(com.fs.graphics.anim.BaseAnimation.class);
        return obj;
    }

}
