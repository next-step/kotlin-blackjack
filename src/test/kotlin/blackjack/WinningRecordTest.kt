package blackjack

import blackjack.domain.Player
import blackjack.domain.ResultStatus
import blackjack.domain.WinningResult
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class WinningRecordTest : DescribeSpec({

    describe("updateRecord") {
        val record = WinningResult(Player("name"))
        record.winCount shouldBe 0
        record.loseCount shouldBe 0

        context("승리한 경우") {
            it("winCount 가 +1 상승한다.") {
                record.updateResult(ResultStatus.Win)
                record.winCount shouldBe 1
            }
        }
        context("패배한 경우") {
            it("loseCount 가 +1 상승한다.") {
                record.updateResult(ResultStatus.Lose)
                record.loseCount shouldBe 1
            }
        }
        context("무승부인 경우") {
            it("drawCount 가 +1 상승한다.") {
                record.updateResult(ResultStatus.Draw)
                record.drawCount shouldBe 1
            }
        }
    }
})
