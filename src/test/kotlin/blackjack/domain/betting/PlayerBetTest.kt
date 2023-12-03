package blackjack.domain.betting

import blackjack.domain.batting.PlayerBet
import blackjack.domain.player.PlayerName
import blackjack.mock.amount
import blackjack.mock.profit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerBetTest : StringSpec({
    "BetPlaced와 수익 정보로 BetFinished 생성" {
        val placed = PlayerBet.Placed(
            playerName = PlayerName("kim"),
            betAmount = amount(3_000)
        )
        val payoutAmount = amount(0)

        val result = PlayerBet.Finished.of(placed, payoutAmount)

        result.playerName shouldBe placed.playerName
        result.betAmount shouldBe placed.betAmount
        result.payoutAmount shouldBe payoutAmount
    }

    "BetFinished의 수익 조회" {
        val betAmount = amount(10000)
        val payoutAmount = amount(25000)
        val bet = PlayerBet.Finished(PlayerName("lee"), betAmount, payoutAmount)

        bet.profit shouldBe profit(15000)
    }
})
