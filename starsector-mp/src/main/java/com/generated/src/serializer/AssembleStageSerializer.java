
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class AssembleStageSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.intel.raid.AssembleStage obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAbortFP());
            MarketAPI[] sourcesArray = obj.getSources().toArray(new MarketAPI[ 0 ] );
            out.writeInt(sourcesArray.length);
            for (int i = 0; (i<sourcesArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.MarketAPI.class));
                    out.write(serializer.serialize(sourcesArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.writeFloat(obj.getSpawnFP());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getStatus().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.intel.raid.AssembleStage deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.intel.raid.AssembleStage obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.intel.raid.AssembleStage.class);
        return obj;
    }

}
