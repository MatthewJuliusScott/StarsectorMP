
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class FleetMemberSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.fleet.FleetMember obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isAlly());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.BuffManager.class));
                out.write(serializer.serialize(obj.getBuffManager()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.fleet.CrewComposition.class));
                out.write(serializer.serialize(obj.getCrewComposition()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isFlagship());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.campaign.fleet.FleetData.class));
                out.write(serializer.serialize(obj.getFleetData()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isForceNoMoreStatsUpdates());
            out.writeUTF(obj.getId());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((org.lwjgl.util.vector.Vector2f.class));
                out.write(serializer.serialize(obj.getOverrideSpriteSize()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getOwner());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.loading.specs.HullVariantSpec.class));
                out.write(serializer.serialize(obj.getSavedVariant()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getShipName());
            out.writeUTF(obj.getSpriteOverride());
            out.writeBoolean(obj.isStatUpdateNeeded());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getHullId().writeObject();
        return obj.getShortName().writeObject();
        return obj.getSpecId().writeObject();
        return obj.getType().writeObject();
    }

    public com.fs.starfarer.campaign.fleet.FleetMember deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.fleet.FleetMember obj = objenesis.newInstance(com.fs.starfarer.campaign.fleet.FleetMember.class);
        return obj;
    }

}
