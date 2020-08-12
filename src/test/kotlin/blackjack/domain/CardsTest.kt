package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardsTest {
    private lateinit var card: Card
    private lateinit var newCard: Card
    private lateinit var cards: Cards
    private lateinit var shouldBeThis: Set<Card>

    @BeforeEach
    fun `set up`() {
        card = Card(Pair(CardScore.ACE, Suit.HEART))
        newCard = Card(Pair(CardScore.KING, Suit.DIAMOND))

        cards = Cards(setOf(card))
        shouldBeThis = setOf(card, newCard)
    }

    @Test
    fun `add a new card to set of cards`() {
        // when
        val newCards = cards.add(newCard)
        // then
        assertThat(newCards).isEqualTo(shouldBeThis)
    }

    @Test
    fun `size of cards`() {
        // when
        val size = cards.size()
        // then
        assertThat(size).isEqualTo(1)
    }

    @DisplayName("카드 점수 합계가 21 이상일 때 true를 반환한다 (테스트시 점수 합계 : 21)")
    @Test
    fun `true only when score is more than maximum score`() {
        // given
        val newCards = cards.add(newCard)

        // when
        val isMoreThanMax = Cards(newCards).isMoreThanMaxScore(cards)

        // then
        assertTrue(isMoreThanMax)
    }

    @Test
    fun `sum of scores`() {
        // given
        val newCards = cards.add(newCard)

        // when
        val score = Cards(newCards).sumOfScores()

        // then
        assertThat(score).isEqualTo(21)
    }
}
