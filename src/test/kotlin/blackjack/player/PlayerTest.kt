package blackjack.player

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardPattern
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
        }
        When("카드를 두 장 받으면") {
            val player = Player("참가자")
            val card1 = Card(CardNumber.JACK, CardPattern.CLOVER)
            val card2 = Card(CardNumber.TEN, CardPattern.CLOVER)
            player.addCard(card1)
            player.addCard(card2)
            Then("참가자는 손패에 카드를 두 장 추가한다.") {
                player.getHandSize() shouldBe 2
            }
        }
    }
})
