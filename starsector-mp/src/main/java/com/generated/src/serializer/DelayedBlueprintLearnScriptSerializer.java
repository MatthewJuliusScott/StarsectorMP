
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class DelayedBlueprintLearnScriptSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.DelayedBlueprintLearnScript obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            java.lang.String[] fightersArray = obj.getFighters().toArray(new java.lang.String[ 0 ] );
            out.writeInt(fightersArray.length);
            for (int i = 0; (i<fightersArray.length); i ++) {
                out.writeUTF(fightersArray[i]);
            }
            java.lang.String[] industriesArray = obj.getIndustries().toArray(new java.lang.String[ 0 ] );
            out.writeInt(industriesArray.length);
            for (int i = 0; (i<industriesArray.length); i ++) {
                out.writeUTF(industriesArray[i]);
            }
            java.lang.String[] shipsArray = obj.getShips().toArray(new java.lang.String[ 0 ] );
            out.writeInt(shipsArray.length);
            for (int i = 0; (i<shipsArray.length); i ++) {
                out.writeUTF(shipsArray[i]);
            }
            java.lang.String[] weaponsArray = obj.getWeapons().toArray(new java.lang.String[ 0 ] );
            out.writeInt(weaponsArray.length);
            for (int i = 0; (i<weaponsArray.length); i ++) {
                out.writeUTF(weaponsArray[i]);
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

    public com.fs.starfarer.api.impl.campaign.DelayedBlueprintLearnScript deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.DelayedBlueprintLearnScript obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.DelayedBlueprintLearnScript.class);
        return obj;
    }

}
