import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DeckTest {
    @Test
    fun `Return playing cards`() {
        // given
        val deck = Deck(listOf(CLUB_SEVEN, CLUB_THREE, CLUB_KING))

        // when
        val actual = deck.drawCard(1)

        // then
        assertAll(
            { assertThat(actual.size).isEqualTo(1) },
            { assertThat(deck.cards.size).isEqualTo(2) },
        )
    }
}
