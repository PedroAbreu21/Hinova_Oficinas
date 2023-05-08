package com.example.hinovaoficinas.ui.activity.workshop;

import static com.example.hinovaoficinas.utils.Constants.EXTRA_WORKSHOP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hinovaoficinas.databinding.ActivityWorkshopBinding;
import com.example.hinovaoficinas.network.workshopApi.ApiService;
import com.example.hinovaoficinas.network.workshopApi.ApiServiceIonImpl;
import com.example.hinovaoficinas.data.models.workshop.WorkshopDTO;
import com.example.hinovaoficinas.network.workshopApi.callback.ApiWorkshopCallback;
import com.example.hinovaoficinas.ui.activity.workshopDetail.WorkShopDetailActivity;

public class WorkShopActivity extends AppCompatActivity implements ApiWorkshopCallback {

    private ActivityWorkshopBinding binding;
    private ApiService ApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkshopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeDependencies();
        loadWorkShops();
    }

    private void initializeDependencies() {
        ApiService = new ApiServiceIonImpl(getApplicationContext());
    }

    private void loadWorkShops() {
        ApiService.getWorkShopsApi(this);
    }

    private void showWorkShops(WorkshopDTO workShops) {
        WorkShopAdapter workShopAdapter = new WorkShopAdapter(workShops.Workshop, this);
        workShopAdapter.setOnItemClickListener(workshop -> {
            Intent intent = new Intent(WorkShopActivity.this, WorkShopDetailActivity.class);
            intent.putExtra(EXTRA_WORKSHOP, workshop);
            startActivity(intent);
        });
        binding.rvWorkshops.setAdapter(workShopAdapter);
    }

    @Override
    public void onWorkShopSuccess(WorkshopDTO workShops) {
        showWorkShops(workShops);
    }

    @Override
    public void onApiError(Exception e) {
        // Exibe uma mensagem de erro na tela
    }

}