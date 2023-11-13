package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayersTest : BehaviorSpec({
    Given("플레이어의 이름들이 주어지면") {
        val names = listOf("pobi", "jason")
        When("Players는") {
            val players = Players.init(names)
            Then("주어진 이름들을 갖는 Players를 생성한다.") {
                players.players[0].name shouldBe "pobi"
                players.players[1].name shouldBe "jason"
            }
        }
    }
})
