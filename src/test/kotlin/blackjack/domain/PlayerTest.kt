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
})
