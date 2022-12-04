package blackjack.util

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class ParserTest : BehaviorSpec({
    Given("사람들의 이름이 쉼표로 구분되어 주어진다면 ") {
        val nameStr = "길상현,상현,현"
        When("파싱을 진행하면 ") {
            val people = Parser.parse(nameStr)
            Then("정상적으로 파싱이 진행된다.") {
                people.size shouldBe 3
            }
        }
    }
})
