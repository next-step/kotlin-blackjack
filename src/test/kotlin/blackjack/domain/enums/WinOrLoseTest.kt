package blackjack.domain.enums

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class WinOrLoseTest : BehaviorSpec({
    Given("Boolean 값이 주어지면 ") {
        val win = true
        When("그에 맞는 WinOrLose로 ") {
            val winOrLose = WinOrLose.of(win)
            Then("변환한다.") {
                winOrLose shouldBe WinOrLose.WIN
            }
        }

        val lose = false
        When("그에 맞는 WinOrLose로 ") {
            val winOrLose = WinOrLose.of(lose)
            Then("변환한다.") {
                winOrLose shouldBe WinOrLose.LOSE
            }
        }
    }
})
