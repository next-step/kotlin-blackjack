package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameTest : StringSpec({
    "Game should distribute two cards to each player at the start" {
        val deck = Deck()
        val players = Players(listOf(Player("pobi"), Player("jason")))
        val game = Game(deck, players)

        game.start()

        players.getPlayers().forEach { player ->
            player.getCards().size shouldBe 2
        }
    }

    "Game should allow a player to hit" {
        val deck = Deck()
        val players = Players(listOf(Player("pobi"), Player("jason")))
        val game = Game(deck, players)

        game.start()
        game.hit("pobi")

        val pobi = players.getPlayers().find { it.name == "pobi" }!!
        pobi.getCards().size shouldBe 3
    }

    "Game should calculate results correctly" {
        val deck = Deck()
        val players = Players(listOf(Player("pobi"), Player("jason")))
        val game = Game(deck, players)

        game.start()

        val results = game.calculateResults()
        results["pobi"] shouldBe players.getPlayers().find { it.name == "pobi" }?.getTotalValue()
        results["jason"] shouldBe players.getPlayers().find { it.name == "jason" }?.getTotalValue()
    }
})
