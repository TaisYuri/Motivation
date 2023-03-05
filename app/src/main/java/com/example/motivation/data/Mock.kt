package com.example.motivation.data

import com.example.motivation.infra.MotivationConstants
import kotlin.random.Random
import kotlin.random.nextInt

data class Phrase(val description: String, val categoryId: Int, val language: String)

class Mock {
    private val all = MotivationConstants.FILTER.ALL_INCLUSIVE
    private val happy = MotivationConstants.FILTER.HAPPY
    private val sunny = MotivationConstants.FILTER.SUNNY

    private val langPt = MotivationConstants.LANGUAGE.PORTUGUESE
    private val langEn = MotivationConstants.LANGUAGE.ENGLISH

    val mListPhrase = listOf<Phrase>(
        Phrase("Não sabendo que era impossível, foi lá e fez.", happy, langPt),
        Phrase(
            "Você não é derrotado quando perde, você é derrotado quando desiste!",
            happy,
            langPt
        ),
        Phrase("Quando está mais escuro, vemos mais estrelas!", happy, langPt),
        Phrase(
            "Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.",
            happy,
            langPt
        ),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", happy, langPt),
        Phrase(
            "O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?",
            happy,
            langPt
        ),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sunny, langPt),
        Phrase("Você perde todas as chances que você não aproveita.", sunny, langPt),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sunny, langPt),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", sunny, langPt),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sunny, langPt),
        Phrase("Se você acredita, faz toda a diferença.", sunny, langPt),
        Phrase(
            "Riscos devem ser corridos, porque o maior perigo é não arriscar nada!",
            sunny,
            langPt
        ),
        Phrase("Not knowing it was impossible, he went there and did it.", happy, langEn),
        Phrase(
            "You are not defeated when you lose, you are defeated when you give up!",
            happy,
            langEn
        ),
        Phrase("When it's darker, we see more stars!", happy, langEn),
        Phrase(
            "Insanity is always doing the same thing and expecting a different result.",
            happy,
            langEn
        ),
        Phrase("Don't stop when you're tired, stop when you're done.", happy, langEn),
        Phrase("What can you do now that has the biggest impact on your success?", happy, langEn),
        Phrase("The best way to predict the future is to invent it.", sunny, langEn),
        Phrase("You lose every chance you don't take.", sunny, langEn),
        Phrase("Failure is the spice that flavors success.", sunny, langEn),
        Phrase(" As long as we are not committed, there will be hesitation!", sunny, langEn),
        Phrase("If you don't know where you want to go, any way will do.", sunny, langEn),
        Phrase("If you believe, it makes all the difference.", sunny, langEn),
        Phrase(
            "Risks must be taken, because the greatest danger is not risking anything!",
            sunny,
            langEn
        ),

    )

    fun getPhrase(category: Int, language: String): Phrase{

        var langFilter = language.lowercase()

        // Caso a língua do usuário não esteja entre as disponíveis
        if(language !in listOf(langPt, langEn)){
            langFilter = langPt
        }

        val filtered = mListPhrase.filter { (it.categoryId == category ||  category == all) && (it.language == langFilter)}
        val randomNumber = Random.nextInt(filtered.size)
        return Phrase(filtered[randomNumber].description, filtered[randomNumber].categoryId, filtered[randomNumber].language)

    }
}