import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class HandTest {
    @Test
    fun `Throw exception when hand has cards less than 2`() {
        assertThrows<IllegalArgumentException> {
            Hand(listOf(CLUB_SEVEN))
        }
    }

    @Test
    fun `Create Hand when hand has cards more than 2`() {
        assertDoesNotThrow {
            Hand(listOf(CLUB_SEVEN, CLUB_SEVEN))
        }
    }

    @Test
    fun `Return total score 9 of cards`() {
        // given
        val cards = listOf(CLUB_SEVEN, CLUB_TWO)
        val hand = Hand(cards)
        val expected = 9

        // when
        val actual = hand.score()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Return total score 10 of cards`() {
        // given
        val cards = listOf(CLUB_SEVEN, CLUB_THREE)
        val hand = Hand(cards)
        val expected = 10

        // when
        val actual = hand.score()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
