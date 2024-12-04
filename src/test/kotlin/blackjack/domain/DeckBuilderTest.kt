package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckBuilderTest {
    @Test
    fun `덱 생성`() {
        val actual =
            createDeck {
                "A" to Suit.CLUB
            }

        val expected = Deck(listOf(Card.of("A", Suit.CLUB)))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `덱에서 카드를 뽑는다`() {
        val deck: Deck =
            createDeck {
                "A" to Suit.CLUB
                "2" to Suit.SPADE
            }

        val actual = deck.popOf(1)

        val expected = Card.of("2", Suit.SPADE)
        assertThat(actual).isEqualTo(expected)
    }
}
