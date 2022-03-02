package com.zybooks.pizzaparty

/*
The Controller (the middle-man, uses the model for calculations, uses the view to interact with the user)
 */

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil
import android.util.Log
import android.widget.Button

const val TAG = "MainActivity"
private const val KEY_TOTAL_PIZZAS = "totalPizzas"

/**
 * This is a MainActivity class. It extends the AppCompatActivity class (an activity displays the UI and process User Input)
 *
 * This class defines the activity which starts when the app runs.
 *
 * @property [numAttendEditText] - 'lateinit' (allows properties to be initiated in [onCreate]), UI for number of Pizza Eaters
 * @property [numPizzasTextView] - ^ , text box to display the results of calculating how many pizzas to order (No UserInput)
 * @property [howHungryRadioGroup] - ^, range of options for how hungry, one selection at a time
 */
class MainActivity : AppCompatActivity() {

    //Variables to be connected to objects in the view
    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup
    private lateinit var calculateButton: Button

    private var totalPizzas = 0
    /**
     * onCreate function (called when the activity is first created)
     *
     * @[setContentView] - sets the MainActivity content (properties) to the layout in activity_main.xml
     * @[findViewById] - retrieves an object from the 'widget' from activity_main.xml
     * @[Log.d] - creates a message on the system log (Useful for debugging)
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //connects activity to the 'view'

        Log.d(TAG, "onCreate was called")

        //objects populating the activity = findViewById connects var to view object
        //                               ie object in View has this quality - android:id="@+id/num_attend_edit_text"
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
        calculateButton = findViewById(R.id.calc_button)

        // initial call to button click changes totalPizza TextView to a result instead of a format symbol
        calculateButton.callOnClick()

        //TextWatcher on the numAttendEditText
        numAttendEditText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) { }
                override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {
                    //on each event in the editText
                    if (s.length > 0) {
                        calculateButton.callOnClick()
                    }
                }

                override fun afterTextChanged(p0: Editable?) {  }
            }
        )
        // Restore state
        if (savedInstanceState != null) {
            totalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS)
            displayTotal()
        }
    }

    /**
     * Restoring the pizzas total
     * Instead of restoring activity state in onCreate(),
     * the Activity method onRestoreInstanceState() may be implemented to restore an activity's state.
     * onRestoreInstanceState() is called immediately after onStart() and
     * has a Bundle parameter containing values saved in onSaveInstanceState().
     *
     **/
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        totalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS)
        displayTotal()
    }

    /**
     * Saves the totalPizzas data that is displayed in the numPizzasTextView to the bundle
     *
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TOTAL_PIZZAS, totalPizzas)
    }

    /**
     * calculateClick function ('Calculate' button on activity_main has been clicked)
     * all information from 'numAttend' and 'slicesPerPerson' are used to calculate the totalPizzas value
     * and update the numPizzaTextView
     *
     *
     *
     */
    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0


        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        //calc is a PizzaCalculator object
        val calc = PizzaCalculator(numAttend, hungerLevel)
        //calc(PizzaCalculator Object) variable 'totalPizzas' is accessed using the getter

        //updates the total pizzas from the PizzaCalculator class variable
        totalPizzas = calc.totalPizzas
        //display function
        displayTotal()

    }

    /**
     * updates the numPizzasTextView with the available data
     */
    private fun displayTotal() {
        val totalText = getString(R.string.total_pizzas, totalPizzas)
        numPizzasTextView.text = totalText
    }
}