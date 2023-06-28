package blackjack.player

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardSuit
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({

    Given("게임을 시작할 때") {
        When("참가자가 생성되면") {
            val player = Player("참가자")
            Then("참가자는 이름을 가진다.") {
                player.name shouldBe "참가자"
            }
            Then("참가자는 빈 손 패를 가진다.") {
                player.getHandSize() shouldBe 0
            }
            Then("참가자의 초기 상태는 WANT이다.") {
                player.getStatus() shouldBe Status.HIT
            }
        }
        When("카드를 두 장 받으면") {
            val player = Player("참가자")
            val card1 = Card(CardNumber.JACK, CardSuit.CLUB)
            val card2 = Card(CardNumber.TEN, CardSuit.CLUB)
            player.addCard(card1)
            player.addCard(card2)
            Then("참가자는 손패에 카드를 두 장 추가한다.") {
                player.getHandSize() shouldBe 2
            }
        }
    }

    Given("게임을 시작해서 자기 순서가 되면") {
        val player = Player("참가자")
        When("카드를 받지 않겠다고 할 때") {
            Then("Status 상태를 STAND로 바꾼다.") {
                player.updateStatus(Status.STAND)
                player.getStatus() shouldBe Status.STAND
            }
            And("카드를 받고 싶다고 할 때") {
                Then("Status 상태를 WANT로 바꾼다.") {
                    player.updateStatus(Status.HIT)
                    player.getStatus() shouldBe Status.HIT
                }
            }
        }
    }
})
