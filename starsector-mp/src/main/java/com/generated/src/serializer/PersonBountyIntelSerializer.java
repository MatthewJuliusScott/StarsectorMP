
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PersonBountyIntelSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.intel.PersonBountyIntel obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getElapsedDays());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Long.class));
                out.write(serializer.serialize(obj.getPlayerVisibleTimestamp()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getPostingLocation()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Float.class));
                out.write(serializer.serialize(obj.getPostingRangeLY()));
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
        return obj.getBackgroundGlowColor().writeObject();
        return obj.getCommMessageSound().writeObject();
        return obj.getImportantIcon().writeObject();
        return obj.getSortString().writeObject();
        return obj.getSortTier().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.intel.PersonBountyIntel deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.intel.PersonBountyIntel obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.intel.PersonBountyIntel.class);
        return obj;
    }

}
