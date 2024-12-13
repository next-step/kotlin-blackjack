package blackjack

import blackjack.card.Deck
import blackjack.participant.Players

data class BlackJackGame(
    val gamePlayers: Players,
    val deck: Deck,
) {
    fun start(): BlackJackGame {
        gamePlayers.player.forEach { player -> player.take(listOf(deck.pick(), deck.pick())) }
        return BlackJackGame(gamePlayers, deck)
    }
}
