
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ReachEconomySerializer {


    public byte[] serialize(com.fs.starfarer.campaign.econ.reach.ReachEconomy obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            LocationAPI[] locationsWithMarketsArray = obj.getLocationsWithMarkets().toArray(new LocationAPI[ 0 ] );
            out.writeInt(locationsWithMarketsArray.length);
            for (int i = 0; (i<locationsWithMarketsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.LocationAPI.class));
                    out.write(serializer.serialize(locationsWithMarketsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            MarketAPI[] marketsArray = obj.getMarkets().toArray(new MarketAPI[ 0 ] );
            out.writeInt(marketsArray.length);
            for (int i = 0; (i<marketsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.MarketAPI.class));
                    out.write(serializer.serialize(marketsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getIdToMarket().writeObject();
    }

    public com.fs.starfarer.campaign.econ.reach.ReachEconomy deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.econ.reach.ReachEconomy obj = objenesis.newInstance(com.fs.starfarer.campaign.econ.reach.ReachEconomy.class);
        return obj;
    }

}
