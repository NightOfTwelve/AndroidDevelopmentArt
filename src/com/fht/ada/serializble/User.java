package com.fht.ada.serializble;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * �ص�:�����,���ܵ�,����Java �����:���ڴ洢�����紫��
 * 
 * @author Fht
 *
 */
public class User implements Serializable {
	public static final long serialVersionUID = 59852348023l;
	private String name = "fht";
	private int age = 26;
	transient private boolean sex = true;// ��������л�

	void write() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.o"));
		oos.writeObject(new User());
		oos.close();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.o"));
		Object o = ois.readObject();
		if (o instanceof User) {
			User user = (User) o;
		}
		ois.close();
	}
}
