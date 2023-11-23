package blackjack.domain.cards

import blackjack.domain.card.Card
import blackjack.domain.card.Character
import blackjack.domain.card.Suit
import io.kotest.core.spec.style.StringSpec
import org.assertj.core.api.Assertions
import java.lang.IllegalArgumentException

class DeckCardsTest : StringSpec({
    "초기 DeckCards 은 52장의 카드로 이루어진다" {
        Assertions.assertThatThrownBy {
            DeckCards(mutableListOf(Card(Suit.Spade, Character.Ace)))
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Invalid initial Card count")
    }

    "초기 DeckCards 의 각 Suit 카드는 13장씩 있어야 한다" {
        Assertions.assertThatThrownBy {
            val deckCards = DeckCards.fullDeckCards().cardList.toMutableList()
            val card = deckCards.removeAt(0)

            deckCards.add(Card(Suit.values().first { it != card.suit }, Character.Ace))
            DeckCards(deckCards)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Invalid suit count")
    }

    "초기 DeckCards 는 중복 없는 카드로 이루어져야 한다" {
        Assertions.assertThatThrownBy {
            val deckCards = DeckCards.fullDeckCards().cardList.toMutableList()
            val card = deckCards.removeAt(0)

            deckCards.add(Card(card.suit, Character.values().first { it != card.character }))
            DeckCards(deckCards)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Duplicate cards")
    }
})
