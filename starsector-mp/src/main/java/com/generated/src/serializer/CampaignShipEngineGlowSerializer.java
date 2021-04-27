
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CampaignShipEngineGlowSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.CampaignShipEngineGlow obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            com.fs.starfarer.campaign.fleet.CampaignShipEngineGlow.SlotData[] clustersArray = obj.getClusters().toArray(new com.fs.starfarer.campaign.fleet.CampaignShipEngineGlow.SlotData[ 0 ] );
            out.writeInt(clustersArray.length);
            for (int i = 0; (i<clustersArray.length); i ++) {
            }
            com.fs.starfarer.campaign.fleet.CampaignShipEngineGlow.SlotData[] slotsArray = obj.getSlots().toArray(new com.fs.starfarer.campaign.fleet.CampaignShipEngineGlow.SlotData[ 0 ] );
            out.writeInt(slotsArray.length);
            for (int i = 0; (i<slotsArray.length); i ++) {
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.fleet.CampaignShipEngineGlow deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.CampaignShipEngineGlow obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.CampaignShipEngineGlow.class);
        return obj;
    }

}
