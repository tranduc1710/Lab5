package com.minh.lab5.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        binding.toolbar.setTitle("jonh Musk");
        binding.toolbar.setSubtitle("Last active");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        GetData();
    }

    private void GetData(){
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
