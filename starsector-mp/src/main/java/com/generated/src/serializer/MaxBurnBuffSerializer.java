
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MaxBurnBuffSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.terrain.MaxBurnBuff obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getDelta());
            out.writeFloat(obj.getDur());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.terrain.MaxBurnBuff deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.terrain.MaxBurnBuff obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.terrain.MaxBurnBuff.class);
        return obj;
    }

}
