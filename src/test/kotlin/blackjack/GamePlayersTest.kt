package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GamePlayersTest : BehaviorSpec({

    Given("게임 참가자들의 이름이 콤마 기준으로 주어진다면") {
        val names = "hyunjun, semin, gilyong"
        Then("여러 참가자들을 생성한다.") {
            GamePlayers.valueOf(names).count shouldBe 3
        }
    }
})
