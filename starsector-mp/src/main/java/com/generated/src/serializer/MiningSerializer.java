
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MiningSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.econ.impl.Mining obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getAICoreId());
            out.writeFloat(obj.getBuildProgress());
            out.writeBoolean(obj.isHidden());
            out.writeBoolean(obj.isImproved());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SpecialItemData.class));
                out.write(serializer.serialize(obj.getSpecialItem()));
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
        return obj.getBuildCostOverride().writeObject();
        return obj.getCanNotShutDownReason().writeObject();
        return obj.getDisruptedKey().writeObject();
        return obj.getId().writeObject();
        return obj.getImproveMenuText().writeObject();
        return obj.getImproveSoundId().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.econ.impl.Mining deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.econ.impl.Mining obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.econ.impl.Mining.class);
        return obj;
    }

}
