
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class WeightedRandomPickerSerializer {


    public byte[] serialize(com.fs.starfarer.api.util.WeightedRandomPicker obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Random.class));
                out.write(serializer.serialize(obj.getRandom()));
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

    public com.fs.starfarer.api.util.WeightedRandomPicker deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.util.WeightedRandomPicker obj = objenesis.newInstance(com.fs.starfarer.api.util.WeightedRandomPicker.class);
        return obj;
    }

}
