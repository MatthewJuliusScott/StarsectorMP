
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class SmoothFacingModuleSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.SmoothFacingModule obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getFacing());
            out.writeFloat(obj.getMaxTurnRate());
            out.writeFloat(obj.getTurnAcceleration());
            out.writeFloat(obj.getTurnRate());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.fleet.SmoothFacingModule deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.SmoothFacingModule obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.SmoothFacingModule.class);
        return obj;
    }

}
