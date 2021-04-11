package com.dasmatarix.multiplayer.serializer;

import java.io.IOException;

public interface ISerializer<T> {
	
	public byte[] serialize(T t) throws IOException;
	
	public T deserialize(byte[] bytes) throws IOException;

}
