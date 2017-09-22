package comfelm84.github.contactslist.ui;

/**
 * Created by Administrator on 04/09/2017.
 */

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import comfelm84.github.contactslist.BuildConfig;
import comfelm84.github.contactslist.util.Utils;

/**
 * This class defines a simple FragmentActivity as the parent of {@link ContactDetailFragment}.
 */
public class ContactDetailActivity extends FragmentActivity {
    // Defines a tag for identifying the single fragment that this activity holds
    private static final String TAG = "ContactDetailActivity";

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            // Enable strict mode checks when in debug modes
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);

        // This activity expects to receive an intent that contains the uri of a contact
        if (getIntent() != null) {

            // For OS versions honeycomb and higher use action bar
            if (Utils.hasHoneycomb()) {
                // Enables action bar "up" navigation
                getActionBar().setDisplayHomeAsUpEnabled(true);
            }

            // Fetch the data Uri from the intent provided to this activity
            final Uri uri = getIntent().getData();

            // Checks to see if fragment has already been added, otherwise adds a new
            // ContactDetailFragment with the Uri provided in the intent
            if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                // Adds a newly created ContactDetailFragment that is instantiated with the
                // data Uri
                ft.add(android.R.id.content, ContactDetailFragment.newInstance(uri), TAG);
                ft.commit();
            }
        } else {
            // No intent provided, nothing to do so finish()
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        // Otherwise, pass the item to the super implementation for handling, as described in the
        // documentation.
        return super.onOptionsItemSelected(item);
    }
}
