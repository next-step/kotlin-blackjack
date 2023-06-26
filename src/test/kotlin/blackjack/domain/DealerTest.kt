package blackjack.domain

import blackjack.domain.enums.Condition
import blackjack.enums.Rank
import blackjack.enums.Symbol
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

    @Test
    fun `딜러도 마찬가지로 카드를 받기 위한 응답 여부의 조건값을 가진다`() {
        val deck = Deck()
        val dealer = Dealer(name = "딜러", cards = deck.drawCard(2), deck = deck)
        dealer.currentCondition() shouldBe Condition.STAY
    }

    @Test
    fun `딜러는 처음 받은 2장의 카드의 합이 16 이하면 1장의 카드를 추가로 받기 위한 상태로 변한다`() {
        val deck = Deck()
        val cards = Cards(listOf(Card(rank = Rank.TWO, symbol = Symbol.SPADES), Card(rank = Rank.KING, symbol = Symbol.SPADES)))
        val dealer = Dealer(name = "딜러", cards = cards, deck = deck)
        dealer.currentCondition() shouldBe Condition.PLAY
    }

    companion object {
        const val TOTAL_CARD_COUNT = 52
        const val BASIC_CARD_COUNT = 2
    }
}
