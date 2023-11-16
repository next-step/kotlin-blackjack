package blackjack.domain.card

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class HandTest : DescribeSpec({
    describe("카드 추가") {
        context("카드가 주어지면") {
            val hand = Hand()
            val card = Card(Suit.HEART, Rank.FOUR)

            hand.add(card)

            it("카드 목록에 추가") {
                hand.cards shouldBe listOf(card)
            }
        }

        context("다른 카드가 있던 경우") {
            val oldCard = Card(Suit.DIAMOND, Rank.ACE)
            val hand = Hand(mutableListOf(oldCard))
            val newCard = Card(Suit.HEART, Rank.FOUR)

            hand.add(newCard)

            it("카드 목록에 추가") {
                hand.cards shouldBe listOf(oldCard, newCard)
            }
        }
    }
})
