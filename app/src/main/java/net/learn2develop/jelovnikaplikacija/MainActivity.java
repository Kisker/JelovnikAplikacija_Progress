package net.learn2develop.jelovnikaplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
// Pre nego sto krenemo da radimo, moramo otici do Gradle biblioteke i implementirati neophodne biblioteke za dalji radi
//Kad odredimo klase Jelo, JeloAdapter, JeloProvider, ListFragment, KategorijeFragment i DetailsFragment, tek onda se vracamo na MainAcitivity!!!

// 3. Implementiramo interface ListFragment.OnJeloClickListener (kako bi nam otvorila fijoku Jelo i sledeca njena dva prozora te fijoku Kategorije -
//Glavno jelo, Dezert, itd. Implementacijom KategorijeFragment.onKategorijaClickListener nama se fijoka Kategorije otvara u sledeca tri prozora!
//Uvek se fragmenti moraju implementirati u MainActivity!
public class MainActivity extends AppCompatActivity implements ListFragment.onJeloClickListener, KategorijeFragment.onKategorijaClickListener{
//4. Odredimo metode
    private Toolbar toolbar;
    private List<String> drawerItems;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private AlertDialog dialog_about;
    private AlertDialog dialog_choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//5. Prvo uradimo toolbar (sandwich), onda da napisemo nazive lista na fijoci , pa tek onda podesavanja Drawer tittle listu.
        setupToolbar();
        fillData();
        setupDrawer();
    }


    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }
    }
    private void fillData() {
        drawerItems = new ArrayList<String>();
        drawerItems.add("Kategorije");
        drawerItems.add("Jela");
        drawerItems.add("Podesavanja");
        drawerItems.add("O aplikaciji");
    }

       //Ovo nam pokazuje Drawer tittle listu i uvek setup ide na ovakav nacin. Prvo uradimo setupDrawer sa else if metodama, a onda se nadovezemo sa drawerToogle
    // Kada kliknemo na jednu specifikaciju, on nam otvori novi list
    //Pozive za R.id moramo da uradimo u main_activity.xml
    private void setupDrawer() {
        drawerList = findViewById(R.id.leftDrawer);
        drawerLayout = findViewById(R.id.drawer_layout);
    //13. Ova metoda ide na samom kraju!
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerItems));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = "";
                if (position == 0) {
                    title = "Kategorije";
                    showKategorijeFragment();
                } else if (position == 1) {
                    title = "Jela";
                    showJelaIKategorijeJelaFragment();
                } else if (position == 2) {
                    title = "Podesavanja";
                    showPreferences();
                } else if (position == 3) {
                    title = "O aplikaciji";
                    showDialog();
                }
                setTitle(title);
                drawerLayout.closeDrawer(drawerList);
            }
        });
        //This class ActionBarDrawerToggle provides a handy way to tie together the functionality of DrawerLayout (ceo ekran)
        // and the framework ActionBar to implement the recommended design for navigation drawers.
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle("");
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("");
                super.onDrawerOpened(drawerView);
            }
        };

    }
// *14. Ovo mozete ubaciti i ne morate, ako ne zelite kreirati Snackbar. Prvo ovo podesite, a onda u okviru 12. tacke ubacite showSnackbar(), kako
    //bi vam ispisalo izvrsenu akciju klikom na dugme Dialog Choose (+).
    private void showSnackbar() {
        Snackbar snackbar;
        snackbar = Snackbar.make(findViewById(R.id.root), "Unos! " + "Izmena! " + "Brisanje!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

//6.Sada krecemo pozivati metode po rednoj listi na samoj aplikaciji. Bez poziva na tr.replace (R.id.root), nama
    //ne bi bila vidljiva kategorija jela unutar fijoke Kategorije (Glavno jelo, Dezert, itd.)
    private void showKategorijeFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        KategorijeFragment fragment = new KategorijeFragment();
        transaction.replace(R.id.root, fragment);
        transaction.commit();
    }
//7. Ova metoda nam omogucava prikaz naziva i opis jela. Bez ovoga ne bismo mogli otvoriti drugi i treci prozor u okvire
    //fijoke Kategorije, a fijoka Jelo bi bila blank (ne bi imala zapisa)
    public void showJelaIKategorijeJelaFragment() {
        ListFragment listfragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.root, listfragment).commit();
    }

//8.  showDetails  metoda i kreiran interface showDetails ukazuje na detaljan opis svih jela. Bez ovoga nasa App bi pukla!
    //Klasa DetailsFragment je morala biti vec kreirana, kako bi nam konkretno dala celokupan opis jela sa slikom!!!
    private void showDetails(Jelo jelo) {
        DetailsFragment detailsFragment = new DetailsFragment();
        //bez unapred odredjenog .setJelo App bi pukao.Obvezno pozvati ovu metodu iz DetailsFragment-a.
        detailsFragment.setJelo(jelo);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, detailsFragment)
                .addToBackStack(null)
                .commit();
    }
// 9. Kreiramo podesavanja na istom sablonu svaki put. Bez poziva na fr.replace (R.id.root),
    // ne bismo bili u mogucnosti videti nasa podesavanja.
    private void showPreferences() {
        FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();
        PreferenceFragment preferenceFragment = new PreferenceFragment();
        fragment.replace(R.id.root, preferenceFragment);
        fragment.commit();
    }
//10. Na kraju kreiramo i pozivamo metodu Dialog About
    private void showDialog() {
        if (dialog_about == null)
            dialog_about = new DialogAbout(this).prepareDialog();
        else if (dialog_about.isShowing())
            dialog_about.dismiss();
        dialog_about.show();
    }
//11. Metoda Dialog Choose (YES or NO)
    private void showDialogChoose() {
        if (dialog_choose == null)
            dialog_choose = new DialogChoose(this).prepareDialog();
        else if (dialog_choose.isShowing())
            dialog_choose.dismiss();
        dialog_choose.show();
    }
// 12. Na ovaj nacin pozivamo da nam se izvrsi akcija na ActionBar-u
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "Action create executed.", Toast.LENGTH_SHORT).show();
                showSnackbar();
                showDialogChoose();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
   // Ova metoda ide zajedno za Izvrsavanje akcija (Dialog Choose). Bez ove metode, nase akcije ne bi bile vidljive!
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return super.onCreateOptionsMenu(menu);
}

    //1. Pre nego sto implementiramo interface na Main Activity implements ... moramo da kreiramo  sam interface!
    @Override
    public void onJeloClicked(Jelo jelo) {
        //pozovemo gorespomenutu metodu, kada je kreiramo
        showDetails(jelo);
    }
    //2.  Kreiramo interface
    @Override
    public void onKategorijaClicked(String kategorija) {
        //pozovemo gorespomenutu metodu, kada je kreiramo
        showJelaIKategorijeJelaFragment();
    }
}