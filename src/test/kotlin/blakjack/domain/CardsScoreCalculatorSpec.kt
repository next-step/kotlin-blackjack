package blakjack.domain

import blakjack.domain.extension.cards
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardsScoreCalculatorSpec : DescribeSpec({
    describe("카드 목록 점수 합산 검증") {
        withData(
            nameFn = { (cards, sum) -> "카드 목록에 카드 ${cards.size}장(${cards.values.joinToString(",")})이 있을 때 점수는 ${sum}이다." },
            ts = listOf(
                cards(Card.HEART_2, Card.CLOVER_3) to 5,
                cards(Card.HEART_ACE, Card.DIAMOND_KING) to 21,
                cards(Card.HEART_ACE, Card.DIAMOND_KING, Card.CLOVER_ACE) to 12,
                cards(Card.HEART_ACE, Card.DIAMOND_9, Card.SPADE_ACE) to 21,
                cards(Card.HEART_ACE, Card.HEART_10, Card.CLOVER_2) to 13,
            )
        ) { (cards, sum) ->
            CardsScoreCalculator.sum(cards) shouldBe sum
        }
    }
})
