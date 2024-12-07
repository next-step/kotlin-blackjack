package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckBuilderTest {
    @Test
    fun `덱 생성`() {
        val actual =
            createDeck {
                CardRank.ACE to Suit.CLUB
            }

        val expected = Deck(listOf(Card(CardRank.ACE, Suit.CLUB)))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `덱에서 카드를 뽑는다`() {
        val deck: Deck =
            createDeck {
                CardRank.ACE to Suit.CLUB
                CardRank.TWO to Suit.SPADE
            }

        val actual = deck.popOf(1)

        val expected = Card(CardRank.TWO, Suit.SPADE)
        assertThat(actual).isEqualTo(expected)
    }
}
