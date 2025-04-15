import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `Return total score 7 of cards`() {
        // given
        val cards = listOf(PlayingCard(Suit.CLUB, Denomination.SEVEN))
        val hand = Hand(cards)
        val expected = 7

        // when
        val actual = hand.score()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Return total score 10 of cards`() {
        // given
        val cards = listOf(PlayingCard(Suit.CLUB, Denomination.SEVEN), PlayingCard(Suit.CLUB, Denomination.THREE))
        val hand = Hand(cards)
        val expected = 10

        // when
        val actual = hand.score()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
