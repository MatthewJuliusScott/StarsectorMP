
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class FighterWingSpecSerializer {


    public byte[] serialize(com.fs.starfarer.loading.specs.FighterWingSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAttackPositionOffset());
            out.writeFloat(obj.getAttackRunRange());
            out.writeFloat(obj.getBaseValue());
            out.writeInt(obj.getFleetPoints());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.loading.FormationType.class));
                out.write(serializer.serialize(obj.getFormation()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getId());
            out.writeInt(obj.getNumFighters());
            out.writeFloat(obj.getRange());
            out.writeFloat(obj.getRarity());
            out.writeFloat(obj.getRefitTime());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.loading.WingRole.class));
                out.write(serializer.serialize(obj.getRole()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getRoleDesc());
            String[] tagsArray = obj.getTags().toArray(new String[ 0 ] );
            out.writeInt(tagsArray.length);
            for (int i = 0; (i<tagsArray.length); i ++) {
                out.writeUTF(tagsArray[i]);
            }
            out.writeInt(obj.getTier());
            out.writeUTF(obj.getVariantId());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getAutofitCategory().writeObject();
    }

    public com.fs.starfarer.loading.specs.FighterWingSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.loading.specs.FighterWingSpec obj = objenesis.newInstance(com.fs.starfarer.loading.specs.FighterWingSpec.class);
        return obj;
    }

}
