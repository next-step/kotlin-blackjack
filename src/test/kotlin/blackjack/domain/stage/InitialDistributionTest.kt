package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerNames
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class InitialDistributionTest : DescribeSpec({
    val game = BlackJackGame(PlayerNames(listOf(PlayerName("홍길동"), PlayerName("이상순"))))
    describe("스테이지 진행") {
        context("카드 배분 스테이지를 진행시키면") {
            InitialDistribution(game).progress()

            it("플레이어마다 2장의 카드 수령") {
                game.players.allPlayers.forEach { player ->
                    player.hand.cards.size shouldBe 2
                }
            }
        }
    }
})
