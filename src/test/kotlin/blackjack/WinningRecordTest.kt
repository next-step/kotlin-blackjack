package blackjack

import blackjack.domain.WinningRecord
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class WinningRecordTest : DescribeSpec({

    describe("updateRecord") {
        val record = WinningRecord()
        record.winCount shouldBe 0
        record.loseCount shouldBe 0

        context("인자가 true 인 경우") {
            it("winCount 가 +1 상승한다.") {
                record.updateRecord(isWin = true)
                record.winCount shouldBe 1
            }
        }
        context("인자가 false 인 경우") {
            it("loseCount 가 +1 상승한다.") {
                record.updateRecord(isWin = true)
                record.loseCount shouldBe 1
            }
        }
    }
})
