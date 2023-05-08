package com.example.hinovaoficinas.ui.activity.workshopDetail;

import static com.example.hinovaoficinas.utils.Constants.EXTRA_WORKSHOP;
import static com.example.hinovaoficinas.utils.Constants.REGEX_NEWLINE;
import static com.example.hinovaoficinas.utils.Constants.SUBSTITUTION_NEWLINE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hinovaoficinas.databinding.ActivityWorkshopDetailBinding;
import com.example.hinovaoficinas.utils.ImageManager;
import com.example.hinovaoficinas.R;
import com.example.hinovaoficinas.data.models.workshop.Workshop;

public class WorkShopDetailActivity extends AppCompatActivity {

    private ActivityWorkshopDetailBinding binding;
    private Workshop workshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkshopDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWorkshopFromIntent();
        setImageWorkshop();
        setViewValues();

    }

    private void getWorkshopFromIntent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_WORKSHOP)) {
            workshop = (Workshop) intent.getSerializableExtra(EXTRA_WORKSHOP);
        }
    }

    private void setImageWorkshop() {
        ImageManager imageManager = new ImageManager(this);
        imageManager.displayImage(workshop.Image, binding.imgWorkshop, workshop.Id);
    }

    private void setViewValues() {
        verifyTextIsEmpty(binding.txtName, null, workshop.Name);
        verifyTextIsEmpty(binding.txtDescription, null, getString(R.string.txt_description, workshop.Description));
        verifyTextIsEmpty(binding.txtEmail, binding.imgEmail, workshop.Email);
        verifyTextIsEmpty(binding.txtLocation, binding.imgLocation, workshop.Locale);
        verifyTextIsEmpty(binding.txtPhone1, binding.imgPhone1, workshop.Phone1);
        verifyTextIsEmpty(binding.txtPhone2, binding.imgPhone2, workshop.Phone2);
    }

    private void verifyTextIsEmpty(TextView textView, ImageView imageView, String text) {
        if ((text != null)) {
            setTextView(textView, text);
        } else {
            hideView(textView, imageView);
        }
    }

    private void hideView(TextView textView, ImageView imageView) {
        textView.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
    }

    private void setTextView(TextView textView, String text) {
        String textFormatted = text.replaceAll(REGEX_NEWLINE, SUBSTITUTION_NEWLINE);
        textView.setText(textFormatted);
    }

}