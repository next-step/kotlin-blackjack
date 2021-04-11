package blackjack.domain.state.started.finished

import blackjack.domain.Card
import blackjack.domain.CardShape
import blackjack.domain.CardType
import blackjack.domain.Cards
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
