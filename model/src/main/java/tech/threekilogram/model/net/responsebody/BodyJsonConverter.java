package tech.threekilogram.model.net.responsebody;

import okhttp3.ResponseBody;
import tech.threekilogram.model.json.GsonConverter;
import tech.threekilogram.model.json.JsonConverter;

/**
 * 将网络响应转换为json bean对象
 *
 * @author liujin
 */
public class BodyJsonConverter<V> implements ResponseBodyConverter<V> {

      @SuppressWarnings("WeakerAccess")
      protected JsonConverter<V> mConverter;

      public BodyJsonConverter ( Class<V> valueType ) {

            mConverter = new GsonConverter<>( valueType );
      }

      public BodyJsonConverter ( JsonConverter<V> converter ) {

            mConverter = converter;
      }

      @Override
      public V onExecuteSuccess (
          String url, ResponseBody response )
          throws Exception {

            return mConverter.from( response.byteStream() );
      }
}