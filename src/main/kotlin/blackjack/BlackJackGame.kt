package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.GameUser
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
    val gameUsers = settingUsers(users)
    private val cardDeck = CardDeck()

    private fun settingUsers(users: String): List<GameUser> {
        val usersText = users.replace(" ", "")
        return usersText.split(",").map {
            GameUser(it)
        }.toList()
    }

    private var inputView: InputView? = null
    private var resultView: ResultView? = null

    fun start(
        inputView: InputView,
        resultView: ResultView,
    ) {
        gameUsers.forEach {
            it.addCard(cardDeck.getNextCard())
            it.addCard(cardDeck.getNextCard())
        }
        this.inputView = inputView
        this.resultView = resultView
    }

    private fun handleCurrentInput(
        user: GameUser,
        decision: Boolean?,
    ) {
        println(decision)
        // 21이 넘는 경우 종료됨.
        when (decision) {
            true -> user.addCard(cardDeck.getNextCard())
            false -> user.doneGame(true)
            else -> {}
        }
    }

    fun turnGameUser(
        user: GameUser,
        inputView: InputView,
    ) {
        inputView.startUser()
        while (user.isDoneGame().not()) {
            val inputData = inputView.inputNextDecision(user.name)
            val decision = YNBooleanValue.toBoolean(inputData)
            handleCurrentInput(user, decision)
        }
    }
}

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val users = inputView.inputUsers()
    val game = BlackJackGame(users)

    game.start(inputView, resultView)
    inputView.startGame(game.gameUsers, 2)

    game.gameUsers.forEach {
        resultView.printUserCards(it)
    }

    game.gameUsers.forEach {
        game.turnGameUser(it, inputView)
    }

    resultView.printResultCards(game.gameUsers)
}
