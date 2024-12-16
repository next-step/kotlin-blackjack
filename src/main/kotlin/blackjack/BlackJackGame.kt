package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.GameUser
import blackjack.domain.GameUserInterface
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
    private val cardDeck = CardDeck()
    private var inputView: InputView? = null
    private var resultView: ResultView? = null

    fun getUsers(): List<GameUserInterface> {
        val userCount = allUsers.size - 1
        return allUsers.take(userCount)
    }

    fun getDealer(): GameUserInterface {
        return allUsers.last()
    }

    private fun settingUsers(users: String): List<GameUserInterface> {
        val usersText = users.replace(" ", "")
        val userList =
            usersText.split(",").map {
                GameUser(it)
            }.toMutableList<GameUserInterface>()
        userList.add(Dealer())
        return userList.toList()
    }

    fun start(
        inputView: InputView,
        resultView: ResultView,
    ) {
        allUsers.forEach {
            it.addCard(cardDeck.getNextCard())
            it.addCard(cardDeck.getNextCard())
        }
        this.inputView = inputView
        this.resultView = resultView
    }

    private fun handleCurrentInput(
        user: GameUserInterface,
        decision: Boolean?,
    ) {
        // 21이 넘는 경우 종료됨.
        when (decision) {
            true -> user.addCard(cardDeck.getNextCard())
            false -> user.doneGame(true)
            else -> {}
        }
    }

    fun turnGameUser(
        user: GameUserInterface,
        inputView: InputView,
    ) {
        inputView.startUser()
        while (user.isDoneGame().not()) {
            turnGameWithType(user, inputView)
        }
    }

    private fun turnGameWithType(
        user: GameUserInterface,
        inputView: InputView,
    ) {
        when (user) {
            is GameUser -> turnUser(user, inputView)
            is Dealer -> turnDealer(user, inputView)
        }
    }

    private fun turnDealer(
        user: GameUserInterface,
        inputView: InputView,
    ) {
        inputView.printAddCardDealer(user.getPoints())
        user.addCard(cardDeck.getNextCard())
    }

    private fun turnUser(
        user: GameUserInterface,
        inputView: InputView,
    ) {
        val inputData = inputView.inputNextDecision(user.getName())
        val decision = YNBooleanValue.toBoolean(inputData)
        handleCurrentInput(user, decision)
    }
}

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val users = inputView.inputUsers()
    val game = BlackJackGame(users)

    game.start(inputView, resultView)
    inputView.startGame(game.allUsers, 2)

    game.allUsers.forEach {
        resultView.printUserCards(it)
    }

    game.allUsers.forEach {
        game.turnGameUser(it, inputView)
    }
    resultView.printResultCards(game.getUsers(), game.getDealer())
}
