package blackjack.card

import blackjack.card.helper.CardsTestFactory
import domain.card.Card
import domain.card.CardNumber
import domain.card.Suit
import domain.player.PlayerGameResult
import domain.state.Burst
import domain.state.TerminationState
import io.kotest.matchers.shouldBe
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
    fun `종료 상태에서 딜러가 버스트 상태면 플레이어는 베팅 금액만큼 수익을 얻는다`() {
        val cards = CardsTestFactory.makeStandCards()
        val dealerCards = CardsTestFactory.makeBurstCards()
        val betAmount = 1000

        val dealerState = Burst(dealerCards)
        val state = TerminationState(cards = cards, betAmount = betAmount)

        state.getRevenue(dealerState) shouldBe 1000
    }

    @Test
    fun `종료 상태에서 딜러도 종료 상태일 때 플레이어의 카드 숫자 총합이 딜러보다 크다면 베팅 금액만큼 수익을 얻는다`() {
        val cards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
        )

        val dealerCards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.SEVEN),
        )

        val betAmount = 1000

        val dealerState = TerminationState(dealerCards)
        val state = TerminationState(cards = cards, betAmount = betAmount)

        state.getRevenue(dealerState) shouldBe 1000
    }

    @Test
    fun `종료 상태에서 딜러도 종료 상태일 때 둘의 카드 숫자 총합이 같다면 수익은 0이다`() {
        val cards = CardsTestFactory.makeStandCards()

        val betAmount = 1000

        val dealerState = TerminationState(cards)
        val state = TerminationState(cards = cards, betAmount = betAmount)

        state.getRevenue(dealerState) shouldBe 0
    }

    @Test
    fun `종료 상태에서 딜러도 종료 상태일 때 플레이어의 카드 숫자 총합이 딜러보다 작다면 베팅 금액을 잃는다`() {
        val cards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.TWO),
        )

        val dealerCards = CardsTestFactory.makeCards(
            Card(suit = Suit.SPADE, number = CardNumber.JACK),
            Card(suit = Suit.SPADE, number = CardNumber.SEVEN),
        )

        val betAmount = 1000

        val dealerState = TerminationState(dealerCards)

        val state = TerminationState(cards = cards, betAmount = betAmount)

        state.getRevenue(dealerState) shouldBe -1000
    }
}
