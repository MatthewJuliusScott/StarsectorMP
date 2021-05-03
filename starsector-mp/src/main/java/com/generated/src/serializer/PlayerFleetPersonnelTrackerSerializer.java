
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.fs.starfarer.api.impl.PlayerFleetPersonnelTracker.PersonnelAtEntity;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PlayerFleetPersonnelTrackerSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.PlayerFleetPersonnelTracker obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            PersonnelAtEntity[] droppedOffArray = obj.getDroppedOff().toArray(new PersonnelAtEntity[ 0 ] );
            out.writeInt(droppedOffArray.length);
            for (int i = 0; (i<droppedOffArray.length); i ++) {
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getIconName().writeObject();
    }

    public com.fs.starfarer.api.impl.PlayerFleetPersonnelTracker deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.PlayerFleetPersonnelTracker obj = objenesis.newInstance(com.fs.starfarer.api.impl.PlayerFleetPersonnelTracker.class);
        return obj;
    }

}
