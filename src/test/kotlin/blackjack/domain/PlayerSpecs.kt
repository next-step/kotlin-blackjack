package blackjack.domain

import blackjack.domain.Denomination.ACE
import blackjack.domain.Denomination.KING
import blackjack.domain.Suit.CLOVER
import blackjack.domain.Suit.SPADE
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe

class PlayerSpecs : DescribeSpec({

    describe("플레이어는") {
        it("카드 패를 가질 수 있다") {
            val hand = hand(KING to CLOVER, ACE to SPADE)
            shouldNotThrowAny { Player(hand) }
        }

        it("카드 2장을 받아 자신의 카드 패에 추가할 수 있다") {
            val player = Player()
            val card1 = Card.from(KING, CLOVER)
            val card2 = Card.from(ACE, SPADE)
            player.initialize(card1 to card2)
            player.hand shouldBeEqualToComparingFields Hand(listOf(card1, card2))
        }

        it("카드 1장을 받아 자신의 카드 패에 추가할 수 있다") {
            val player = Player()
            val card = Card.from(KING, CLOVER)
            player.hit(card)
            player.hand shouldBeEqualToComparingFields Hand(listOf(card))
        }

        it("자신이 지닌 카드 패의 점수를 계산할 수 있다") {
            val player = Player(hand(KING to CLOVER, ACE to SPADE))
            player.calculate() shouldBe 21
        }
    }
})
