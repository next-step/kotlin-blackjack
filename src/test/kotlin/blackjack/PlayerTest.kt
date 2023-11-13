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

    Given("이름과 패가 주어지면") {
        val name = "pobi"
        val hand = Hand(listOf(Card(CardSuit.HEART, CardNumber.TWO), Card(CardSuit.SPADE, CardNumber.EIGHT)))
        When("플레이어는") {
            val player = Player(name, hand)
            Then("주어진 이름과 패를 갖는 플레이어가 생성된다.") {
                player.name shouldBe name
                player.hand shouldBe hand
            }
        }
    }
})
