
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MutatingValueSerializer {


    public byte[] serialize(com.fs.starfarer.util.MutatingValue obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getMax());
            out.writeFloat(obj.getMin());
            out.writeFloat(obj.getRate());
            out.writeFloat(obj.getRateSign());
            out.writeFloat(obj.getSign());
            out.writeFloat(obj.getValue());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.util.MutatingValue deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.util.MutatingValue obj = objenesis.newInstance(com.fs.starfarer.util.MutatingValue.class);
        return obj;
    }

}
