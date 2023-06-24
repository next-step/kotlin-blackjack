package blackjack

import blackjack.domain.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어는 이름을 가진다" {
        val player = Player("Park")
        player.name shouldBe "Park"
    }
})
