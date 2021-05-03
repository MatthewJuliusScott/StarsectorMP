
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BaseRaidStageSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.intel.raid.BaseRaidStage obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAbortFP());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getStatus().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.intel.raid.BaseRaidStage deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.intel.raid.BaseRaidStage obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.intel.raid.BaseRaidStage.class);
        return obj;
    }

}
