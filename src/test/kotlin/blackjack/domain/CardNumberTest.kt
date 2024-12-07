package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardNumberTest : FreeSpec({
    "카드의 값을 결정한다." - {
        listOf(
            row(CardNumber.TWO, 2),
            row(CardNumber.THREE, 3),
            row(CardNumber.FOUR, 4),
            row(CardNumber.FIVE, 5),
            row(CardNumber.SIX, 6),
            row(CardNumber.SEVEN, 7),
            row(CardNumber.EIGHT, 8),
            row(CardNumber.NINE, 9),
            row(CardNumber.TEN, 10),
            row(CardNumber.JACK, 10),
            row(CardNumber.QUEEN, 10),
            row(CardNumber.KING, 10),
        )
            .forAll { (card, expected) ->
                "${card.name}의 값은 ${expected}로 결정한다." - {
                    card.value(21) shouldBe expected
                }
            }
    }

    "ACE 카드의 값을 결정한다." - {
        listOf(
            row(
                "현재 계산된 값이 11 미만이면 11로 결정한다.",
                10,
                11,
            ),
            row(
                "현재 계산된 값이 11 이상이면 1로 결정한다.",
                11,
                1,
            ),
        )
            .forAll { (description, hands, expected) ->
                description - {
                    CardNumber.ACE.value(hands) shouldBe expected
                }
            }
    }
})
