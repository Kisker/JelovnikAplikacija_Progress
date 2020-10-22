package net.learn2develop.jelovnikaplikacija;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
//Kada kreiramo klasu JeloAdpter uvek nasledjujemo BaseAdapter, jer nam upravo on omogucava ListView
//u ovom primeru dodatna dva prozora unutar Kategorije i Jela na samoj app.
public class JeloAdapter extends BaseAdapter {

    private List<Jelo> jela;
    private Activity activity;

    public JeloAdapter(List<Jelo> jela, Activity activity) {
        this.jela = jela;
        this.activity = activity;
    }

    @Override
    public int getCount() {return jela.size(); }

    @Override
    public Jelo getItem(int i) {
        return jela.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView (int i, View view, ViewGroup viewGroup){
        if (view == null) {
            //Pozivamo da nam se otvori sledeci prozor sa nazivom kategorije i jela. Bez ovog aplikacija
            //nece biti u mogucnosti da otvori sledeca dva prozora jedan sa nazivom, a drugi sa opisom jela
            //Pre nego sto krenemo sa ovom metodom, moramo kreirari listview_jela_naziv_kategorija.xml
            view = activity.getLayoutInflater().inflate(R.layout.listview_jela_naziv_kategorija, null);
        }
// Unutar fijoke Kategorije i Jela, ukoliko ovde ne podesimo TextView, necemo biti u prilici da, posle kada kliknemo na primer
        //kategoriju Glavno jelo, vidimo u drugom prozoru naziv i kategoriju jela. Ovo obvezno ubaciiti. listview_jela_naziv_kategorija.xml
        TextView tvNaziv = view.findViewById(R.id.textView_NazivJela);
        TextView tvKategorija = view.findViewById(R.id.textView_KategorijaJela);
//getNaziv i getKategorija vucemo iz klase Jelo. Bez podesavanja ovih poziva, necemo videti naziv i opis jela u drawer-u Kategorije i Jelo.
        tvNaziv.setText(jela.get(i).getNaziv());
        tvKategorija.setText(jela.get(i).getKategorija());

        return view;
    }
}