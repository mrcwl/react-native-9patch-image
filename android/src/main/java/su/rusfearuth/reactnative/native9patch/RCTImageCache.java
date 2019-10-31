package su.rusfearuth.reactnative.native9patch;

import android.graphics.Bitmap;
import android.net.Uri;

import java.lang.ref.WeakReference;
import java.util.HashMap;


public class RCTImageCache {
    private volatile static RCTImageCache sInstance = null;
    private HashMap<String, Uri> mCache;

    public static RCTImageCache getInstance() {
        if (sInstance == null) {
            synchronized (RCTImageCache.class) {
                if (sInstance == null) {
                    sInstance = new RCTImageCache();
                }
            }
        }

        return sInstance;
    }

    private RCTImageCache() {
        mCache = new HashMap<>();
    }

    public void put(final String key,
                    Uri uri) {
        mCache.put(key, uri);
    }

    public Uri get(final String key) {
        return mCache.get(key);
    }

    public boolean has(final String key) {
        return mCache.containsKey(key) && get(key) != null;
    }

    public void remove(final String key) {
        if (!mCache.containsKey(key)) {
            return;
        }

        mCache.remove(key);
    }
}
