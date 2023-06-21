package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DeckTest : FunSpec({
    context("덱은 끗수 13개, 슈트 4개를 조합하여 52 종의 카드를 가진다.") {
        val deck = Deck.init()

        Denomination.values().forEach { denomination ->
            Suit.values().forEach { suit ->
                test("$denomination $suit") {
                    (Card(denomination, suit) in deck.cards) shouldBe true
                }
            }
        }
        deck.cards.size shouldBe 52
    }

    test("마지막 카드를 1장을 뽑는다.") {
        val deck = Deck(
            Card(Denomination.ACE, Suit.SPADES),
            Card(Denomination.JACK, Suit.SPADES),
            Card(Denomination.TWO, Suit.HEARTS),
        )

        deck.draw() shouldBe Card(Denomination.TWO, Suit.HEARTS)
    }

    test("카드를 여려장 뽑는다.") {
        val deck = Deck(
            Card(Denomination.ACE, Suit.SPADES),
            Card(Denomination.JACK, Suit.SPADES),
            Card(Denomination.TWO, Suit.HEARTS),
        )

        deck.draw(2) shouldBe Cards(
            Card(Denomination.TWO, Suit.HEARTS),
            Card(Denomination.JACK, Suit.SPADES),
        )
    }
})
