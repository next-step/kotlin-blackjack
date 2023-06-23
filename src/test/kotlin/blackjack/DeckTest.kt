package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DeckTest : FunSpec({
    test("덱은 끗수 13개, 슈트 4개를 조합하여 52 종의 카드를 가진다.") {
        val deck = Deck.shuffled()
        val cards = Denomination.values().flatMap { denomination ->
            Suit.values().map { suit ->
                Card.of(denomination, suit)
            }
        }

        cards.forEach {
            (it in deck) shouldBe true
        }

        deck.size() shouldBe 52
    }

    test("마지막 카드를 1장을 뽑는다.") {
        val deck = Deck(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
            Card.of(Denomination.TWO, Suit.HEARTS),
        )

        deck.draw() shouldBe Card.of(Denomination.TWO, Suit.HEARTS)
    }

    test("카드를 여려장 뽑는다.") {
        val deck = Deck(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
            Card.of(Denomination.TWO, Suit.HEARTS),
        )

        deck.draw(2) shouldBe Cards(
            Card.of(Denomination.TWO, Suit.HEARTS),
            Card.of(Denomination.JACK, Suit.SPADES),
        )
    }

    context("덱이 안 비어있는지 확인한다.") {
        data class DeckNotEmpty(val deck: Deck, val expected: Boolean)
        withData(
            DeckNotEmpty(Deck(Card.of(Denomination.ACE, Suit.SPADES)), true),
            DeckNotEmpty(Deck(), false),
        ) { (deck, expected) ->
            deck.isNotEmpty() shouldBe expected
        }
    }

    test("카드가 덱에 있는지 확인한다") {
        val deck = Deck(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
            Card.of(Denomination.TWO, Suit.HEARTS),
        )
        val card = Card.of(Denomination.TWO, Suit.HEARTS)

        (card in deck) shouldBe true
    }

    test("덱에 카드가 몇장있는지 구한다") {
        val deck = Deck(
            Card.of(Denomination.ACE, Suit.SPADES),
            Card.of(Denomination.JACK, Suit.SPADES),
            Card.of(Denomination.TWO, Suit.HEARTS),
        )

        deck.size() shouldBe 3
    }
})
