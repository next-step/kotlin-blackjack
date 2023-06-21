package blackjack

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.Users
import blackjack.util.CardSelector
import blackjack.util.RandomCardSelector
import java.util.LinkedList

class BlackjackGame(
    val users: Users,
    private val cardSelector: CardSelector = RandomCardSelector(),
) {
    init {
        initialUsersDeck()
    }

    private fun initialUsersDeck() {
        for (user in users) {
            val cardList = LinkedList<Card>()
            repeat(INITIAL_DECK_SIZE) {
                cardList.add(cardSelector.getCard())
            }
            val deck = Deck(cardList)
            user.initDeck(deck)
        }
    }

    companion object {
        const val INITIAL_DECK_SIZE = 2
    }
}
