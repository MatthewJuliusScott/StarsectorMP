
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class GoDarkAbilitySerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.abilities.GoDarkAbility obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getCooldownLeft());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getActiveLayers().writeObject();
        return obj.getCooldownColor().writeObject();
        return obj.getId().writeObject();
        return obj.getModId().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.abilities.GoDarkAbility deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.abilities.GoDarkAbility obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.abilities.GoDarkAbility.class);
        return obj;
    }

}
