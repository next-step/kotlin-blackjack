package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    "Players should distribute two cards to each player" {
        val players = Players(listOf(Player("pobi"), Player("jason")))
        val deck = Deck()

        players.distributeInitialCards(deck)

        players.getPlayers().forEach { player ->
            player.getCards().size shouldBe 2
        }
    }

    "Players should allow a specific player to draw a card" {
        val players = Players(listOf(Player("pobi"), Player("jason")))
        val deck = Deck()

        players.distributeInitialCards(deck)
        players.hit("pobi", deck)

        val pobi = players.getPlayers().find { it.name == "pobi" }!!
        pobi.getCards().size shouldBe 3
    }

    "Players should throw exception if a non-existent player tries to hit" {
        val players = Players(listOf(Player("pobi"), Player("jason")))
        val deck = Deck()

        shouldThrow<NoSuchElementException> {
            players.hit("unknown", deck)
        }
    }
})
