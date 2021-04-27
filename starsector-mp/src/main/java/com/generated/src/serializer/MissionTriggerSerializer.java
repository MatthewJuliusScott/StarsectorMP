
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.fs.starfarer.api.impl.campaign.missions.hub.MissionTrigger.TriggerAction;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MissionTriggerSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.missions.hub.MissionTrigger obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            TriggerAction[] actionsArray = obj.getActions().toArray(new TriggerAction[ 0 ] );
            out.writeInt(actionsArray.length);
            for (int i = 0; (i<actionsArray.length); i ++) {
            }
            Object[] stagesArray = obj.getStages().toArray(new Object[ 0 ] );
            out.writeInt(stagesArray.length);
            for (int i = 0; (i<stagesArray.length); i ++) {
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

    public com.fs.starfarer.api.impl.campaign.missions.hub.MissionTrigger deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.missions.hub.MissionTrigger obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.missions.hub.MissionTrigger.class);
        return obj;
    }

}
