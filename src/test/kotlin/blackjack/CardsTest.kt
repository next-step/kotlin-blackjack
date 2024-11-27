package blackjack

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
    fun `Cards는 현재 카드의 점수를 계산한다`(
        rank1: Rank,
        rank2: Rank,
        rank3: Rank,
        expected: Int
    ) {
        val cards = Cards()
        cards.add(Card.of(Suit.HEART, rank1))
        cards.add(Card.of(Suit.HEART, rank2))
        cards.add(Card.of(Suit.HEART, rank3))

        val score = cards.calculateScore()

        assertThat(score).isEqualTo(expected)
    }
}
