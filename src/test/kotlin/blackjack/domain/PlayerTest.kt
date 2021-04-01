package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `21값이 넘지 않을 경우 true`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.DIAMOND, Rank.FIVE),
                Card(Suit.HEART, Rank.QUEEN)
            )
        )
        val player = Player("test", cards)

        assertThat(player.isMoreCard()).isEqualTo(true)
    }

    @Test
    fun `21값을 넘을 경우 false`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.DIAMOND, Rank.FIVE),
                Card(Suit.HEART, Rank.QUEEN),
                Card(Suit.CLUB, Rank.EIGHT)
            )
        )
        val player = Player("test", cards)

        assertThat(player.isMoreCard()).isEqualTo(false)
    }

    @Test
    fun `21값과 같을 경우 false`() {
        val cards = Cards(
            mutableListOf(
                Card(Suit.DIAMOND, Rank.ACE),
                Card(Suit.HEART, Rank.QUEEN)
            )
        )
        val player = Player("test", cards)

        assertThat(player.isMoreCard()).isEqualTo(false)
    }
}
