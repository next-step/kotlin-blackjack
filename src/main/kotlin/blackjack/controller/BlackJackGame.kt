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
    private var inputView: InputView? = null
    private var resultView: ResultView? = null

    private fun settingUsers(users: String): List<Player> {
        val usersText = users.replace(" ", "")
        val userList =
            usersText.split(",").map {
                GameUser(it) { InputView.inputNextDecision(it).toInputState() }
            }.toMutableList<Player>()
        return userList.toList()
    }

    fun start(
        inputView: InputView,
        resultView: ResultView,
    ) {
        (allUsers + dealer).forEach {
            it.cards.add(cardDeck.getNextCard())
            it.cards.add(cardDeck.getNextCard())
        }

        this.inputView = inputView
        this.resultView = resultView
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

    game.start(InputView, ResultView)
    ResultView.printStartGame(game.dealer, game.allUsers, 2)

    (listOf(game.dealer) + game.allUsers).forEach {
        ResultView.printPlayerCards(it)
    }

    (game.allUsers + game.dealer).forEach {
        game.turnGameUser(it)
    }

    val gameResult = GameResult(game.dealer, game.allUsers)

    ResultView.printResultCards(game.dealer, game.allUsers)

    ResultView.printGameResult(gameResult)
}
