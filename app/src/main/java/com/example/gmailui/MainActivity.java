package com.example.gmailui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ItemModel> items;
    ActionBar actionBar;
    SearchView txtSearchView;
    ItemModel openItem;
    ListView listView;
    ListView listView2;
    String querySearch;
    SearchView searchView;
    ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("TAG", "Oncreate");
        items = new ArrayList<>();
        items.add(new ItemModel("Sam","for the party","at saturday afternoon this weekend","12:07PM","#eb7a34"));
        items.add(new ItemModel("Andrew","the constract","we need to have a meeting at monday...","3:30PM","#a8eb34"));
        items.add(new ItemModel("Jack","lorem sapbhies jdia aoies...","bisj js ofj eq j djoaid","2:24PM","#34eba8"));
        items.add(new ItemModel("Sophia","Aliquam ullamcorper magna ","quis mollis felis interdum","6:30AM","#34c3eb"));
        items.add(new ItemModel("Quisque eleifend","Aliquam varius turpis dolor, quis mollis","Nulla aliquet, dui quis eleifend placerat","8:18AM","#3462eb"));
        items.add(new ItemModel("Anna","Lorem ipsum dolor sit amet","Convallis convallis tellus ","11:11AM","#a3ab59"));
        items.add(new ItemModel("Adaline Reichel","Sed quis nisl sit amet neque laoreet finibus"," proin nibh nisl condimentum. ","12:03AM","#c2bdbc"));
        items.add(new ItemModel("Roscoe Johns","Phasellus faucibus felis"," congue mi sit amet, convallis est.","2:33AM","#b734eb"));
        items.add(new ItemModel("Keegan Thiel","Aliquam vel mauris fringilla","Ut ut lectus feugiat libero","7:31PM","#cf9988"));
        items.add(new ItemModel("Ms. Karley Kiehn V","Morbi blandit nibh nec"," elit tristique semper.","4:16AM","#917ca1"));
        items.add(new ItemModel("Emmett Lebsack","In luctus ligula eget nibh ","Et netus et malesuada","10:22PM","#ed5a73"));
        items.add(new ItemModel("Gracie Weber","In aliquam sem fringilla","pharetra et ultrices neque.","6:20PM","#f0f0f5"));
        items.add(new ItemModel("Max Coppola","Vitae tortor condimentum","labore et dolore magna ","10:20AM","#daa6e0"));

         adapter = new ItemAdapter(this, items);
         listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);



        registerForContextMenu(listView);
        listView.setLongClickable(true);
        //create actionbar
//        actionBar = getSupportActionBar();
//        actionBar.setDisplayShowCustomEnabled(true);
        // create contextmenu

//        registerForContextMenu(listView);
//        listView.setLongClickable(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main_menu,menu);
         searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v("TAG", "Search with keyword: " + query);
                querySearch = query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v("TAG", "Keyword: " + newText);
                    adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_favorite){
            List<ItemModel> items2 = new ArrayList<>();
//            if(listView.getVisibility() == View.GONE){
                for (int i = 0; i < items.size(); i++){
                    if(items.get(i).isFavorite()) {
                        Log.v("favorite", "item" + i);
                        items2.add(items.get(i));
                        Log.v("add2favorite", "item" + items2.size());
                    }
                }
                listView.setVisibility(View.GONE);
                ItemAdapter adapter1 = new ItemAdapter(this, items2);
                listView2 = findViewById(R.id.list_favorite);
                listView2.setAdapter(adapter1);
//            }
//            else{
//                listView.setVisibility(View.VISIBLE);
//                listView2.setVisibility(View.GONE);
//            }

           return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Select action");
        menu.add(0,101, 0,"Details");
        menu.add(0,102,0,"Delete");
    }
    private void go2Details(ItemModel item){
        Intent details = new Intent(MainActivity.this,Details.class);
        //create Bundle
        Bundle myBunder = new Bundle();
        //add <key,value>
        myBunder.putString("name",item.getName());
        myBunder.putString("title",item.getTitle());
        myBunder.putString("time",item.getTime());
        myBunder.putString("content",item.getContent());
        myBunder.putString("color",item.getMyHexColor());
        myBunder.putBoolean("favorite",item.isFavorite());
        //attatch the container to the intent
        details.putExtras(myBunder);

        startActivityForResult(details,10);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == 101){
            Log.v("tag/","intent");
            go2Details(items.get(info.position));

        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if(requestCode == 10 && resultCode == Activity.RESULT_OK){
                Log.v("TAG", "sucess");
            }

        }catch (Exception e){
            Log.v("TAG", "KHONG MO DC INTENT");
        }
    }
}