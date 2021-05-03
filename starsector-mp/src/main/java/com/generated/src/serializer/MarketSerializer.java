
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.campaign.econ.CommodityOnMarket;
import com.fs.starfarer.campaign.econ.Submarket;
import com.fs.starfarer.rpg.Person;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MarketSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.econ.Market obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isAllowExport());
            out.writeBoolean(obj.isAllowImport());
            CommodityOnMarket[] commoditiesArray = obj.getCommodities().toArray(new CommodityOnMarket[ 0 ] );
            out.writeInt(commoditiesArray.length);
            for (int i = 0; (i<commoditiesArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.econ.CommodityOnMarket.class));
                    out.write(serializer.serialize(commoditiesArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            MarketConditionAPI[] conditionsArray = obj.getConditions().toArray(new MarketConditionAPI[ 0 ] );
            out.writeInt(conditionsArray.length);
            for (int i = 0; (i<conditionsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.MarketConditionAPI.class));
                    out.write(serializer.serialize(conditionsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            SectorEntityToken[] connectedEntitiesArray = obj.getConnectedEntities().toArray(new SectorEntityToken[ 0 ] );
            out.writeInt(connectedEntitiesArray.length);
            for (int i = 0; (i<connectedEntitiesArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                    out.write(serializer.serialize(connectedEntitiesArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.writeFloat(obj.getDaysInExistence());
            out.writeUTF(obj.getEconGroup());
            out.writeUTF(obj.getFactionId());
            out.writeBoolean(obj.isForceNoConvertOnSave());
            out.writeBoolean(obj.isFreePort());
            out.writeUTF(obj.getId());
            out.writeFloat(obj.getIncentiveCredits());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.impl.campaign.population.PopulationComposition.class));
                out.write(serializer.serialize(obj.getIncoming()));
            } catch (SerializerNotFoundException _x) {
            }
            Industry[] industriesArray = obj.getIndustries().toArray(new Industry[ 0 ] );
            out.writeInt(industriesArray.length);
            for (int i = 0; (i<industriesArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.Industry.class));
                    out.write(serializer.serialize(industriesArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.writeUTF(obj.getName());
            Person[] peopleArray = obj.getPeople().toArray(new Person[ 0 ] );
            out.writeInt(peopleArray.length);
            for (int i = 0; (i<peopleArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.rpg.Person.class));
                    out.write(serializer.serialize(peopleArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.writeBoolean(obj.isPlanetConditionMarketOnly());
            out.writeBoolean(obj.isPlayerOwned());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SectorEntityToken.class));
                out.write(serializer.serialize(obj.getPrimaryEntity()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getSize());
            Submarket[] submarketsArray = obj.getSubmarkets().toArray(new Submarket[ 0 ] );
            out.writeInt(submarketsArray.length);
            for (int i = 0; (i<submarketsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.econ.Submarket.class));
                    out.write(serializer.serialize(submarketsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            String[] tagsArray = obj.getTags().toArray(new String[ 0 ] );
            out.writeInt(tagsArray.length);
            for (int i = 0; (i<tagsArray.length); i ++) {
                out.writeUTF(tagsArray[i]);
            }
            out.writeBoolean(obj.isUseStockpilesForShortages());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getImmigrationModifiers().writeObject();
        return obj.getLocation().writeObject();
        return obj.getLocationInHyperspace().writeObject();
        return obj.getOnOrAt().writeObject();
        return obj.getSimDisplayLocation().writeObject();
        return obj.getSuppressedConditions().writeObject();
        return obj.getTransientImmigrationModifiers().writeObject();
    }

    public com.fs.starfarer.campaign.econ.Market deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.econ.Market obj = objenesis.newInstance(com.fs.starfarer.campaign.econ.Market.class);
        return obj;
    }

}
