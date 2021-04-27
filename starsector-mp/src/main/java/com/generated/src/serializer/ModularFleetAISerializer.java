
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ModularFleetAISerializer {


    public byte[] serialize(com.fs.starfarer.campaign.ai.ModularFleetAI obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getActionTextOverride());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.FleetActionTextProvider.class));
                out.write(serializer.serialize(obj.getActionTextProvider()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.ai.AssignmentModulePlugin.class));
                out.write(serializer.serialize(obj.getAssignmentModule()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.ai.NavigationModulePlugin.class));
                out.write(serializer.serialize(obj.getNavModule()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.ai.StrategicModulePlugin.class));
                out.write(serializer.serialize(obj.getStrategicModule()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.ai.TacticalModulePlugin.class));
                out.write(serializer.serialize(obj.getTacticalModule()));
            } catch (SerializerNotFoundException _x) {
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

    public com.fs.starfarer.campaign.ai.ModularFleetAI deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.ai.ModularFleetAI obj = objenesis.newInstance(com.fs.starfarer.campaign.ai.ModularFleetAI.class);
        return obj;
    }

}
