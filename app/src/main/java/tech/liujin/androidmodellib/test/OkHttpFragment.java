package tech.liujin.androidmodellib.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.threekilogram.objectbus.Threads;
import tech.liujin.cache.converter.GsonConverter;
import tech.liujin.cache.converter.StringConverter;
import tech.liujin.cache.net.BaseNetLoader;
import tech.liujin.cache.net.OkHttpLoader;
import tech.liujin.androidmodellib.R;
import tech.liujin.androidmodellib.bean.GankCategoryBean;

/**
 * @author Liujin 2018-10-25:17:26
 */
public class OkHttpFragment extends Fragment implements OnClickListener {

      private Button   mGetString;
      private Button   mGetJson;
      private TextView mTextView2;

      private BaseNetLoader<String>           mStringNetLoader;
      private BaseNetLoader<GankCategoryBean> mJsonNetLoader;

      public static OkHttpFragment newInstance ( ) {

            Bundle args = new Bundle();

            OkHttpFragment fragment = new OkHttpFragment();
            fragment.setArguments( args );
            return fragment;
      }

      @Nullable
      @Override
      public View onCreateView (
          @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
          @Nullable Bundle savedInstanceState ) {

            return inflater.inflate( R.layout.fragment_net, container, false );
      }

      private void initView ( @NonNull final View itemView ) {

            mGetString = itemView.findViewById( R.id.getString );
            mGetString.setOnClickListener( this );
            mGetJson = itemView.findViewById( R.id.getJson );
            mGetJson.setOnClickListener( this );
            mTextView2 = itemView.findViewById( R.id.textView2 );
      }

      @Override
      public void onViewCreated ( @NonNull View view, @Nullable Bundle savedInstanceState ) {

            super.onViewCreated( view, savedInstanceState );
            initView( view );

            mStringNetLoader = new OkHttpLoader<>( new StringConverter() );
            mJsonNetLoader = new OkHttpLoader<>( new GsonConverter<>( GankCategoryBean.class ) );
      }

      @Override
      public void onClick ( View v ) {

            final String url = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/2/1";
            switch( v.getId() ) {
                  case R.id.getString:
                        Threads.COMPUTATION.execute( ( ) -> {

                              String load = mStringNetLoader.load( url );
                              mTextView2.post( ( ) -> setText( load ) );
                        } );
                        break;
                  case R.id.getJson:
                        Threads.COMPUTATION.execute( ( ) -> {

                              GankCategoryBean load = mJsonNetLoader.load( url );
                              mTextView2.post( ( ) -> setText( load.toString() ) );
                        } );
                        break;
                  default:
                        break;
            }
      }

      private void setText ( String text ) {

            mTextView2.setText( text );
      }
}
