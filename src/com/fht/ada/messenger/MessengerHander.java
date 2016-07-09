package com.fht.ada.messenger;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerHander extends Handler {
	private static final String TAG = "Messenger";

	public void handleMessage(android.os.Message msg) {
		Messenger messenger = msg.replyTo;
		switch (msg.what) {
		case 0:// �յ��˿ͻ��˷���������Ϣ��Ȼ��ظ��ͻ���
			Log.i(TAG, "################service####################");
			Log.i(TAG, "client-->service : " + msg.getData().getString("msg"));
			Message replyMesage = Message.obtain(null, 1);
			Bundle data = new Bundle();
			data.putString("msg", "���յ��������Ϣ���һᾡ�촦���㷢�͵���Ϣ ");
			replyMesage.setData(data);
			try {
				messenger.send(replyMesage);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			Log.i(TAG, "################service####################");
			break;
		case 1:// ����ͻ��˻ظ�����Ϣ
			Log.i(TAG, "################client####################");
			Log.i(TAG, "service-->client : " + msg.getData().getString("msg"));
			Log.i(TAG, "################client####################");
			String strMsg = msg.getData().getString("msg");
			String mid = ClientActivity.mTextView.getText().toString();
			String str = "###################\n service-->client: \n" + strMsg + "\n###################";
			ClientActivity.mTextView.setText(str + "\n" + mid);
			break;
		}
	};

}
