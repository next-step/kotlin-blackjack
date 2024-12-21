package blackjack.controller

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.GameUser
import blackjack.domain.player.Player
import blackjack.domain.state.GameResult
import blackjack.domain.state.toInputState
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackGame(users: String) {
    val allUsers = settingUsers(users)
    val dealer = Dealer()
    private val cardDeck = CardDeck()

    private fun settingUsers(users: String): List<GameUser> {
        val usersText = users.replace(" ", "")
        return usersText.split(",").map {
            val bettingMoney = InputView.inputBetting(it).toInt()
            GameUser(it, bettingMoney) { InputView.inputNextDecision(it).toInputState() }
        }
    }

    fun start() {
        (allUsers + dealer).forEach {
            it.cards.add(cardDeck.getNextCard())
            it.cards.add(cardDeck.getNextCard())
        }

        ResultView.printStartGame(dealer, allUsers, 2)

        (listOf(dealer) + allUsers).forEach {
            ResultView.printPlayerCards(it)
        }

        (allUsers + dealer).forEach {
            turnGameUser(it)
        }

        ResultView.printResultCards(listOf(dealer) + allUsers)

        val gameResult = GameResult(dealer, allUsers)

        ResultView.printGameResult(gameResult)
    }

    private fun turnGameUser(user: Player) {
        while (user.isDoneGame().not()) {
            user.turn(
                nextCard = { cardDeck.getNextCard() },
                display = { message -> ResultView.printMessage(message) },
            )
        }
    }
}

fun main() {
    val users = InputView.inputUsers()
    val game = BlackJackGame(users)

    game.start()
}
