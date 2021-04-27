
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class IntervalTrackerSerializer {


    public byte[] serialize(com.fs.starfarer.util.IntervalTracker obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getRemaining());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.util.IntervalTracker deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.util.IntervalTracker obj = objenesis.newInstance(com.fs.starfarer.util.IntervalTracker.class);
        return obj;
    }

}
