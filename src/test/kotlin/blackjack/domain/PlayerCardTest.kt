package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerCardTest : BehaviorSpec({
    given("트럼프 카드가 주어지고") {
        val trumpCard = TrumpCard.init()
        `when`("플레이어 카드를 생성하면") {
            val playerCard = PlayerCard.init(trumpCard)
            then("플레이어 카드는 2장이다.") {
                playerCard.cards.size shouldBe 2
            }
        }
    }

    given("트럼프 카드가 주어지고 플레이어 카드가 생성이 되었다면") {
        val trumpCard = TrumpCard.init()
        val playerCard = PlayerCard.init(trumpCard)
        `when`("플레이어 카드에 카드를 한장 더 뽑으면") {
            playerCard.add(trumpCard.draw())
            then("플레이어 카드는 3장이다.") {
                playerCard.cards.size shouldBe 3
            }
        }
    }
})
