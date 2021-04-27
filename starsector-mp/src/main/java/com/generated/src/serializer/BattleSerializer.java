
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BattleSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.Battle obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isPlayerInvolvedAtStart());
            com.fs.starfarer.api.campaign.CampaignFleetAPI[] sideOneArray = obj.getSideOne().toArray(new com.fs.starfarer.api.campaign.CampaignFleetAPI[ 0 ] );
            out.writeInt(sideOneArray.length);
            for (int i = 0; (i<sideOneArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.CampaignFleetAPI.class));
                    out.write(serializer.serialize(sideOneArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            com.fs.starfarer.api.campaign.CampaignFleetAPI[] sideTwoArray = obj.getSideTwo().toArray(new com.fs.starfarer.api.campaign.CampaignFleetAPI[ 0 ] );
            out.writeInt(sideTwoArray.length);
            for (int i = 0; (i<sideTwoArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.CampaignFleetAPI.class));
                    out.write(serializer.serialize(sideTwoArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
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

    public com.fs.starfarer.campaign.fleet.Battle deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.Battle obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.Battle.class);
        return obj;
    }

}
