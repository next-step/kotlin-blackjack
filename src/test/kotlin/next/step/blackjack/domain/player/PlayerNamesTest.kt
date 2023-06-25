package next.step.blackjack.domain.player

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayerNamesTest : DescribeSpec({
    
    describe("PlayerNames method") {
        context("names()") {
            it("문자열 셋으로 이름 제공") {
                PlayerNames.of(setOf(PlayerName("name1"), PlayerName("name2"))).names() shouldBe setOf("name1", "name2")
            }
        }
    }

})
