package blackjack

import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.Denomination.EIGHT
import blackjack.model.Denomination.JACK
import blackjack.model.Denomination.KING
import blackjack.model.Denomination.NINE
import blackjack.model.Denomination.TEN
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Suit.CLOVER
import blackjack.model.Suit.DIAMOND
import blackjack.model.Suit.HEART
import blackjack.model.Suit.SPADE
import blackjack.view.InputView
import blackjack.view.OutputView
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackjackApplicationTest {
    @Test
    internal fun `딜러의 초기 카드 합이 16 이하면 1장을 추가로 받는다`() {
        // given
        val cardDeck = CardDeck.of(
            Card(SPADE, EIGHT),
            Card(HEART, EIGHT),
            Card(CLOVER, EIGHT),
            Card(DIAMOND, EIGHT)
        )
        val inputView = object : InputView {
            override val readPlayers: () -> String = { "pobi" }
            override val readPickAnswer: (Player) -> Boolean = { false }
        }

        val outputView = mockk<OutputView>(relaxed = true)
        val dealerAfterInit = slot<Player>()

        // when
        BlackjackApplication(cardDeck, inputView, outputView).play()
        verify { outputView.printInitCards(capture(dealerAfterInit), any()) }

        val dealerCardCount = dealerAfterInit.captured.cards.size
        val dealerScore = dealerAfterInit.captured.getFinalScore()

        // then
        assertThat(dealerCardCount).isEqualTo(3)
        assertThat(dealerScore == 0 || dealerScore > 16).isTrue
    }

    @Test
    internal fun `딜러의 초기 카드 합이 17 이상이면 추가로 받지 않는다`() {
        // given
        val cardDeck = CardDeck.of(
            Card(SPADE, NINE),
            Card(HEART, NINE),
            Card(CLOVER, NINE),
            Card(DIAMOND, NINE)
        )
        val inputView = object : InputView {
            override val readPlayers: () -> String = { "pobi" }
            override val readPickAnswer: (Player) -> Boolean = { false }
        }

        val outputView = mockk<OutputView>(relaxed = true)
        val dealerAfterInit = slot<Player>()

        // when
        BlackjackApplication(cardDeck, inputView, outputView).play()
        verify { outputView.printInitCards(capture(dealerAfterInit), any()) }

        val dealerCardCount = dealerAfterInit.captured.cards.size
        val dealerScore = dealerAfterInit.captured.getFinalScore()

        // then
        assertThat(dealerCardCount).isEqualTo(2)
        assertThat(dealerScore).isEqualTo(18)
    }

    @Test
    internal fun `전부 y로 응답하는 플레이어 테스트`() {
        // given
        val cardDeck = listOf(
            Card(SPADE, TEN),
            Card(HEART, TEN),
            Card(CLOVER, TEN),
            Card(CLOVER, JACK),
            Card(CLOVER, KING)
        )
        val clonedCardDeck = CardDeck.of(cardDeck)
        val inputView = object : InputView {
            override val readPlayers: () -> String = { "pobi" }
            override val readPickAnswer: (Player) -> Boolean = { true }
        }

        val outputView = mockk<OutputView>(relaxed = true)
        val playersAfterInit = slot<Players>()
        val playersAfterCardPick = slot<Player>()
        val playersAfterGameEnd = slot<Players>()

        // when
        BlackjackApplication(clonedCardDeck, inputView, outputView).play()
        verify { outputView.printInitCards(any(), capture(playersAfterInit)) }
        verify { outputView.printPlayerCards(capture(playersAfterCardPick)) }
        verify { outputView.printResult(capture(playersAfterGameEnd)) }

        val playersCount = playersAfterInit.captured.size
        val playerNames = playersAfterInit.captured.map { it.name }
        val playerCardCount = playersAfterCardPick.captured.cards.size
        val playerCardContains = playersAfterCardPick.captured.cards.all { cardDeck.contains(it) }
        val sumOfFinalScore = playersAfterGameEnd.captured.sumOf { it.getFinalScore() }

        // then
        assertThat(playersCount).isEqualTo(1)
        assertThat(playerNames).containsExactly("pobi")
        assertThat(playerCardCount).isEqualTo(3)
        assertThat(playerCardContains).isTrue
        assertThat(sumOfFinalScore).isEqualTo(0)
    }

    @Test
    internal fun `전부 n으로 응답하는 플레이어 테스트`() {
        // given
        val cardDeck = listOf(
            Card(SPADE, TEN),
            Card(HEART, TEN),
            Card(CLOVER, TEN),
            Card(DIAMOND, TEN),
            Card(DIAMOND, JACK),
            Card(DIAMOND, KING)
        )
        val clonedCardDeck = CardDeck.of(cardDeck)
        val inputView = object : InputView {
            override val readPlayers: () -> String = { "pobi, jason" }
            override val readPickAnswer: (Player) -> Boolean = { false }
        }

        val outputView = mockk<OutputView>(relaxed = true)
        val playersAfterInit = slot<Players>()
        val playersAfterGameEnd = slot<Players>()

        // when
        BlackjackApplication(clonedCardDeck, inputView, outputView).play()
        verify { outputView.printInitCards(any(), capture(playersAfterInit)) }
        verify { outputView.printResult(capture(playersAfterGameEnd)) }

        val playersCount = playersAfterInit.captured.size
        val playerNames = playersAfterInit.captured.map { it.name }
        val sumOfFinalScore = playersAfterGameEnd.captured.sumOf { it.getFinalScore() }

        // then
        assertThat(playersCount).isEqualTo(2)
        assertThat(playerNames).containsExactly("pobi", "jason")
        assertThat(sumOfFinalScore).isEqualTo(40)
    }
}
