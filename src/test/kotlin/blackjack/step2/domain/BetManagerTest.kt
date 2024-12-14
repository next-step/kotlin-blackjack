package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BetManagerTest : FunSpec({
    test("getBetAmount() 테스트 - 플레이어의 베팅 금액을 반환한다.") {
        // given
        val bets =
            listOf(
                Bet("dongyeon", 10000),
                Bet("pobi", 20000),
                Bet("jason", 30000),
            )
        val betManager = BetManager(bets)

        // when
        val betAmount1 = betManager.getBetAmount(Player("dongyeon"))
        val betAmount2 = betManager.getBetAmount(Player("pobi"))
        val betAmount3 = betManager.getBetAmount(Player("jason"))

        // then
        betAmount1 shouldBe 10000
        betAmount2 shouldBe 20000
        betAmount3 shouldBe 30000
    }
})
