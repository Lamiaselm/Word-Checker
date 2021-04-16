package com.example.exo3_tp2
import kotlin.random.Random
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

fun rand(start: Int, end: Int): Int {
    require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
    return Random(System.nanoTime()).nextInt(end - start + 1) + start
}
/* Fonction de génération aléatoire des mots de longeur i */
fun getRandomWord(list:List<String>,i:Int): String {
    val listRand: MutableList<String> = mutableListOf()
    for (item in list)
    {
        if ((item.length)==i
        )
        {  /* Liste des mots de longeurs i */
            listRand.add(item)

        }
    }
    val randomValue = Random.nextInt(listRand.size)
    return listRand[randomValue]
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listMots: MutableList<String> = mutableListOf("Web","PHP","JAVA","Noha","Lamia","JUnit","Japan","Kotlin","Flower")
        /* Liste des longueurs des mots */
        val listLength: MutableList<Int> = mutableListOf()
        var score=0
        for (mot in listMots)
        {
            if (!(listLength.contains(mot.length)))
            {
                listLength.add(mot.length)

            }
        }
        val start = listLength.first()
        val end = listLength.last()
        val n =  rand(start,end)
        println("your number is $n")
        val random_n = findViewById <TextView>(R.id.random_n)
        random_n.setText(" $n")
        val check =findViewById<Button>(R.id.check)
        check.setOnClickListener { view ->
            val input = findViewById <EditText>(R.id.mot)
            val inputValue = input.text.toString()
            println("you intered $inputValue")
            if (inputValue != null) {
                if((inputValue.length)!=n) {
                    println("Erreur :  Vous avez pas entrer un mot de taille $n ")
                }
                else
                {
                    val result = getRandomWord(listMots,n)
                    val genere = findViewById <TextView>(R.id.mot_genere)
                    genere.setText(" $result")
                    println("le mot choisi aleatoirement est : $result")
                    if (result == inputValue)
                    {
                        score += 5
                        println("*** BRAVO ! votre score est augmente de 5, votre score est a  : $score ***")
                    }
                    else
                    {
                        println("*** ECHEC u.u votre score est a  : $score ***")
                    }
                }
            }
        }

    }
}