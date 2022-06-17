package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PlayerTest {

    @Test
    fun `Player는 이름을 가진다`() {
        val name = "han"
        val player = Player(name)

        assertThat(player.name).isEqualTo(name)
    }

    @Test
    fun `Player는 카드를 가진다`() {
        val name = "han"
        val player = Player(name)

        assertThat(player.name).isEqualTo(name)
        assertThat(player.cards).hasSize(0)
    }

    @Test
    fun `Player에 카드를 1장 추가할 수 있다`() {
        val name = "han"
        val player = Player(name)

        assertThat(player.name).isEqualTo(name)
        assertThat(player.cards).hasSize(0)

        player.addCard(Card(Suit.SPADE, Denomination.ACE))
        assertThat(player.cards).hasSize(1)
    }

    @Test
    fun `Player에 카드를 2장 추가할 수 있다`() {
        val name = "han"
        val player = Player(name)

        assertThat(player.name).isEqualTo(name)
        assertThat(player.cards).hasSize(0)

        player.addCards(
            listOf(
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.TWO)
            )
        )
        assertThat(player.cards).hasSize(2)
    }

    @ParameterizedTest
    @MethodSource("cardsArguments")
    fun `Player의 점수를 계산할 수 있다`(cards: List<Card>, points: Int) {
        val player = Player("player")

        player.addCards(cards)

        assertThat(player.getPoints()).isEqualTo(points)
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
