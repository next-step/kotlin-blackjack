package blackjack.domain.game

import blackjack.domain.deck.Deck
import blackjack.domain.player.Players
import blackjack.view.BlackJackView
import blackjack.view.PlayerView

class BlackJackGame {
    private val deck = Deck.makeDeck()
    private lateinit var players: Players
    fun start() {
        BlackJackView.printPlayerInputView()
        players = Players.of(StringParser.getPlayersFromString(readln(), deck))
        BlackJackView.printPlayersInitView(players)
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
    val game = BlackJackGame()
    game.start()
}
