package com.fht.ada.view.gesture.detector;

import com.fht.ada.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnContextClickListener;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class GestureDetectorActivity extends Activity {
	private static final String TAG = "GestureDetector";
	private GestureDetector mGestureDetector;
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_gesture_detector);
		mGestureDetector = new GestureDetector(getApplicationContext(), new GestureDecectorListener());
		// ���������Ļ�����޷��϶�������
		mGestureDetector.setIsLongpressEnabled(false);
		mTextView = (TextView) this.findViewById(R.id.show_gesture_detector);
		mTextView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				boolean b = mGestureDetector.onTouchEvent(event);
				return true;
			}
		});
	}

	class GestureDecectorListener implements OnGestureListener, OnDoubleTapListener {
		int count = 0;
		@Override
		public boolean onDown(MotionEvent e) {
			// ��ָ������Ļ��ʱ��ز���һ��down�¼���Ҳ������ָ����
			Log.i(TAG, "onDown(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onDown(MotionEvent e)\n" + mid);
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// ���Ҳ����ָ�ոհ��µ�ʱ��Ҳ����ACTION_DOWN�����ģ���ǿ��������ָ����û��̧��򻬶���״̬
			Log.i(TAG, "onShowPress(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onShowPress(MotionEvent e)\n" + mid);
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// ��ָ���£�����̧������Ҳ��������˵�ĵ���
			Log.i(TAG, "onSingleTapUp(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onSingleTapUp(MotionEvent e)\n" + mid);
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			// ��ָ���£���������Ļ���滬����������һ���϶��¼�����һ��Action_DOWN �Ͷ�� Action_MOVE
			Log.i(TAG, "onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++)
					+ "   onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)\n" + mid);
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// ��ָ���£���������Ļ����ͣ��һ����ʱ�� Ҳ����������˵�ĳ����¼�
			Log.i(TAG, "onLongPress(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onLongPress(MotionEvent e)\n" + mid);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			// ����ָ����Ļ������ٵĻ���һ��
			Log.i(TAG, "onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)");
			String mid = mTextView.getText().toString();
			mTextView.setText(
					(count++) + "   onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)\n" + mid);
			return false;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// �ϸ�ĵ����¼�
			Log.i(TAG, "onSingleTapConfirmed(MotionEvent e) ");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onSingleTapConfirmed(MotionEvent e)\n" + mid + mid);
			return false;
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// ˫���¼� �����ܺ�onSingleTapConfirmed����
			Log.i(TAG, "onDoubleTap(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onDoubleTap(MotionEvent e)\n" + mid);
			return false;
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			// ˫���¼������û�˫�����¼��ACTION_DOWN,ACTION_MOVE,ACTION_UP����𴥷�
			Log.i(TAG, "onDoubleTapEvent(MotionEvent e)");
			String mid = mTextView.getText().toString();
			mTextView.setText((count++) + "   onDoubleTapEvent(MotionEvent e)\n" + mid);
			return false;
		}

	}
}
