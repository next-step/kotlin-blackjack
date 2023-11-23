package blackjack.domain.stage

import blackjack.domain.Action
import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.player.DealerPlayer
import blackjack.mock.InputProcessorMock
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class DealToDealerTest : DescribeSpec({
    describe("DealToDealer") {
        val under16ScoreCards = mutableListOf(
            Card(Suit.SPADE, Rank.TWO),
            Card(Suit.SPADE, Rank.THREE),
        )
        val dealer = Dealer(player = DealerPlayer(Hand(under16ScoreCards)))
        context("딜러가 HIT을 하면") {
            val game = BlackJackGame(InputProcessorMock(), dealer = dealer)
            val dealToDealer = DealToDealer()
            game.setDistributor(dealToDealer)
            dealer.hitOrStand() shouldBe Action.HIT

            val result = dealToDealer(game)

            it("딜러는 카드 한 장을 더 받는다") {
                game.dealer.hand.cards.size shouldBe 3
            }

            it("배분 결과 참을 반환") {
                result.isHit shouldBe true
            }

            it("게임의 다음 배분은 종료 상태") {
                game.dealCards.shouldBeTypeOf<DistributionEnd>()
            }
        }

        context("딜러가 STAND를 하면") {
            val under16ScoreCards = mutableListOf(
                Card(Suit.SPADE, Rank.QUEEN),
                Card(Suit.SPADE, Rank.QUEEN),
            )
            val dealer = Dealer(player = DealerPlayer(Hand(under16ScoreCards)))
            val game = BlackJackGame(InputProcessorMock(), dealer = dealer)
            val dealToDealer = DealToDealer()
            game.setDistributor(dealToDealer)
            dealer.hitOrStand() shouldBe Action.STAND

            val result = dealToDealer(game)

            it("딜러는 카드를 받지 않는다") {
                game.dealer.hand.cards.size shouldBe 2
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
