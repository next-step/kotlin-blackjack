package blackjack.domain.state.started.finished

import blackjack.domain.card.CardType
import blackjack.domain.cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class StayTest {

    @Test
    fun `cardPoint가 dealer보다 큰 경우 profit을 계산한다`() {
        val playerCards = cards(CardType.QUEEN, CardType.ACE)
        val dealerCards = cards(CardType.TWO, CardType.ACE)
        val playerState = Stay(playerCards)
        val dealerState = Stay(dealerCards)

        val profit = playerState.profit(10000, dealerState)
        assertThat(profit).isEqualTo(BigDecimal(10000))
    }

    @Test
    fun `cardPoint가 dealer보다 작은 경우 profit을 계산한다`() {
        val playerCards = cards(CardType.TWO, CardType.ACE)
        val dealerCards = cards(CardType.QUEEN, CardType.ACE)

        val playerState = Stay(playerCards)
        val dealerState = Stay(dealerCards)

        val profit = playerState.profit(10000, dealerState)
        assertThat(profit).isEqualTo(BigDecimal(-10000))
    }

    @Test
    fun `cardPoint가 dealer와 같은 경우 profit을 계산한다`() {
        val playerCards = cards(CardType.QUEEN, CardType.ACE)
        val dealerCards = cards(CardType.QUEEN, CardType.ACE)
        val playerState = Stay(playerCards)
        val dealerState = Stay(dealerCards)

        val profit = playerState.profit(10000, dealerState)
        assertThat(profit).isEqualTo(BigDecimal(0))
    }

    @Test
    fun `dealer가 blackjack인 경우 profit을 계산한다`() {
        val playerCards = cards(CardType.QUEEN, CardType.TEN)
        val dealerCards = cards(CardType.QUEEN, CardType.ACE)
        val playerState = Stay(playerCards)
        val dealerState = BlackJack(dealerCards)

        val profit = playerState.profit(10000, dealerState)
        assertThat(profit).isEqualTo(BigDecimal(-10000))
    }

    @Test
    fun `dealer가 21을 초과하고, player는 초과하지 않은 경우 profit을 계산한다`() {
        val playerCards = cards(CardType.TWO, CardType.ACE)
        val dealerCards = cards(CardType.QUEEN, CardType.JACK, CardType.TEN)
        val playerState = Stay(playerCards)
        val dealerState = Bust(dealerCards)

        val profit = playerState.profit(10000, dealerState)
        assertThat(profit).isEqualTo(BigDecimal(10000))
    }
}
