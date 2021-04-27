
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ConstellationSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.procgen.Constellation obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.procgen.StarAge.class));
                out.write(serializer.serialize(obj.getAge()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Map.class));
                out.write(serializer.serialize(obj.getAllEntitiesAdded()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Map.class));
                out.write(serializer.serialize(obj.getLagrangeParentMap()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isLeavePickedNameUnused());
            out.writeUTF(obj.getNameOverride());
            StarSystemAPI[] systemsArray = obj.getSystems().toArray(new StarSystemAPI[ 0 ] );
            out.writeInt(systemsArray.length);
            for (int i = 0; (i<systemsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.StarSystemAPI.class));
                    out.write(serializer.serialize(systemsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
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

    public com.fs.starfarer.api.impl.campaign.procgen.Constellation deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.procgen.Constellation obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.procgen.Constellation.class);
        return obj;
    }

}
