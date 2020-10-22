package net.learn2develop.jelovnikaplikacija;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.InputStream;
//DetailsFragment (detaljan opis svega) dolazi posle kreiranja klasa Jelo, Jelo Adapter, Jelo Provider i ListFragment!
public class DetailsFragment extends Fragment {

    private TextView tvNaziv, tvOpis, tvKategorija, tvSastojci, tvKalorije, tvCena;
    private ImageView ivSlika;
    private Jelo jelo;

    //1.Kod fragmenata uvek mora biti konstruktor bez parametara
    public DetailsFragment() {
    }
    //2.Ova metoda unutar klase DetailsFragment sluzi da nam se otvori drugi prozor kada stnisnemo na fijoku Jelo
    // ili unutar treceg prozora fijoke Kategorije. Poziv na unapred kreiranu fragment_details.xml nam to omogucava
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
  // ovo se vec nalazi u okviru unapred kreirane fragment_details.xml. Ovde ih samo pozovemo
        tvNaziv = view.findViewById(R.id.textView_Naziv);
        tvOpis = view.findViewById(R.id.textView_Opis);
        tvKategorija = view.findViewById(R.id.textView_Kategorija);
        tvSastojci = view.findViewById(R.id.textView_Sastojci);
        tvKalorije = view.findViewById(R.id.textView_Kalorije);
        tvCena = view.findViewById(R.id.textView_Cena);
        ivSlika = view.findViewById(R.id.imageView_Slika);

    //4. sada ovde ubacujemo setupViews, kako bismo je pozvali
        setupViews();

    }
    //3. Ovde kreiramo metodu setupViews, koja ce nam omoguciti da vidimo ceo sadrzaj, odnosno opis jela
    private void setupViews() {
        tvNaziv.setText(jelo.getNaziv());
        tvOpis.setText(jelo.getOpis());
        tvKategorija.setText(jelo.getKategorija());
        tvSastojci.setText(jelo.getSastojci());
        tvKalorije.setText(jelo.getKategorija() + " KCAL");
        tvCena.setText(jelo.getCena() + " RSD");

        try {
            InputStream is = getContext().getAssets().open(jelo.getSlikaUrl());
            Drawable drawable = Drawable.createFromStream(is, null);

            ivSlika.setImageDrawable(drawable);
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//    //5. Kreiramo metodu setJelo pozivanjem na vec unapred kreiranu klasu Jelo, kako bi nam izlistala sve vrednosti
         // a setupViews to pokazao. Ovako kreirana metoda mora biti pozvana unutar showDetails na MainActivity (tacka 8)!
    public void setJelo(Jelo jelo) {
        this.jelo = jelo;
    }
}
