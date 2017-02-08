package com.xiangmu.lzx.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.litao.android.lib.Utils.GridSpacingItemDecoration;
import com.litao.android.lib.entity.PhotoEntry;
import com.xiangmu.lzx.CostomAdapter.ChooseAdapter;
import com.xiangmu.lzx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainPotoActivity extends AppCompatActivity implements ChooseAdapter.OnItmeClickListener {

    private RecyclerView mRecyclerView;

    private ChooseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpoto);

        EventBus.getDefault().register(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ChooseAdapter(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(5, 2, true));

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemClicked(int position) {
        if (position == mAdapter.getItemCount()-1) {
            startActivity(new Intent(MainPotoActivity.this, PhotosActivity.class));
            EventBus.getDefault().postSticky(new EventEntry(mAdapter.getData(),EventEntry.SELECTED_PHOTOS_ID));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entries){
        if (entries.id == EventEntry.RECEIVED_PHOTOS_ID) {
            mAdapter.reloadList(entries.photos);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photoMessageEvent(PhotoEntry entry){
        mAdapter.appendPhoto(entry);
    }

}
