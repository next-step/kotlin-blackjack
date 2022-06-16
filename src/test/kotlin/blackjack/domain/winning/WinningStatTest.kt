package blackjack.domain.winning

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.score.PlayerScore
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class WinningStatTest : FreeSpec({

    "playerGameResult" - {
        "우승 지표를 가져온다." {
            val player = Player("test")
            val winningStat = WinningStat(
                listOf(PlayerScore(player, 2)),
                PlayerScore(Dealer(), 3)
            )

            val result = winningStat.playerGameResult()
            result.size shouldBe 2
            result[0].player.name shouldBe "test"
            result[0].winCount shouldBe 1
            result[0].looseCount shouldBe 0
            result[0].tieCount shouldBe 0
        }
    }

    "dealerWinning" - {
        "딜러의 점수가 21을 초과할 경우 false를 반환한다." {
            val player = Player("test")
            val winningStat = WinningStat(
                listOf(PlayerScore(player, 2)),
                PlayerScore(Dealer(), 21)
            )

            val result = winningStat.dealerWinning()
            result shouldBe false
        }

        "딜러의 점수가 21을 미만일 경우 true를 반환한다." {
            val player = Player("test")
            val winningStat = WinningStat(
                listOf(PlayerScore(player, 2)),
                PlayerScore(Dealer(), 20)
            )

            val result = winningStat.dealerWinning()
            result shouldBe true
        }
    }
})
