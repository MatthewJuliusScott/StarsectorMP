
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class NameGenDataSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.procgen.NameGenData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getFrequency());
            out.writeUTF(obj.getName());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Set.class));
                out.write(serializer.serialize(obj.getParents()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isReusable());
            out.writeUTF(obj.getSecondary());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Set.class));
                out.write(serializer.serialize(obj.getTags()));
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
    }

    public com.fs.starfarer.api.impl.campaign.procgen.NameGenData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.procgen.NameGenData obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.procgen.NameGenData.class);
        return obj;
    }

}
