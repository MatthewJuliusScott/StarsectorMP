
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CampaignFleetAISerializer {


    public byte[] serialize(com.fs.starfarer.campaign.ai.CampaignFleetAI obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.FleetActionTextProvider.class));
                out.write(serializer.serialize(obj.getActionTextProvider()));
            } catch (SerializerNotFoundException _x) {
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getCurrentAssignmentType().writeObject();
    }

    public com.fs.starfarer.campaign.ai.CampaignFleetAI deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.ai.CampaignFleetAI obj = objenesis.newInstance(com.fs.starfarer.campaign.ai.CampaignFleetAI.class);
        return obj;
    }

}
