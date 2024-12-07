package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.random.Random

class DeckTest : StringSpec({
    "Deck should initialize with 52 unique cards" {
        val deck = Deck(Random(21))
        val cards = deck.getCards()

        cards.size shouldBe 52
        cards.distinctBy { it.rank to it.suit }.size shouldBe 52
    }

    "Deck should return a random card and remove it from the deck" {
        val fixedRandom = Random(21)
        val deck = Deck(fixedRandom)
        val initialSize = deck.getCards().size

        val drawnCard = deck.draw()
        deck.getCards().size shouldBe initialSize - 1
        deck.getCards().contains(drawnCard) shouldBe false
    }

    "Deck should throw an exception when drawing from an empty deck" {
        val deck = Deck(Random(21))
        repeat(52) { deck.draw() }

        val exception =
            shouldThrow<IllegalStateException> {
                deck.draw()
            }
        exception.message shouldBe "No cards left in the deck"
    }
})
