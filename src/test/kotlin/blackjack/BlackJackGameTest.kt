package blackjack

import baclkjack.domain.BlackJackGame
import baclkjack.domain.play.Money
import baclkjack.domain.play.Player
import baclkjack.domain.play.toPlayers
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({

    "Player([a, 1000],[b, 2000], [c, 3000]) 와 딜러 BlackJackGame 생성한다" {

        val players = mutableListOf(
            Player(name = "a", money = Money(1_000)),
            Player(name = "b", money = Money(2_000)),
            Player(name = "c", money = Money(3_000)),
        ).toPlayers()

        val game = BlackJackGame(players)

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
