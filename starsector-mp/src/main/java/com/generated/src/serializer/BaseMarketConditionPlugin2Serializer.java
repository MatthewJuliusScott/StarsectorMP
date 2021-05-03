
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BaseMarketConditionPlugin2Serializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin2 obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getDaysActive());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getHighlightColors().writeObject();
        return obj.getHighlights().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin2 deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin2 obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin2 .class);
        return obj;
    }

}
