package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions
import java.lang.IllegalArgumentException

class DeckTest : StringSpec({
    "Deck 은 52장의 중복 없는 카드로 이루어진다" {
        Assertions.assertThatThrownBy {
            Deck(Cards(mutableListOf(Card(Suit.Spade, Character.Ace))))
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Invalid initial Card count")

        Assertions.assertThatThrownBy {
            val cards = Cards.fullCards()
            val card = cards.drawTop()

            cards.add(Card(Suit.values().first { it != card.suit }, Character.Ace))
            Deck(cards)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Invalid suit count")

        Assertions.assertThatThrownBy {
            val cards = Cards.fullCards()
            val card = cards.drawTop()

            cards.add(Card(card.suit, Character.values().first { it != card.character }))
            Deck(cards)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Duplicate cards")
    }

    "Deck 에서 draw 하면 Deck 의 카드 수는 1 감소해야 한다" {
        val deck = Deck.fullDeck()
        val prevSize = deck.cardCount()
        deck.draw()
        val nowSize = deck.cardCount()
        prevSize shouldBe nowSize + 1
    }

    "Deck 에서 draw 한 카드는 Deck 에서 사라져야 한다" {
        val deck = Deck.fullDeck()
        val card = deck.draw()

        deck.cards.cardList.contains(card) shouldBe false
    }
})
