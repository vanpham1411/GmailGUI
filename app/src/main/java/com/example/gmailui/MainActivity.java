package com.example.gmailui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ItemModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        ItemAdapter adapter = new ItemAdapter(this, items);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}