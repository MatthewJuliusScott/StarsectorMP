
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MuzzleFlashSpecSerializer {


    public byte[] serialize(com.fs.starfarer.api.loading.MuzzleFlashSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getLength());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getParticleColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getParticleCount());
            out.writeFloat(obj.getParticleDuration());
            out.writeFloat(obj.getParticleSizeMin());
            out.writeFloat(obj.getParticleSizeRange());
            out.writeFloat(obj.getSpread());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.loading.MuzzleFlashSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.loading.MuzzleFlashSpec obj = objenesis.newInstance(com.fs.starfarer.api.loading.MuzzleFlashSpec.class);
        return obj;
    }

}
