package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class PlayerTest() {

    private val player = Player(name = "mark")
    private val newCard = Card(Pair(CardScore.SEVEN, Suit.SPADE))

    @Test
    fun `draw a card`() {
        // when
        val cards = player.draw(newCard).toList()

        // then
        assertThat(cards[0]).isEqualTo(newCard)
    }

    @Test
    fun `has score more than maximum score`() {
        // when
        val isMoreThanMax = player.hasScoreMoreThanMax()

        // then
        assertFalse(isMoreThanMax)
    }
}
