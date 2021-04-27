
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class HyperspaceSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.Hyperspace obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getBackgroundTextureFilename());
            out.writeUTF(obj.getDisplayName());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getLightColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getName());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.Hyperspace deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.Hyperspace obj = objenesis.newInstance(com.fs.starfarer.campaign.Hyperspace.class);
        return obj;
    }

}
