
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class AICoreAdminSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.econ.AICoreAdmin obj)
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

    public com.fs.starfarer.api.impl.campaign.econ.AICoreAdmin deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.econ.AICoreAdmin obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.econ.AICoreAdmin.class);
        return obj;
    }

}
