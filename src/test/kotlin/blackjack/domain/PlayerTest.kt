package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    given("플레이어 이름이 주어지면") {
        val name = "원동재"
        `when`("플레이어 생성을 하면") {
            val player = Player(name)
            then("플레이어의 이름은 원동재가 된다.") {
                player.name shouldBe "원동재"
            }
        }
    }

    given("플레이어 이름과 트럼프 카드가 주어지고") {
        val name = "원동재"
        val trumpCard = TrumpCard.init()
        val player = Player(name)
        `when`("플레이어가 초기 카드를 받게되면") {
            player.init(trumpCard)
            then("플레이어의 카드는 2장이 된다.") {
                player.cardSet.size shouldBe 2
            }
        }
    }

    given("트럼프 카드가 주어지고 플레이어가 2장을 받았다면") {
        val trumpCard = TrumpCard.init()
        val player = Player("원동재").apply { init(trumpCard) }
        `when`("플레이어 카드에 카드를 한장 더 뽑으면") {
            player.hit(trumpCard)
            then("플레이어 카드는 3장이다.") {
                player.cardSet.size shouldBe 3
            }
        }
    }
})
