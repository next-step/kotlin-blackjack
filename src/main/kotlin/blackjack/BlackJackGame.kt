package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.GameUser
import blackjack.view.InputView
import blackjack.view.ResultView

fun String.toYNBoolean(): Boolean? {
    if (this == "y") return true
    if (this == "n") return false
    return null
}

class BlackJackGame {
    val gameUsers = mutableListOf<GameUser>()
    lateinit var cardDeck: CardDeck

    // return setting user count
    fun settingUsers(users: String): Int {
        val usersText = users.replace(" ", "")
        usersText.split(",").forEach {
            gameUsers.add(GameUser(it))
        }
        return gameUsers.size
    }

    fun initializeCardDeck() {
        cardDeck = CardDeck()
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

    private fun currentProcess(
        user: GameUser,
        decision: Boolean?,
    ): Boolean {
        // 21이 넘는 경우 종료됨.
        when (decision) {
            true -> user.addCard(cardDeck.getNextCard())
            false -> return false
            else -> {}
        }
        resultView?.printUserCards(user)
        return user.points < 21
    }

    fun turnGameUser(
        user: GameUser,
        inputView: InputView,
    ) {
        var continueDecision: Boolean
        do {
            val inputData = inputView.inputNextDecision(user.name)
            val decision = inputData.toYNBoolean()
            continueDecision = currentProcess(user, decision)
        } while (continueDecision)
    }
}

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val users = inputView.inputUsers()
    val game =
        BlackJackGame().apply {
            settingUsers(users)
            initializeCardDeck()
        }

    game.start(inputView, resultView)
    inputView.startGame(game.gameUsers, 2)
    println()

    game.gameUsers.forEach {
        resultView.printUserCards(it)
    }

    println()

    game.gameUsers.forEach {
        game.turnGameUser(it, inputView)
    }

    println()
    resultView.printResultCards(game.gameUsers)
}
