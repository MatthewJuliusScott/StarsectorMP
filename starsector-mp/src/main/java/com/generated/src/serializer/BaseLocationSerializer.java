
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.campaign.FleetStubAPI;
import com.fs.starfarer.api.campaign.SpawnPointPlugin;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class BaseLocationSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.BaseLocation obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeUTF(obj.getBackgroundTextureFilename());
            out.writeUTF(obj.getDisplayName());
            FleetStubAPI[] fleetStubsArray = obj.getFleetStubs().toArray(new FleetStubAPI[ 0 ] );
            out.writeInt(fleetStubsArray.length);
            for (int i = 0; (i<fleetStubsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.FleetStubAPI.class));
                    out.write(serializer.serialize(fleetStubsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getLightColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getName());
            EveryFrameScript[] scriptsArray = obj.getScripts().toArray(new EveryFrameScript[ 0 ] );
            out.writeInt(scriptsArray.length);
            for (int i = 0; (i<scriptsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.EveryFrameScript.class));
                    out.write(serializer.serialize(scriptsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            SpawnPointPlugin[] spawnPointsArray = obj.getSpawnPoints().toArray(new SpawnPointPlugin[ 0 ] );
            out.writeInt(spawnPointsArray.length);
            for (int i = 0; (i<spawnPointsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.SpawnPointPlugin.class));
                    out.write(serializer.serialize(spawnPointsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            String[] tagsArray = obj.getTags().toArray(new String[ 0 ] );
            out.writeInt(tagsArray.length);
            for (int i = 0; (i<tagsArray.length); i ++) {
                out.writeUTF(tagsArray[i]);
            }
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
        return obj.getLocation().writeObject();
    }

    public com.fs.starfarer.campaign.BaseLocation deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.BaseLocation obj = objenesis.newInstance(com.fs.starfarer.campaign.BaseLocation.class);
        return obj;
    }

}
