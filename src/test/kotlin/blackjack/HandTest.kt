package blackjack

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
})
