
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.fs.starfarer.campaign.MissionBoard.MissionAvailability;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MissionBoardSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.MissionBoard obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            MissionAvailability[] missionsArray = obj.getMissions().toArray(new MissionAvailability[ 0 ] );
            out.writeInt(missionsArray.length);
            for (int i = 0; (i<missionsArray.length); i ++) {
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

    public com.fs.starfarer.campaign.MissionBoard deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.MissionBoard obj = objenesis.newInstance(com.fs.starfarer.campaign.MissionBoard.class);
        return obj;
    }

}
