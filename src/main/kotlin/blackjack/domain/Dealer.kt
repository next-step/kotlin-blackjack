package blackjack.domain

import blackjack.domain.card.CardSet

class Dealer {

    fun dealOutCards(cards: CardSet, players: Players) {
        players.players.forEach {
            val peekedCards = cards.peekCards(DEAL_OUT_CARD_AMOUNT)
            it.cards = peekedCards
        }
    }

    companion object {
        const val DEAL_OUT_CARD_AMOUNT = 2
    }
}
