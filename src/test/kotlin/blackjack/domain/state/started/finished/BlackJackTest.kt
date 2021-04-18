package blackjack.domain.state.started.finished

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions

import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class BlackJackTest {

    @Test
    fun `dealerState가 Blackjack인 경우 profit 계산`() {
        val blackjackCards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.ACE)))
        val playerState = BlackJack(blackjackCards)
        val dealerState = BlackJack(blackjackCards)

        val profit = playerState.profit(10000, dealerState)
        Assertions.assertThat(profit).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun `dealerState가 Blackjack이 아닌 경우 profit 계산`() {
        val blackjackCards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.ACE)))
        val playerState = BlackJack(blackjackCards)
        val dealerState = Stay(blackjackCards)

        val profit = playerState.profit(10000, dealerState)
        Assertions.assertThat(profit).isEqualTo(BigDecimal(15000))
    }
}
