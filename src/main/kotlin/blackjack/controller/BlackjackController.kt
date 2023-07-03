package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.BlackjackGame.Companion.BLACKJACK_VALUE
import blackjack.domain.Cards
import blackjack.domain.User
import blackjack.view.enterUserNames
import blackjack.view.printCardReceiveWant
import blackjack.view.printLine
import blackjack.view.printResults
import blackjack.view.printUserCards
import blackjack.view.printUserNames

class BlackjackController {
    fun start() {
        val userNames = enterUserNames().trim()
        val blackjackGame = BlackjackGame.from(userNames)
        printUserNames(userNames)
        printUserCardInfos(blackjackGame.userCards())

        handOutCards(blackjackGame)

        printGameResults(blackjackGame)
    }

    private fun printUserCardInfos(userCards: Map<User, Cards>) {
        for (userCard in userCards) {
            val user = userCard.key
            printUserCards(user.name, user.cards)
        }
    }

    private fun handOutCards(blackjackGame: BlackjackGame) {
        val users = blackjackGame.cardReceivePossibleUsers()
        users.forEach { cardReceive(it, blackjackGame) }
    }

    private fun cardReceive(user: User, blackjackGame: BlackjackGame) {
        when (printCardReceiveWant(user.name)) {
            "y" -> {
                user.addCard(blackjackGame.handOutCard())
                printUserCards(user.name, user.cards)
            }

            "n" -> {
                user.deckComplete()
            }
        }

        if (user.cardValues() >= BLACKJACK_VALUE) {
            user.deckComplete()
        }

        if (!user.isDeckComplete) {
            cardReceive(user, blackjackGame)
        }
    }

    private fun printGameResults(blackjackGame: BlackjackGame) {
        printLine()
        for (userCard in blackjackGame.userCards()) {
            printResults(userCard.key)
        }
    }
}
