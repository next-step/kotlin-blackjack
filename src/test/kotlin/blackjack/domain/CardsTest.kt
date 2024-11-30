package blackjack.domain

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Rank
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardsTest {

    @CsvSource(
        "ACE, TWO, THREE, 16",
        "TWO, THREE, FOUR, 9",
        "TWO, THREE, KING, 15",
    )
    @ParameterizedTest
    fun `Cards는 현재 카드의 점수를 계산한다`(rank1: Rank, rank2: Rank, rank3: Rank, expected: Int) {
        val cards = Cards()
        cards.add(Card.of(Suit.HEART, rank1))
        cards.add(Card.of(Suit.HEART, rank2))
        cards.add(Card.of(Suit.HEART, rank3))

        val score = cards.calculateScore()

        assertThat(score).isEqualTo(expected)
    }

    @CsvSource(
        "ACE, TWO, THREE, false",
        "TWO, THREE, FOUR, false",
        "KING, QUEEN, KING, true",
    )
    @ParameterizedTest
    fun `Cards는 21점을 초과하는지 확인한다`(rank1: Rank, rank2: Rank, rank3: Rank, expected: Boolean) {
        val cards = Cards()
        cards.add(Card.of(Suit.HEART, rank1))
        cards.add(Card.of(Suit.HEART, rank2))
        cards.add(Card.of(Suit.HEART, rank3))

        val isOverMaxScore = cards.isOverMaxScore()

        assertThat(isOverMaxScore).isEqualTo(expected)
    }
}
