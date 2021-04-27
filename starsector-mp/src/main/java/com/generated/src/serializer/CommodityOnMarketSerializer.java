
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CommodityOnMarketSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.econ.CommodityOnMarket obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.loading.F.class));
                out.write(serializer.serialize(obj.getCommodity()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isDemandLegal());
            out.writeInt(obj.getMaxDemand());
            out.writeInt(obj.getMaxSupply());
            out.writeFloat(obj.getStockpile());
            out.writeBoolean(obj.isSupplyLegal());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.econ.CommodityOnMarket deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.econ.CommodityOnMarket obj = objenesis.newInstance(com.fs.starfarer.campaign.econ.CommodityOnMarket.class);
        return obj;
    }

}
