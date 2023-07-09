package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Cards
import blackjack.domain.users.Player
import blackjack.domain.users.User
import blackjack.view.USER_NAME_SPLIT_SYMBOL
import blackjack.view.enterUserNames
import blackjack.view.printBlackjackResult
import blackjack.view.printCardReceiveNotWant
import blackjack.view.printLine
import blackjack.view.printResults
import blackjack.view.printUserCards
import blackjack.view.printUserNames

class BlackjackController {
    fun start() {
        val userNames = enterUserNames()
        val blackjackGame = BlackjackGame.from(userNames)
        printUserNames(userNames.joinToString(USER_NAME_SPLIT_SYMBOL))
        printUserCardInfos(blackjackGame.userCards())

        handOutCards(blackjackGame)

        printCardResults(blackjackGame.userCards())

        val blackjackResult = blackjackGame.calculateBlackjackResult()
        printBlackjackResult(blackjackResult)
    }

    private fun printUserCardInfos(userCards: Map<User, Cards>) {
        for (userCard in userCards) {
            val user = userCard.key
            printUserCards(user.name, user.cards)
        }
    }

    private fun handOutCards(blackjackGame: BlackjackGame) {
        blackjackGame.cardReceivePossibleUsers().forEach {
            cardReceiveWant(it, blackjackGame)
        }

        blackjackGame.dealerCardReceive()
    }

    private fun cardReceiveWant(user: Player, blackjackGame: BlackjackGame) {

        if (printCardReceiveNotWant(user.name)) {
            return
        }

        blackjackGame.userCardReceive(user)

        printUserCards(user.name, user.cards)

        if (user.isDeckInComplete()) {
            cardReceiveWant(user, blackjackGame)
        }
    }

    private fun printCardResults(userCards: Map<User, Cards>) {
        printLine()
        for (userCard in userCards) {
            val user = userCard.key
            printResults(user.name, user.cardList(), user.cardValues())
        }
    }
}
