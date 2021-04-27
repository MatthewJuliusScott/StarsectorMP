
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.fs.starfarer.api.fleet.RepairTrackerAPI.CREvent;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class RepairTrackerSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.RepairTracker obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getCRPriorToMothballing());
            out.writeBoolean(obj.isCrashMothballed());
            out.writeBoolean(obj.isMothballed());
            CREvent[] recentEventsArray = obj.getRecentEvents().toArray(new CREvent[ 0 ] );
            out.writeInt(recentEventsArray.length);
            for (int i = 0; (i<recentEventsArray.length); i ++) {
            }
            out.writeBoolean(obj.isSuspendRepairs());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.fleet.RepairTracker deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.RepairTracker obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.RepairTracker.class);
        return obj;
    }

}
