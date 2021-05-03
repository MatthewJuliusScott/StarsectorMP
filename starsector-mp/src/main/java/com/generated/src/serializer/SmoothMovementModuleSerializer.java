
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class SmoothMovementModuleSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.SmoothMovementModule obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAcceleration());
            out.writeFloat(obj.getHardSpeedLimit());
            out.writeFloat(obj.getMaxSpeed());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getLocation().writeObject();
        return obj.getVelocity().writeObject();
    }

    public com.fs.starfarer.campaign.fleet.SmoothMovementModule deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.SmoothMovementModule obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.SmoothMovementModule.class);
        return obj;
    }

}
