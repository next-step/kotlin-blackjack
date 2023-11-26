package blackjack.domain

import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJack() {
    private val cardDraw = CardDraw.init()
    private val players = InputView.getPlayers()

    fun run() {
        startRound()
        progressRound()
        getGameResult()
    }

    private fun startRound() {
        repeat(CardDraw.INITIAL_SIZE) {
            val cards = cardDraw.draw(players.players.size)
            players.receiveCards(cards)
        }
        ResultView.printPlayerStates(players, CardDraw.INITIAL_SIZE)
    }

    private fun Player.play(cardDraw: CardDraw) {
        if (InputView.askPlayer(name)) {
            this.receiveCard(cardDraw.draw())
            ResultView.printPlayerCards(this)
        } else {
            this.setStand()
        }
    }

    private fun progressRound() {
        while (players.getHits().isNotEmpty()) {
            players.getHits().first().play(cardDraw)
        }
    }

    private fun getGameResult() {
        players.players.forEach {
            ResultView.printResult(it, it.getScore())
        }
    }
}
