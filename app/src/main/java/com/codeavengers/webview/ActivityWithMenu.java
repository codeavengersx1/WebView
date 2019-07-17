package com.codeavengers.webview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityWithMenu extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        /*
        * You have to write this code this way only #Janmabhar
        * */
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_on_activity_with_menu,menu);

        return true;
    }

    /*
    * This method will be used to set Click Listeners on MenuItems
    *
    * "MenuItem" means the item on which user has clicked
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int menuItemId = item.getItemId();

        if (menuItemId == R.id.menu_load_desktop_site)
        {
            Toast.makeText(this, "User Clicked on Load Desktop Site", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (menuItemId == R.id.menu_exit_app)
        {
            Toast.makeText(this, "User Clicked on Exit App", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
