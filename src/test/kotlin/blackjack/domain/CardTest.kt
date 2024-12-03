package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EnumSource

@Suppress("NonAsciiCharacters")
class CardTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "TWO, 2",
            "THREE, 3",
            "FOUR, 4",
            "FIVE, 5",
            "SIX, 6",
            "SEVEN, 7",
            "EIGHT, 8",
            "NINE, 9",
            "TEN, 10",
        ],
    )
    fun `숫자 카드의 값은 자신이다`(
        rank: Rank,
        expected: Int,
    ) {
        val card = Card.of(DUMMY_SUIT, rank)
        card.rankValue shouldBe expected
    }

    @ParameterizedTest
    @EnumSource(
        value = Rank::class,
        names = ["JACK", "QUEEN", "KING"],
    )
    fun `페이스 카드등의 값은 10이다`(rank: Rank) {
        val card = Card.of(DUMMY_SUIT, rank)
        card.rankValue shouldBe 10
    }

    @Test
    fun `에이스의 값은 1이다`() {
        val card = Card.of(DUMMY_SUIT, Rank.ACE)
        card.rankValue shouldBe 1
    }

    companion object {
        private val DUMMY_SUIT = Suit.HEARTS
    }
}
