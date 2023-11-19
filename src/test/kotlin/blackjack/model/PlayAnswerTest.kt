package blackjack.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class PlayAnswerTest : BehaviorSpec({
    Given("PlayAnswer") {
        When("y로 키 찾을시") {
            val answer = PlayAnswer.getKey("y")
            Then("Y") {
                answer shouldBe PlayAnswer.Y
            }
        }
    }
})
