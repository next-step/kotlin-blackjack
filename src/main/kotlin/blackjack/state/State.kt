package blackjack.state

import blackjack.Card
import blackjack.PlayerDeck

const val BLACKJACK_NUMBER = 21

interface State {
    fun currentCard(): PlayerDeck
    fun isFinish(): Boolean
    fun draw(card: Card): State
    fun score(cards: List<Card>): Int {
        return cards.map { it.cardSymbol }.sumOf { it.score }
    }
}
