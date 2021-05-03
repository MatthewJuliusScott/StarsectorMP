
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.plugins.AutofitPlugin.AutofitOption;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CoreAutofitPluginSerializer {


    public byte[] serialize(com.fs.starfarer.api.plugins.impl.CoreAutofitPlugin obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            AutofitOption[] optionsArray = obj.getOptions().toArray(new AutofitOption[ 0 ] );
            out.writeInt(optionsArray.length);
            for (int i = 0; (i<optionsArray.length); i ++) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.util.Random.class));
                out.write(serializer.serialize(obj.getRandom()));
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
        return obj.getQuickActionText().writeObject();
        return obj.getQuickActionTooltip().writeObject();
    }

    public com.fs.starfarer.api.plugins.impl.CoreAutofitPlugin deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.plugins.impl.CoreAutofitPlugin obj = objenesis.newInstance(com.fs.starfarer.api.plugins.impl.CoreAutofitPlugin.class);
        return obj;
    }

}
