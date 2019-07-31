package com.minh.lab5.view;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.minh.lab5.R;
import com.minh.lab5.databinding.ActivityMainBinding;
import com.minh.lab5.model.data.Content;
import com.minh.lab5.model.data.Lab5;
import com.minh.lab5.model.server.APIuntil;
import com.minh.lab5.view.adapter.AdapterIconChat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private AdapterIconChat adapterIconChat;
    private List<Lab5> lab5s;
    private int mPageNumer = 1;
    private int mTotalItem = 10;

    private boolean isLoading = true;
    private int visibleitem, visibleItemCount, totalItem, pre_item ;
    private int view_the = 10;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        binding.toolbar.setTitle("jonh Musk");
        binding.toolbar.setSubtitle("Last active");
        layoutManager = new LinearLayoutManager(this);

        binding.recyclerView.setLayoutManager(layoutManager);
        ((LinearLayoutManager)binding.recyclerView.getLayoutManager()).setStackFromEnd(true);
        binding.recyclerView.setHasFixedSize(true);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                visibleItemCount = layoutManager.getChildCount();
                totalItem = layoutManager.getItemCount();
                visibleitem = ((LinearLayoutManager) binding.recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (dy > 0) {
                    if (isLoading) {
                        if (totalItem > pre_item) {
                            isLoading = false;
                            pre_item = totalItem;
                        }
                    }
                    if (!isLoading && (totalItem - visibleItemCount) <= (visibleitem + view_the)) {
                        mPageNumer++;
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
        GetData();


    }
    private void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.progressBar.setVisibility(View.VISIBLE);
                APIuntil.getData().getLab("5", "1").enqueue(new Callback<List<Lab5>>() {
                    @Override
                    public void onResponse(Call<List<Lab5>> call, Response<List<Lab5>> response) {
                       List<Lab5> lab5 = response.body();

                        adapterIconChat.AddItem(lab5);
                        binding.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<List<Lab5>> call, Throwable t) {
                        Log.d("////", t.getMessage());
                    }
                });
            }
        }, 1500);

    }

    private void GetData(){
        binding.progressBar.setVisibility(View.VISIBLE);
        APIuntil.getData().getLab("5", "1").enqueue(new Callback<List<Lab5>>() {
            @Override
            public void onResponse(Call<List<Lab5>> call, Response<List<Lab5>> response) {
                lab5s = response.body();

                adapterIconChat = new AdapterIconChat(lab5s, MainActivity.this);
                binding.recyclerView.setAdapter(adapterIconChat);
            }

            @Override
            public void onFailure(Call<List<Lab5>> call, Throwable t) {
                Log.d("////", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
