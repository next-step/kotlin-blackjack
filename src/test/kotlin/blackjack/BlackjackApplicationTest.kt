package blackjack

import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.Denomination.EIGHT
import blackjack.model.Denomination.NINE
import blackjack.model.Denomination.SEVEN
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
    internal fun `전부 y로 응답하는 play 테스트`() {
        // given
        val cardDeck = listOf(
            Card(SPADE, TEN),
            Card(HEART, TEN),
            Card(CLOVER, TEN)
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
        verify { outputView.printInitCards(capture(playersAfterInit)) }
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
    internal fun `전부 n으로 응답하는 play 테스트`() {
        // given
        val cardDeck = listOf(
            Card(SPADE, TEN),
            Card(HEART, NINE),
            Card(CLOVER, EIGHT),
            Card(DIAMOND, SEVEN)
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
        verify { outputView.printInitCards(capture(playersAfterInit)) }
        verify { outputView.printResult(capture(playersAfterGameEnd)) }

        val playersCount = playersAfterInit.captured.size
        val playerNames = playersAfterInit.captured.map { it.name }
        val sumOfFinalScore = playersAfterGameEnd.captured.sumOf { it.getFinalScore() }

        // then
        assertThat(playersCount).isEqualTo(2)
        assertThat(playerNames).containsExactly("pobi", "jason")
        assertThat(sumOfFinalScore).isEqualTo(34)
    }
}
