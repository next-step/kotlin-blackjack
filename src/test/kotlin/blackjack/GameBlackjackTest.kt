package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameBlackjackTest : BehaviorSpec({

    Given("게임 참가자들이 존재하고") {
        val names = "test1,test2"
        When("블랙잭을 시작하면") {
            val playing = GameBlackjack.initialDealing(names)
            Then("처음은 딜러가 2장의 카드를 나눈다.") {
                playing.players.map {
                    it.cards.size shouldBe 2
                }
            }
        }
    }
})
