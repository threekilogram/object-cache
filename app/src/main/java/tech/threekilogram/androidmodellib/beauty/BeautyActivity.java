package tech.threekilogram.androidmodellib.beauty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import tech.threekilogram.androidmodellib.R;

/**
 * @author liujin
 */
public class BeautyActivity extends AppCompatActivity {

      private static final String TAG = BeautyActivity.class.getSimpleName();
      private RecyclerView mRecycler;

      public static void start (Context context) {

            Intent starter = new Intent(context, BeautyActivity.class);
            context.startActivity(starter);
      }

      @Override
      protected void onCreate (Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_beauty);
            initView();

            BeautyManager.getInstance().bind(this);
      }

      private void initView () {

            mRecycler = findViewById(R.id.recycler);
            mRecycler.setLayoutManager(new LinearLayoutManager(this));
            mRecycler.setAdapter(new BeautyRecyclerAdapter());
      }
}
