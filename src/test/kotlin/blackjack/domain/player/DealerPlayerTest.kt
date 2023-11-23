package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerPlayerTest : DescribeSpec({
    describe("hitOrStand") {

        context("16점 이하라면") {
            val score16Cards = mutableListOf(
                Card(Suit.HEART, Rank.EIGHT),
                Card(Suit.HEART, Rank.EIGHT),
            )
            val player = DealerPlayer(Hand(score16Cards))

            it("HIT 반환") {
                val result = player.hitOrStand()

                result shouldBe Action.HIT
            }
        }

        context("17점 이상이라면") {
            val score20Cards = mutableListOf(
                Card(Suit.HEART, Rank.TEN),
                Card(Suit.HEART, Rank.TEN),
            )
            val player = DealerPlayer(Hand(score20Cards))

            it("STAND 반환") {
                val result = player.hitOrStand()

                result shouldBe Action.STAND
            }
        }
    }
})
