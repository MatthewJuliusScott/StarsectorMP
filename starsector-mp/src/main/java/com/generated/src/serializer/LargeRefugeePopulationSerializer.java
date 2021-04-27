
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class LargeRefugeePopulationSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.econ.LargeRefugeePopulation obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.econ.LargeRefugeePopulation deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.econ.LargeRefugeePopulation obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.econ.LargeRefugeePopulation.class);
        return obj;
    }

}
