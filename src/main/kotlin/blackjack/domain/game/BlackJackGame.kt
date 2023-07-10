package blackjack.domain.game

import blackjack.domain.deck.Deck
import blackjack.domain.player.Players
import blackjack.view.BlackJackView
import blackjack.view.InputView
import blackjack.view.PlayerView

const val BLACKJACK_NUMBER = 21
const val ACE_HIDDEN_VALUE = 10

class BlackJackGame(private val deck: Deck) {
    private lateinit var players: Players
    fun start() {
        players = InputView.getPlayerInput()
        BlackJackView.printPlayersInitView(players)
        players.players.forEach {
            it.drawStartHand(deck)
        }
        BlackJackView.printPlayersCardsView(players)
        race(players)
        BlackJackView.printPlayersResultView(players)
    }

    private fun race(players: Players) {
        players.players.forEach {
            while (it.canPlayable()) {
                PlayerView.printPlayerMoreCardView(it)
                val yOrN = readln()
                if (yOrN == "y") {
                    it.hit(deck)
                }
                if (yOrN == "n") {
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
