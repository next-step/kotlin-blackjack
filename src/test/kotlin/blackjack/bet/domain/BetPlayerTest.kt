package blackjack.bet.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BetPlayerTest : StringSpec({
    "플레이어는 지갑에 돈을 충전 할 수 있다." {
        val player = BetPlayer("돈버그")

        player.charge(50_000)

        player.wallet().bettingAmount() shouldBe 50_000
        player.wallet().income() shouldBe 0
    }

    "플레이어는 게임이 끝날때 손익을 정산한다" {
        val player = BetPlayer("돈버그")
        player.charge(50_000)

        player.wallet().settle(-5_000)

        player.wallet().bettingAmount() shouldBe 45_000
        player.wallet().income() shouldBe -5_000
    }
})
