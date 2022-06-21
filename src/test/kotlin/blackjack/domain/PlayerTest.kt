package blackjack.domain

import blackjack.domain.Denomination.ACE
import blackjack.domain.Denomination.FIVE
import blackjack.domain.Denomination.FOUR
import blackjack.domain.Denomination.JACK
import blackjack.domain.Denomination.QUEEN
import blackjack.domain.Denomination.SIX
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

        player.addCard(Card(Suit.SPADE, ACE))
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
                Card(Suit.SPADE, ACE), Card(Suit.SPADE, Denomination.TWO)
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
                Arguments.of(createCards(ACE), 11),
                Arguments.of(createCards(JACK, QUEEN), 20),
                Arguments.of(createCards(JACK, ACE), 21),
                Arguments.of(createCards(JACK, QUEEN, ACE), 21),
                Arguments.of(createCards(ACE, JACK, QUEEN), 21),
                Arguments.of(createCards(ACE, ACE), 12),
                Arguments.of(createCards(ACE, ACE, ACE), 13),
                Arguments.of(createCards(ACE, ACE, ACE, ACE), 14),
                Arguments.of(createCards(SIX, FIVE, ACE), 12),
                Arguments.of(createCards(FIVE, FIVE, ACE), 21),
                Arguments.of(createCards(FOUR, FIVE, ACE), 20),
            )
        }

        private fun createCards(vararg denominations: Denomination): List<Card> {
            return denominations.map { Card(Suit.DIAMOND, it) }
        }
    }
}
