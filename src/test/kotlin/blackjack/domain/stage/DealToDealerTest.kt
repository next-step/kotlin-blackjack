package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.mock.InputProcessorMock
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class DealToDealerTest : DescribeSpec({
    describe("deal to dealer") {
        context("플레이어가 받은 카드가 16이하인 경우") {
            val under16ScoreCards = mutableListOf(
                Card(Suit.SPADE, Rank.TWO),
                Card(Suit.SPADE, Rank.THREE),
            )
            val dealer = Dealer(hand = Hand(under16ScoreCards))
            val game = BlackJackGame(InputProcessorMock(), dealer = dealer)

            val dealToDealer = DealToDealer()
            game.setDistributor(dealToDealer)
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

        context("플레이어가 받은 카드가 17이상인 경우") {
            val under16ScoreCards = mutableListOf(
                Card(Suit.SPADE, Rank.QUEEN),
                Card(Suit.SPADE, Rank.QUEEN),
            )
            val dealer = Dealer(hand = Hand(under16ScoreCards))
            val game = BlackJackGame(InputProcessorMock(), dealer = dealer)

            val dealToDealer = DealToDealer()
            game.setDistributor(dealToDealer)
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
