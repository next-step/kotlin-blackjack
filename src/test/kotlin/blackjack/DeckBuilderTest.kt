package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckBuilderTest {
    @Test
    fun `덱 생성`() {
        val actual =
            createDeck {
                cards {
                    "A" to Suit.CLUB
                    "2" to Suit.SPADE
                }
            }

        val expected = Deck(listOf(Card("A", Suit.CLUB), Card("2", Suit.SPADE)))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `덱에서 카드를 뽑는다`() {
        val deck: Deck =
            createDeck {
                cards {
                    "A" to Suit.CLUB
                    "2" to Suit.SPADE
                }
            }

        val actual = deck.popOf(1)

        val expected = Card("2", Suit.SPADE)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Cache 덱은 52장의 카드를 가진다`() {
        val actual: Deck = DeckBuilder.createDeck()

        val expected = 52

        assertThat(actual.size()).isEqualTo(expected)
    }
}
