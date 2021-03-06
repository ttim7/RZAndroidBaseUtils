package com.raizlabs.baseutils;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

public class ThreadingUtils {
	/**
	 * @return True if this function was called from the UI thread
	 */
	public static boolean isOnUIThread() {
		return Looper.getMainLooper().equals(Looper.myLooper());
	}
	
	private static Handler uiHandler;
	/**
	 * @return A {@link Handler} that is bound to the UI thread.
	 */
	public static Handler getUIHandler() {
		if (uiHandler == null) uiHandler = new Handler(Looper.getMainLooper());
		return uiHandler;
	}
	
	/**
	 * Runs the given {@link Runnable} on the UI thread. This will execute
	 * immediately, before this function returns, if this function was called
	 * on the UI thread. Otherwise, the {@link Runnable} will be posted to the
	 * UI thread.
	 * @see #runOnUIThread(Runnable, Handler)
	 * @see #runOnUIThread(Runnable, View)
	 * @param action The {@link Runnable} to execute on the UI thread.
	 * @return True if the action was already executed before this function
	 * returned, or false if the action was posted to be handled later.
	 */
	public static boolean runOnUIThread(Runnable action) {
		if (isOnUIThread()) {
			action.run();
			return true;
		} else {
			getUIHandler().post(action);
			return false;
		}
	}
	
	/**
	 * Runs the given {@link Runnable} on the UI thread. This will execute
	 * immediately, before this function returns, if this function was called
	 * on the UI thread. Otherwise, the {@link Runnable} will be posted using
	 * the given {@link View}.
	 * <br><br>
	 * WARNING: This action will never be executed if the view is not attached
	 * to a window. See {@link View#post(Runnable)}.
	 * @see #runOnUIThread(Runnable)
	 * @see #runOnUIThread(Runnable, Handler)
	 * @param action The {@link Runnable} to execute.
	 * @param v A {@link View} to use to post the {@link Runnable} if this
	 * wasn't called on the UI thread.
	 * @return True if the action was already executed before this function
	 * returned, or false if the action was posted.
	 */
	public static boolean runOnUIThread(View v, Runnable action) {
		if (isOnUIThread()) {
			action.run();
			return true;
		} else {
			v.post(action);
			return false;
		}
	}
	
	/**
	 * Runs the given {@link Runnable} on the UI thread. This will execute
	 * immediately, before this function returns, if this function was called
	 * on the UI thread. Otherwise, the {@link Runnable} will be posted using
	 * the given {@link View}.
	 * <br><br>
	 * WARNING: This action will never be executed if the view is not attached
	 * to a window. See {@link View#post(Runnable)}.
	 * @see #runOnUIThread(Runnable)
	 * @see #runOnUIThread(Runnable, Handler)
	 * @param v A {@link View} to use to post the {@link Runnable} if this
	 * wasn't called on the UI thread.
	 * @param action The {@link Runnable} to execute.
	 * @return True if the action was already executed before this function
	 * returned, or false if the action was posted.
	 */
	public static boolean runOnUIThread(Runnable action, View v) {
		if (isOnUIThread()) {
			action.run();
			return true;
		} else {
			v.post(action);
			return false;
		}
	}
	
	/**
	 * Runs the given {@link Runnable} immediately if this function is called
	 * on the UI thread. Otherwise, it is posted to the given {@link Handler}
	 * and executed on its bound thread. Though it is assumed that the given
	 * {@link Handler} is bound to the UI thread, it is not necessary, and it
	 * will execute the action either way.
	 * @param action The {@link Runnable} to execute.
	 * @param handler The {@link Handler} to post the action to if if this
	 * wasn't called on the UI thread.
	 * @return True if the action was already executed before this function
	 * returned, or false if the action was posted to the {@link Handler}.
	 */
	public static boolean runOnUIThread(Runnable action, Handler handler) {
		if (isOnUIThread()) {
			action.run();
			return true;
		} else {
			handler.post(action);
			return false;
		}
	}
	
	/**
	 * Runs the given {@link Runnable} immediately if this function is called
	 * on the UI thread. Otherwise, it is posted to the given {@link Handler}
	 * and executed on its bound thread. Though it is assumed that the given
	 * {@link Handler} is bound to the UI thread, it is not necessary, and it
	 * will execute the action either way.
	 * @param handler The {@link Handler} to post the action to if if this
	 * wasn't called on the UI thread.
	 * @param action The {@link Runnable} to execute.
	 * @return True if the action was already executed before this function
	 * returned, or false if the action was posted to the {@link Handler}.
	 */
	public static boolean runOnUIThread(Handler handler, Runnable action) {
		if (isOnUIThread()) {
			action.run();
			return true;
		} else {
			handler.post(action);
			return false;
		}
	}
}
