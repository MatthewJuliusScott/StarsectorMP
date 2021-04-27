
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
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
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.loading.specs.Y.class));
                out.write(serializer.serialize(obj.getLaunchPoint()));
            } catch (SerializerNotFoundException _x) {
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

    public com.fs.starfarer.combat.ai.system.drones.DroneAI deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.combat.ai.system.drones.DroneAI obj = objenesis.newInstance(com.fs.starfarer.combat.ai.system.drones.DroneAI.class);
        return obj;
    }

}
