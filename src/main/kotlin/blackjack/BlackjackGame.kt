package blackjack

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.User
import blackjack.domain.Users
import blackjack.io.InputView
import blackjack.io.ResultView
import blackjack.util.CardSelector
import blackjack.util.PointCalculator
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

    fun dealCards() {
        for (user in users) {
            checkHit(user)
        }
    }

    private fun checkHit(user: User) {
        while (InputView.checkHit(user.name)) {
            user.addCard(cardSelector.getCard())
            PointCalculator.calculateUserPoint(user.deck) ?: break
            ResultView.printUserDeck(user)
        }
        ResultView.printUserDeck(user)
    }

    companion object {
        const val INITIAL_DECK_SIZE = 2
        const val BLACKJACK_LIMIT = 21
    }
}
