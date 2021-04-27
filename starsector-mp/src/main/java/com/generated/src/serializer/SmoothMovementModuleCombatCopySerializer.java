
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class SmoothMovementModuleCombatCopySerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.SmoothMovementModuleCombatCopy obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAcceleration());
            out.writeFloat(obj.getMaxSpeed());
            out.writeBoolean(obj.isSmoothCap());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.fleet.SmoothMovementModuleCombatCopy deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.SmoothMovementModuleCombatCopy obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.SmoothMovementModuleCombatCopy.class);
        return obj;
    }

}
