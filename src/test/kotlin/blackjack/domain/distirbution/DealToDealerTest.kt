package blackjack.domain.distirbution

import blackjack.domain.Action
import blackjack.domain.Dealer
import blackjack.domain.card.Rank
import blackjack.domain.player.DealerPlayer
import blackjack.mock.blackJackGame
import blackjack.mock.card
import blackjack.mock.hand
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class DealToDealerTest : DescribeSpec({
    describe("DealToDealer") {
        context("딜러가 (16점 이하라) HIT를 하면") {
            val under16ScoreCards = hand(card(Rank.TWO), card(Rank.THREE))
            val dealer = Dealer(player = DealerPlayer(under16ScoreCards))
            dealer.hitOrStand() shouldBe Action.HIT

            val game = blackJackGame(dealer = dealer)
            val dealToDealer = DealToDealer()
            game.setDistributor(dealToDealer)

            val result = dealToDealer(game.table) { distributor -> game.setDistributor(distributor) }

            it("딜러는 카드 한 장을 더 받는다") {
                game.table.dealer.hand.cards.size shouldBe 3
            }

            it("배분 결과 참을 반환") {
                result.isHit shouldBe true
            }

            it("게임의 다음 배분은 종료 상태") {
                game.dealCards.shouldBeTypeOf<DistributionEnd>()
            }
        }

        context("딜러가 (17점 이상이라) STAND를 하면") {
            val over16ScoreCards = hand(card(Rank.QUEEN), card(Rank.QUEEN))
            val dealer = Dealer(player = DealerPlayer(over16ScoreCards))
            dealer.hitOrStand() shouldBe Action.STAND

            val game = blackJackGame(dealer = dealer)
            val dealToDealer = DealToDealer()
            game.setDistributor(dealToDealer)

            val result = dealToDealer(game.table) { distributor -> game.setDistributor(distributor) }

            it("딜러는 카드를 받지 않는다") {
                game.table.dealer.hand.cards.size shouldBe 2
            }

            it("배분 결과 거짓을 반환") {
                result.isHit shouldBe false
            }

            it("게임의 다음 배분은 종료 상태") {
                game.dealCards.shouldBeTypeOf<DistributionEnd>()
            }
        }
    }
})
