package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드 덱 생성을 생성 한다`() {
        val deck = CardDeck()
        deck.remainCount() shouldBe 52
    }

    @Test
    fun `덱에서 카드 한장을 얻을수 있다`() {
        val deck = CardDeck()
        println(deck.getNextCard())
    }
}
