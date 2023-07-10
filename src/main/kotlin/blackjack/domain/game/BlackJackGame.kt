package blackjack.domain.game

import blackjack.domain.deck.Deck
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

const val BLACKJACK_NUMBER = 21
const val ACE_HIDDEN_VALUE = 10
const val START_DRAW_COUNT = 2

class BlackJackGame(private val deck: Deck) {
    private lateinit var players: Players
    fun start() {
        players = InputView.getPlayerInput()
        OutputView.printPlayersInitView(players)
        players.prepareGame(deck)
        OutputView.printPlayersHandsView(players)
        race(players)
        OutputView.printPlayersResultView(players)
    }

    private fun race(players: Players) {
        players.players.forEach {
            while (!it.canPlayable()) {
                val isHit = InputView.getYorN(it.name)
                if (isHit) {
                    it.hit(deck)
                    OutputView.printPlayerHandsView(it)
                } else {
                    break
                }
            }
        }
    }
}

fun main() {
    val game = BlackJackGame(Deck.makeDeck())
    game.start()
}
