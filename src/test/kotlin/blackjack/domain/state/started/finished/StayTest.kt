package blackjack.domain.state.started.finished

import blackjack.domain.Card
import blackjack.domain.CardShape
import blackjack.domain.CardType
import blackjack.domain.Cards
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class StayTest {

    @Test
    fun `cardPoint가 dealer보다 큰 경우 profit을 계산한다`() {
        val playerCards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.ACE)))
        val dealerCards = Cards(listOf(Card(CardShape.SPADE, CardType.TWO), Card(CardShape.SPADE, CardType.ACE)))
        val playerState = Stay(playerCards)
        val dealerState = Stay(dealerCards)

        val profit = playerState.profit(10000, dealerState)
        Assertions.assertThat(profit).isEqualTo(BigDecimal(10000))
    }

    @Test
    fun `cardPoint가 dealer보다 작은 경우 profit을 계산한다`() {
        val playerCards = Cards(listOf(Card(CardShape.SPADE, CardType.TWO), Card(CardShape.SPADE, CardType.ACE)))
        val dealerCards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.ACE)))
        val playerState = Stay(playerCards)
        val dealerState = Stay(dealerCards)

        val profit = playerState.profit(10000, dealerState)
        Assertions.assertThat(profit).isEqualTo(BigDecimal(-10000))
    }

    @Test
    fun `cardPoint가 dealer와 같은 경우 profit을 계산한다`() {
        val playerCards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.ACE)))
        val dealerCards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.ACE)))
        val playerState = Stay(playerCards)
        val dealerState = Stay(dealerCards)

        val profit = playerState.profit(10000, dealerState)
        Assertions.assertThat(profit).isEqualTo(BigDecimal(0))
    }

    @Test
    fun `dealer가 blackjack인 경우 profit을 계산한다`() {
        val playerCards = Cards(listOf(Card(CardShape.SPADE, CardType.QUEEN), Card(CardShape.SPADE, CardType.ACE)))
        val dealerCards = Cards(listOf(Card(CardShape.SPADE, CardType.TWO), Card(CardShape.SPADE, CardType.ACE)))
        val playerState = Stay(playerCards)
        val dealerState = BlackJack(dealerCards)

        val profit = playerState.profit(10000, dealerState)
        Assertions.assertThat(profit).isEqualTo(BigDecimal(-15000))
    }
}
