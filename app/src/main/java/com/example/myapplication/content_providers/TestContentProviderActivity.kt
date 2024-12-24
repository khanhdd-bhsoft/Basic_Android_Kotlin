package com.example.myapplication.content_providers

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.utils.ToolbarUtils
import com.google.android.material.button.MaterialButton

class TestContentProviderActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var smsButton  : MaterialButton
    private lateinit var catalogHisButton: MaterialButton
    private lateinit var contactsButton : MaterialButton
    private lateinit var listContentProviderView: ListView
    private var listData = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_content_provider)
        initViews()
        onHandleClick()
    }
    private fun initViews()
    {
        toolbar = findViewById(R.id.contentProviderToolBar)
        setSupportActionBar(toolbar)
        ToolbarUtils().setupBasicToolbar(toolbar,{
                                                 finish()
        },this)
        smsButton = findViewById(R.id.SMSButton)
        catalogHisButton = findViewById(R.id.catalogHistoryButton)
        contactsButton = findViewById(R.id.contactsButton)
        listContentProviderView = findViewById(R.id.listContentProviderView)
        // set text
        smsButton.text = getString(R.string.sms)
        catalogHisButton.text = getString(R.string.catalogHistory)
        contactsButton.text = getString(R.string.contacts)

        // init list view
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData)
        listContentProviderView.adapter = adapter
    }
    private fun onHandleClick()
    {
        onHandleContactsClick()
        onHandleSMSClick()
        onHandleCatalogHistoryClick()

    }
    private fun onHandleSMSClick()
    {
        smsButton.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_SMS),
                    REQUEST_CODE_READ_SMS
                )
            }else{
                fetchContacts()
            }
        }
    }
    private fun onHandleContactsClick()
    {
        contactsButton.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    REQUEST_CODE_READ_CONTACTS
                )
            }else{
                fetchContacts()
            }
        }
    }
    private fun onHandleCatalogHistoryClick()
    {
        catalogHisButton.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_CALL_LOG),
                    REQUEST_CODE_READ_CALL_LOG
                )
            }else{
                fetchCallLogs()
            }
        }
    }


    private fun fetchCallLogs()
    {
        listData.clear()
        val uri = Uri.parse("content://call_log/calls")
        val cursor: Cursor? = contentResolver.query(uri,null,null,null,null)
        cursor?.let {
            while (it.moveToNext()) {
                val number = it.getString(it.getColumnIndexOrThrow("number"))
                val type = it.getInt(it.getColumnIndexOrThrow("type"))
                val date = it.getLong(it.getColumnIndexOrThrow("date"))
                val duration = it.getInt(it.getColumnIndexOrThrow("duration"))

                println("Number: $number, Type: $type, Date: $date, Duration: $duration")
                val value = "Number: $number, Type: $type, Date: $date, Duration: $duration"
                listData.add(value)
            }
            it.close()
            adapter.notifyDataSetChanged()
        }
    }
    private fun fetchSMS()
    {
        listData.clear()
        val uri = Uri.parse("content://sms/inbox")
        val cursor = contentResolver.query(uri, null, null, null, null)

        cursor?.let {
            while (it.moveToNext()) {
                val address = it.getString(it.getColumnIndexOrThrow("address"))
                val body = it.getString(it.getColumnIndexOrThrow("body"))
                val date = it.getLong(it.getColumnIndexOrThrow("date"))

                println("From: $address, Message: $body, Date: $date")
                val value = "From: $address, Message: $body, Date: $date"
                listData.add(value)
            }
            it.close()
        }
    }
    private fun fetchContacts()
    {
        listData.clear()
        val uri = Uri.parse("content://com.android.contacts/contacts")
        val cursor = contentResolver.query(uri, null, null, null, null)

        cursor?.let {
            while (it.moveToNext()) {
                val id = it.getString(it.getColumnIndexOrThrow("_id"))
                val name = it.getString(it.getColumnIndexOrThrow("display_name"))
                println("ID: $id, Name: $name")
                val value = "ID: $id, Name: $name"
                listData.add(value)
            }
            it.close()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_READ_CALL_LOG && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            fetchCallLogs()
        } else if(requestCode == REQUEST_CODE_READ_CONTACTS && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED){
            fetchContacts()
        }else if(requestCode == REQUEST_CODE_READ_SMS && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            fetchSMS()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val REQUEST_CODE_READ_CALL_LOG = 100
        const val REQUEST_CODE_READ_CONTACTS = 101
        const val REQUEST_CODE_READ_SMS = 102
    }
}