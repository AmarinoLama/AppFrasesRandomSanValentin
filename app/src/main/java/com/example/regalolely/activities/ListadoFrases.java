package com.example.regalolely.activities;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.regalolely.menu.MenuHandler;
import com.example.regalolely.R;
import com.example.regalolely.conexion.Conexion;
import com.example.regalolely.conexion.dao.FraseDao;
import com.example.regalolely.conexion.model.Frase;
import com.example.regalolely.viewModels.FrasesViewModel;
import java.util.List;

public class ListadoFrases extends AppCompatActivity {

    private FraseDao fraseDao;

    private FrasesViewModel frasesViewModel;
    private RecyclerView recyclerView;
    private FrasesAdapter adapter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_frases);

        inicializar();

        // Configurar el menú principal
        setSupportActionBar(toolbar);
        addMenuProvider(new MenuHandler(this));

        // Inicializar la base de datos
        Conexion.runBBDD(this);
        fraseDao = Conexion.getFraseDao();

        List<Frase> frases = fraseDao.getAllFrases();

        // Inicializar el ViewModel
        cargarAdapterFrases();

    }

    private void inicializar() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        toolbar = findViewById(R.id.toolbar);
    }

    private void cargarAdapterFrases() {

        List<Frase> Allfrases = fraseDao.getAllFrases();

        frasesViewModel = new ViewModelProvider(this).get(FrasesViewModel.class);
        adapter = new FrasesAdapter();
        recyclerView.setAdapter(adapter);
        frasesViewModel.getFrases().observe(this, new Observer<List<Frase>>() {
            @Override
            public void onChanged(List<Frase> frases) {
                adapter.setFrases(frases);
            }
        });

        frasesViewModel.setFrases(Allfrases);
    }

    private class FrasesAdapter extends RecyclerView.Adapter<FrasesAdapter.MyViewHolder> {

        private List<Frase> frases;

        class MyViewHolder extends RecyclerView.ViewHolder {

            // Variables que se necesitan para el fragment
            TextView txtIdFrase, txtFrase;
            Button btnEditar, btnEliminar;

            // Constructor para inicializar las variables
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                txtIdFrase = itemView.findViewById(R.id.txtIdFrase);
                txtFrase = itemView.findViewById(R.id.txtFrase);
                btnEditar = itemView.findViewById(R.id.btnEditar);
                btnEliminar = itemView.findViewById(R.id.btnEliminar);
            }

            public TextView getTxtIdFrase() {
                return txtIdFrase;
            }

            public TextView getTxtFrase() {
                return txtFrase;
            }

            public Button getBtnEditar() {
                return btnEditar;
            }

            public Button getBtnEliminar() {
                return btnEliminar;
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_frase, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            Frase frase = frases.get(position);

            myViewHolder.getTxtIdFrase().setText(String.valueOf(frase.getId()));
            myViewHolder.getTxtFrase().setText(frase.getFrase());

            myViewHolder.getBtnEditar().setOnClickListener((view) -> {
                Toast.makeText(ListadoFrases.this, "Editar", Toast.LENGTH_SHORT).show();
            });

            myViewHolder.getBtnEliminar().setOnClickListener((view) -> {
                fraseDao.deleteFrase(frase.getId());
                cargarAdapterFrases();
            });
        }

        @Override
        public int getItemCount() {
            return frases.size();
        }

        void setFrases(List<Frase> newFrases) {
            this.frases = newFrases;
            notifyDataSetChanged();
        }
    }
}