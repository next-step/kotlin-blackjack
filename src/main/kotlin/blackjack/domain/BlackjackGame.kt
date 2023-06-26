package blackjack.domain

import blackjack.util.CardSelector
import blackjack.util.RandomCardSelector

class BlackjackGame(private val cardSelector: CardSelector = RandomCardSelector()) {
    private val dealer = User(DEALER_NAME, getInitDeck())

    fun getDealerDeck(): Deck = dealer.deck

    fun getInitDeck(): Deck {
        val cardList = mutableListOf<Card>()
        repeat(INITIAL_DECK_SIZE) {
            cardList.add(cardSelector.drawCard())
        }
        return Deck(cardList)
    }

    private fun addCardTo(user: User) {
        user.addCard(cardSelector.drawCard())
    }

    fun userHit(user: User, checkHit: (User) -> Boolean, afterHit: () -> Unit) {
        while (checkHit(user)) {
            addCardTo(user)
            afterHit()
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val INITIAL_DECK_SIZE = 2
    }
}
