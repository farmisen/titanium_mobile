/**
 * 
 */
package org.appcelerator.titanium.proxy;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;
import org.appcelerator.titanium.view.TiUIActivityWindow;
import org.appcelerator.titanium.view.TiUIView;

import ti.modules.titanium.android.AndroidModule;

@Kroll.proxy(creatableInModule=AndroidModule.class)
public class TiActivityWindowProxy extends TiWindowProxy 
{
	private static final String LCAT = "TiActivityWindowProxy";
	private static final boolean DBG = TiConfig.LOGD;
	
	public TiActivityWindowProxy(TiContext tiContext) 
	{
		super(tiContext);
		
		// force to true since the window is actually opened from TiUIActivityWindow
		// TODO make this lifecycle less weird
		opened = true;
	}

	public void setView(TiUIView view) {
		this.view = view;
	}
	
	@Override
	protected void handleClose(KrollDict options) {
		if (DBG) {
			Log.d(LCAT, "handleClose");
		}
		fireEvent("close", null);
		
		if (view != null) {
			((TiUIActivityWindow)view).close();
		}
		
		releaseViews();
		opened = false;
	}

	@Override
	protected void handleOpen(KrollDict options) {
	}
}
