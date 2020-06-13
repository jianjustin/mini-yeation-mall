package org.mini.yeation.mall.fragment.area_list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import org.mini.yeation.mall.Constants;
import org.mini.yeation.mall.adapter.recyclerview.BaseRecyclerAdapter;
import org.mini.yeation.mall.adapter.recyclerview.LinearDividerItemDecoration;
import org.mini.yeation.mall.adapter.recyclerview.ViewHolder;
import org.mini.yeation.mall.domain.Area;
import org.mini.yeation.mall.fragment.base.BaseFragment;
import org.mini.yeation.mall.utils.Event;
import org.mini.yeation.mall.utils.app.DPUtils;

import org.mini.yeation.mall.R;
import org.mini.yeation.mall.presenter.base.IPresenter;

import org.greenrobot.eventbus.EventBus;
import org.mini.yeation.mall.utils.JsonUtils;

import java.util.List;

import butterknife.BindView;

public class AreaListFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    BaseRecyclerAdapter<Area> mAdapter;

    private Area area; //当前选择区域

    public static AreaListFragment newInstance(List<Area> areaList) {
        return newInstance(areaList, null);
    }

    public static AreaListFragment newInstance(List<Area> areaList, Area area) {
        AreaListFragment fragment = new AreaListFragment();
        Bundle args = new Bundle();
        args.putString(Constants.INTENT_KEY1, JsonUtils.toString(areaList));
        if (area != null) {
            args.putString(Constants.INTENT_KEY2, JsonUtils.toString(area));
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_area_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mAdapter = new BaseRecyclerAdapter<Area>(R.layout.item_area) {
            @Override
            protected void convert(ViewHolder viewHolder, Area item, int position) {
                ViewGroup viewGroup = viewHolder.findViewById(R.id.root_layout);
                TextView name = viewHolder.findViewById(R.id.name);
                name.setText(item.getName());
                if (area != null && area.getId() == item.getId()) {
                    viewGroup.setBackgroundResource(R.color.colorOrange);
                } else {
                    viewGroup.setBackgroundResource(R.color.colorWhite);
                }
            }

            @Override
            protected void onItemClick(Area item, int position) {
                area = item;
                Event.ClickAreaEvent event = new Event.ClickAreaEvent();
                event.area = item;
                EventBus.getDefault().post(event);
            }
        };
        mRecyclerView.addItemDecoration(new LinearDividerItemDecoration(LinearDividerItemDecoration.VERTICAL, DPUtils.dp2px(getResources(), 1)));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        assert getArguments() != null;
        String text1 = getArguments().getString(Constants.INTENT_KEY1);
        List<Area>  areaList = JsonUtils.toList(text1, Area.class);
        if (getArguments().containsKey(Constants.INTENT_KEY2)) {
            String key2 = getArguments().getString(Constants.INTENT_KEY2);
            area = JsonUtils.toObject(key2, Area.class);
        }
        mAdapter.addAll(areaList);
    }

    @Override
    public IPresenter createPresenter() {
        return null;
    }

    public Area getArea() {
        return area;
    }
}
