package fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.onlineshoppingapp.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    private EditText etName, etUsername, etEmail, etPassword, etConPassword;
    private Button btnRegister;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        etName = view.findViewById(R.id.etFullname);
        etEmail = view.findViewById(R.id.etEmail);
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        etConPassword = view.findViewById(R.id.etConPassword);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()){
                    signUp();
                }

            }
        });

        return view;
    }

    private void signUp(){
        String password = etPassword.getText().toString();
        String conPassword = etConPassword.getText().toString();
        String username = etUsername.getText().toString();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

        if (password.equals(conPassword)) {

            SharedPreferences sharedPreference = this.getActivity().getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreference.edit();

            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("username", username);
            editor.putString("password", password);
            editor.commit();

            Toast.makeText(getActivity(), "Successfully User Created", Toast.LENGTH_LONG).show();
        }
        else {
            etConPassword.setError("Please enter Confirm password");
            etConPassword.requestFocus();
        }

    }

    public boolean validation(){
        boolean flag = true;
        if (TextUtils.isEmpty(etName.getText().toString())){
            etName.setError("Please enter Name");
            etName.requestFocus();
            flag = false;
        }
        else if (TextUtils.isEmpty(etUsername.getText().toString())){
            etUsername.setError("Please enter Username");
            etUsername.requestFocus();
            flag = false;
        }
        else if (TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError("Please enter Password");
            etPassword.requestFocus();
            flag = false;
        }
        else if (TextUtils.isEmpty(etConPassword.getText().toString())){
            etConPassword.setError("Please enter Confirm Password");
            etConPassword.requestFocus();
            flag = false;
        }
        else if (TextUtils.isEmpty(etEmail.getText().toString())){
            etEmail.setError("Please enter Email");
            etEmail.requestFocus();
            flag = false;
        }
        return flag;
    }

}
