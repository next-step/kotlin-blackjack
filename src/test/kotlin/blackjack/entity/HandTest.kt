package blackjack.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class HandTest : DescribeSpec({
    describe("플레이어의 손패는") {
        val hand = Hand()

        it("초기 상태에서 카드 리스트는 비어 있어야 한다") {
            hand.cards.shouldBeEmpty()
        }

        it("카드를 추가할 수 있어야 한다") {
            val card = Card(Suit.HEARTS, Rank.ACE)
            hand.addCard(card)
            hand.cards.size shouldBe 1
            hand.cards[0] shouldBe card
        }
    }
    describe("플레이어 손패는") {
        context("점수 계산을 요청한 경우") {
            val hand =
                Hand().apply {
                    addCard(Card(Suit.HEARTS, Rank.ACE))
                    addCard(Card(Suit.SPADES, Rank.KING))
                }

            it("현재 점수를 계산한다") {
                hand.calculateScore() shouldBe 21
            }
        }
    }
})
