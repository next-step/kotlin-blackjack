package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.card.Cards
import blackjack.domain.card.InputCardReceiveSelector
import blackjack.domain.card.PrintUserCards
import blackjack.domain.users.User
import blackjack.view.USER_NAME_SPLIT_SYMBOL
import blackjack.view.enterUserBettingAmount
import blackjack.view.enterUserNames
import blackjack.view.printBlackjackResult
import blackjack.view.printLine
import blackjack.view.printResults
import blackjack.view.printUserCards
import blackjack.view.printUserNames

class BlackjackController {
    fun start() {
        val userNames = enterUserNames()

        val blackjackGame = BlackjackGame.from(userNames.associateBy({ it }, { enterUserBettingAmount(it) }))
        printUserNames(userNames.joinToString(USER_NAME_SPLIT_SYMBOL))
        printUserCardInfos(blackjackGame.userCards())

        blackjackGame.handsOutCards(InputCardReceiveSelector(), PrintUserCards())

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

    private fun printCardResults(userCards: Map<User, Cards>) {
        printLine()
        for (userCard in userCards) {
            val user = userCard.key
            printResults(user.name, user.cardList(), user.cardValues())
        }
    }
}
