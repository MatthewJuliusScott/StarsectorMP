
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CampaignPingSpecSerializer {


    public byte[] serialize(com.fs.starfarer.api.loading.CampaignPingSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAlphaMult());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getDelay());
            out.writeFloat(obj.getDuration());
            out.writeUTF(obj.getId());
            out.writeFloat(obj.getInFraction());
            out.writeBoolean(obj.isInvert());
            out.writeFloat(obj.getMinRange());
            out.writeInt(obj.getNum());
            out.writeFloat(obj.getRange());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.List.class));
                out.write(serializer.serialize(obj.getSounds()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isUseFactionColor());
            out.writeFloat(obj.getWidth());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.loading.CampaignPingSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.loading.CampaignPingSpec obj = objenesis.newInstance(com.fs.starfarer.api.loading.CampaignPingSpec.class);
        return obj;
    }

}
