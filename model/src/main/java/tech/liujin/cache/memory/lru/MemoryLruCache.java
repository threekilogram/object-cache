package tech.liujin.cache.memory.lru;

import android.support.v4.util.LruCache;
import tech.liujin.cache.memory.Memory;
import tech.liujin.cache.memory.lru.size.ObjectSize;
import tech.liujin.cache.memory.lru.size.SimpleObjectSize;

/**
 * 使用{@link LruCache}在内存中保存数据
 *
 * @author: Liujin
 * @version: V1.0
 * @date: 2018-07-30
 * @time: 8:26
 */
@SuppressWarnings("WeakerAccess")
public class MemoryLruCache<K, V> implements Memory<K, V> {

      /**
       * 容器
       */
      private ConstructLruCache mContainer;

      /**
       * 创建一个默认50条数据的缓存
       */
      public MemoryLruCache ( ) {

            this( 50, new SimpleObjectSize<V>() );
      }

      /**
       * 创建一个指定最大数量的缓存
       */
      public MemoryLruCache ( int maxSize ) {

            this( maxSize, new SimpleObjectSize<V>() );
      }

      /**
       * 创建一个指定最大数量,指定每个数据大小如何计算的缓存
       *
       * @param maxSize 最大容量
       * @param valueSize 每个数据大小计算
       */
      public MemoryLruCache (
          int maxSize,
          ObjectSize<V> valueSize ) {

            mContainer = new ConstructLruCache( maxSize, valueSize );
      }

      @Override
      public void clear ( ) {

            mContainer.evictAll();
      }

      @Override
      public int size ( ) {

            return mContainer.size();
      }

      @Override
      public V save ( K key, V v ) {

            return mContainer.put( key, v );
      }

      @Override
      public V load ( K key ) {

            return mContainer.get( key );
      }

      @Override
      public V remove ( K key ) {

            return mContainer.remove( key );
      }

      @Override
      public boolean containsOf ( K key ) {

            return mContainer.get( key ) != null;
      }

      public void resize ( int maxSize ) {

            mContainer.resize( maxSize );
      }

      /**
       * 使用{@link ObjectSize}构造一个{@link LruCache}
       */
      private class ConstructLruCache extends LruCache<K, V> {

            private ObjectSize<V> mValueSize;

            /**
             * @param maxSize for caches that do not override {@link #sizeOf}, this is the
             *     maximum
             *     number of entries in the cache. For all other caches, this is the maximum sum of
             *     the
             *     sizes of the entries in this cache.
             */
            public ConstructLruCache ( int maxSize, ObjectSize<V> valueSize ) {

                  super( maxSize );
                  mValueSize = valueSize;
            }

            @Override
            protected int sizeOf ( K key, V value ) {

                  return mValueSize.sizeOf( value );
            }
      }
}
