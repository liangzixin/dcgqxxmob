package com.xiangmu.lzx.conent_frament;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xiangmu.lzx.Bean.Collection;
import com.xiangmu.lzx.CostomAdapter.CollectionNews_adapter;
import com.xiangmu.lzx.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/12.
 */
public class Collection_news extends Fragment {
    private ListView listView;
    List<Collection> myDataList;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hgz_collection_news_fragment, container, false);
//        ininView(view);
        return view;
    }

    private void ininView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
        getData();
        CollectionNews_adapter adapter = new CollectionNews_adapter(context, myDataList);
        listView.setAdapter(adapter);
    }

    private void getData() {
        myDataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Collection myData = new Collection();
            myData.setName("xiaohuang" + i);
            myData.setNumber("150" + i);
            myDataList.add(myData);
        }
    }


}
