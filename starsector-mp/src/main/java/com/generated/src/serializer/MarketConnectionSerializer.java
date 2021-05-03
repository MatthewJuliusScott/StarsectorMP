
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MarketConnectionSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.econ.MarketConnection obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isAllTradeIsSmuggling());
            out.writeBoolean(obj.isEnabled());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getId().writeObject();
    }

    public com.fs.starfarer.campaign.econ.MarketConnection deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.econ.MarketConnection obj = objenesis.newInstance(com.fs.starfarer.campaign.econ.MarketConnection.class);
        return obj;
    }

}
