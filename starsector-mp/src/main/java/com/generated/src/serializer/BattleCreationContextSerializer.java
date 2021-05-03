
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BattleCreationContextSerializer {


    public byte[] serialize(com.fs.starfarer.api.combat.BattleCreationContext obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getEscapeDeploymentBurnDuration());
            out.writeFloat(obj.getFlankDeploymentDistance());
            out.writeFloat(obj.getInitialDeploymentBurnDuration());
            out.writeFloat(obj.getInitialEscapeRange());
            out.writeFloat(obj.getInitialNumSteps());
            out.writeFloat(obj.getInitialStepSize());
            out.writeFloat(obj.getNormalDeploymentBurnDuration());
            out.writeInt(obj.getPlayerCommandPoints());
            out.writeFloat(obj.getPursuitRangeModifier());
            out.writeFloat(obj.getStandoffRange());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getOtherGoal().writeObject();
        return obj.getPlayerGoal().writeObject();
    }

    public com.fs.starfarer.api.combat.BattleCreationContext deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.combat.BattleCreationContext obj = objenesis.newInstance(com.fs.starfarer.api.combat.BattleCreationContext.class);
        return obj;
    }

}
