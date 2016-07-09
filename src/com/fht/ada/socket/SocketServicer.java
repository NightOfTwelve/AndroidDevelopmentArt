package com.fht.ada.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class SocketServicer extends android.app.Service {
	private static final String TAG = "Socket";
	private ServerSocket mServerSocket;
	private static final int port = 44534;
	private ExecutorService mExecutorServicePool;

	public SocketServicer() {
		try {
			mServerSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// new �̳߳�
		mExecutorServicePool = Executors.newFixedThreadPool(5);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "start SocketServicer");
		// ������������ʱ�� ���Ǿ�����һ���̣߳�������һ��Socket�ȴ�Զ�̵�����
		new Thread() {
			@Override
			public void run() {
				// �������whileѭ������Ϊ�˿����ö���ͻ���һ�����ӽ���
				while (true) {
					try {
						Log.i(TAG, "mServerSocket �����Ѿ����������ڵȴ�����");
						Socket socket = mServerSocket.accept();// �������е������ˣ���ȴ��ͻ��˵�����
						// ���ǰ�socket�Ž��̳߳��н��й���
						mExecutorServicePool.execute(new SocketHandler(socket));
						Log.i(TAG, "mServerSocket ��������һ��");
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}.start();
	}

	// SocketHandler��������ÿһ�����ӵ�socket
	class SocketHandler implements Runnable {
		private Socket mSocket;

		public SocketHandler(Socket socket) {
			mSocket = socket;
		}

		@Override
		public void run() {
			try {
				InputStream in = mSocket.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				OutputStream out = mSocket.getOutputStream();

				while (true) {
					String inputData = bufferedReader.readLine();
					String reply = mReplyText[point];
					SystemClock.sleep(1000);
					point++;
					point=point%mReplyText.length;
					out.write((reply + "\n").getBytes());
					out.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	int point = 0;
	String[] mReplyText = new String[] { "��ϲ������ȥ������������", "�����Ͼ����������ġ���", "�ɼ�˼��", "�ţ�����һͷĸ���������۾����µ���", "�з���" };

}
