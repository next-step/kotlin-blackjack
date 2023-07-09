package blackjack.domain.result

import blackjack.domain.BetAmount
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BetResultTest : StringSpec({
    "무승부인 경우 베팅 금액에 상관없이 수익은 항상 0원이다." {
        val betResult = BetResult.ofDraw()

        betResult.earningAmount shouldBe 0
    }

    "이긴 경우 베팅금액을 얻는다." {
        val betAmount = BetAmount(10_000)
        val betResult = BetResult.ofWin(betAmount)

        betResult.earningAmount shouldBe betAmount.value
    }

    "진 경우 베팅금액을 잃는다." {
        val betAmount = BetAmount(10_000)
        val betResult = BetResult.ofLose(betAmount)

        betResult.earningAmount shouldBe -betAmount.value
    }

    "블랙잭 상태로 이긴 경우 베팅금액에 1.5배를 얻는다." {
        val betResult = BetResult.ofBlackJackWin(BetAmount(10_000))

        betResult.earningAmount shouldBe 15_000
    }

    "블랙잭 상태로 비긴 경우 베팅금액을 얻는다." {
        val betAmount = BetAmount(10_000)
        val betResult = BetResult.ofBlackJackDraw(betAmount)

        betResult.earningAmount shouldBe betAmount.value

    }

    "결과가 '승'이면 반대 결과는 '패'이고 수익만큼 잃는다." {
        val earningAmount = 10_000
        val betResult = BetResult(Result.WIN, earningAmount)
        val oppositeResult = betResult.opposite()

        oppositeResult.result shouldBe Result.LOSE
        oppositeResult.earningAmount shouldBe -earningAmount
    }

    "결과가 '패'이면 반대 결과는 '승'이고 수익만큼 얻는다." {
        val earningAmount = -10_000
        val betResult = BetResult(Result.LOSE, earningAmount)
        val oppositeResult = betResult.opposite()

        oppositeResult.result shouldBe Result.WIN
        oppositeResult.earningAmount shouldBe -earningAmount
    }
})
