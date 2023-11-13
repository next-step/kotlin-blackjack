package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MoneyTest : BehaviorSpec({
    given("Money가 주어진다면") {
        val money = Money(1000)
        When("Money가 주어진다면") {
            then("Money는 1000이다.") {
                money.amount shouldBe 1000
            }
        }
    }

    given("베팅금액이 1000원이 주어진다면") {
        val money = Money(1000)
        When("승패가 승리일 때 수익 계산시") {
            val result = money.calculateWinLoseMoney(WinLose.WIN)
            then("수익은 1000원이다.") {
                result.amount shouldBe 1000
            }
        }

        When("승패가 패배일 때 수익 계산시") {
            val result = money.calculateWinLoseMoney(WinLose.LOSE)
            then("수익은 -1000원이다.") {
                result.amount shouldBe -1000
            }
        }
    }

    given("베팅금액이 1000원이 주어진다면") {
        val money = Money(1000)
        When("승패가 승리하고, 블랙잭 일시에") {
            val result = money.calculateWinLoseMoney(WinLose.WIN, true)
            then("수익은 1500원이다.") {
                result.amount shouldBe 1500
            }
        }
    }
})
