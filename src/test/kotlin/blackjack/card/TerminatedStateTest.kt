package blackjack.card

import blackjack.card.helper.CardsTestFactory
import domain.card.Card
import domain.card.CardNumber
import domain.card.Suit
import domain.player.PlayerGameResult
import domain.state.Burst
import domain.state.TerminationState
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import org.junit.jupiter.api.Test

class TerminatedStateTest {

    @Test
    fun `종료 상태에서 딜러가 버스트 상태라면 승이다`() {
        val cards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
        )

        val dealerCards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.FIVE),
        )

        val state = TerminationState(cards)
        val dealerState = Burst(dealerCards)

        state.getPlayerGameResult(dealerState) shouldBe PlayerGameResult.WIN
    }

    @Test
    fun `종료 상태에서 딜러도 종료 상태일 때 플레이어의 카드 숫자 총합이 더 크면 승이다`() {
        val cards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
        )

        val dealerCards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.SEVEN),
        )

        val dealerState = TerminationState(dealerCards)

        val state = TerminationState(cards)

        state.getPlayerGameResult(dealerState) shouldBe PlayerGameResult.WIN
    }

    @Test
    fun `종료 상태에서 딜러도 종료 상태일 때 둘의 카드 숫자 총합이 같다면 무승무다`() {
        val cards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
        )

        val dealerState = TerminationState(cards)

        val state = TerminationState(cards)

        state.getPlayerGameResult(dealerState) shouldBe PlayerGameResult.DRAW
    }

    @Test
    fun `종료 상태에서 딜러도 종료 상태일 때 플레이어의 카드 숫자 총합이 딜러보다 작다면 패한다`() {
        val cards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.TWO),
        )

        val dealerCards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.SEVEN),
        )

        val dealerState = TerminationState(dealerCards)

        val state = TerminationState(cards)

        state.getPlayerGameResult(dealerState) shouldBe PlayerGameResult.LOSE
    }

    @Test
    fun `종료 상태에서 딜러가 버스트 상태면 플레이어의 수익률은 1이다`() {
        val cards = CardsTestFactory.makeStandCards()
        val dealerCards = CardsTestFactory.makeBurstCards()

        val dealerState = Burst(dealerCards)
        val state = TerminationState(cards = cards)

        state.getRevenueRate(dealerState) shouldBe BigDecimal.ONE
    }

    @Test
    fun `종료 상태에서 딜러도 종료 상태일 때 플레이어의 카드 숫자 총합이 딜러보다 크다면 수익률은 1이다`() {
        val cards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
        )

        val dealerCards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.SEVEN),
        )

        val dealerState = TerminationState(dealerCards)
        val state = TerminationState(cards = cards)

        state.getRevenueRate(dealerState) shouldBe BigDecimal.ONE
    }

    @Test
    fun `종료 상태에서 딜러도 종료 상태일 때 둘의 카드 숫자 총합이 같다면 수익률은 0이다`() {
        val cards = CardsTestFactory.makeStandCards()

        val dealerState = TerminationState(cards)
        val state = TerminationState(cards = cards)

        state.getRevenueRate(dealerState) shouldBe BigDecimal.ZERO
    }

    @Test
    fun `종료 상태에서 딜러도 종료 상태일 때 플레이어의 카드 숫자 총합이 딜러보다 작다면 수익률은 -1이다`() {
        val cards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.TWO),
        )

        val dealerCards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.SEVEN),
        )

        val dealerState = TerminationState(dealerCards)

        val state = TerminationState(cards = cards)

        state.getRevenueRate(dealerState) shouldBe BigDecimal(-1)
    }
}
