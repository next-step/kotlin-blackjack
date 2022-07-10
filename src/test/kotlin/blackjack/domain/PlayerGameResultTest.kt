package blackjack.domain

import blackjack.domain.participant.BetAmount
import blackjack.domain.participant.GameResult
import blackjack.domain.participant.PlayerName
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class PlayerGameResultTest : FreeSpec({

    "게임 승리 방식에 따라 수익률이 다르다." - {
        val playerName = PlayerName("최현구")
        val betAmount = BetAmount(1_000.0)

        "블랙잭으로 승리한 경우 1.5배 수익률이 발생한다." {
            val gameResult = PlayerGameResult(
                playerName = playerName,
                betAmount = betAmount,
                gameResult = GameResult.BLACKJACK_WIN
            )

            gameResult.yield shouldBe 1_500
        }

        "블랙잭으로 승리한 경우 1배 수익률이 발생한다." {
            val gameResult = PlayerGameResult(
                playerName = playerName,
                betAmount = betAmount,
                gameResult = GameResult.BLACKJACK_WIN
            )

            gameResult.yield shouldBe 1_500
        }

        "승리한 경우 1배 수익률이 발생한다." {
            val gameResult = PlayerGameResult(
                playerName = playerName,
                betAmount = betAmount,
                gameResult = GameResult.WIN
            )

            gameResult.yield shouldBe 1_000
        }

        "비긴 경우 1배 수익률이 발생한다." {
            val gameResult = PlayerGameResult(
                playerName = playerName,
                betAmount = betAmount,
                gameResult = GameResult.DRAW
            )

            gameResult.yield shouldBe 1_000
        }

        "패배한 경우 -1배 수익률이 발생한다." {
            val gameResult = PlayerGameResult(
                playerName = playerName,
                betAmount = betAmount,
                gameResult = GameResult.LOSE
            )

            gameResult.yield shouldBe -1_000
        }
    }
})
