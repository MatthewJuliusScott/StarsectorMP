
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class DescriptionSerializer {


    public byte[] serialize(com.fs.starfarer.api.loading.Description obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getText1());
            out.writeUTF(obj.getText2());
            out.writeUTF(obj.getText3());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getText1FirstPara().writeObject();
    }

    public com.fs.starfarer.api.loading.Description deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.loading.Description obj = objenesis.newInstance(com.fs.starfarer.api.loading.Description.class);
        return obj;
    }

}
