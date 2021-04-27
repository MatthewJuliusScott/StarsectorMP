
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.campaign.ui.trade.CargoItemStack;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CargoDataSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.CargoData obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getExtraCargoUsed());
            out.writeFloat(obj.getExtraCrewUsed());
            out.writeFloat(obj.getExtraFuelUsed());
            out.writeFloat(obj.getExtraMarinesUsed());
            out.writeFloat(obj.getExtraSuppliesUsed());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.fleet.FleetData.class));
                out.write(serializer.serialize(obj.getFleetData()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isFreeTransfer());
            out.writeFloat(obj.getMaxCapacity());
            out.writeFloat(obj.getMaxFuel());
            out.writeFloat(obj.getMaxPersonnel());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.fleet.FleetData.class));
                out.write(serializer.serialize(obj.getMothballedShips()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.CargoAPI.class));
                out.write(serializer.serialize(obj.getOrigSource()));
            } catch (SerializerNotFoundException _x) {
            }
            CargoItemStack[] stacksArray = obj.getStacks().toArray(new CargoItemStack[ 0 ] );
            out.writeInt(stacksArray.length);
            for (int i = 0; (i<stacksArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.ui.trade.CargoItemStack.class));
                    out.write(serializer.serialize(stacksArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.writeBoolean(obj.isUnlimitedStacks());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.fleet.CargoData deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.CargoData obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.CargoData.class);
        return obj;
    }

}
