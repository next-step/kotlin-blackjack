package blackjack.domain

import blackjack.enums.Type
import blackjack.enums.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `Cards object can add using plus operator`() {
        val cardsOne = Cards(listOf(Card(Type.SPADE, Value.EIGHT)))
        val cardsTwo = Cards(listOf(Card(Type.HEART, Value.TEN)))
        val expected = Cards(
            listOf(
                Card(Type.SPADE, Value.EIGHT),
                Card(Type.HEART, Value.TEN)
            )
        )

        val result = cardsOne + cardsTwo

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `player can take card from cards`() {
        val cards = Cards(
            listOf(
                Card(Type.SPADE, Value.ACE),
                Card(Type.HEART, Value.ACE),
                Card(Type.CLUB, Value.ACE),
                Card(Type.DIAMOND, Value.ACE)
            )
        )
        val expected = Cards(
            listOf(
                Card(Type.SPADE, Value.ACE),
                Card(Type.HEART, Value.ACE),
            )
        )

        val result = cards.take(2)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `player remove cards after take the cards`() {
        val cards = Cards(
            listOf(
                Card(Type.SPADE, Value.ACE),
                Card(Type.HEART, Value.ACE),
                Card(Type.CLUB, Value.ACE),
                Card(Type.DIAMOND, Value.ACE)
            )
        )
        val removeTarget = Cards(
            listOf(
                Card(Type.CLUB, Value.ACE),
                Card(Type.DIAMOND, Value.ACE)
            )
        )
        val expected = Cards(
            listOf(
                Card(Type.SPADE, Value.ACE),
                Card(Type.HEART, Value.ACE),
            )
        )

        val result = cards.removeAll(removeTarget)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `player can get score from the cards`() {
        val cards = Cards(
            listOf(
                Card(Type.SPADE, Value.ACE),
                Card(Type.HEART, Value.TEN),
            )
        )
        val expected = Score(11, 21)

        val result = cards.score()

        assertThat(result).isEqualTo(expected)
    }
}
