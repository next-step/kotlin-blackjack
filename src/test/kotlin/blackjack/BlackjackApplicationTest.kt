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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackjackApplicationTest {
    @Test
    internal fun `전부 y로 응답하는 play 테스트`() {
        // given
        val cardDeck = listOf(
            Card(SPADE, TEN),
            Card(HEART, TEN),
            Card(CLOVER, TEN),
        )
        val inputView = object : InputView {
            override val readPlayers: () -> String = { "pobi" }
            override val readPickAnswer: (Player) -> Boolean = { true }
        }

        // then
        val assertionView = object : OutputView {
            override val printInitCards: (Players) -> Unit = { players ->
                val playerNames = players.map { it.name }
                assertThat(players.size).isEqualTo(1)
                assertThat(playerNames).containsExactly("pobi")
            }
            override val printPlayerCards: (Player) -> Unit = { player ->
                assertThat(player.cards.size).isEqualTo(3)
                assertThat(cardDeck.containsAll(player.cards)).isTrue
            }
            override val printResult: (Players) -> Unit = { players ->
                val sumOfFinalScore = players.sumOf { it.getFinalScore() }
                assertThat(sumOfFinalScore).isEqualTo(0)
            }
        }

        // when
        val clonedCardDeck = CardDeck.of(cardDeck)
        val application = BlackjackApplication(clonedCardDeck, inputView, assertionView)
        application.play()
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
        val inputView = object : InputView {
            override val readPlayers: () -> String = { "pobi, jason" }
            override val readPickAnswer: (Player) -> Boolean = { false }
        }

        // then
        val assertionView = object : OutputView {
            override val printInitCards: (Players) -> Unit = { players ->
                val playerNames = players.map { it.name }
                assertThat(players.size).isEqualTo(2)
                assertThat(playerNames).containsExactly("pobi", "jason")
            }
            override val printPlayerCards: (Player) -> Unit = { player ->
                assertThat(player.cards.size).isEqualTo(2)
                assertThat(cardDeck.containsAll(player.cards)).isTrue
            }
            override val printResult: (Players) -> Unit = { players ->
                val sumOfFinalScore = players.sumOf { it.getFinalScore() }
                assertThat(sumOfFinalScore).isEqualTo(34)
            }
        }

        // when
        val clonedCardDeck = CardDeck.of(cardDeck)
        val application = BlackjackApplication(clonedCardDeck, inputView, assertionView)
        application.play()
    }
}
