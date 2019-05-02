package fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.onlineshoppingapp.DashboardActivity;
import com.e.onlineshoppingapp.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private EditText etUsername, etPassword;
    private Button btnLogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    login();
                }
            }
        });

        return view;
    }

    private void login(){
        SharedPreferences sharedPreference = this.getActivity().getSharedPreferences("user", MODE_PRIVATE);
        String username = sharedPreference.getString("username","");
        String password = sharedPreference.getString("password","");

        if (username.equals(etUsername.getText().toString()) || password.equals(etPassword.getText().toString())){
            Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), DashboardActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_LONG).show();
        }

    }

    public boolean validation(){
        boolean flag = true;
        if (TextUtils.isEmpty(etUsername.getText().toString())){
            etUsername.setError("Please enter Username");
            etUsername.requestFocus();
            flag = false;
        }
        else if (TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError("Please enter Password");
            etPassword.requestFocus();
            flag = false;
        }
        return flag;
    }

}
