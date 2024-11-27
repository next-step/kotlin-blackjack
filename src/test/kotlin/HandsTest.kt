import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class HandsTest : FreeSpec({
    "핸드에 존재하는 카드 값의 총합을 계산한다." - {
        listOf(
            row(
                "2, 3, 4, A인 경우 합계 20이다.",
                Hands(
                    mutableListOf(
                        Card.TWO,
                        Card.THREE,
                        Card.FOUR,
                        Card.ACE
                    )
                ),
                20
            ),
            row(
                "2, 3, 4, A, A인 경우 합계 21이다.",
                Hands(
                    mutableListOf(
                        Card.TWO,
                        Card.THREE,
                        Card.FOUR,
                        Card.ACE,
                        Card.ACE,
                    )
                ),
                21
            ),
        )
            .forAll { (description, hands, expected) ->
                description - {
                    hands.totalValue() shouldBe expected
                }
            }
    }
})