
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class FactionDoctrineSerializer {


    public byte[] serialize(com.fs.starfarer.loading.specs.FactionDoctrine obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeInt(obj.getAggression());
            out.writeFloat(obj.getAutofitRandomizeProbability());
            out.writeInt(obj.getCarriers());
            out.writeFloat(obj.getCombatFreighterCombatUseFraction());
            out.writeFloat(obj.getCombatFreighterCombatUseFractionWhenPriority());
            out.writeFloat(obj.getCombatFreighterProbability());
            String[] commanderSkillsArray = obj.getCommanderSkills().toArray(new String[ 0 ] );
            out.writeInt(commanderSkillsArray.length);
            for (int i = 0; (i<commanderSkillsArray.length); i ++) {
                out.writeUTF(commanderSkillsArray[i]);
            }
            out.writeFloat(obj.getCommanderSkillsShuffleProbability());
            out.writeInt(obj.getFleets());
            out.writeInt(obj.getNumShips());
            out.writeInt(obj.getOfficerQuality());
            out.writeInt(obj.getPhaseShips());
            out.writeInt(obj.getShipQuality());
            out.writeInt(obj.getShipSize());
            out.writeBoolean(obj.isStrictComposition());
            out.writeInt(obj.getWarships());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.loading.specs.FactionDoctrine deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.loading.specs.FactionDoctrine obj = objenesis.newInstance(com.fs.starfarer.loading.specs.FactionDoctrine.class);
        return obj;
    }

}
