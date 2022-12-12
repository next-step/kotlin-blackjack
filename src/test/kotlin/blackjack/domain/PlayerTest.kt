package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class PlayerTest : BehaviorSpec({
    given("게임에 참여한 플레이어는") {
        val name = "윤영빈"

        `when`("플레이어 객체가 생성될 때") {
            val player = Player(name)

            then("2장의 카드를 지급받는다.") {
                player.cards.values.size shouldBe 2
            }
        }
    }

    given("프레이어가 가진 카드의 총합이") {
        `when`("21 을 초과했을 때") {
            val cards = Cards(listOf(
                Card(Suite.CLOVER, Denomination.QUEEN),
                Card(Suite.CLOVER, Denomination.JACK),
                Card(Suite.CLOVER, Denomination.KING),
            ))
            val player = Player("윤영빈", cards)
            val result = player.isBust()

            then("true 를 반환한다. (버스트다)") {
                result shouldBe true
            }
        }
    }
})
