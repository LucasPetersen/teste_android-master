package com.salcisne.androidteste.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.salcisne.androidteste.R;
import com.salcisne.androidteste.databinding.ActivityListaProdutosBindingImpl;
import com.salcisne.androidteste.model.Produto;
import com.salcisne.androidteste.viewmodel.ProdutoViewModel;

import java.util.List;

public class ListaProdutoActivity extends AppCompatActivity {

    private ProdutoViewModel viewModel;

    private ActivityListaProdutosBindingImpl binding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == R.id.download ){
            updateList();

            return true;
        }
        else {
            return  super.onOptionsItemSelected( item );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_lista_produtos);
        binding.setLifecycleOwner(this);

        setupViewModel( savedInstanceState );
    }

    private void setupViewModel( Bundle savedInstanceState ) {
        viewModel = ViewModelProviders.of(this).get(ProdutoViewModel.class);
        binding.setViewModel(viewModel);

        if (savedInstanceState == null) {
            viewModel.init();
        }
    }

    private void updateList() {
        viewModel.loading.set(View.VISIBLE);
        viewModel.showEmpty.set(View.GONE);
        viewModel.fetchList();
        viewModel.getListaProdutos().observe(this, new Observer<List<Produto>>() {
            @Override
            public void onChanged(List<Produto> produtos) {
                viewModel.loading.set(View.GONE);
                if (produtos.size() == 0) {
                    viewModel.showEmpty.set(View.VISIBLE);
                } else {
                    viewModel.showEmpty.set(View.GONE);
                    viewModel.setProdutosAdapter(produtos);
                }
            }
        });
    }
}
