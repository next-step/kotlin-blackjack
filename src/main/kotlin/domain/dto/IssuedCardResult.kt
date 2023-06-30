package domain.dto

import domain.player.Dealer
import domain.player.Players

class IssuedCardResult(players: Players, dealer: Dealer) {

    val dealerResult: PlayerIssuedCardsResult
    val playerResults: PlayerIssuedCardsResults

    init {
        dealerResult = PlayerIssuedCardsResult(name = dealer.name, issuedCards = dealer.cards)
        playerResults =
            PlayerIssuedCardsResults(players.map { PlayerIssuedCardsResult(name = it.name, issuedCards = it.cards) })
    }
}
