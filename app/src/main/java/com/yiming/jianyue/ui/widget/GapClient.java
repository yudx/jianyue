package com.yiming.jianyue.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Provides a hook for calling "alert" from javascript. Useful for
 * debugging your javascript.
 * 给 javascript 提供 原生的弹窗
 */
public class GapClient extends WebChromeClient {

    private Context ctx;
    
    /**
     * Constructor.
     * 
     * @param ctx
     */
    public GapClient(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Tell the client to display a javascript alert dialog.
     * 
     * @param view
     * @param url
     * @param message
     * @param result
     */
    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this.ctx);
        dlg.setMessage(message);
        dlg.setTitle("提示");
        dlg.setCancelable(false);
        dlg.setPositiveButton(android.R.string.ok,
        	new AlertDialog.OnClickListener() {
            	public void onClick(DialogInterface dialog, int which) {
            		result.confirm();
            	}
        	});
        dlg.create();
        try {
        	dlg.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return true;
    }       

    /**
     * Tell the client to display a confirm dialog to the user.
     * 
     * @param view
     * @param url
     * @param message
     * @param result
     */
    @Override
    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this.ctx);
        dlg.setMessage(message);
        dlg.setTitle("确认");
        dlg.setCancelable(false);
        dlg.setPositiveButton(android.R.string.ok, 
        	new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int which) {
            		result.confirm();
                }
            });
        dlg.setNegativeButton(android.R.string.cancel, 
        	new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int which) {
            		result.cancel();
                }
            });
        dlg.create();
        dlg.show();
        return true;
    }
}