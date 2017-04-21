package combruce_willis.httpsgithub.yamblztranslate.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ncapdevi.fragnav.FragNavController;

import combruce_willis.httpsgithub.yamblztranslate.R;
import combruce_willis.httpsgithub.yamblztranslate.View.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements TranslateFragment.FragmentNavigation, FragNavController.RootFragmentListener, LanguageFragment.OnListFragmentInteractionListener  {

    private FragNavController fragNavController;

    private final int TAB_TRANSLATE = FragNavController.TAB1;
    private final int TAB_FAVORITE = FragNavController.TAB2;
    private final int TAB_HISTORY = FragNavController.TAB3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_translate:
                    fragNavController.switchTab(TAB_TRANSLATE);
                    break;
                case R.id.navigation_favorites:
                    fragNavController.switchTab(TAB_FAVORITE);
                    break;
                case R.id.navigation_history:
                    fragNavController.switchTab(TAB_HISTORY);
                    break;
            }
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.fragmentContainer)
                .rootFragmentListener(this, 3)
                .build();

        fragNavController.switchTab(TAB_TRANSLATE);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_translate);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {
            case TAB_TRANSLATE: return TranslateFragment.newInstance();
            case TAB_HISTORY: return HistoryFragment.newInstance();
            case TAB_FAVORITE: return FavoriteFragment.newInstance();
        }
        throw new IllegalStateException("Need to send known index");
    }

    @Override
    public void pushFragment(Fragment fragment) {
        if (fragNavController != null) {
            fragNavController.pushFragment(fragment);
        }
    }

    @Override
    public void onBackPressed() {
        if (!fragNavController.isRootFragment()) {
            fragNavController.popFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
