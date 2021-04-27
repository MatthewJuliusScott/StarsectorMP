
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class GenericSpecialItemPluginSerializer {


    public byte[] serialize(com.fs.starfarer.api.campaign.impl.items.GenericSpecialItemPlugin obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getId());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.campaign.impl.items.GenericSpecialItemPlugin deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.campaign.impl.items.GenericSpecialItemPlugin obj = objenesis.newInstance(com.fs.starfarer.api.campaign.impl.items.GenericSpecialItemPlugin.class);
        return obj;
    }

}
