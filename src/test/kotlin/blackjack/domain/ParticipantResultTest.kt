package blackjack.domain

import blackjack.domain.dto.ParticipantResult
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class ParticipantResultTest : BehaviorSpec({
    Given("결과 중 ") {
        val result1 = ParticipantResult("길상현", WinOrLose.WIN)
        When("승을 가지고 있다면 ") {
            val isWin = result1.isWin()
            Then("true를 반환한다.") {
                isWin shouldBe true
            }
        }

        val result2 = ParticipantResult("길상현", WinOrLose.LOSE)
        When("패을 가지고 있다면 ") {
            val isWin = result2.isWin()
            Then("false를 반환한다.") {
                isWin shouldBe false
            }
        }
    }
})
