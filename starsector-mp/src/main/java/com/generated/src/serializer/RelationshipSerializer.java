
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class RelationshipSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.Relationship obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.RepLevel.class));
                out.write(serializer.serialize(obj.getLevel()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getRel());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.Relationship deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.Relationship obj = objenesis.newInstance(com.fs.starfarer.campaign.Relationship.class);
        return obj;
    }

}
