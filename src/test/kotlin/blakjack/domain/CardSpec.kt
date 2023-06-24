package blakjack.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardSpec : DescribeSpec({
    describe("ACE 카드 여부 검증") {
        withData(
            nameFn = { (card, result) -> "카드가 '${card.korean}'이면 ACE 카드 여부는 '$result'이다." },
            ts = listOf(
                Card.HEART_ACE to true,
                Card.DIAMOND_ACE to true,
                Card.SPADE_ACE to true,
                Card.CLOVER_ACE to true,
                Card.HEART_2 to false,
                Card.DIAMOND_3 to false,
                Card.SPADE_4 to false,
                Card.CLOVER_5 to false,
            )
        ) { (card, result) ->
            card.isAce() shouldBe result
        }
    }
})
