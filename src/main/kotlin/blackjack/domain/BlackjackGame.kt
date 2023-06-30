package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val deck: Deck
) {

    private val players = mutableListOf<Player>()

    fun start() {
        val inputPlayers = inputView.inputPlayers()
        outputView.printFirstDeal(inputPlayers)

        transformToPlayers(inputPlayers)
    }

    fun transformToPlayers(input: List<String>) {
        val playerList = input.map {
            Player(it.trim()).apply {
                cards.add(deck.draw())
                cards.add(deck.draw())
            }
        }
        players.addAll(playerList)
    }

    fun hitOrStay() {
        println()
        players.forEach {
            do {
                val answer = inputView.inputHitOrStay(it)
                deal(answer, it)
            } while (answer == Answer.HIT)
            outputView.printPlayerCards(it)
        }
    }

    fun showResult() {
        println()
        players.forEach {
            outputView.printResult(it)
        }
    }

    private fun deal(
        answer: Answer,
        player: Player
    ) {
        when (answer) {
            Answer.HIT -> {
                player.hit(deck.draw())
            }
            Answer.STAY -> {
            }
        }
    }

    fun getPlayers(): List<Player> = players
}
