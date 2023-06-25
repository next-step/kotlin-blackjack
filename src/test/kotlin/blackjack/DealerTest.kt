package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.player.Dealer
import blackjack.domain.player.PlayerStatus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `처음받은 2장의 score 합계가 16이하이면 추가 카드를 받을 수 있는 상태가 된다`() {
        val dealer = Dealer()
        val cards = Cards.from(
            Card(Denomination.ACE, CardType.CLUBS),
            Card(Denomination.FIVE, CardType.HEARTS),
        )

        dealer.getPlayerStatus() shouldBe PlayerStatus.NOT_INIT
        dealer.initCards(cards)
        dealer.getPlayerStatus() shouldBe PlayerStatus.RECEIVE
    }

    @Test
    fun `처음받은 2장의 score 합계가 17이상이면 추가 카드를 받을 수 없는 상태가 된다`() {
        val dealer = Dealer()
        val cards = Cards.from(
            Card(Denomination.JACK, CardType.CLUBS),
            Card(Denomination.JACK, CardType.HEARTS),
        )

        dealer.getPlayerStatus() shouldBe PlayerStatus.NOT_INIT
        dealer.initCards(cards)
        dealer.getPlayerStatus() shouldBe PlayerStatus.STAY
    }

    @Test
    fun `카드 score 합계가 21을 초과하면 BUST 상태로 변경된다`() {
        val dealer = Dealer()
        val cards = Cards.from(
            Card(Denomination.JACK, CardType.CLUBS),
            Card(Denomination.SIX, CardType.HEARTS),
        )
        val addCard = Card(Denomination.EIGHT, CardType.DIAMONDS)

        dealer.initCards(cards)
        dealer.receiveCard(addCard)

        dealer.cards.getOptimizedScore() shouldBe 24
        dealer.getPlayerStatus() shouldBe PlayerStatus.BUST
    }
}
