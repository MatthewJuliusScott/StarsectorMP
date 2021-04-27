
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class FleetEncounterContextSerializer {


    public byte[] serialize(com.fs.starfarer.api.impl.campaign.FleetEncounterContext obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeFloat(obj.getAllyFPHullDamageToEnemies());
            out.writeBoolean(obj.isAutoresolve());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.BattleAPI.class));
                out.write(serializer.serialize(obj.getBattle()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isComputedDifficulty());
            out.writeFloat(obj.getDifficulty());
            out.writeBoolean(obj.isEngagedInActualBattle());
            out.writeBoolean(obj.isEngagedInHostilities());
            out.writeBoolean(obj.isOtherFleetHarriedPlayer());
            out.writeFloat(obj.getPlayerFPHullDamageToEnemies());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Random.class));
                out.write(serializer.serialize(obj.getSalvageRandom()));
            } catch (SerializerNotFoundException _x) {
            }
            FleetMemberAPI[] storyRecoverableShipsArray = obj.getStoryRecoverableShips().toArray(new FleetMemberAPI[ 0 ] );
            out.writeInt(storyRecoverableShipsArray.length);
            for (int i = 0; (i<storyRecoverableShipsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.fleet.FleetMemberAPI.class));
                    out.write(serializer.serialize(storyRecoverableShipsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.TextPanelAPI.class));
                out.write(serializer.serialize(obj.getTextPanelForXPGain()));
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

    public com.fs.starfarer.api.impl.campaign.FleetEncounterContext deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.impl.campaign.FleetEncounterContext obj = objenesis.newInstance(com.fs.starfarer.api.impl.campaign.FleetEncounterContext.class);
        return obj;
    }

}
