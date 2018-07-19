package com.bone.github.cache.util;

import java.io.*;

/**
 * @author hejiwei
 */
public class SerialUtil {

    public static <V extends Serializable> byte[] writeObject(V object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("对象无法序列化");
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static <V> V readObject(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (V) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("对象反序列化失败");
        }
    }
}
