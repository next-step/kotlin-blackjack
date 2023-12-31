package blackjack.domain.distirbution

import blackjack.domain.Action
import blackjack.domain.Dealer
import blackjack.domain.card.Rank
import blackjack.domain.player.DealerPlayer
import blackjack.mock.card
import blackjack.mock.hand
import blackjack.mock.table
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class DealToDealerTest : DescribeSpec({
    describe("deal") {
        context("딜러가 (16점 이하라) HIT를 하면") {
            val under16ScoreCards = hand(card(Rank.TWO), card(Rank.THREE))
            val dealer = Dealer(player = DealerPlayer(under16ScoreCards))
            dealer.hitOrStand() shouldBe Action.HIT
            val table = table(dealer = dealer)
            val dealToDealer = DealToDealer(table)

            val result = dealToDealer.deal()

            it("딜러는 카드 한 장을 더 받는다") {
                table.dealer.hand.cards.size shouldBe 3
            }

            it("배분이 진행되어 배분 결과는 참을 반환한다") {
                result.isHit shouldBe true
            }

            it("게임의 다음 배분은 DealEnd이다") {
                dealToDealer.nextDistributor.shouldBeTypeOf<DealEnd>()
            }
        }

        context("딜러가 (17점 이상이라) STAND를 하면") {
            val over16ScoreCards = hand(card(Rank.QUEEN), card(Rank.QUEEN))
            val dealer = Dealer(player = DealerPlayer(over16ScoreCards))
            dealer.hitOrStand() shouldBe Action.STAND
            val table = table(dealer = dealer)
            val dealToDealer = DealToDealer(table)

            val result = dealToDealer.deal()

            it("딜러는 카드를 받지 않는다") {
                table.dealer.hand.cards.size shouldBe 2
            }

            it("배분이 진행되지 않아 배분 결과는 거짓을 반환한다") {
                result.isHit shouldBe false
            }

            it("게임의 다음 배분은 DealEnd 이다") {
                dealToDealer.nextDistributor.shouldBeTypeOf<DealEnd>()
            }
        }
    }
})
