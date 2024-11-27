import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardTest : FreeSpec({
    "카드의 값을 결정한다." - {
        listOf(
            row(Card.TWO, 2),
            row(Card.THREE, 3),
            row(Card.FOUR, 4),
            row(Card.FIVE, 5),
            row(Card.SIX, 6),
            row(Card.SEVEN, 7),
            row(Card.EIGHT, 8),
            row(Card.NINE, 9),
            row(Card.TEN, 10),
            row(Card.JACK, 10),
            row(Card.QUEEN, 10),
            row(Card.KING, 10),
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
                    Card.ACE.value(hands) shouldBe expected
                }
            }
    }
})