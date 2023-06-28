package blackjack.domain.game

import blackjack.domain.gamer.PlayerName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerMatchResultTest : StringSpec({

    "플레이어가 승리 했다면 딜러는 패배했다" {
        val playerMatchResult = listOf(
            PlayerMatchResult(
                PlayerName("test"),
                MatchResultType.WIN,
            )
        )
        val dealerMatchResult = DealerMatchResult.create(playerMatchResult)
        dealerMatchResult shouldBe DealerMatchResult(0, 0, 1)
    }

    "플레이어가 무승부라면 딜러도 무승부이다" {
        val playerMatchResult = listOf(
            PlayerMatchResult(
                PlayerName("test"),
                MatchResultType.TIE,
            )
        )
        val dealerMatchResult = DealerMatchResult.create(playerMatchResult)
        dealerMatchResult shouldBe DealerMatchResult(0, 1, 0)
    }

    "플레이어가 패배 했다면 딜러는 승리했다" {
        val playerMatchResult = listOf(
            PlayerMatchResult(
                PlayerName("test"),
                MatchResultType.LOSE,
            )
        )
        val dealerMatchResult = DealerMatchResult.create(playerMatchResult)
        dealerMatchResult shouldBe DealerMatchResult(1, 0, 0)
    }
})
