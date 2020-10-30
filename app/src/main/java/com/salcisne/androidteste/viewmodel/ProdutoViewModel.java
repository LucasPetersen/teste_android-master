package com.salcisne.androidteste.viewmodel;

import android.view.View;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.salcisne.androidteste.R;
import com.salcisne.androidteste.adapter.ProdutosAdapter;
import com.salcisne.androidteste.model.Produto;
import com.salcisne.androidteste.service.IProdutoService;
import com.salcisne.androidteste.service.ProdutoService;

import java.util.List;

public class ProdutoViewModel extends ViewModel {

    private IProdutoService service;
    private ProdutosAdapter produtosAdapter;
    private MutableLiveData<List<Produto>> listaProdutos;
    public ObservableInt loading;
    public ObservableInt showEmpty;


    public MutableLiveData<List<Produto>> getListaProdutos() {
        return listaProdutos;
    }

    public ProdutosAdapter getAdapter() {
        return produtosAdapter;
    }

    public void setProdutosAdapter(List<Produto> produtoList) {
        produtosAdapter.setProdutos( produtoList );
        produtosAdapter.notifyDataSetChanged();
    }

    public void init() {
        service = new ProdutoService();
        produtosAdapter = new ProdutosAdapter(R.layout.view_produto, this  );
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.VISIBLE);
    }

    public void fetchList(){
        listaProdutos = service.getListaProdutos();
    }

    public Produto getProdutoAt(Integer index) {
        if (listaProdutos.getValue() != null &&
                index != null &&
                listaProdutos.getValue().size() > index) {
            return listaProdutos.getValue().get(index);
        }
        return null;
    }
}
