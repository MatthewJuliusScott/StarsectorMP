
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CommMessageSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.comms.CommMessage obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isAddToIntelTab());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getCenterMapOnEntity()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getChannel());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Object.class));
                out.write(serializer.serialize(obj.getCustomData()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Map.class));
                out.write(serializer.serialize(obj.getCustomMap()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getDeliveredBy());
            out.writeUTF(obj.getImage());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((org.lwjgl.util.vector.Vector2f.class));
                out.write(serializer.serialize(obj.getLocInHyper()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getLocationString());
            out.writeUTF(obj.getMarketId());
            out.writeUTF(obj.getNote());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getNoteColor()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.OnMessageDeliveryScript.class));
                out.write(serializer.serialize(obj.getOnDelivery()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.List.class));
                out.write(serializer.serialize(obj.getPriceUpdates()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getSender());
            out.writeUTF(obj.getShortType());
            out.writeBoolean(obj.isShowInCampaignList());
            out.writeUTF(obj.getSmallIcon());
            out.writeUTF(obj.getSound());
            out.writeUTF(obj.getStarSystemId());
            out.writeUTF(obj.getSubject());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.util.Highlights.class));
                out.write(serializer.serialize(obj.getSubjectHighlights()));
            } catch (SerializerNotFoundException _x) {
            }
            String[] tagsArray = obj.getTags().toArray(new String[ 0 ] );
            out.writeInt(tagsArray.length);
            for (int i = 0; (i<tagsArray.length); i ++) {
                out.writeUTF(tagsArray[i]);
            }
            out.writeLong(obj.getTimeReceived());
            out.writeLong(obj.getTimeSent());
            out.writeUTF(obj.getType());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.comms.CommMessage deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.comms.CommMessage obj = objenesis.newInstance(com.fs.starfarer.campaign.comms.CommMessage.class);
        return obj;
    }

}
