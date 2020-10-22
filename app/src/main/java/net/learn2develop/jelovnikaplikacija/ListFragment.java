package net.learn2develop.jelovnikaplikacija;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
//Kada odredimo Jelo, Jelo Adapter i Jelo Provider, kreiramo novu klasu List Fragment.
public class ListFragment extends Fragment {
// Ovde implementiramo 3 metode i jedan interface. Prva metoda listView_Jela nam ukazuje samo na listu naziva jela unutar
    //fijoka Kategorija i Jela. Druga metoda celotan opis jela, a treca metoda ce nam pokazivati kategorisane nazive jela (Glavno jelo, Dezert, etc.)
    private ListView listView_Jela;
    private List<Jelo> jela = new ArrayList<>();
    private String kategorija;
    private onJeloClickListener listener;

    //1.Kod fragmenata uvek mora biti konstruktor bez parametara
    public ListFragment() {
    }
 //2.Ova metoda unutar klase ListFragment sluzi da nam se otvori sledeci novi prozor kada stnisnemo na fijoku Jelo
    // ili unutar fijoke Kategorije na primer kada kliknemo na Glavno jelo. Poziv na unapred kreiranu fragment_lista.xml nam to omogucava
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }
    // Ova metoda onViewCreated mora biti u korelaciji sa onCreateView, kako bismo otvorili novi prozor, kao sto je gore objasnjenjo
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //trazimo View jela/podatke unutar fragment_lista.xml
        listView_Jela = view.findViewById(R.id.lvPodaci);
//6.Prvo moramo uraditi metode setupListByKategorija i setupList, i tek ih onda ovde pozvati.
        if (kategorija == null)
            setupList();
        else
            setupListByKategorija();

    }
    //4. Unutar ove metode u okviru Kategorije dobijamo podatke o kategoriji jela (npr.Glavno jelo), nazivu jela (drugi prozor) te opisu (treci prozor)
    private void setupListByKategorija() {
        //pozivamo klasu Jelo Provider, odnosno metodu getAllJelaByKategorija i sva jela po kategoriji
        jela = JeloProvider.getAllJelaByKategorija(kategorija);
        JeloAdapter adapter = new JeloAdapter(jela, getActivity());
        listView_Jela.setAdapter(adapter);
        // pozivom na ovu metodu, odnosno na interface onItemClick, nama se otvara treci prozor sa opisom jela
        listView_Jela.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listener != null) {
                    listener.onJeloClicked(jela.get(i));
                }
            }
        });
    }
//5. Unutar ove metode dobijamo podatke podatke klikom na fijoku Jelo
    private void setupList() {
        jela = JeloProvider.getAllJela();

        JeloAdapter adapter = new JeloAdapter(jela, getActivity());
        listView_Jela.setAdapter(adapter);
        // pozivom na ovu metodu, odnosno na interface onItemClick unutar fijoke Jelo, nama se otvara sledeci prozor sa opisom jela
        listView_Jela.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listener != null) {
                    listener.onJeloClicked(jela.get(i));
                }
            }
        });
    }
    //7. Bez if else metode u okviru onAttach ne bismo bili u mogucnosti da nam se prikaze treci poslednji prozor u
    //okviru Kategorije iliti drugog prozora unutar fijoke Jela. Povezano je sa implements na MainActivity!!!
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onJeloClickListener) {
            listener = (onJeloClickListener) context;
        } else {
            Toast.makeText(getContext(), "Morate implementirati onAtach List", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

//3. Najvaznije od svega jeste da prvo kreiramo interface pre nego sto krenemo sa realizacijom metoda
    //setupListByKategorija i setupList
    public interface onJeloClickListener {
        void onJeloClicked(Jelo jelo);
    }
}