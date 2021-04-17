package com.example.exo3_tp2
import kotlin.random.Random
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

        val listLength: MutableList<Int> = mutableListOf()
        val scoreView = findViewById <TextView>(R.id.score)
        var genere = findViewById <TextView>(R.id.mot_genere)
        val input = findViewById <EditText>(R.id.mot)
        val random_n = findViewById <TextView>(R.id.random_n)
        val check =findViewById<Button>(R.id.check)
        var score=0
        genere.setText("")
        scoreView.setText("$score points ")

        for (mot in listMots)
        {
            if (!(listLength.contains(mot.length)))
            {
                listLength.add(mot.length)

            }
        }
        val start = listLength.first()
        val end = listLength.last()

        var n =  rand(start,end)


        random_n.setText(" $n")

        check.setOnClickListener { view ->


            val inputValue = input.text.toString()

            if (inputValue != null) {
                if((inputValue.length)!=n) {
                    Toast.makeText(getApplicationContext(), "Erreur :  Vous avez pas entrer un mot de taille $n ", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    val result = getRandomWord(listMots,n)

                    genere.setText("$result")

                    if (result == inputValue)
                    {   score+=5
                        scoreView.setText("$score points")

                        Toast.makeText(getApplicationContext(), "BRAVO !!!! vous avez obtenu 5points", Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Echec !!!! Le mot saisi n’est pas le bon", Toast.LENGTH_SHORT).show();

                    }
                }

            }

            n =  rand(start,end)
            random_n.setText(" $n")
            input.getText().clear()



        }

    }
}