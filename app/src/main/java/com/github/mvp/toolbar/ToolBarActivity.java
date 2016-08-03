package com.github.mvp.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolBarActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        ButterKnife.bind(this);

        initToolBar();
    }

    private void initToolBar() {
        toolBar.setTitle("ToolBar Title");
        toolBar.setTitleTextColor(getResources().getColor(R.color.account_main_text));

        toolBar.setSubtitle("Sub Title");
        toolBar.setSubtitleTextColor(getResources().getColor(R.color.account_sub_text));

        toolBar.setLogo(R.drawable.main_ic_home_selected);
        toolBar.setLogoDescription("Logo Description");

        toolBar.setNavigationIcon(R.drawable.ic_home_back);
        toolBar.setNavigationContentDescription("Navi Description");

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

                if(!msg.equals("")) {
                    Toast.makeText(ToolBarActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if(!msg.equals("")) {
            Toast.makeText(ToolBarActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
