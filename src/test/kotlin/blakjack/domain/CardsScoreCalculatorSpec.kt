package blakjack.domain

import blakjack.domain.extension.cards
import blakjack.domain.extension.heart10
import blakjack.domain.extension.heart2
import blakjack.domain.extension.heart3
import blakjack.domain.extension.heart9
import blakjack.domain.extension.heartAce
import blakjack.domain.extension.heartKing
import blakjack.domain.extension.spadeAce
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardsScoreCalculatorSpec : DescribeSpec({
    describe("카드 목록 점수 합산 검증") {
        withData(
            nameFn = { (cards, sum) -> "카드 목록에 카드 ${cards.size}장(${cards.values.joinToString(",")})이 있을 때 점수는 ${sum}이다." },
            ts = listOf(
                cards(heart2, heart3) to 5,
                cards(heartAce, heartKing) to 21,
                cards(heartAce, heartKing, spadeAce) to 12,
                cards(heartAce, heart9, spadeAce) to 21,
                cards(heartAce, heart10, heart2) to 13,
            )
        ) { (cards, sum) ->
            CardsScoreCalculator.sum(cards) shouldBe sum
        }
    }
})
