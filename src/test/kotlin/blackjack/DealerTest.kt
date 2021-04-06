package blackjack

import blackjack.card.CardFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
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

}