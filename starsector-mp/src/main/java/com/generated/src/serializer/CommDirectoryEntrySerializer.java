
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CommDirectoryEntrySerializer {


    public byte[] serialize(com.fs.starfarer.campaign.CommDirectoryEntry obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isHidden());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getId().writeObject();
        return obj.getSpriteName().writeObject();
        return obj.getText().writeObject();
        return obj.getTitle().writeObject();
    }

    public com.fs.starfarer.campaign.CommDirectoryEntry deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.CommDirectoryEntry obj = objenesis.newInstance(com.fs.starfarer.campaign.CommDirectoryEntry.class);
        return obj;
    }

}
