package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class PlayerTest : BehaviorSpec({
    given("플레이어가 가진 카드의 총합이") {
        `when`("21 을 초과했을 때") {
            val cards = Cards(
                listOf(
                    Card(Suite.CLOVER, Denomination.QUEEN),
                    Card(Suite.CLOVER, Denomination.JACK),
                    Card(Suite.CLOVER, Denomination.KING),
                )
            )
            val player = Player("윤영빈", cards)
            val result = player.isBust()

            then("true 를 반환한다. (버스트다)") {
                result shouldBe true
            }
        }
    }
})
