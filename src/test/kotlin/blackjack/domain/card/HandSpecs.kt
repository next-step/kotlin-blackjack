package blackjack.domain.card

import blackjack.domain.card.Denomination.ACE
import blackjack.domain.card.Denomination.KING
import blackjack.domain.card.Denomination.SEVEN
import blackjack.domain.card.Denomination.SIX
import blackjack.domain.card.Denomination.TEN
import blackjack.domain.card.Denomination.THREE
import blackjack.domain.card.Denomination.TWO
import blackjack.domain.card.Suit.CLOVER
import blackjack.domain.card.Suit.DIAMOND
import blackjack.domain.card.Suit.HEART
import blackjack.domain.card.Suit.SPADE
import blackjack.domain.hand
import blackjack.domain.to
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class HandSpecs : DescribeSpec({

    describe("카드 패는") {

        it("가지고 있는 모든 카드의 점수를 계산할 수 있다") {
            val cards = hand(
                KING to HEART,
                TWO to DIAMOND,
                THREE to CLOVER,
                TEN to SPADE
            )
            cards.calculate() shouldBe Point(25)
        }

        it("새로운 카드를 추가할 수 있다") {
            val cards = hand(
                KING to HEART,
            )
            val card = Card(KING, CLOVER)
            cards.add(card)
            cards.calculate() shouldBe Point(20)
        }

        context("카드 패에 끗수가 ACE인 카드가 포함된 경우") {
            it(
                """ACE 카드를 제외한 다른 모든 카드의 점수와 `ACE 카드로 만들 수 있는 모든 점수의 조합 중 하나`를 더한 값 중, 
                21 보다 작거나 같은 값이 있다면 그 값 중 가장 큰 값을 선택한다."""
            ) {
                io.kotest.data.forAll(
                    table(
                        headers("카드 패", "기대 점수"),
                        row(hand(ACE to SPADE, KING to HEART), Point(21)),
                        row(hand(ACE to SPADE, SEVEN to HEART, SIX to CLOVER), Point(14)),
                        row(hand(ACE to SPADE, KING to HEART, TEN to CLOVER), Point(21)),
                    )
                ) { cards, point ->
                    cards.calculate() shouldBe point
                }
            }

            it("ACE를 제외한 모든 카드의 점수 합이 21이 넘는 경우 그 중에서 가장 작은 값을 선택한다.") {
                val cards = hand(
                    ACE to SPADE,
                    KING to HEART,
                    TEN to CLOVER,
                    TWO to CLOVER,
                )
                cards.calculate() shouldBe Point(23)
            }
        }
    }
})
