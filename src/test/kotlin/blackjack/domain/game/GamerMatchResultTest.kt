package blackjack.domain.game

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class GamerMatchResultTest : StringSpec({

    "딜러의 패배 카운트와 플레이어들의 승리 카운트가 다르면 RuntimeException 에외 처리를 한다" {
        val dealerMatchResult = DealerMatchResult(0, 0, 2)
        val playerMatchResults = listOf(
            PlayerMatchResult(
                "test",
                MatchResultType.WIN
            )
        )
        shouldThrow<RuntimeException> {
            GamerMatchResult(dealerMatchResult, playerMatchResults)
        }
    }

    "딜러의 승리 카운트와 플레이어들의 패배 카운트가 다르면 RuntimeException 예외 처리를 한다" {
        val dealerMatchResult = DealerMatchResult(2, 0, 0)
        val playerMatchResults = listOf(
            PlayerMatchResult(
                "test",
                MatchResultType.LOSE
            )
        )
        shouldThrow<RuntimeException> {
            GamerMatchResult(dealerMatchResult, playerMatchResults)
        }
    }

    "딜러의 무승부 카운트와 플레이어들의 무승부 카운트가 다르다면 RuntimeException 예외 처리를 한다" {
        val dealerMatchResult = DealerMatchResult(0, 2, 0)
        val playerMatchResults = listOf(
            PlayerMatchResult(
                "test",
                MatchResultType.TIE
            )
        )
        shouldThrow<RuntimeException> {
            GamerMatchResult(dealerMatchResult, playerMatchResults)
        }
    }
})
