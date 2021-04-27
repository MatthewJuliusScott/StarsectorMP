
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class EmergencyBurnAbilitySerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.abilities.EmergencyBurnAbility obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getActiveDaysLeft());
            out.writeFloat(obj.getCooldownLeft());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.abilities.EmergencyBurnAbility deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.abilities.EmergencyBurnAbility obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.abilities.EmergencyBurnAbility.class);
        return obj;
    }

}
