
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PlanetConditionMarketSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.econ.PlanetConditionMarket obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAccessibility());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.characters.PersonAPI.class));
                out.write(serializer.serialize(obj.getAdmin()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isAllowExport());
            out.writeBoolean(obj.isAllowImport());
            out.writeInt(obj.getBaseSmugglingStabilityValue());
            MarketConditionAPI[] conditionsArray = obj.getConditions().toArray(new MarketConditionAPI[ 0 ] );
            out.writeInt(conditionsArray.length);
            for (int i = 0; (i<conditionsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.MarketConditionAPI.class));
                    out.write(serializer.serialize(conditionsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.writeFloat(obj.getDaysInExistence());
            out.writeUTF(obj.getEconGroup());
            out.writeUTF(obj.getFactionId());
            out.writeBoolean(obj.isForceNoConvertOnSave());
            out.writeBoolean(obj.isFreePort());
            out.writeUTF(obj.getId());
            out.writeBoolean(obj.isImmigrationClosed());
            out.writeFloat(obj.getIncentiveCredits());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.population.PopulationComposition.class));
                out.write(serializer.serialize(obj.getIncoming()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getName());
            out.writeUTF(obj.getParent());
            out.writeBoolean(obj.isPlanetConditionMarketOnly());
            out.writeBoolean(obj.isPlayerOwned());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.population.PopulationComposition.class));
                out.write(serializer.serialize(obj.getPopulation()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getPrimaryEntity()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getSize());
            out.writeBoolean(obj.isUseStockpilesForShortages());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.econ.PlanetConditionMarket deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.econ.PlanetConditionMarket obj = objenesis.newInstance(com.fs.starfarer.campaign.econ.PlanetConditionMarket.class);
        return obj;
    }

}
