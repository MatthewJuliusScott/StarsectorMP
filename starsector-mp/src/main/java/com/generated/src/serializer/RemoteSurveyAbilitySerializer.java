
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class RemoteSurveyAbilitySerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.abilities.RemoteSurveyAbility obj)
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
        return obj.getActiveLayers().writeObject();
        return obj.getCooldownColor().writeObject();
        return obj.getId().writeObject();
        return obj.getModId().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.abilities.RemoteSurveyAbility deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.abilities.RemoteSurveyAbility obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.abilities.RemoteSurveyAbility.class);
        return obj;
    }

}
