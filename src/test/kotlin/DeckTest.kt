import card.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DeckTest {
    @Test
    fun `Return playing cards`() {
        // given
        val deck = Deck(listOf(CLUB_SEVEN, CLUB_THREE, CLUB_KING))

        // when
        val actual = deck.drawOne()

        // then
        assertAll(
            { assertThat(actual).isEqualTo(CLUB_SEVEN) },
            { assertThat(deck.cards.size).isEqualTo(2) },
        )
    }
}
