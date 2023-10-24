package com.risusapp.anarcho.capitalism.quotes.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.risusapp.anarcho.capitalism.quotes.Quote;
import com.risusapp.anarcho.capitalism.quotes.QuoteAdapter;
import com.risusapp.anarcho.capitalism.quotes.R;
import com.risusapp.anarcho.capitalism.quotes.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    List<Quote> quotes;
    private RecyclerView recyclerView;
    private QuoteAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        loadData();

        int numColumns = 2;

        recyclerView = binding.rvQuote;
        if(numColumns == 1){
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        }else{
            recyclerView.setLayoutManager( new GridLayoutManager(getContext(), numColumns));
        }
        adapter = new QuoteAdapter(getContext(), quotes, numColumns);
        recyclerView.setAdapter(adapter);
        return root;
    }

    private void loadData() {

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.assets);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String jsonContent = stringBuilder.toString();

            // Utiliza Gson para deserializar el JSON en una lista de objetos Frase.
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Quote>>() {}.getType();
            quotes = gson.fromJson(jsonContent, listType);

            // Ahora tienes una lista de objetos Frase que puedes utilizar en tu aplicación.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}