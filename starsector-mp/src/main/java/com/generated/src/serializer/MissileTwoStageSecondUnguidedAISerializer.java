
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MissileTwoStageSecondUnguidedAISerializer {


    public byte[] serialize(com.fs.starfarer.combat.ai.missile.MissileTwoStageSecondUnguidedAI obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isDirectAim());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.combat.CombatEntityAPI.class));
                out.write(serializer.serialize(obj.getTarget()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getZeroOffsetRange());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.combat.ai.missile.MissileTwoStageSecondUnguidedAI deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.combat.ai.missile.MissileTwoStageSecondUnguidedAI obj = objenesis.newInstance(com.fs.starfarer.combat.ai.missile.MissileTwoStageSecondUnguidedAI.class);
        return obj;
    }

}
