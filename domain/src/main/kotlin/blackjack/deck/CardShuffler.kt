package blackjack.deck

import blackjack.card.Card

interface CardShuffler {
    fun shuffle(cards: List<Card>): List<Card>
}
