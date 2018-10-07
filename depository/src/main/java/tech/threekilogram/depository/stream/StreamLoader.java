package tech.threekilogram.depository.stream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import tech.threekilogram.depository.cache.bitmap.BitmapConverter;
import tech.threekilogram.depository.cache.bitmap.BitmapConverter.ScaleMode;
import tech.threekilogram.depository.cache.json.ObjectLoader;
import tech.threekilogram.depository.file.converter.FileStringConverter;
import tech.threekilogram.depository.net.retrofit.converter.RetrofitBitmapConverter;
import tech.threekilogram.depository.net.retrofit.converter.RetrofitStringConverter;
import tech.threekilogram.depository.net.retrofit.down.Downer;
import tech.threekilogram.depository.net.retrofit.down.Downer.OnDownloadUpdateListener;
import tech.threekilogram.depository.net.retrofit.loader.RetrofitLoader;

/**
 * 从网络
 *
 * @author Liujin 2018-10-07:19:56
 */
public class StreamLoader {

      private static RetrofitLoader<String> sRetrofitStringLoader;
      private static RetrofitLoader<Bitmap> sRetrofitBitmapLoader;
      private static FileStringConverter    sFileStringConverter;

      private StreamLoader ( ) { }

      /**
       * 从网络加载string
       *
       * @param url url
       *
       * @return url 对应的 string
       */
      public static String loadStringFromNet ( String url ) {

            if( sRetrofitStringLoader == null ) {
                  sRetrofitStringLoader = new RetrofitLoader<>(
                      new RetrofitStringConverter()
                  );
            }
            return sRetrofitStringLoader.load( url );
      }

      /**
       * 保存一个string到文件
       *
       * @param data string 数据
       * @param file string 需要保存到的文件
       */
      public static void saveStringToFile ( String data, File file ) {

            if( sFileStringConverter == null ) {
                  sFileStringConverter = new FileStringConverter();
            }

            try {
                  FileOutputStream outputStream = new FileOutputStream( file );
                  sFileStringConverter.to( outputStream, data );
            } catch(FileNotFoundException e) {
                  e.printStackTrace();
            }
      }

      /**
       * 从文件加载string
       *
       * @param file string 文件
       *
       * @return file 对应 string
       */
      public static String loadStringFromFile ( File file ) {

            if( sFileStringConverter == null ) {
                  sFileStringConverter = new FileStringConverter();
            }

            if( file.exists() && file.isFile() ) {

                  try {
                        FileInputStream inputStream = new FileInputStream( file );
                        return sFileStringConverter.from( inputStream );
                  } catch(FileNotFoundException e) {
                        e.printStackTrace();
                  }
            }
            return null;
      }

      /**
       * 从网络加载json
       *
       * @param url url
       * @param beanClass json bean class
       *
       * @return url 对应的 json bean
       */
      public static <V> V loadJsonFromNet ( String url, Class<V> beanClass ) {

            return ObjectLoader.loadFromNet( url, beanClass );
      }

      /**
       * 保存一个json到文件
       *
       * @param beanClass json bean class
       * @param json string 数据
       * @param file string 需要保存到的文件
       */
      public static <V> void saveJsonToFile ( File file, Class<V> beanClass, V json ) {

            ObjectLoader.toFile( file, json, beanClass );
      }

      /**
       * 从网络加载json
       *
       * @param file json 对应 file
       * @param beanClass json bean class
       *
       * @return url 对应的 json bean
       */
      public static <V> V loadJsonFromFile ( File file, Class<V> beanClass ) {

            return ObjectLoader.loadFromFile( file, beanClass );
      }

      /**
       * 从网络下载url对应文件
       *
       * @param url url
       * @param file url保存的文件
       */
      public static void downLoad ( String url, File file ) {

            Downer.downloadTo( file, url );
      }

      /**
       * 从网络下载url对应文件
       *
       * @param url url
       * @param file url保存的文件
       * @param updateListener 下载进度监听
       */
      public static void downLoad (
          String url, File file, OnDownloadUpdateListener updateListener ) {

            Downer.downloadTo( file, url, updateListener, null, null );
      }

      /**
       * 从网络加载json
       *
       * @param url url
       *
       * @return url 对应的 json bean
       */
      public static Bitmap loadBitmapFromNet ( String url ) {

            if( sRetrofitBitmapLoader == null ) {
                  sRetrofitBitmapLoader = new RetrofitLoader<>( new RetrofitBitmapConverter() );
            }

            return sRetrofitBitmapLoader.load( url );
      }

      /**
       * 保存一个json到文件
       *
       * @param file string 需要保存到的文件
       */
      public static void saveBitmapToFile ( File file, Bitmap bitmap ) {

            try {
                  bitmap.compress( CompressFormat.PNG, 100, new FileOutputStream( file ) );
            } catch(FileNotFoundException e) {
                  e.printStackTrace();
            }
      }

      /**
       * 保存一个json到文件
       *
       * @param file string 需要保存到的文件
       */
      public static void saveBitmapToFile (
          File file, Bitmap bitmap, CompressFormat compressFormat, int quality ) {

            try {
                  bitmap.compress( compressFormat, quality, new FileOutputStream( file ) );
            } catch(FileNotFoundException e) {
                  e.printStackTrace();
            }
      }

      /**
       * 从网络加载json
       *
       * @param file json 对应 file
       *
       * @return url 对应的 json bean
       */
      public static Bitmap loadJsonFromFile ( File file ) {

            return BitmapFactory.decodeFile( file.getAbsolutePath() );
      }

      /**
       * 从网络加载json
       *
       * @param file json 对应 file
       *
       * @return url 对应的 json bean
       */
      public static Bitmap loadJsonFromFile (
          File file, int width, int height, @ScaleMode int scaleMode, Config config ) {

            BitmapConverter converter = new BitmapConverter();
            converter.configBitmap( width, height, scaleMode, config );
            return loadJsonFromFile( file, converter );
      }

      /**
       * 从网络加载json
       *
       * @param file json 对应 file
       *
       * @return url 对应的 json bean
       */
      public static Bitmap loadJsonFromFile ( File file, int width, int height ) {

            BitmapConverter converter = new BitmapConverter();
            converter.configBitmap( width, height );
            return loadJsonFromFile( file, converter );
      }

      /**
       * 从网络加载json
       *
       * @param file json 对应 file
       *
       * @return url 对应的 json bean
       */
      public static Bitmap loadJsonFromFile ( File file, BitmapConverter bitmapConverter ) {

            return bitmapConverter.from( file );
      }
}