package combruce_willis.httpsgithub.yamblztranslate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ncapdevi.fragnav.FragNavController;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        List<android.support.v4.app.Fragment> fragments = new ArrayList<>(3);
        fragments.add(new TranslateFragment());
        fragments.add(new FavoriteFragment());
        fragments.add(new HistoryFragment());

        FragNavController.Builder builder = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.fragmentContainer);
        builder.rootFragments(fragments);
        fragNavController = builder.build();

        fragNavController.switchTab(TAB_TRANSLATE);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
