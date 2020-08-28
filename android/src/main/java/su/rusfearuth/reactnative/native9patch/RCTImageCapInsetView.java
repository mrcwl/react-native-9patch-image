package su.rusfearuth.reactnative.native9patch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

import su.rusfearuth.reactnative.native9patch.RCTImageCache;


public class RCTImageCapInsetView extends ImageView {
    private String mUri;
    private Context mContext;

    public RCTImageCapInsetView(Context context) {
        super(context);
        this.mContext = context;
    }

    public void setSource(String uri) {
        mUri = uri;
        reload();
    }

    public void reload() {

        Integer resId = null;

        resId = getResourceDrawableId(mUri);

        setBackgroundResource(resId);

    }

    private Integer getResourceDrawableId(final String aName) {
        if (aName == null || aName.isEmpty()) {
            return 0;
        }

        final String name = aName.toLowerCase().replace("-", "_");

        return getResources().getIdentifier(
                name,
                "drawable",
                getContext().getPackageName()
        );
    }

    private RCTImageCache getImageCache()
    {
        return RCTImageCache.getInstance();
    }
}
