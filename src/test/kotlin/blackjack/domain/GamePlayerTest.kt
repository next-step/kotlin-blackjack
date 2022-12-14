package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GamePlayerTest {
    @Test
    fun `참가자는 카드를 받을 수 있다`() {
        val card = Card.of(CardNumber.FIVE, CardShape.CLOVER)
        val deck = Deck(listOf(card))
        val player = GamePlayer("pobi").hit(deck)
        player.cards.count() shouldBe 1
        player.cards.contains(card) shouldBe true
    }
}
