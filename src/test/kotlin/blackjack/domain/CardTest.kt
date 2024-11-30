package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드는 무늬와 숫자를 가진다" {
        val actual = Card(Suit.SPADE, Rank.ACE)

        actual.suit shouldBe Suit.SPADE
        actual.rank shouldBe Rank.ACE
    }

    "카드는 숫자에 따라 값을 가진다" {
        forAll(
            row(Rank.ACE, 1),
            row(Rank.TWO, 2),
            row(Rank.THREE, 3),
            row(Rank.FOUR, 4),
            row(Rank.FIVE, 5),
            row(Rank.SIX, 6),
            row(Rank.SEVEN, 7),
            row(Rank.EIGHT, 8),
            row(Rank.NINE, 9),
            row(Rank.TEN, 10),
            row(Rank.JACK, 10),
            row(Rank.QUEEN, 10),
            row(Rank.KING, 10),
        ) { rank, expected ->
            val actual = Card(Suit.SPADE, rank)

            actual.value shouldBe expected
        }
    }
})

