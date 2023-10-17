package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recycleview.adapter.StarAdapter;
import com.example.recycleview.beans.Star;
import com.example.recycleview.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity"; // Define the TAG variable here

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, stars);
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Assuming you have imported LinearLayoutManager
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BLEACH");
    }

    public void init() {
        stars.add(new Star("kurusaki itchigo",  "https://th.bing.com/th/id/OIP.2t6QltHLxvXYvw8wGGo8GgAAAA?w=152&h=142&c=7&r=0&o=5&pid=1.7", 3.5f));
        stars.add(new Star("aizan sama", "https://th.bing.com/th/id/OIP.lnsROHV8d6bR4m_FhnPnpgAAAA?pid=ImgDet&w=474&h=466&rs=1", 3));
        stars.add(new Star("rukia", "https://th.bing.com/th/id/R.4c07149377c1be39fc9a560c79f02b0f?rik=38U8r0QBE9vVBQ&riu=http%3a%2f%2fimages6.fanpop.com%2fimage%2fphotos%2f33200000%2fRukia-bleach-anime-33225206-469-571.jpg&ehk=do7AOo5sFEkZlKFnNLdhm2B9BtFncxOD704DNVSXooc%3d&risl=&pid=ImgRaw&r=0", 5));
        stars.add(new Star("ohrihimi", "https://th.bing.com/th/id/OIP.QJ9BNFrJcEPt-eV4OsE0CQHaFj?pid=ImgDet&rs=1", 1));
        stars.add(new Star("ichida", "https://th.bing.com/th/id/R.04a04b0a45b2564bb067f7c6b5195beb?rik=7Cz9e%2br0qx0dMw&riu=http%3a%2f%2fimages6.fanpop.com%2fimage%2fphotos%2f33300000%2fUryu-Ishida-bleach-anime-33340038-818-720.png&ehk=b5VIjY%2b7UG2tgXhPKke1MWaGKX7DLpStXdwHfTLGb2g%3d&risl=&pid=ImgRaw&r=0", 5));
        stars.add(new Star("rinji", "https://th.bing.com/th/id/OIP.FQWINquOwfp8NNvEDgxVvAHaGk?pid=ImgDet&rs=1", 1));
        stars.add(new Star("hisuka", "https://th.bing.com/th/id/R.368002b19daab7f2622e47b51cd5e49f?rik=AKc3HQF2%2bUKhxA&pid=ImgRaw&r=0", 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

}
