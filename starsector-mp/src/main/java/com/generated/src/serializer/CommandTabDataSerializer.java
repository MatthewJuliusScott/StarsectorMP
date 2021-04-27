
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CommandTabDataSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.command.CommandTabData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeInt(obj.getSelectedSubtabIndex());
            out.writeInt(obj.getSelectedTabIndex());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.command.CommandTabData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.command.CommandTabData obj = objenesis.newInstance(com.fs.starfarer.campaign.command.CommandTabData.class);
        return obj;
    }

}
