package mchehab.com.kotlinapp

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    private var person = Person()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isPersonSaved()){
            button.text = "Update"
            person = getPersonFromRealm()!!
        }

        editTextFirstName.setText(person.firstName)
        editTextLastName.setText(person.lastName)
        editTextAge.setText("${person.age} ")

        button.setOnClickListener {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()

            person.firstName = editTextFirstName.text.toString()
            person.lastName = editTextLastName.text.toString()
            person.age = editTextAge.text.toString().trim().toInt()

            if(button.text.toString().equals("save", true)){
                realm.insert(person)
            }
            realm.commitTransaction()
        }

        buttonRestart.setOnClickListener {
            val intent = intent
            finish()
            startActivity(intent)
        }
    }

    private fun isPersonSaved(): Boolean {
        Realm.init(this)
        return Realm.getDefaultInstance().where(Person::class.java).findFirst() != null
    }

    private fun getPersonFromRealm(): Person? {
        Realm.init(this)
        val realm = Realm.getDefaultInstance()
        return realm.where(Person::class.java).findFirst()
    }
}