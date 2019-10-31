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
        Uri resUri = null;
        if (getImageCache().has(mUri)) {
            resUri = getImageCache().get(mUri);
            if (resUri == null) {
                getImageCache().remove(mUri);
            }
        }

        if (resUri == null) {
            resUri = Uri.fromFile(new File(mUri));
            getImageCache().put(mUri, resUri);
        }

        if (resUri == null) {
            return;
        }

        try {
            Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(resUri));
            byte[] chunk = bitmap.getNinePatchChunk();
            if (NinePatch.isNinePatchChunk(chunk)) {
                NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(mContext.getResources(), bitmap, bitmap.getNinePatchChunk(), new Rect(), null);
                setBackgroundDrawable(ninePatchDrawable);
            } else {
                Log.e("NinePath", "----------> is false");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private RCTImageCache getImageCache()
    {
        return RCTImageCache.getInstance();
    }
}
