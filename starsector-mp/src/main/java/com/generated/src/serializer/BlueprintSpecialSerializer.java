
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BlueprintSpecialSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.rulecmd.salvage.special.BlueprintSpecial obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isDone());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.api.impl.campaign.rulecmd.salvage.special.BlueprintSpecial deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.rulecmd.salvage.special.BlueprintSpecial obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.rulecmd.salvage.special.BlueprintSpecial.class);
        return obj;
    }

}
