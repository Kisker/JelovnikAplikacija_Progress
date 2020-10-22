package net.learn2develop.jelovnikaplikacija;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
// Pre nego sto krenemo da radimo sa klasom KategorijeFragment, prvo kreiramo klase Jelo, Jelo Adapter, Jelo Provider, ListFragment i DetailsFragment
   //U okviru klase KategorijeFregment, mi odredjujemo metode listView_Jela koja nam ukazuje samo na listu naziva jela unutar fijoke Kategorija
      // i listu stringova kategorije (Glavno jelo, dezert, itd.) te interface onKategorijaClickListener
public class KategorijeFragment extends Fragment {

    private List<String> kategorije = new ArrayList<>();
    private ListView listView_Jela;
    private onKategorijaClickListener listener;
    //2.Ova metoda unutar klase KategorijeFragment sluzi da nam se otvori sledeci novi prozor kada stnisnemo na fijoku Kategorije
    // Poziv na unapred kreiranu fragment_kategorija.xml nam to omogucava
    public KategorijeFragment() {
    }
    //LayoutInflater converts an XML layout file into corresponding ViewGroups and Widgets
  //A ViewGroup is a special view that can contain other views (called children.)
  // The view group is the base class for layouts and views containers
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kategorija, container, false);
    }
     //Ova metoda onViewCreated mora biti u korelaciji sa onCreateView, kako bismo otvorili novi prozor, kao sto je gore objasnjenjo
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView_Jela = view.findViewById(R.id.listView_Kategorije);

        setupList();
    }
//4. Pozivamo se na metodu List<String> getKategorije() unutar klase JeloProvider, koja nam ispisuje kategorije Glavno jelo, Dezert, itd.
    private void setupList() {
        kategorije = JeloProvider.getKategorije();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, kategorije);
        listView_Jela.setAdapter(adapter);
//5. Bez ove metode i poziva na interfejs, ne bismo mogli otvoriti sledeci prozor, ako na primer kliknemo na Glavno jelo!
        listView_Jela.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onKategorijaClicked(kategorije.get(position));
            }
        });
    }
//6.  Bez if else metode u okviru onAttach ne bismo bili u mogucnosti da nam se prikaze drugi i treci prozor u
     //okviru Kategorije. App bi nam pukao. Povezano je sa implements na MainActivity!!!
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ListFragment.onJeloClickListener) {
            listener = (onKategorijaClickListener) context;
        } else {
            Toast.makeText(getContext(), "Morate implementirati onAttach Kategorija", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
//3. Pre nego sto krenemo da radimo na setupList metodi, prvo kreiramo interface
    public interface onKategorijaClickListener {
        void onKategorijaClicked(String kategorija);
    }
}
