
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BaseCustomEntityPluginSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.BaseCustomEntityPlugin obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.BaseCustomEntityPlugin deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.BaseCustomEntityPlugin obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.BaseCustomEntityPlugin.class);
        return obj;
    }

}
