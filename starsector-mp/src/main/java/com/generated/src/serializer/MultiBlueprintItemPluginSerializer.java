
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MultiBlueprintItemPluginSerializer {


    public byte[] serialize(com.fs.starfarer.api.campaign.impl.items.MultiBlueprintItemPlugin obj)
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
        return obj.getDesignType().writeObject();
    }

    public com.fs.starfarer.api.campaign.impl.items.MultiBlueprintItemPlugin deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.campaign.impl.items.MultiBlueprintItemPlugin obj = objenesis.newInstance(com.fs.starfarer.api.campaign.impl.items.MultiBlueprintItemPlugin.class);
        return obj;
    }

}
