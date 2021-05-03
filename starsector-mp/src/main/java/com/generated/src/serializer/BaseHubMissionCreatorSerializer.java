
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BaseHubMissionCreatorSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.missions.hub.BaseHubMissionCreator obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isActive());
            out.writeInt(obj.getNumCompleted());
            out.writeInt(obj.getNumFailed());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getGenRandom().writeObject();
        return obj.getSpecId().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.missions.hub.BaseHubMissionCreator deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.missions.hub.BaseHubMissionCreator obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.missions.hub.BaseHubMissionCreator.class);
        return obj;
    }

}
