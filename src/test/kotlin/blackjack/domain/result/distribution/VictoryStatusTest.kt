package blackjack.domain.result.distribution

import blackjack.domain.result.game.VictoryStatus
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class VictoryStatusTest : DescribeSpec({
    describe("opponentResult") {
        context("WIN인 경우") {
            val status = VictoryStatus.WIN
            it("상대방은 LOSS") {
                status.opponentResult shouldBe VictoryStatus.LOSS
            }
        }

        context("PUSH인 경우") {
            val status = VictoryStatus.PUSH
            it("상대방도 PUSH") {
                status.opponentResult shouldBe VictoryStatus.PUSH
            }
        }

        context("LOSS인 경우") {
            val status = VictoryStatus.LOSS
            it("상대방은 WIN") {
                status.opponentResult shouldBe VictoryStatus.WIN
            }
        }
    }
})
