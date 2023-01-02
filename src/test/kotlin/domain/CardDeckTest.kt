package domain

import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `divideTest`(){
        val cardDeck = CardDeck()
        println(cardDeck.divide().toString())
    }
}