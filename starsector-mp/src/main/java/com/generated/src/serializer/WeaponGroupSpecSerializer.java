
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class WeaponGroupSpecSerializer {


    public byte[] serialize(com.fs.starfarer.api.loading.WeaponGroupSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isAutofireOnByDefault());
            String[] slotsArray = obj.getSlots().toArray(new String[ 0 ] );
            out.writeInt(slotsArray.length);
            for (int i = 0; (i<slotsArray.length); i ++) {
                out.writeUTF(slotsArray[i]);
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.loading.WeaponGroupType.class));
                out.write(serializer.serialize(obj.getType()));
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

    public com.fs.starfarer.api.loading.WeaponGroupSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.loading.WeaponGroupSpec obj = objenesis.newInstance(com.fs.starfarer.api.loading.WeaponGroupSpec.class);
        return obj;
    }

}
