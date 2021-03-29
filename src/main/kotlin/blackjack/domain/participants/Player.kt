package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.card.Score
import blackjack.domain.state.FirstTurn
import blackjack.domain.state.State

class Player(
    val name: String,
    initCards: ArrayList<Card> = arrayListOf<Card>()
) {
    private val cards: Cards = Cards(initCards)
    var state: State

    init {
        if(initCards.isEmpty()) {
            initCards()
        }
        state = FirstTurn().draw(cards)
    }

    fun drawCard() {
        if(!state.isFinished) {
            val card = CardDeck.drawCard()
            state = state.draw(card)
        }
    }

    fun getPlayerScore(): Int {
        return cards.score().value
    }

    private fun initCards() {
        repeat(INIT_CARDS) {
            cards.add(CardDeck.drawCard())
        }
    }

    companion object {
        private const val INIT_CARDS = 2
    }
}