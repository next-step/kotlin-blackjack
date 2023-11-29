package blackjack.domain

import blackjack.domain.BlackJackGame.MAX_SCORE
import blackjack.domain.BlackJackGame.score

class Player(val name: String, val cards: CardList = CardList()) {
    fun addCard(newCard: Card) {
        cards.addCard(newCard)
    }

    fun addCard(newCards: List<Card>) {
        cards.addCard(newCards)
    }

    fun canDraw(): Boolean {
        return score(cards) < MAX_SCORE
    }
}
