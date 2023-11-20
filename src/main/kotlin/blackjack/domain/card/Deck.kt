package blackjack.domain.card

import blackjack.model.Card
import blackjack.model.Character
import blackjack.model.Suit

class Deck(val deckCards: DeckCards) {
    init {
        require(deckCards.size == initialCardCount) { "Invalid initial Card count ${deckCards.size}" }
        require(deckCards.cardList.count { it.suit == Suit.Spade } == Character.values().size) { "Invalid suit count ${Suit.Spade}" }
        require(deckCards.cardList.count { it.suit == Suit.Diamond } == Character.values().size) { "Invalid suit count ${Suit.Diamond}" }
        require(deckCards.cardList.count { it.suit == Suit.Heart } == Character.values().size) { "Invalid suit count ${Suit.Heart}" }
        require(deckCards.cardList.count { it.suit == Suit.Clover } == Character.values().size) { "Invalid suit count ${Suit.Clover}" }
        require(deckCards.cardList.toSet().size == initialCardCount) { "Duplicate cards ${deckCards.cardList}" }
    }

    fun draw(): Card = deckCards.drawTop()

    fun shuffle() {
        deckCards.shuffle()
    }

    fun cardCount(): Int = deckCards.size

    companion object {
        val initialCardCount = Suit.values().size * Character.values().size

        fun fullDeck(): Deck {
            return Deck(
                DeckCards.fullDeckCards()
            )
        }
    }
}
