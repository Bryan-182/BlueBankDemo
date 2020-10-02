package com.bryanortiz.bluesoft.bluebank.mainmvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bryanortiz.bluesoft.bluebank.R;
import com.bryanortiz.bluesoft.bluebank.data.model.Account;
import com.bryanortiz.bluesoft.bluebank.root.App;

import javax.inject.Inject;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static com.bryanortiz.bluesoft.bluebank.utils.Constants.TRANS_CREATE_ACCOUNT;
import static com.bryanortiz.bluesoft.bluebank.utils.Constants.TRANS_QUERY;
import static com.bryanortiz.bluesoft.bluebank.utils.Constants.TRANS_RETIREMENT;
import static com.bryanortiz.bluesoft.bluebank.utils.Constants.TRANS_TRANSFER;

public class MainView extends AppCompatActivity implements MainMVP.View {

    @Inject
    MainMVP.Presenter presenter;

    CardView btn_create, btn_transfer, btn_retirement, btn_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getComponent().inject(this);
        initElements();

        //Permissions
        requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);

        presenter.createDB(this);

        btn_create.setOnClickListener(view -> showDialogTans(TRANS_CREATE_ACCOUNT));

        btn_transfer.setOnClickListener(view -> showDialogTans(TRANS_TRANSFER));

        btn_retirement.setOnClickListener(view -> showDialogTans(TRANS_RETIREMENT));

        btn_query.setOnClickListener(view -> showDialogTans(TRANS_QUERY));
    }

    private void initElements() {
        btn_create = findViewById(R.id.btn_create);
        btn_transfer = findViewById(R.id.btn_transfer);
        btn_retirement = findViewById(R.id.btn_retirement);
        btn_query = findViewById(R.id.btn_query);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);

        //Thread for checking changes each 5 Sec
        new Thread(() -> {
            while (true) {
                presenter.updateDB(presenter.getAccounts(this), this);

                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * Method that calls a presenter method depending on what transaction you want to do
     *
     * @param from Trans
     */
    public void showDialogTans(int from) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        EditText editText = customLayout.findViewById(R.id.editText);
        EditText editText1 = customLayout.findViewById(R.id.editText1);
        builder.setView(customLayout);

        switch (from) {
            case TRANS_CREATE_ACCOUNT:
                builder.setTitle(getResources().getString(R.string.create_title));

                editText.setHint(getResources().getString(R.string.text_create1));
                editText1.setHint(getResources().getString(R.string.text_create2));


                builder.setPositiveButton(getResources().getString(R.string.text_btn_create), (dialogInterface, i) -> {
                    if (!editText.getText().toString().equals("") && !editText1.getText().toString().equals("")) {
                        presenter.createAccount(editText.getText().toString(),
                                editText1.getText().toString(), this);
                    } else {
                        Toast.makeText(this, "Campos invalidos", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case TRANS_TRANSFER:
                builder.setTitle(getResources().getString(R.string.transfer_title));

                editText.setHint(getResources().getString(R.string.text_accountno));
                editText1.setHint(getResources().getString(R.string.text_transfer2));

                builder.setPositiveButton(getResources().getString(R.string.text_btn_transfer), (dialogInterface, i) -> {
                    if (!editText.getText().toString().equals("") && !editText1.getText().toString().equals("")) {
                        presenter.transferMoney(editText.getText().toString(),
                                editText1.getText().toString(), this);
                    } else {
                        Toast.makeText(this, "Campos invalidos", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case TRANS_RETIREMENT:
                builder.setTitle(getResources().getString(R.string.retirement_title));

                editText.setHint(getResources().getString(R.string.text_accountno));
                editText1.setHint(getResources().getString(R.string.text_retirement2));

                builder.setPositiveButton(getResources().getString(R.string.text_btn_retirement), (dialogInterface, i) -> {
                    if (!editText.getText().toString().equals("") && !editText1.getText().toString().equals("")) {
                        presenter.retirementMoney(editText.getText().toString(),
                                editText1.getText().toString(), this);
                    } else {
                        Toast.makeText(this, "Campos invalidos", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case TRANS_QUERY:
                editText1.setVisibility(View.GONE);
                builder.setTitle(getResources().getString(R.string.query_title));
                editText.setHint(getResources().getString(R.string.text_accountno));

                builder.setPositiveButton(getResources().getString(R.string.text_btn_query), (dialogInterface, i) -> {
                    if (!editText.getText().toString().equals("")) {
                        presenter.queryBalance(editText.getText().toString(), this);
                    } else {
                        Toast.makeText(this, "Campos invalidos", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Method that show result depending on what transaction you do
     *
     * @param account Account
     * @param from    Trans
     */
    @Override
    public void showResult(Account account, int from) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.result_layout, null);
        builder.setView(customLayout);
        TextView textView = customLayout.findViewById(R.id.tv_result);

        switch (from) {
            case TRANS_CREATE_ACCOUNT:
                builder.setTitle(getResources().getString(R.string.create_title));

                if (account != null) {
                    String msg = getResources().getString(R.string.text_result_create) + account.getAccount();
                    textView.setText(msg);
                } else {
                    textView.setText(getResources().getString(R.string.text_result_create_fail));
                }
                break;

            case TRANS_TRANSFER:
                builder.setTitle(getResources().getString(R.string.transfer_title));

                if (account != null) {
                    textView.setText(getResources().getString(R.string.text_result_transfer));
                } else {
                    textView.setText(getResources().getString(R.string.text_result_fail));
                }
                break;

            case TRANS_RETIREMENT:
                builder.setTitle(getResources().getString(R.string.retirement_title));

                if (account != null) {
                    textView.setText(getResources().getString(R.string.text_result_retirement));
                } else {
                    textView.setText(getResources().getString(R.string.text_result_fail));
                }
                break;

            case TRANS_QUERY:
                builder.setTitle(getResources().getString(R.string.query_title));

                if (account != null) {
                    textView.setText(account.getBalance());
                } else {
                    textView.setText(getResources().getString(R.string.text_result_fail_query));
                }
                break;
        }

        builder.setPositiveButton(getResources().getString(R.string.text_result_btn), (dialog, which) -> {
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}