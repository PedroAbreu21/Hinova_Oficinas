package com.example.hinovaoficinas.ui.activity.workshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hinovaoficinas.utils.ImageManager;
import com.example.hinovaoficinas.data.models.workshop.Workshop;
import com.example.hinovaoficinas.databinding.WorkshopItemBinding;

import java.util.List;

public class WorkShopAdapter extends RecyclerView.Adapter<WorkShopAdapter.WorkshopViewHolder> {

    private final List<Workshop> workShops;
    private final ImageManager imageManager;
    private OnItemClickListener onItemClickListener;

    public WorkShopAdapter(List<Workshop> workShops, Context context) {
        this.workShops = workShops;
        this.imageManager =  new ImageManager(context);
    }

    public interface OnItemClickListener {
        void onItemClick(Workshop workshop);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public WorkshopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WorkshopItemBinding binding = WorkshopItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WorkshopViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkshopViewHolder holder, int position) {
        Workshop workshop = workShops.get(position);
        holder.bind(workshop);
    }

    @Override
    public int getItemCount() {
        return workShops.size();
    }

    class WorkshopViewHolder extends RecyclerView.ViewHolder {

        private final WorkshopItemBinding binding;

        public WorkshopViewHolder(@NonNull WorkshopItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Workshop workshop) {
            binding.txtName.setText(workshop.Name);
            binding.txtDescription.setText(workshop.ShortDescription);
            binding.txtEmail.setText(workshop.Email);
            binding.txtLocation.setText(workshop.Locale);
            setImageWorkshop(workshop.Image, workshop.Id);

            binding.getRoot().setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(workshop);
                }
            });
        }

        private void setImageWorkshop(String image, int id) {
            imageManager.displayImage(image, binding.imgWorkshop, id);
        }

    }

}
