package com.dayspass.datacenter.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具类
 * @author kouyi
 * @2016-12-05
 */
public class SerializableUtil {
	
	/**
	 * 对象序列化
	 * @param object
	 * @return
	 */
	public static byte[] serializable(Object object) {
		ObjectOutputStream outputsteam = null;
		ByteArrayOutputStream byteoutputstream = null;
		try {
			byteoutputstream = new ByteArrayOutputStream();
			outputsteam = new ObjectOutputStream(byteoutputstream);
			outputsteam.writeObject(object);
			byte[] bytes = byteoutputstream.toByteArray();
			return bytes;
		} catch (Exception e) {
			System.out.println("序列化对象失败,cause="+e.getMessage());
		}
		return null;
	}

	/**
	 * 对象反序列化
	 * @param bytes
	 * @return
	 */
	public static Object unserializable(byte[] bytes) {
		ByteArrayInputStream byteinputstram = null;
		try {
			// 反序列化
			byteinputstram = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(byteinputstram);
			return ois.readObject();
		} catch (Exception e) {
			System.out.println("反序列化对象失败,cause="+e.getMessage());
		}
		return null;
	}
}
