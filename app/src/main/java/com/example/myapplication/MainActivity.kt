package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupActions()
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
        resultView = findViewById(R.id.resultTextView)
    }
    private fun setupActions(){
        myButton.setOnClickListener {
            resultTextView.text = listData.toString()
        }

        navToActivity2Button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        })


        addElementButton.setOnClickListener(View.OnClickListener {
            val newList = testController.addNewElementInList(listData,"OKE")
            resultView.text = "Add element: ${newList.toString()}"
        })

        sumListButton.setOnClickListener(View.OnClickListener {
            val result = testController.sumOfList(listInteger)
            resultView.text = "SUm of list: $result"
        })

        findStringButton.setOnClickListener(View.OnClickListener {
            val textInput: String = inputText.text.toString();
            val result: String? = testController.getStringInListString(listData,textInput)
            resultView.text = if (result != null) "Founded: $result" else "not found $textInput in the list"
        })

    }

    fun onNavToMedia(view: View){
        var intent = Intent(this,SettingsActivity::class.java)
        startActivity(intent)
    }
    fun onNavToRecyclerView(view: View){
        var intent = Intent(this,RecyclerViewActivity::class.java)
        startActivity(intent)
    }
    fun onNaToFragment(view: View){
        var intent = Intent(this,MuitlFragmentActivity::class.java)
        startActivity(intent)
    }

    fun onNavToStorage(view: View){
//        var intent = Intent(this,MuitlFragmentActivity::class.java)
//        startActivity(intent)
    }

    fun onViewPagerClick(view: View){

    }
    fun onTabViewClick(view: View){
        var intent = Intent(this,TabBarActivity::class.java)
        startActivity(intent)
    }




}