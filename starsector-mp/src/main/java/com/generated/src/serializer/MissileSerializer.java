
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.loading.specs.EngineSlot;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class MissileSerializer {


    public byte[] serialize(com.fs.starfarer.combat.entities.Missile obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.combat.ai.AI.class));
                out.write(serializer.serialize(obj.getAI()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getAngularVelocity());
            out.writeBoolean(obj.isArmedWhileFizzling());
            out.writeFloat(obj.getArmingTime());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.combat.CollisionClass.class));
                out.write(serializer.serialize(obj.getCollisionClass()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getCollisionRadius());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.combat.E.F.class));
                out.write(serializer.serialize(obj.getDamage()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getEccmChanceBonus());
            out.writeFloat(obj.getEccmChanceOverride());
            out.writeInt(obj.getEmpResistance());
            EngineSlot[] engineLocationsArray = obj.getEngineLocations().toArray(new EngineSlot[ 0 ] );
            out.writeInt(engineLocationsArray.length);
            for (int i = 0; (i<engineLocationsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.loading.specs.EngineSlot.class));
                    out.write(serializer.serialize(engineLocationsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            out.writeFloat(obj.getFacing());
            out.writeFloat(obj.getFlightTime());
            out.writeBoolean(obj.isForceAlwaysArmed());
            out.writeBoolean(obj.isFromMissile());
            out.writeBoolean(obj.isGhost());
            out.writeFloat(obj.getGlowRadius());
            out.writeFloat(obj.getHitpoints());
            out.writeFloat(obj.getImpact());
            out.writeFloat(obj.getMass());
            out.writeFloat(obj.getMaxHitpoints());
            out.writeBoolean(obj.isMine());
            out.writeFloat(obj.getMineExplosionRange());
            out.writeBoolean(obj.isMineHasNoSprite());
            out.writeBoolean(obj.isMinePrimed());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.combat.MissileAIPlugin.class));
                out.write(serializer.serialize(obj.getMissileAI()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isNoMineFFConcerns());
            out.writeInt(obj.getOwner());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.lang.Object.class));
                out.write(serializer.serialize(obj.getParamAboutToApplyDamage()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isRenderGlowAbove());
            out.writeFloat(obj.getTimeSinceFizzling());
            out.writeFloat(obj.getUntilMineExplosion());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.combat.entities.Missile deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.combat.entities.Missile obj = objenesis.newInstance(com.fs.starfarer.combat.entities.Missile.class);
        return obj;
    }

}
