package com.example.dicepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dicepractice.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //will automatically add import above.

    private var points = 1 //re-initialized to dice value
    private var player1Points = 0
    private var player2Points = 0
    private var answer = 0
    private var p1Turn = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
//the actual coding
        //initialize points to 0
        binding.tvP1Score.text = player1Points.toString()
        binding.tvP2Score.text = player2Points.toString()


        //set button event handler. below is exclusive of top value.
        binding.btnRollDie.setOnClickListener {
            var diceValue = Random.nextInt(1,7)  //use kotlin version of random. (Alt + Enter)
            //clever way to avoid many if statements
            var imageName = "@drawable/dice" + diceValue //only works bc png share dice name + value.
            //retrieve resource ID. system knows this.
            var resourceID = resources.getIdentifier(imageName, "drawable", getPackageName())

            binding.ivDie.setImageResource(resourceID)

            var num1 = Random.nextInt(0,100)
            var num2 = Random.nextInt(0,100)


            var points = diceValue //re-initialized to dice value

            //Conditional Switch Statement in Kotlin
            when (diceValue){
                1->{    //addition
                    answer = num1 + num2

                    val builder = StringBuilder()       //complicated manner to concatenate values to output
                    builder.append(num1)
                    builder.append(" + ")
                    builder.append(num2)
                    builder.append(" = ")
                    binding.tvMath.text = builder.toString()
                }
                2->{    //subtraction

                    answer = num1-num2
                    val builder = StringBuilder()
                    builder.append(num1)
                    builder.append(" - ")
                    builder.append(num2)
                    builder.append(" = ")
                    binding.tvMath.text = builder.toString()
                }
                3->{    //multiplication
                    answer = num1*num2
                    val builder = StringBuilder()
                    builder.append(num1)
                    builder.append(" * ")
                    builder.append(num2)
                    builder.append(" = ")
                    binding.tvMath.text = builder.toString()
                }
                4->{    //double points roll again
                    points *=2
                }
                5->{    //lose a turn

                }
                6->  {  //JACKPOT
                    points *=5
                }



            }
            binding.btnGuess.setOnClickListener{
                var attempt : Int = binding.etEnterAnswer.text.toString().toInt()   //this is the weirdest conversion.
                if(attempt == answer){

                    binding.tvAnswerEcho.text = "CORRECT ANSWER! \nPOINTS EARNED"
                }
                else{
                    binding.tvAnswerEcho.text = "INCORRECT, NO POINTS"
                }
            }

        }//end. test on macbook with virtual AVD





    }//on Create end
}


//step one, set viewbinding to true in build.gradle
//step two, copy paste stuff into drawable.

//My best bet in doing this stuff is to master the Control Flow and Function building for Kotlin