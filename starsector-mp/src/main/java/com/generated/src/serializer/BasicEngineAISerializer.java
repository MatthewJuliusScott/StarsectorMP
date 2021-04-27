
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BasicEngineAISerializer {


    public byte[] serialize(com.fs.starfarer.combat.ai.movement.BasicEngineAI obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isAlwaysAccelerate());
            out.writeBoolean(obj.isAvoidingCollision());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.combat.ai.movement.BasicEngineAI deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.combat.ai.movement.BasicEngineAI obj = objenesis.newInstance(com.fs.starfarer.combat.ai.movement.BasicEngineAI.class);
        return obj;
    }

}
