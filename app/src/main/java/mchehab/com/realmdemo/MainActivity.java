package mchehab.com.realmdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;



import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private Button buttonSave;
    private Button buttonRestart;

    private Person person = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSave = findViewById(R.id.button);
        buttonRestart = findViewById(R.id.buttonRestart);

        if(isPersonSaved()){
            person = getPerson();
            buttonSave.setText("Update");
        }

        editTextFirstName.setText(person.getFirstName());
        editTextLastName.setText(person.getLastName());
        if(person.getAge() > 0){
            editTextAge.setText(person.getAge() + "");
        }

        buttonSave.setOnClickListener(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();

            person.setFirstName(editTextFirstName.getText().toString());
            person.setLastName(editTextLastName.getText().toString());
            person.setAge(Integer.parseInt(editTextAge.getText().toString()));

            if(buttonSave.getText().toString().equalsIgnoreCase("save")){
                realm.insert(person);
            }
            realm.commitTransaction();
            realm.close();
        });

        buttonRestart.setOnClickListener(e -> {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });
    }

    private boolean isPersonSaved(){
        Realm.init(this);
        return Realm.getDefaultInstance().where(Person.class).findFirst() != null;
    }

    private Person getPerson(){
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Person.class).findFirst();
    }
}