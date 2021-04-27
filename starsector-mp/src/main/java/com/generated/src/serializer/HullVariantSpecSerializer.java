
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.loading.WeaponGroupSpec;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class HullVariantSpecSerializer {


    public byte[] serialize(com.fs.starfarer.loading.specs.HullVariantSpec obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isGoalVariant());
            java.lang.String[] hullModsArray = obj.getHullMods().toArray(new java.lang.String[ 0 ] );
            out.writeInt(hullModsArray.length);
            for (int i = 0; (i<hullModsArray.length); i ++) {
                out.writeUTF(hullModsArray[i]);
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.loading.specs.O00oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.class));
                out.write(serializer.serialize(obj.getHullSpec()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getHullVariantId());
            out.writeBoolean(obj.isMayAutoAssignWeapons());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Map.class));
                out.write(serializer.serialize(obj.getModuleVariants()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getNumFluxCapacitors());
            out.writeInt(obj.getNumFluxVents());
            out.writeUTF(obj.getOriginalVariant());
            java.lang.String[] permaModsArray = obj.getPermaMods().toArray(new java.lang.String[ 0 ] );
            out.writeInt(permaModsArray.length);
            for (int i = 0; (i<permaModsArray.length); i ++) {
                out.writeUTF(permaModsArray[i]);
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.loading.VariantSource.class));
                out.write(serializer.serialize(obj.getSource()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeUTF(obj.getSourceDetail());
            java.lang.String[] suppressedModsArray = obj.getSuppressedMods().toArray(new java.lang.String[ 0 ] );
            out.writeInt(suppressedModsArray.length);
            for (int i = 0; (i<suppressedModsArray.length); i ++) {
                out.writeUTF(suppressedModsArray[i]);
            }
            java.lang.String[] tagsArray = obj.getTags().toArray(new java.lang.String[ 0 ] );
            out.writeInt(tagsArray.length);
            for (int i = 0; (i<tagsArray.length); i ++) {
                out.writeUTF(tagsArray[i]);
            }
            WeaponGroupSpec[] weaponGroupsArray = obj.getWeaponGroups().toArray(new WeaponGroupSpec[ 0 ] );
            out.writeInt(weaponGroupsArray.length);
            for (int i = 0; (i<weaponGroupsArray.length); i ++) {
                try {
                    ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.loading.WeaponGroupSpec.class));
                    out.write(serializer.serialize(weaponGroupsArray[i]));
                } catch (SerializerNotFoundException _x) {
                }
            }
            java.lang.String[] wingsArray = obj.getWings().toArray(new java.lang.String[ 0 ] );
            out.writeInt(wingsArray.length);
            for (int i = 0; (i<wingsArray.length); i ++) {
                out.writeUTF(wingsArray[i]);
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

    public com.fs.starfarer.loading.specs.HullVariantSpec deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.loading.specs.HullVariantSpec obj = objenesis.newInstance(com.fs.starfarer.loading.specs.HullVariantSpec.class);
        return obj;
    }

}
