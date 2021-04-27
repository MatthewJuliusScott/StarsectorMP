
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class FleetCreationSpecSerializer {


    public byte[] serialize(com.fs.starfarer.loading.FleetCreationSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getDisplayName());
            out.writeFloat(obj.getMaxDaysWorthSupplies());
            out.writeFloat(obj.getMaxExtraCrewPercent());
            out.writeFloat(obj.getMaxFleetPoints());
            out.writeFloat(obj.getMaxLYWorthOfFuel());
            out.writeFloat(obj.getMaxMarinesPercent());
            out.writeFloat(obj.getMinDaysWorthSupplies());
            out.writeFloat(obj.getMinExtraCrewPercent());
            out.writeFloat(obj.getMinLYWorthOfFuel());
            out.writeFloat(obj.getMinMarinesPercent());
            out.writeUTF(obj.getSpecId());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.loading.FleetCreationSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.loading.FleetCreationSpec obj = objenesis.newInstance(com.fs.starfarer.loading.FleetCreationSpec.class);
        return obj;
    }

}
