package com.android.shintaku.redpacket.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import com.android.shintaku.redpacket.R;
import com.android.shintaku.redpacket.activities.WebViewActivity;

public class GeneralSettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.general_preferences);
        setPrefListeners();
    }

    private void setPrefListeners() {
        // Open issue
        Preference issuePref = findPreference("pref_etc_issue");
        issuePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Intent webViewIntent = new Intent(getActivity(), WebViewActivity.class);
                webViewIntent.putExtra("title", "项目主页");
                webViewIntent.putExtra("url", "https://github.com/liangzhenduo0608/Red-Packet");
                webViewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(webViewIntent);
                return false;
            }
        });

        // Update
        Preference updatePref = findPreference("pref_etc_check_update");
        updatePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Intent webViewIntent = new Intent(getActivity(), WebViewActivity.class);
                webViewIntent.putExtra("title", "检查更新");
                webViewIntent.putExtra("url", "https://github.com/liangzhenduo0608/Red-Packet/releases");
                webViewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(webViewIntent);
                return false;
            }
        });

        Preference excludeWordsPref = findPreference("pref_watch_exclude_words");
        String summary = getResources().getString(R.string.pref_watch_exclude_words_summary);
        String value = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("pref_watch_exclude_words", "");
        if (value.length() > 0) excludeWordsPref.setSummary(summary + ":" + value);

        excludeWordsPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                String summary = getResources().getString(R.string.pref_watch_exclude_words_summary);
                if (o != null && o.toString().length() > 0) {
                    preference.setSummary(summary + ":" + o.toString());
                } else {
                    preference.setSummary(summary);
                }
                return true;
            }
        });
    }
}
