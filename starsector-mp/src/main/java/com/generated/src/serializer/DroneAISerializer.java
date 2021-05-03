
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class DroneAISerializer {


    public byte[] serialize(com.fs.starfarer.combat.ai.system.drones.DroneAI obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isEngaged());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getDesiredLocation().writeObject();
    }

    public com.fs.starfarer.combat.ai.system.drones.DroneAI deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.combat.ai.system.drones.DroneAI obj = objenesis.newInstance(com.fs.starfarer.combat.ai.system.drones.DroneAI.class);
        return obj;
    }

}
