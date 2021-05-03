
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class OpenMarketPluginSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.submarkets.OpenMarketPlugin obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getMinSWUpdateInterval());
            out.writeFloat(obj.getSinceLastCargoUpdate());
            out.writeFloat(obj.getSinceSWUpdate());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getBuyVerb().writeObject();
        return obj.getName().writeObject();
        return obj.getPlayerEconomyImpactMode().writeObject();
        return obj.getSellVerb().writeObject();
        return obj.getTariffTextOverride().writeObject();
        return obj.getTariffValueOverride().writeObject();
        return obj.getTotalTextOverride().writeObject();
        return obj.getTotalValueOverride().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.submarkets.OpenMarketPlugin deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.submarkets.OpenMarketPlugin obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.submarkets.OpenMarketPlugin.class);
        return obj;
    }

}
