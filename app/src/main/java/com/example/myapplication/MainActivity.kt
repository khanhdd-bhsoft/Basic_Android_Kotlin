package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.CustomServices.MusicPlayerActivity
import com.example.myapplication.MVVM.PostModule.Views.PostActivity
import com.example.myapplication.api_operations.views.HouseActivity
import com.example.myapplication.api_operations.views.ListQuoteActivity
import com.example.myapplication.api_operations.views.MarsPhotoActivity
import com.example.myapplication.api_operations.views.TicketActivity
import com.example.myapplication.broadcast_example.AirplaneModeChangeReceiver
import com.example.myapplication.content_providers.TestContentProviderActivity
import com.example.myapplication.views.movies.views.ListMovieActivity
import com.example.myapplication.views.student_management.views.ListStudentActivity
import com.example.myapplication.views.tab_layout.TabBarActivity

class MainActivity : AppCompatActivity() {


    private lateinit var myButton: Button
    private lateinit var myTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var navToActivity2Button: Button

    private lateinit var addElementButton: Button
    private lateinit var sumListButton: Button
    private lateinit var findStringButton: Button
    private lateinit var resultView: TextView
    private lateinit var inputText: EditText





    private var listData = arrayListOf<String>("fdsaf","fasdf","dafsafs")
    private val listNullData = arrayOfNulls<Double>(16)
    private var listEmptyData: Array<String> = emptyArray()

    private var listInteger = arrayListOf<Int>(1,2,3,4,5,6,7,8,9)





    private val testController: TestControllerImpl = TestControllerImpl()

    private var isReceiverRegistered = false

    private lateinit var airPlaneReceiver: AirplaneModeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setupActions()
        initReceivers()
    }
    private fun initializeViews(){
        myButton = findViewById(R.id.testButton)
        myTextView = findViewById(R.id.helloWorldText)
        resultTextView = findViewById(R.id.resultTextView)
        navToActivity2Button = findViewById(R.id.navToActivity2Button)
        addElementButton = findViewById(R.id.addElementButton)
        sumListButton = findViewById(R.id.sumOfListButton)
        findStringButton = findViewById(R.id.findStringButton)
        inputText = findViewById(R.id.inputText)
    }
    private fun initReceivers()
    {
        airPlaneReceiver = AirplaneModeChangeReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(airPlaneReceiver,it)
            isReceiverRegistered = true
        }
    }
    private fun setupActions(){
        myButton.setOnClickListener {
            resultTextView.text = listData.toString()
        }

        navToActivity2Button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


        addElementButton.setOnClickListener(View.OnClickListener {
            val newList = testController.addNewElementInList(listData,"OKE")
            resultView.text = "Add element: ${newList.toString()}"
        })

        sumListButton.setOnClickListener(View.OnClickListener {
            val result = testController.sumOfList(listInteger)
            resultView.text = "SUm of list: $result"
        })

        findStringButton.setOnClickListener(View.OnClickListener {
            val textInput: String = inputText.text.toString()
            val result: String? = testController.getStringInListString(listData,textInput)
            resultView.text = if (result != null) "Founded: $result" else "not found $textInput in the list"
        })

    }

    override fun onStop() {
        super.onStop()
        if(isReceiverRegistered)
        {
            unregisterReceiver(airPlaneReceiver)
            isReceiverRegistered = false
        }
    }

    fun onNavToMedia(view: View){
        val intent = Intent(this,SettingsActivity::class.java)
        startActivity(intent)
    }
    fun onNavToRecyclerView(view: View){
        val intent = Intent(this,RecyclerViewActivity::class.java)
        startActivity(intent)
    }
    fun onNaToFragment(view: View){
        val intent = Intent(this,MuitlFragmentActivity::class.java)
        startActivity(intent)
    }

    fun onNavToStorage(view: View){
//        var intent = Intent(this,MuitlFragmentActivity::class.java)
//        startActivity(intent)
    }

    fun onViewPagerClick(view: View){

    }
    fun onTabViewClick(view: View){
        val intent = Intent(this,TabBarActivity::class.java)
        startActivity(intent)
    }

    fun onSQLiteClick(view: View){
        val intent = Intent(this,ListStudentActivity::class.java)
        startActivity(intent)
    }

    fun onRoomDBClick(view: View){
        val intent = Intent(this,ListMovieActivity::class.java)
        startActivity(intent)
    }

    fun onContentProviderBClick(view: View)
    {
        val intent = Intent(this,TestContentProviderActivity::class.java)
        startActivity(intent)
    }
    fun onBroadCastClick(view: View)
    {
        val intent = Intent(this,TestContentProviderActivity::class.java)
        startActivity(intent)
    }

    fun onMusicPlayerClick(view: View)
    {
        val intent = Intent(this,MusicPlayerActivity::class.java)
        startActivity(intent)
    }


    fun onListQuoteClick(view: View)
    {
        val intent = Intent(this,ListQuoteActivity::class.java)
        startActivity(intent)
    }



    fun onListTicketClick(view: View)
    {
        val intent = Intent(this,TicketActivity::class.java)
        startActivity(intent)
    }



    fun onListPhotoClick(view: View)
    {
        val intent = Intent(this,MarsPhotoActivity::class.java)
        startActivity(intent)
    }

    fun onListHouseClick(view: View)
    {
        val intent = Intent(this,HouseActivity::class.java)
        startActivity(intent)
    }

    fun onListPostClick(view: View)
    {
        val intent = Intent(this,PostActivity::class.java)
        startActivity(intent)
    }
}