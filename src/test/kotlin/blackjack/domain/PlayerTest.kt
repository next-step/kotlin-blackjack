package blackjack.domain

import blackjack.enums.Type
import blackjack.enums.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `a player show all of card`() {
        val name = "qyu"
        val cards = Cards(
            listOf(
                Card(Type.SPADE, Value.KING),
                Card(Type.HEART, Value.NINE)
            )
        )

        val player = Player(name, cards)

        assertThat(player.toString())
            .contains("KING of SPADE", "NINE of HEART")
    }

    @Test
    fun `player cannot draw a card if the score is greater than 21`() {
        val name = "qyu"
        val cards = Cards(
            listOf(
                Card(Type.SPADE, Value.KING),
                Card(Type.HEART, Value.NINE),
                Card(Type.HEART, Value.EIGHT),
            )
        )

        val player = Player(name, cards)

        assertThat(player.couldGetMoreCard()).isFalse

        val name2 = "qyu2"
        val cards2 = Cards(
            listOf(
                Card(Type.SPADE, Value.KING),
                Card(Type.HEART, Value.NINE),
            )
        )

        val player2 = Player(name2, cards2)

        assertThat(player2.couldGetMoreCard()).isTrue
    }

    @Test
    fun `the highest score between origin and alternative is a score close to 21 and less than 21`() {
        val name = "qyu"
        val cards = Cards(
            listOf(
                Card(Type.SPADE, Value.ACE),
                Card(Type.HEART, Value.NINE),
                Card(Type.HEART, Value.TEN),
            )
        )
        val expected = 20

        val player = Player(name, cards)

        assertThat(player.result()).isEqualTo(expected)
    }
}
