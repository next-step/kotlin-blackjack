package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest: BehaviorSpec({
    given("오픈 카드가 블랙잭 이면") {
        `when`("플레이어 생성 시") {
            val openCard = Card(CardShape.HEART, CardNumber.ACE) to Card(CardShape.HEART, CardNumber.KING)
            then("BLACKJACK 상태 이다.") {
                Player.of("player", openCard).status shouldBe PlayerStatus.BLACKJACK
            }
        }
    }

    given("오픈 카드가 블랙잭이 아니면") {
        `when`("플레이어 생성 시") {
            val openCard = Card(CardShape.HEART, CardNumber.ACE) to Card(CardShape.HEART, CardNumber._9)
            then("INIT 상태 이다.") {
                Player.of("player", openCard).status shouldBe PlayerStatus.INIT
            }
        }
    }

    given("INIT 상태의 플레이어가") {
        val openCard = Card(CardShape.HEART, CardNumber._7) to Card(CardShape.HEART, CardNumber._8)
        `when`("HIT 후 21이 넘었을 때") {
            val player = Player.of("player", openCard)
            player.hit(Card(CardShape.CLUB, CardNumber._9))
            then("BURST 상태 이다.") {
                player.status shouldBe PlayerStatus.BURST
            }
        }
        `when`("HIT 후 21이 되었을 때") {
            val player = Player.of("player", openCard)
            player.hit(Card(CardShape.CLUB, CardNumber._6))
            then("BLACKJACK 상태 이다.") {
                player.status shouldBe PlayerStatus.BLACKJACK
            }
        }
        `when`("HIT 후 21이 넘지 않았을 때") {
            val player = Player.of("player", openCard)
            player.hit(Card(CardShape.CLUB, CardNumber._5))
            then("BLACKJACK 상태 이다.") {
                player.status shouldBe PlayerStatus.HIT
            }
        }
    }

    given("HIT 상태의 플레이어가") {
        val openCard = Card(CardShape.HEART, CardNumber._7) to Card(CardShape.HEART, CardNumber._8)
        val player = Player.of("player", openCard)
        `when`("STAY 하면") {
            player.stay()
            then("STAY 상태 이다.") {
                player.status shouldBe PlayerStatus.STAY
            }
        }
    }

})
