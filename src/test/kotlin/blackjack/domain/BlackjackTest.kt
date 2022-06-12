package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class BlackjackTest {

    @Test
    fun `blackjack은 player와 deck을 가진다`() {
        val blackjack = Blackjack(listOf(Player("han")))

        assertThat(blackjack.players).hasSize(1)
    }

    @Test
    fun `han은 카드를 한 장 뽑을 수 있다`() {
        val player = Player("han")
        val blackjack = Blackjack(listOf(player))

        blackjack.drawingCard(player)

        assertThat(blackjack.players).hasSize(1)
        assertThat(blackjack.players.first().cards).hasSize(1)
    }

    @Test
    fun `han은 카드를 두 장 뽑을 수 있다`() {
        val player = Player("han")
        val blackjack = Blackjack(listOf(player))

        blackjack.drawingCards(player, 2)

        assertThat(blackjack.players).hasSize(1)
        assertThat(blackjack.players.first().cards).hasSize(2)
    }

    @ParameterizedTest
    @MethodSource("cardsArguments")
    fun `Player의 점수를 계산할 수 있다`(cards: List<Card>, points: Int) {
        val player = Player("han")
        val blackjack = Blackjack(listOf(player))

        player.addCards(cards)

        assertThat(blackjack.calculatePoints(player)).isEqualTo(points)
    }

    companion object {
        @JvmStatic
        fun cardsArguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf<Card>(), 0),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Denomination.ACE)
                    ),
                    11
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Denomination.JACK),
                        Card(Suit.DIAMOND, Denomination.QUEEN)
                    ),
                    20
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Denomination.JACK),
                        Card(Suit.DIAMOND, Denomination.ACE)
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Denomination.JACK),
                        Card(Suit.DIAMOND, Denomination.QUEEN),
                        Card(Suit.DIAMOND, Denomination.ACE)
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.DIAMOND, Denomination.ACE),
                        Card(Suit.SPADE, Denomination.JACK),
                        Card(Suit.DIAMOND, Denomination.QUEEN)
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Denomination.ACE),
                        Card(Suit.DIAMOND, Denomination.ACE)
                    ),
                    12
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Denomination.SIX),
                        Card(Suit.DIAMOND, Denomination.FIVE),
                        Card(Suit.SPADE, Denomination.ACE)
                    ),
                    12
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Denomination.FIVE),
                        Card(Suit.DIAMOND, Denomination.FIVE),
                        Card(Suit.SPADE, Denomination.ACE)
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Denomination.FOUR),
                        Card(Suit.DIAMOND, Denomination.FIVE),
                        Card(Suit.SPADE, Denomination.ACE)
                    ),
                    20
                ),
            )
        }
    }
}
