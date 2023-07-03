package blackjack

import baclkjack.domain.BlackJackGame
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({

    "a,b,c를 Player 와 딜러 BlackJackGame 생성한다" {
        val game = BlackJackGame(
            mutableListOf<String>().apply {
                add("a")
                add("b")
                add("c")
            }
        )

        assertSoftly(game.players) {
            game.players[0].name shouldBe "a"
            game.players[1].name shouldBe "b"
            game.players[2].name shouldBe "c"

            game.dealer.name shouldBe "딜러"
        }
    }
})
