package com.example.jsonrestapitest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.jsonrestapitest.databinding.FragmentFirstBinding;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(v -> new Thread(() -> {
            Call<List<Blog>> call = ApiService.getInstance().service.getBlogs();
            try {
                List<Blog> blogs = call.execute().body();
                requireActivity().runOnUiThread(() -> {
                    assert blogs != null;
                    String blogText = blogs.get(0).title;
                    binding.textviewFirst.setText(blogText);
                });
            } catch (IOException ignored) {
            }
        }).start());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}