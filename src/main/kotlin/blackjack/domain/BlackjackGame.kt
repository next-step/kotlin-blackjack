package blackjack.domain

import blackjack.util.CardSelector
import blackjack.util.RandomCardSelector
import java.util.LinkedList

class BlackjackGame(private val cardSelector: CardSelector = RandomCardSelector()) {

    fun getInitDeck(): Deck {
        val cardList = LinkedList<Card>()
        repeat(INITIAL_DECK_SIZE) {
            cardList.add(cardSelector.drawCard())
        }
        return Deck(cardList)
    }

    fun addCardTo(user: User) {
        user.addCard(cardSelector.drawCard())
    }

    companion object {
        private const val INITIAL_DECK_SIZE = 2
    }
}
