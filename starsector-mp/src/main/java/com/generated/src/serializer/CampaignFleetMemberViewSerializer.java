
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CampaignFleetMemberViewSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.CampaignFleetMemberView obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getExtraAlphaMult());
            out.writeFloat(obj.getScaleMult());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.fleet.CampaignFleetMemberView deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.CampaignFleetMemberView obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.CampaignFleetMemberView.class);
        return obj;
    }

}
