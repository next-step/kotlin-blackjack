package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeckTest {
    private lateinit var ACE_CLUB: Card
    private lateinit var TEN_CLUB: Card
    private lateinit var cards: Set<Card>
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        ACE_CLUB = Card(CardScore.ACE, Suit.CLUB)
        TEN_CLUB = Card(CardScore.TEN, Suit.CLUB)
        cards = setOf(ACE_CLUB)
        deck = Deck(cards, RandomShufflingStrategy)
    }

    @Test
    fun `원하는 카드를 뽑을 수 있다`() {
        // given
        val deck = Deck(
            cards,
            object : ShufflingStrategy {
                override fun shuffle(cards: Set<Card>): Set<Card> = cards.sortedBy { it.score }.toSet()
            }
        )

        // when
        val actual: Card = deck.provideCard()

        // then
        assertThat(actual).isEqualTo(Card(CardScore.ACE, Suit.CLUB))
    }

    @Test
    fun `카드 한 장을 뽑아 제거한다`() {
        // when
        val actual = deck.provideCard()

        // then
        assertThat(actual).isEqualTo(ACE_CLUB)
        assertThat(deck.size).isEqualTo(0)
    }

    @Test
    fun `기본 카드 2장을 뽑아 제거한다`() {
        // given
        val expected = Cards(ACE_CLUB, TEN_CLUB)
        val deck = Deck(setOf(ACE_CLUB, TEN_CLUB), RandomShufflingStrategy)

        // when
        val actual = deck.provideCards()

        // then
        assertThat(actual).isEqualTo(expected)
        assertThat(deck.size).isEqualTo(0)
    }

    @Test
    fun `덱이 비었을 때 새로운 덱을 추가한다`() {
        // given
        deck.provideCard()

        // when
        val card53th = deck.provideCard()

        // then
        assertThat(card53th).isNotNull
    }
}
