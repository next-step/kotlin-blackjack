package blackjack.controller

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
    private val cardSelector: CardSelector = RandomCardSelector(),
) {
    var users: Users = InputView.getUsers()

    init {
        initUsersDeck()
        ResultView.printUsersDeck(users)
    }

    fun start() {
        dealCards()
        ResultView.printUsersResult(users)
    }

    private fun initUsersDeck() {
        for (user in users) {
            val deck = getInitDeck()
            user.initDeck(deck)
        }
    }

    private fun getInitDeck(): Deck {
        val cardList = LinkedList<Card>()
        repeat(INITIAL_DECK_SIZE) {
            cardList.add(cardSelector.getCard())
        }
        return Deck(cardList)
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
