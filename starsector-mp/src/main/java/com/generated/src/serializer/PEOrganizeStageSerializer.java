
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PEOrganizeStageSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.intel.punitive.PEOrganizeStage obj)
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

    public com.fs.starfarer.api.impl.campaign.intel.punitive.PEOrganizeStage deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.intel.punitive.PEOrganizeStage obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.intel.punitive.PEOrganizeStage.class);
        return obj;
    }

}
