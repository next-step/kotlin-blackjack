package blackjack.controller

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.GameUser
import blackjack.domain.player.Player
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
    }

    fun turnGameUser(user: Player) {
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
    ResultView.printStartGame(game.dealer, game.allUsers, 2)

    (listOf(game.dealer) + game.allUsers).forEach {
        ResultView.printPlayerCards(it)
    }

    (game.allUsers + game.dealer).forEach {
        game.turnGameUser(it)
    }
    game.dealer.updateRevenue(game.allUsers)

    ResultView.printResultCards(listOf(game.dealer) + game.allUsers)

    ResultView.printGameResult(game.dealer, game.allUsers)
}
