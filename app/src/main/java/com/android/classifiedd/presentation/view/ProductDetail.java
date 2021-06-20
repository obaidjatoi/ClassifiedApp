package com.android.classifiedd.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.classifiedd.data.models.ProductListing;
import com.android.classifiedd.databinding.ProductDetailBinding;


import org.jetbrains.annotations.NotNull;

import static com.android.classifiedd.utils.ImageUtilsKt.loadImageFromRemote;

public class ProductDetail extends Fragment {

    private ProductDetailBinding binding;
    private ProductListing productDetail;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = ProductDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productDetail = ProductDetailArgs.fromBundle(getArguments()).getProduct();
        }
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (productDetail != null) {
            binding.title.setText(productDetail.getName());
            binding.price.setText(productDetail.getPrice());
            binding.creationDate.setText(productDetail.getCreated_at());
            if (!productDetail.getImage_urls().isEmpty())
                loadImageFromRemote(binding.banner, productDetail.getImage_urls().get(0));
        }
    }
}
