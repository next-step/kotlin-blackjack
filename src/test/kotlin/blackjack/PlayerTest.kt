package blackjack

import baclkjack.domain.play.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "a Player 를 생성한다" {
        val player = Player("a")
        player.name shouldBe "a"
    }

})
