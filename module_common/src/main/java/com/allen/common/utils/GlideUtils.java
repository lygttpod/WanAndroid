package com.allen.common.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * <pre>
 *      @author : xiaoyao
 *      e-mail  : xiaoyao@51vest.com
 *      date    : 2018/02/05
 *      desc    : 图片加载处理管理类
 *      version : 1.0
 * </pre>
 */

public class GlideUtils {

    public static void loadImgWithRoundedCorners(Context context, String url, int roundedCorners, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transform(new RoundedCornersTransformation(DisplayUtils.dp2px(roundedCorners), 0));
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadImgWithRoundedCorners(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transform(new RoundedCornersTransformation(DisplayUtils.dp2px(5), 0));
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadImgWithCircle(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transform(new CircleCrop());
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadImg(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(url).apply(options).into(imageView);
    }
    public static void loadImgWithDefaultIcon(Context context, String url,int defaultIcon, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(context.getResources().getDrawable(defaultIcon))
                .placeholder(context.getResources().getDrawable(defaultIcon));
        Glide.with(context).load(url).apply(options).into(imageView);
    }
    public static void loadCircleImgWithDefaultIcon(Context context, String url,int defaultIcon, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(context.getResources().getDrawable(defaultIcon))
                .placeholder(context.getResources().getDrawable(defaultIcon))
                .transform(new CircleCrop());
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadImgWithTopRoundedCorners(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transform(new CropTransformation(0, 0, CropTransformation.CropType.TOP));
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadImgWithBottomRoundedCorners(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transform(new CropTransformation(0, 0, CropTransformation.CropType.BOTTOM));
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadImgCallbackDrawable(Context context, String url, SimpleTarget<Drawable> simpleTarget) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(url).apply(options).into(simpleTarget);
    }
}
