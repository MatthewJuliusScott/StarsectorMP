
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CommodityGroundRaidObjectivePluginImplSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.graid.CommodityGroundRaidObjectivePluginImpl obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getAssignedForcesColumnColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getAssignedForcesColumnText());
            out.writeInt(obj.getDeficitActuallyCaused());
            out.writeUTF(obj.getId());
            out.writeInt(obj.getMarinesAssigned());
            out.writeInt(obj.getMarinesRequired());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.MarketAPI.class));
                out.write(serializer.serialize(obj.getMarket()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getNameOverride());
            out.writeInt(obj.getQuantityLooted());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.econ.Industry.class));
                out.write(serializer.serialize(obj.getSource()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getXpGained());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getDisruptedAlreadyString().writeObject();
        return obj.getIconName().writeObject();
        return obj.getSourceString().writeObject();
    }

    public com.fs.starfarer.api.impl.campaign.graid.CommodityGroundRaidObjectivePluginImpl deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.graid.CommodityGroundRaidObjectivePluginImpl obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.graid.CommodityGroundRaidObjectivePluginImpl.class);
        return obj;
    }

}
