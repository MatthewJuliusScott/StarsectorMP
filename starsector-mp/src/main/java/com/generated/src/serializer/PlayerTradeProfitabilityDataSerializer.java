
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PlayerTradeProfitabilityDataSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.shared.PlayerTradeProfitabilityData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeLong(obj.getAccruedXP());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.shared.PlayerTradeProfitabilityData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.shared.PlayerTradeProfitabilityData obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.shared.PlayerTradeProfitabilityData.class);
        return obj;
    }

}
