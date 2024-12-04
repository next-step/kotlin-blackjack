package blackjack.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BettingAmountTest : DescribeSpec({
    describe("베팅 금액은") {
        context("베팅 금액 생성 시") {
            it("0보다 큰 금액이면 생성된다") {
                val bettingAmount = BettingAmount(100)
                bettingAmount.amount shouldBe 100
            }

            it("0 이하의 금액이면 예외가 발생한다") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        BettingAmount(0)
                    }
                exception.message shouldBe "베팅 금액은 0보다 커야합니다."

                val negativeException =
                    shouldThrow<IllegalArgumentException> {
                        BettingAmount(-10)
                    }
                negativeException.message shouldBe "베팅 금액은 0보다 커야합니다."
            }
        }
        context("플레이어가 BUst 상태인 경우") {
            it("모든 금액을 잃는다") {
                val bettingAmount = BettingAmount(100)
                bettingAmount.loseIfBusted(true) shouldBe -100
            }
        }
        context("플레이어가 Bust 상태가 아닌 경우") {
            it("금액 변화가 없다") {
                val bettingAmount = BettingAmount(100)
                bettingAmount.loseIfBusted(false) shouldBe 0
            }
        }
        context("플레이어가 패배한 경우") {
            it("베팅 금액만큼 잃는다") {
                val bettingAmount = BettingAmount(100)
                bettingAmount.loseBet() shouldBe -100
            }
        }
    }
})
