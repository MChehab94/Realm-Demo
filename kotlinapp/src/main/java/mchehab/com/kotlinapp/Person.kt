package mchehab.com.kotlinapp

import io.realm.RealmObject

/**
 * Created by muhammadchehab on 1/4/18.
 */
open class Person: RealmObject(){
    var firstName = ""
    var lastName = ""
    var age = 0
}