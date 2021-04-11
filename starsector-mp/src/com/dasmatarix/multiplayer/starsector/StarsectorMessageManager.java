
package com.dasmatarix.multiplayer.starsector;

import com.dasmatarix.multiplayer.MessageManager;
import com.dasmatarix.multiplayer.handler.ClientExampleObjectHandler;
import com.dasmatarix.multiplayer.handler.ServerExampleObjectHandler;
import com.dasmatarix.multiplayer.model.ExampleObject;
import com.dasmatarix.multiplayer.serializer.ExampleObjectSerializer;

/**
 * A factory for creating StarsectorSerializer objects.
 */
public class StarsectorMessageManager extends MessageManager {
	
	private StarsectorMessageManager() {
        throw new UnsupportedOperationException();
    }
	
	public static MessageManager createServerMessageManager() {
		MessageManager messageManager = new MessageManager();
		messageManager.registerMessage(new ExampleObjectSerializer(), new ServerExampleObjectHandler(), ExampleObject.class);
		return messageManager;
	}
	
	public static MessageManager createClientMessageManager() {
		MessageManager messageManager = new MessageManager();
		messageManager.registerMessage(new ExampleObjectSerializer(), new ClientExampleObjectHandler(), ExampleObject.class);
		return messageManager;
	}
}
