
package com.generated.src.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.dasmatarix.multiplayer.ISerializer;
import com.dasmatarix.multiplayer.MessageSerializer;
import com.dasmatarix.multiplayer.exception.SerializerNotFoundException;
import com.fs.starfarer.api.campaign.PlayerMarketTransaction.TransactionLineItem;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class PlayerMarketTransactionSerializer {


    public byte[] serialize(com.fs.starfarer.api.campaign.PlayerMarketTransaction obj)
        throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.CargoAPI.class));
                out.write(serializer.serialize(obj.getBought()));
            } catch (SerializerNotFoundException _x) {
            }
            out.writeFloat(obj.getCreditValue());
            TransactionLineItem[] lineItemsArray = obj.getLineItems().toArray(new TransactionLineItem[ 0 ] );
            out.writeInt(lineItemsArray.length);
            for (int i = 0; (i<lineItemsArray.length); i ++) {
            }
            com.fs.starfarer.api.campaign.PlayerMarketTransaction.ShipSaleInfo[] shipsBoughtArray = obj.getShipsBought().toArray(new com.fs.starfarer.api.campaign.PlayerMarketTransaction.ShipSaleInfo[ 0 ] );
            out.writeInt(shipsBoughtArray.length);
            for (int i = 0; (i<shipsBoughtArray.length); i ++) {
            }
            com.fs.starfarer.api.campaign.PlayerMarketTransaction.ShipSaleInfo[] shipsSoldArray = obj.getShipsSold().toArray(new com.fs.starfarer.api.campaign.PlayerMarketTransaction.ShipSaleInfo[ 0 ] );
            out.writeInt(shipsSoldArray.length);
            for (int i = 0; (i<shipsSoldArray.length); i ++) {
            }
            try {
                ISerializer serializer = MessageSerializer.getInstance().getSerializer((com.fs.starfarer.api.campaign.CargoAPI.class));
                out.write(serializer.serialize(obj.getSold()));
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
        return obj.getTradeMode().writeObject();
    }

    public com.fs.starfarer.api.campaign.PlayerMarketTransaction deserialize() {
        Objenesis objenesis = new ObjenesisStd();
        com.fs.starfarer.api.campaign.PlayerMarketTransaction obj = objenesis.newInstance(com.fs.starfarer.api.campaign.PlayerMarketTransaction.class);
        return obj;
    }

}
