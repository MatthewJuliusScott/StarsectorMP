
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class EventProbabilitySerializer {


    public byte[] serialize(com.fs.starfarer.campaign.events.EventProbability obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.events.CampaignEventPlugin.class));
                out.write(serializer.serialize(obj.getPlugin()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isPrimed());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getEventType().writeObject();
    }

    public com.fs.starfarer.campaign.events.EventProbability deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.events.EventProbability obj = objenesis.newInstance(com.fs.starfarer.campaign.events.EventProbability.class);
        return obj;
    }

}
