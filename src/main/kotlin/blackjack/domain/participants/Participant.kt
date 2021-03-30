package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.state.FirstTurn
import blackjack.domain.state.State

abstract class Participant(
    val name: String,
    initCards: ArrayList<Card>
) {
    val cards: Cards = Cards(initCards)
    var state: State

    init {
        if(initCards.isEmpty()) {
            initCards()
        }
        state = FirstTurn().draw(cards)
    }

    abstract fun checkCardDrawAvailable(): Boolean
    abstract fun drawCard()

    fun getScore(): Int {
        return cards.score().value
    }

    fun showCards(): String {
        return cards.displayCards.joinToString(", ")
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