package blackjack

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Players

data class BlackJackGame(
    val dealer: Dealer,
    val gamePlayers: Players,
    val deck: Deck,
) {
    fun start(): BlackJackGame {
        dealer.take(listOf(deck.pick(), deck.pick()))
        gamePlayers.player.forEach { player -> player.take(listOf(deck.pick(), deck.pick())) }
        return BlackJackGame(dealer, gamePlayers, deck)
    }
}
