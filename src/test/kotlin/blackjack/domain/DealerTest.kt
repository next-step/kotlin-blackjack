package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 이름과 카드 및 덱을 가진다`() {
        val deck = Deck()
        val dealer = Dealer(name = "딜러", cards = deck.drawCard(2), deck = deck)

        dealer.name shouldBe "딜러"
        dealer.cards.cards.size shouldBe BASIC_CARD_COUNT
        dealer.deck.cardCount shouldBe TOTAL_CARD_COUNT - BASIC_CARD_COUNT
    }

    companion object {
        const val TOTAL_CARD_COUNT = 52
        const val BASIC_CARD_COUNT = 2
    }
}
