
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class CargoDataGridViewSerializer {


    public byte[] serialize(com.fs.starfarer.campaign.ui.trade.CargoDataGridView obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeBoolean(obj.isClipping());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((java.awt.Color.class));
                out.write(serializer.serialize(obj.getColor()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getCols());
            out.writeBoolean(obj.isEnabled());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.graphics.util.Fader.class));
                out.write(serializer.serialize(obj.getFader()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getOpacity());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.ui.oO00OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.class));
                out.write(serializer.serialize(obj.getParent()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeInt(obj.getRows());
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.ui.U.class));
                out.write(serializer.serialize(obj.getTooltipLogic()));
            } catch (SerializerNotFoundException _x) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.ui.U.class));
                out.write(serializer.serialize(obj.getTooltipLogicResetDelegate()));
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

    public com.fs.starfarer.campaign.ui.trade.CargoDataGridView deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.campaign.ui.trade.CargoDataGridView obj = objenesis.newInstance(com.fs.starfarer.campaign.ui.trade.CargoDataGridView.class);
        return obj;
    }

}
