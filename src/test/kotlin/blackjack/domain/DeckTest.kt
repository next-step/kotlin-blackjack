package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "Deck should initialize with 52 unique cards" {
        val deck = Deck()
        val cards = deck.getCards()

        cards.size shouldBe 52
        cards.distinctBy { it.rank to it.suit }.size shouldBe 52
    }

    "Deck should return a random card and remove it from the deck" {
        val deck = Deck()
        val initialzSize = deck.getCards().size

        val drawnCard = deck.draw()
        deck.getCards().size shouldBe initialzSize - 1
        deck.getCards().contains(drawnCard) shouldBe false
    }

    "Deck should throw an exception when drawing from an empty deck" {
        val deck = Deck()
        repeat(52) { deck.draw() }
        shouldThrow<NoSuchElementException> {
            deck.draw()
        }
    }
})
