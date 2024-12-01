package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun initCardDeckTest() {
        val cardDeck = CardDeck
        cardDeck.fillDeck(Card.cards)
        cardDeck.cards.size shouldBe 52
    }

    @Test
    fun drawCardTest() {
        val cardDeck = CardDeck
        cardDeck.fillDeck(Card.cards)
        val card = cardDeck.drawCard()

        cardDeck.cards.size shouldBe 51
        cardDeck.cards.find { it == card } shouldBe null
    }
}
