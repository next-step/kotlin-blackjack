package blackjack.domain.game

import blackjack.domain.deck.Deck
import blackjack.domain.player.Players
import blackjack.view.BlackJackView
import blackjack.view.InputView
import blackjack.view.PlayerView

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
                it.hitOrStand(yOrN, deck)
                it.printCards()
            }
        }
    }
}

fun main() {
    val game = BlackJackGame(Deck.makeDeck())
    game.start()
}
