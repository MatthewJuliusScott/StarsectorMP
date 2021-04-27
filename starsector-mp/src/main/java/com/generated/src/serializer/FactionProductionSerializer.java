
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class FactionProductionSerializer {


    public byte[] serialize(com.fs.starfarer.loading.specs.FactionProduction obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeInt(obj.getAccruedProduction());
            out.writeFloat(obj.getCostMult());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.loading.specs.FactionProduction deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.loading.specs.FactionProduction obj = objenesis.newInstance(com.fs.starfarer.loading.specs.FactionProduction.class);
        return obj;
    }

}
