package domain.player

import domain.card.Card
import domain.card.CardNumber
import domain.card.Suit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 카드 합계가 16이하이면 반드시 1장의 카드를 추가로 받는다`() {
        val card1 = Card(suit = Suit.SPADE, number = CardNumber.FIVE)
        val card2 = Card(suit = Suit.SPADE, number = CardNumber.FIVE)
        val dealer = Dealer(card1 = card1, card2 = card2)
        val newCard = Card(suit = Suit.HEART, number = CardNumber.FIVE)

        dealer.draw(newCard)

        dealer.cards.sum shouldBe 15
    }

    @Test
    fun `딜러는 카드 합계가 17 이상이면 반드시 1장의 카드를 추가로 받지 않는다`() {
        val card1 = Card(suit = Suit.SPADE, number = CardNumber.JACK)
        val card2 = Card(suit = Suit.SPADE, number = CardNumber.SEVEN)
        val dealer = Dealer(card1 = card1, card2 = card2)

        dealer.isDrawable() shouldBe false
    }
}
