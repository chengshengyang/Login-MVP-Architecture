package com.github.mvp.toolbar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolBarActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.collapsing_tool_bar)
    CollapsingToolbarLayout mCollapsingToolBar;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        ButterKnife.bind(this);
        context = this;

        initToolBar();

        initRecyclerView();
    }

    private void initToolBar() {
        toolBar.setTitle("ToolBar Title");
        toolBar.setTitleTextColor(getResources().getColor(R.color.account_main_text));

//        toolBar.setSubtitle("Sub Title");
//        toolBar.setSubtitleTextColor(getResources().getColor(R.color.account_sub_text));
//
//        toolBar.setLogo(R.drawable.main_ic_home_selected);
//        toolBar.setLogoDescription("Logo Description");

//        toolBar.setNavigationIcon(R.drawable.ic_home_back);
//        toolBar.setNavigationContentDescription("Navi Description");

        setSupportActionBar(toolBar);

        // 必须在setSupportActionBar()之后调用才生效，会拦截onOptionsItemSelected
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolBarActivity.this, "click navigation icon", Toast.LENGTH_SHORT).show();
            }
        });

        // 必须在setSupportActionBar()之后调用才生效，会拦截onOptionsItemSelected
        // toolBar.inflateMenu(R.menu.menu_main);--没发现什么用，并不能像这个方法名字一样加载menu布局
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        msg += "Click edit";
                        break;
                    case R.id.action_share:
                        msg += "Click share";
                        break;
                    case R.id.action_settings:
                        msg += "Click setting";
                        break;
//                    完全不会生效的
//                    case android.R.id.home:
//                        msg += "Click back";
//                        break;
                }

                if (!msg.equals("")) {
                    Toast.makeText(ToolBarActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.action_edit:
                msg += "Click edit--111";
                break;
            case R.id.action_share:
                msg += "Click share--111";
                break;
            case R.id.action_settings:
                msg += "Click setting--111";
                break;
            case android.R.id.home:
                msg += "Click back--111";
                break;
        }

        if (!msg.equals("")) {
            Toast.makeText(ToolBarActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    String data[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","1","2","3","4","5","6","7","8","9","10","11","12","13"};

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new MyAdapter());
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView.setText(data[position]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);

                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }

}
