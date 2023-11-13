package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    Given("이름이 주어지면") {
        val name = "pobi"
        When("플레이어는") {
            val player = Player(name)
            Then("주어진 이름을 갖는 플레이어가 생성된다.") {
                player.name shouldBe name
            }
        }
    }
})
