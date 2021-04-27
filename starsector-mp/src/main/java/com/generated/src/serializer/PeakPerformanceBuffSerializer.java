
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PeakPerformanceBuffSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.terrain.PeakPerformanceBuff obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getDur());
            out.writeFloat(obj.getMult());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.terrain.PeakPerformanceBuff deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.terrain.PeakPerformanceBuff obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.terrain.PeakPerformanceBuff.class);
        return obj;
    }

}
