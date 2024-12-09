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
        context("플레이어가 패배한 경우") {
            it("베팅 금액만큼 잃는다") {
                val bettingAmount = BettingAmount(100)
                bettingAmount.loseBet() shouldBe -100
            }
        }
        context("플레이어가 승리한 경우") {
            it("베팅 금액만큼 얻는다") {
                val bettingAmount = BettingAmount(100)
                bettingAmount.winBet() shouldBe 100
            }
        }
        context("플레이어가 블랙잭으로 승리한 경우") {
            it("베팅 금액의 1.5배만큼 얻는다") {
                val bettingAmount = BettingAmount(100)
                bettingAmount.winBlackjack() shouldBe 150
            }
        }
    }
})
