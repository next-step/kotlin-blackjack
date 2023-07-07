package blackjack

import baclkjack.domain.BlackJackGame
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({

    "Player([a, 1000],[b, 2000], [c, 3000]) 와 딜러 BlackJackGame 생성한다" {
        val game = BlackJackGame(
            mutableListOf<String>().apply {
                add("a")
                add("b")
                add("c")
            },

            mutableListOf<Int>().apply {
                add(1_000)
                add(2_000)
                add(3_000)
            }
        )

        assertSoftly(game.players) {
            it[0].name shouldBe "a"
            it[0].money.value shouldBe 1_000
            it[1].name shouldBe "b"
            it[1].money.value shouldBe 2_000
            it[2].name shouldBe "c"
            it[2].money.value shouldBe 3_000
        }
        game.dealer.name shouldBe "딜러"
    }
})
