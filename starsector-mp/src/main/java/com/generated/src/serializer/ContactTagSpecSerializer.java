
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ContactTagSpecSerializer {


    public byte[] serialize(com.fs.starfarer.api.loading.ContactTagSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getName());
            out.writeBoolean(obj.isPutFirst());
            out.writeFloat(obj.getSort());
            out.writeUTF(obj.getTag());
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

    public com.fs.starfarer.api.loading.ContactTagSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.loading.ContactTagSpec obj = objenesis.newInstance(com.fs.starfarer.api.loading.ContactTagSpec.class);
        return obj;
    }

}
