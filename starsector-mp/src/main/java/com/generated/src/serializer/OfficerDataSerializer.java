
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class OfficerDataSerializer {


    public byte[] serialize(com.fs.starfarer.rpg.OfficerData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            String[] skillPicksArray = obj.getSkillPicks().toArray(new String[ 0 ] );
            out.writeInt(skillPicksArray.length);
            for (int i = 0; (i<skillPicksArray.length); i ++) {
                out.writeUTF(skillPicksArray[i]);
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

    public com.fs.starfarer.rpg.OfficerData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.rpg.OfficerData obj = objenesis.newInstance(com.fs.starfarer.rpg.OfficerData.class);
        return obj;
    }

}
