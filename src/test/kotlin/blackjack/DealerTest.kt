package blackjack

import blackjack.card.Card
import blackjack.card.CardFactory
import blackjack.card.CardType
import blackjack.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러 카드 개수 확인`() {
        val makeCards = CardFactory.makeCards()
        val dealer = Dealer(makeCards)
        assertThat(dealer.cardCount).isEqualTo(makeCards.size)
    }

    @Test
    fun `딜러 카드 개수 확인 - 카드 한 개 준 후 확인`() {
        val makeCards = CardFactory.makeCards()
        val dealer = Dealer(makeCards)
        dealer.giveCard()
        assertThat(dealer.cardCount).isEqualTo(makeCards.size - 1)
    }

    @Test
    fun `딜러 카드 개수 확인 - 카드 두 개 준 후 확인`() {
        val makeCards = CardFactory.makeCards()
        val dealer = Dealer(makeCards)
        dealer.giveTwoCards()
        assertThat(dealer.cardCount).isEqualTo(makeCards.size - 2)
    }

    @Test
    fun `딜러 플레이어 - 카드 지급 가능 확인`() {
        val cards = listOf(Card(CardType.CLOVER, CardValue.SIX), Card(CardType.CLOVER, CardValue.QUEEN))
        val dealer = Dealer(cards)
        dealer.receiveCards(dealer.giveTwoCards())
        assertThat(dealer.isReceiveMoreCard()).isEqualTo(true)
    }

    @Test
    fun `딜러 플레이어 - 카드 지급 불가능 확인`() {
        val cards = listOf(Card(CardType.CLOVER, CardValue.SEVEN), Card(CardType.CLOVER, CardValue.QUEEN))
        val dealer = Dealer(cards)
        dealer.receiveCards(dealer.giveTwoCards())
        assertThat(dealer.isReceiveMoreCard()).isEqualTo(false)
    }
}
