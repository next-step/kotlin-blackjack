package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class GameTest : BehaviorSpec({
    given("블랙잭 게임을 시작했을 때") {
        `when`("게임에 참가하는 플레이어가 있다면") {
            val name = "윤영빈"
            val player = Player(name)

            then("2장의 카드를 지급한다.") {
                Game.addPlayer(player)

                val addedPlayer = Game.players
                    .firstOrNull { it.name == name }
                addedPlayer!!.cards.values.size shouldBe 2
            }
        }
    }
})
