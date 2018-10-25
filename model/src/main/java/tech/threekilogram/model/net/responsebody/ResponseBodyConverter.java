package tech.threekilogram.model.net.responsebody;

import okhttp3.ResponseBody;
import tech.threekilogram.model.net.NetConverter;

/**
 * 将{@link ResponseBody}转换为指定类型数据
 *
 * @param <V> 数据类型
 *
 * @author liujin
 */
public interface ResponseBodyConverter<V> extends NetConverter<V, ResponseBody> { }