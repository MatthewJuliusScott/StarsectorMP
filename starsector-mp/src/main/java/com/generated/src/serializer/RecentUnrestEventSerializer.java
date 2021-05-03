
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class RecentUnrestEventSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.events.RecentUnrestEvent obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeInt(obj.getStabilityPenalty());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getCurrentImage().writeObject();
        return obj.getCurrentMessageIcon().writeObject();
        return obj.getEventCategory().writeObject();
        return obj.getEventIcon().writeObject();
        return obj.getEventType().writeObject();
        return obj.getStageIdForLikely().writeObject();
        return obj.getStageIdForPossible().writeObject();
        return obj.getStatModId().writeObject();
        return obj.getWarningWhenLikelyPriority().writeObject();
        return obj.getWarningWhenPossiblePriority().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.events.RecentUnrestEvent deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.events.RecentUnrestEvent obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.events.RecentUnrestEvent.class);
        return obj;
    }

}
