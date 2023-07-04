package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.BlackjackGame.Companion.BLACKJACK_VALUE
import blackjack.domain.Cards
import blackjack.domain.result.DealerResult
import blackjack.domain.users.Player
import blackjack.domain.users.User
import blackjack.view.enterUserNames
import blackjack.view.printBlackjackResult
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

        printCardResults(blackjackGame.userCards())

        calculateBlackjackResult(blackjackGame)
        printBlackjackResult(blackjackGame.users)
    }

    private fun printUserCardInfos(userCards: Map<User, Cards>) {
        for (userCard in userCards) {
            val user = userCard.key
            printUserCards(user.name(), user.cards())
        }
    }

    private fun handOutCards(blackjackGame: BlackjackGame) {
        val users = blackjackGame.cardReceivePossibleUsers()
        users.forEach { userCardReceive(it, blackjackGame) }

        dealerCardReceive(blackjackGame)
    }

    private fun userCardReceive(user: Player, blackjackGame: BlackjackGame) {
        cardReceiveWant(user, blackjackGame)

        if (user.cardValues() >= BLACKJACK_VALUE) {
            user.deckComplete()
        }

        if (!user.isDeckComplete()) {
            userCardReceive(user, blackjackGame)
        }
    }

    private fun dealerCardReceive(blackjackGame: BlackjackGame) {
        val dealer = blackjackGame.users.dealer
        if (dealer.cardReceivePossible()) {
            dealer.addCard(blackjackGame.handOutCard())
        }
    }

    private fun cardReceiveWant(user: Player, blackjackGame: BlackjackGame) {
        when (printCardReceiveWant(user.name())) {
            "y" -> {
                user.addCard(blackjackGame.handOutCard())
                printUserCards(user.name(), user.cards())
            }

            "n" -> user.deckComplete()
        }
    }

    private fun printCardResults(userCards: Map<User, Cards>) {
        printLine()
        for (userCard in userCards) {
            val user = userCard.key
            printResults(user.name(), user.cardList(), user.cardValues())
        }
    }

    private fun calculateBlackjackResult(blackjackGame: BlackjackGame) {
        val dealerCardValue = blackjackGame.dealerCardValue()
        val users = blackjackGame.users

        if (dealerCardValue > BLACKJACK_VALUE) {
            users.dealerLose(blackjackGame)
            return
        }

        val dealerResult = users.calculateDealerResult(dealerCardValue)
        blackjackGame.updateDealerResult(DealerResult(dealerResult))
    }
}
