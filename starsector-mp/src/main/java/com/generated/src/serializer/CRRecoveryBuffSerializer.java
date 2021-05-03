
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CRRecoveryBuffSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.terrain.CRRecoveryBuff obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getDur());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getId().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.terrain.CRRecoveryBuff deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.terrain.CRRecoveryBuff obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.terrain.CRRecoveryBuff.class);
        return obj;
    }

}
