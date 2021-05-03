
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CargoStackViewSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.ui.trade.CargoStackView obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isClipping());
            out.writeBoolean(obj.isEnabled());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.graphics.util.Fader.class));
                out.write(serializer.serialize(obj.getFader()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeBoolean(obj.isHighlightOnMouseOver());
            out.writeFloat(obj.getOpacity());
            out.writeBoolean(obj.isShowRedBackground());
            out.flush();
            return bos.toByteArray();
        } finally {
            try {
                bos.close();
            } catch (IOException _x) {
            }
        }
    }

    public com.fs.starfarer.campaign.ui.trade.CargoStackView deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.ui.trade.CargoStackView obj = objenesis.newInstance(com.fs.starfarer.campaign.ui.trade.CargoStackView.class);
        return obj;
    }

}
