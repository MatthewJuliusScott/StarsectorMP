
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.fs.starfarer.rpg.ImportantPeople.PersonData;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ImportantPeopleSerializer {


    public byte[] serialize(com.fs.starfarer.rpg.ImportantPeople obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            PersonData[] peopleArray = obj.getPeople().toArray(new PersonData[ 0 ] );
            out.writeInt(peopleArray.length);
            for (int i = 0; (i<peopleArray.length); i ++) {
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.rpg.ImportantPeople deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.rpg.ImportantPeople obj = objenesis.newInstance(com.fs.starfarer.rpg.ImportantPeople.class);
        return obj;
    }

}
