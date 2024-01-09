package com.example.traveljournal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.traveljournal.data.UserData

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    var logged_user_username = ""
    var logged_user_firstname = ""
    var logged_user_lastname = ""
    var logged_user_email = ""

    companion object {
        private const val DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "userdb"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_FIRSTNAME = "first_name"
        private const val COLUMN_LASTNAME = "last_name"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY,
            $COLUMN_USERNAME TEXT NOT NULL,
            $COLUMN_EMAIL TEXT NOT NULL,
            $COLUMN_FIRSTNAME TEXT NOT NULL,
            $COLUMN_LASTNAME TEXT NOT NULL,
            $COLUMN_PASSWORD TEXT NOT NULL
        );"""

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }

    fun insertUser(userData: UserData) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, userData.username)
            put(COLUMN_EMAIL, userData.email)
            put(COLUMN_FIRSTNAME, userData.first_name)
            put(COLUMN_LASTNAME, userData.last_name)
            put(COLUMN_PASSWORD, userData.password)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun verifyUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        val result = cursor.count > 0
        cursor.close()
        return result
    }

    fun checkRegisterFields(userData: UserData): Pair<Boolean, String> {

        if (userData.username.length < 5) {
            return Pair(false, "Username needs to be at least 5 characters.")
        }

        if (userData.email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(userData.email).matches()) {
            return Pair(false, "Invalid email address.")
        }

        if (userData.first_name.isEmpty()) {
            return Pair(false, "First name cannot be empty.")
        }

        if (userData.last_name.isEmpty()) {
            return Pair(false, "Last name cannot be empty.")
        }

        if (userData.password.length < 6) {
            return Pair(false, "Password needs to be at least 6 characters.")
        }

        if (usernameExists(userData.username)) {
            return Pair(false, "Username already exists.")
        }

        if (emailExists(userData.email)) {
            return Pair(false, "Email already exists.")
        }

        return Pair(true, "")
    }

    private fun usernameExists(username: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        val result = cursor.count > 0
        cursor.close()
        return result
    }

    private fun emailExists(email: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        val result = cursor.count > 0
        cursor.close()
        return result
    }

    fun getFirstNameByUsername(username: String): String {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_FIRSTNAME), selection, selectionArgs, null, null, null)

        var firstName: String = ""

        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(COLUMN_FIRSTNAME)
            if (columnIndex != -1) {
                firstName = cursor.getString(columnIndex)
            }
        }

        cursor.close()
        return firstName
    }

    fun getLastNameByUsername(username: String): String {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_LASTNAME), selection, selectionArgs, null, null, null)

        var lastName: String = ""

        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(COLUMN_LASTNAME)
            if (columnIndex != -1) {
                lastName = cursor.getString(columnIndex)
            }
        }

        cursor.close()
        return lastName
    }

    fun getEmailByUsername(username: String): String {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ?"
        val selectionArgs = arrayOf(username)
        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_EMAIL), selection, selectionArgs, null, null, null)

        var email: String = ""

        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(COLUMN_EMAIL)
            if (columnIndex != -1) {
                email = cursor.getString(columnIndex)
            }
        }

        cursor.close()
        return email
    }

}