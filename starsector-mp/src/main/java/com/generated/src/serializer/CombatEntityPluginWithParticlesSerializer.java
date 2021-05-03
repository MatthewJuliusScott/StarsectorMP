
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CombatEntityPluginWithParticlesSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.combat.CombatEntityPluginWithParticles obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getDarkSpriteSheetKey());
            out.writeUTF(obj.getSpriteSheetKey());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getActiveLayers().writeObject();
    }

    public com.fs.starfarer.api.impl.combat.CombatEntityPluginWithParticles deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.combat.CombatEntityPluginWithParticles obj = objenesis.newInstance(com.fs.starfarer.api.impl.combat.CombatEntityPluginWithParticles.class);
        return obj;
    }

}
