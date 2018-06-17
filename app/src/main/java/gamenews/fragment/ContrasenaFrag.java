package gamenews.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gamenews.R;
import gamenews.viewmodel.GamesNewsViewModel;

public class ContrasenaFrag extends Fragment {

    EditText old_password, new_password, confirm_password;

    Button save;
    private GamesNewsViewModel gamesNewsViewModel;
    private SharedPreferences sp;

    public ContrasenaFrag() {
    }

    public static ContrasenaFrag newInstance() {
        ContrasenaFrag fragment = new ContrasenaFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_password, container, false);
        old_password = v.findViewById(R.id.old_password_txt);
        new_password = v.findViewById(R.id.new_password_txt);
        confirm_password = v.findViewById(R.id.confirm_password_txt);
        save = v.findViewById(R.id.save_buttton);
        gamesNewsViewModel = ViewModelProviders.of(this).get(GamesNewsViewModel.class);
        sp = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_password_txt = new_password.getText().toString();
                String confirm_password_txt = confirm_password.getText().toString();

                if (TextUtils.isEmpty(new_password_txt)) {
                    new_password.setError(getString(R.string.empty_field));
                }
                if (TextUtils.isEmpty(confirm_password_txt)) {
                    confirm_password.setError(getString(R.string.empty_field));
                }
                if (!new_password_txt.equals(confirm_password_txt)) {
                    confirm_password.setError(getString(R.string.not_match));
                }
                if (!TextUtils.isEmpty(new_password_txt) && !TextUtils.isEmpty(confirm_password_txt) && new_password_txt.equals(confirm_password_txt)) {
                    doInBackground task = new doInBackground();
                    String token = sp.getString("token", "");
                    String user = sp.getString("userId", "");
                    task.execute("Bearer " + token, user, new_password_txt);
                }
            }
        });
        return v;
    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public class doInBackground extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            return gamesNewsViewModel.updatePassword(strings[0], strings[1], strings[2]);
        }

        @Override
        protected void onPostExecute(Boolean b) {

            if (b) {
                new_password.setText("");
                old_password.setText("");
                confirm_password.setText("");
                Toast.makeText(getContext(), R.string.password_updated, Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getContext(), R.string.password_updated, Toast.LENGTH_SHORT).show();

            super.onPostExecute(b);
        }
    }
}
