
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class FullNameSerializer {


    public byte[] serialize(com.fs.starfarer.api.characters.FullName obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getFirst());
            out.writeUTF(obj.getLast());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getFullName().writeObject();
    }

    public com.fs.starfarer.api.characters.FullName deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.characters.FullName obj = objenesis.newInstance(com.fs.starfarer.api.characters.FullName.class);
        return obj;
    }

}
