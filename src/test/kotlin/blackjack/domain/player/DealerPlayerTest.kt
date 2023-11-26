package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card
import blackjack.domain.card.Rank
import blackjack.domain.hand
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerPlayerTest : DescribeSpec({
    describe("hitOrStand") {

        context("16점 이하라면") {
            val score16Cards = hand(
                card(Rank.EIGHT),
                card(Rank.EIGHT),
            )
            val player = DealerPlayer(score16Cards)

            it("HIT 반환") {
                val result = player.hitOrStand()

                result shouldBe Action.HIT
            }
        }

        context("17점 이상이라면") {
            val score20Cards = hand(
                card(Rank.TEN),
                card(Rank.TEN),
            )
            val player = DealerPlayer(score20Cards)

            it("STAND 반환") {
                val result = player.hitOrStand()

                result shouldBe Action.STAND
            }
        }
    }
})
