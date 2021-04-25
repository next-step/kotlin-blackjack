package blackjack.domain.state.started.finished

import blackjack.domain.card.CardType
import blackjack.domain.cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class BlackJackTest {

    @Test
    fun `dealerState가 Blackjack인 경우 profit 계산`() {
        val blackjackCards = cards(CardType.QUEEN, CardType.ACE)
        val playerState = BlackJack(blackjackCards)
        val dealerState = BlackJack(blackjackCards)

        val profit = playerState.profit(10000, dealerState)
        assertThat(profit).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun `dealerState가 stay인 경우 profit 계산`() {
        val blackjackCards = cards(CardType.QUEEN, CardType.ACE)
        val stayCards = cards(CardType.QUEEN, CardType.JACK)
        val playerState = BlackJack(blackjackCards)
        val dealerState = Stay(stayCards)

        val profit = playerState.profit(10000, dealerState)
        assertThat(profit).isEqualTo(BigDecimal(15000))
    }

    @Test
    fun `dealerState가 bust인 경우 profit 계산`() {
        val blackjackCards = cards(CardType.QUEEN, CardType.ACE)
        val bustCards = cards(CardType.QUEEN, CardType.JACK, CardType.TEN)

        val playerState = BlackJack(blackjackCards)
        val dealerState = Bust(bustCards)

        val profit = playerState.profit(10000, dealerState)
        assertThat(profit).isEqualTo(BigDecimal(15000))
    }
}
