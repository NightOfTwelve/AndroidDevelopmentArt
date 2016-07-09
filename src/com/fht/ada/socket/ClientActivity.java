package com.fht.ada.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.fht.ada.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class ClientActivity extends Activity {
	private static final String TAG = "Socket";
	private static final int port = 44534;
	private TextView mTextView;
	private EditText mEditText;
	private BufferedReader mBufferedReader;
	private OutputStream mOutputStream;
	private ScrollView mScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_client_socket);
		Intent socketServicer = new Intent(this, SocketServicer.class);
		this.startService(socketServicer);// ����������
		mTextView = (TextView) this.findViewById(R.id.show_chat);
		mEditText = (EditText) this.findViewById(R.id.input_message);
		mScrollView = (ScrollView) this.findViewById(R.id.scroll_view);
	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onResume() {
		super.onResume();
		connectService();
		mEditText.setText(mIntputText[0]);
	}

	String[] mIntputText = new String[] { "��ϲ������һ������С6���Ů���������ϳ��У�����������", "�Ұ��ҼҵĹ������ˣ�������Ҳ�������ң�ƽʱ�е���ô�����ղŵ���ʱ����û���Ƶ�������˯����",
			"�������˸�Ů�ޣ��ǳ��ɰ������λ�ﰮŮ��������Ƶ����֣������ճ�", "����һ��Ů�ˣ��Ҿ��ܴ����һ�����壡", "���ֺ��˼���˼��" };
	int point = 0;

	public void sendMessage(View view) {
		final String inputText = mEditText.getText().toString();
		point++;
		point = point % mIntputText.length;
		mEditText.setText(mIntputText[point]);
		new Thread() {
			@Override
			public void run() {
				try {
					mOutputStream.write((inputText + "\n").getBytes());
					mOutputStream.flush();
					Log.i(TAG, "client:" + inputText);
					show("client:2016-6-10:\n    " + inputText + "\n");
					String replyText = mBufferedReader.readLine();
					Log.i(TAG, "server:" + replyText);
					show("server:2016-6-10:\n    " + replyText + "\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void connectService() {
		new Thread() {
			public void run() {
				// ������onStart()������ȥ���ӷ�����
				while (true) {
					try {
						Socket socket = new Socket("localhost", port);
						InputStream in = socket.getInputStream();
						InputStreamReader inputStreamReader = new InputStreamReader(in);
						mBufferedReader = new BufferedReader(inputStreamReader);
						mOutputStream = socket.getOutputStream();
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	public void show(final String s) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.i(TAG, mTextView == null ? "null" : "no null");
				mTextView.append(s);
				int offset = mTextView.getMeasuredHeight() - mScrollView.getHeight(); 
				mScrollView.scrollTo(0, offset);
			}
		});
	}
}
