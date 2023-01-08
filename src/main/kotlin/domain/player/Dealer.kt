package domain.player

import domain.card.CardDeck
import domain.card.Cards
import domain.factory.DefaultCardsFactory

class Dealer(
    val deck: CardDeck = CardDeck(cardsFactory = DefaultCardsFactory)
) : Playable {

    override val hands: Cards = Cards(limitReceiveScore = AVAILABLE_TARGET_NUMBER)

    init {
        deck.shuffle()
    }

    fun giveCard(playable: Playable) {
        val card = deck.pop()
        playable.receiveCard(card = card)
    }

    override fun isAvailableReceive(): Boolean = this.hands.isAvailableReceiveNumber()

    fun isWin(playable: Playable): Boolean {
        if (this.handsCardScore() > WIN_SCORE) {
            return true
        }

        if (WIN_SCORE < playable.handsCardScore()) {
            return false
        }

        return playable.handsCardScore() > this.handsCardScore()
    }

    fun drawCard() {
        giveCard(this)
    }

    private companion object {
        const val AVAILABLE_TARGET_NUMBER = 16
        const val WIN_SCORE = 21
    }
}
