package blackjack.controller

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.GameUser
import blackjack.domain.player.Player
import blackjack.view.InputView
import blackjack.view.ResultView

enum class YNBooleanValue(val key: String, val booleanValue: Boolean) {
    YES("y", true),
    NO("n", false),
    ;

    companion object {
        fun toBoolean(key: String): Boolean? {
            return when (key) {
                YES.key -> YES.booleanValue
                NO.key -> NO.booleanValue
                else -> null
            }
        }
    }
}

class BlackJackGame(users: String) {
    val allUsers = settingUsers(users)
    val dealer = Dealer()
    private val cardDeck = CardDeck()
    private var inputView: InputView? = null
    private var resultView: ResultView? = null

    fun getUsers(): List<Player> {
        return allUsers
    }

    fun getDealer(): Player {
        return dealer
    }

    private fun settingUsers(users: String): List<Player> {
        val usersText = users.replace(" ", "")
        val userList =
            usersText.split(",").map {
                GameUser(it)
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

    private fun handleCurrentInput(
        user: Player,
        decision: Boolean?,
    ) {
        // 21이 넘는 경우 종료됨.
        when (decision) {
            true -> user.cards.add(cardDeck.getNextCard())
            false -> user.setDoneGame(true)
            else -> {}
        }
    }

    fun turnGameUser(
        user: Player,
        inputView: InputView,
    ) {
        inputView.startUser()
        while (user.isDoneGame().not()) {
            user.turn(inputView) { cardDeck.getNextCard() }
        }
    }
}

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val users = inputView.inputUsers()
    val game = BlackJackGame(users)

    game.start(inputView, resultView)
    inputView.startGame(game.dealer, game.allUsers, 2)

    (listOf(game.dealer) + game.allUsers).forEach {
        resultView.printPlayerCards(it)
    }

    game.allUsers.forEach {
        game.turnGameUser(it, inputView)
    }
    resultView.printResultCards(game.getUsers(), game.getDealer())
}
