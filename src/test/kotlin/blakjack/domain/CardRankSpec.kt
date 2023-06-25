package blakjack.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardRankSpec : DescribeSpec({
    describe("ACE 카드 여부 검증") {
        withData(
            nameFn = { (cardRank, result) -> "카드 랭크가 '$cardRank'이면 ACE 카드 여부는 '$result'이다." },
            ts = listOf(
                CardRank.ACE to true,
                CardRank.TWO to false,
                CardRank.THREE to false,
                CardRank.FOUR to false,
                CardRank.FIVE to false,
                CardRank.SIX to false,
                CardRank.SEVEN to false,
                CardRank.EIGHT to false,
                CardRank.NINE to false,
                CardRank.TEN to false,
                CardRank.JACK to false,
                CardRank.QUEEN to false,
                CardRank.KING to false,
            )
        ) { (cardRank, result) ->
            cardRank.isAce() shouldBe result
        }
    }
})
