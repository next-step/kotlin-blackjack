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
        newCard = Card(Pair(CardScore.TWO, Suit.DIAMOND))

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

    @DisplayName("점수 합계가 17 미만일 때 true를 반환한다 (테스트시 점수 합계 : 13)")
    @Test
    fun `return true when score is less than 17`() {
        // given
        val newCards = cards.add(newCard)

        // when
        val isLessThan17 = Cards(newCards).isLessThan17()

        // then
        assertTrue(isLessThan17)
    }

    @DisplayName("카드점수 합계가 21을 초과하지 않는 한 ACE는 11로 계산할 수 있다")
    @Test
    fun `sum of scores`() {
        // given
        val newCards = cards.add(newCard)

        // when
        val score = Cards(newCards).sumOfScores()

        // then
        assertThat(score).isEqualTo(13)
    }
}
