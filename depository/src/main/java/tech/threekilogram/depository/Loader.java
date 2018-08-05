package tech.threekilogram.depository;

/**
 * 这是这个数据层框架的总接口,所有内存缓存,文件缓存,网络缓存搜需要实现该接口,
 * 该框架可以使用任意类型的key{@code K}保存读取任意类型的数据{@code V}
 * <p>
 * this interface is used for support build a data depository, every loader should implements this
 *
 * @param <K> k use this type key to save/load a value
 * @param <V> type of value to save/load
 *
 * @author liujin
 */
public interface Loader<K, V> {

      /**
       * load a value by this key from memory / file / database / net
       *
       * @param key load a value with this key
       *
       * @return value to this key
       */
      V load (K key);
}
