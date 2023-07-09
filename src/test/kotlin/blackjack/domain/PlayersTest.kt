package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    "플레이어 생성시 이름값을 넘기면 정상적으로 플레이어가 생성된다." {
        val players = Players.of(
            listOf("tony", "lia")
        )
        with(players) {
            size shouldBe 2
            get(0).name shouldBe "tony"
        }
    }
})
