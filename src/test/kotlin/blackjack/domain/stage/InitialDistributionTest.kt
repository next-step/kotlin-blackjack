package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerNames
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class InitialDistributionTest : DescribeSpec({
    val game = BlackJackGame(PlayerNames(listOf(PlayerName("홍길동"), PlayerName("이상순"))))
    describe("카드 배분 진행") {
        context("카드 배분 스테이지를 진행시키면") {
            val stage = InitialDistribution(game)
            stage.progress()

            it("플레이어마다 2장의 카드 수령") {
                game.players.allPlayers.forEach { player ->
                    player.hand.cards.size shouldBe 2
                }
            }

            it("게임 진행 여부 true로 변경") {
                stage.isProgressDone shouldBe true
            }
        }
    }

    describe("다음 스테이지 반환") {
        context("게임 완료 후 다음 스테이지 요청") {
            val stage = InitialDistribution(game)
            stage.progress()

            it("게임 진행 스테이지 반환") {
                val nextStage = stage.nextStage()

                nextStage.shouldBeTypeOf<InGame>()
            }
        }

        context("게임 진행하지 않은 상태에서 다음 스테이지 요청") {
            val stage = InitialDistribution(game)

            it("카드 배분 스테이지 반환") {
                val nextStage = stage.nextStage()

                nextStage.shouldBeTypeOf<InitialDistribution>()
            }
        }
    }
})
