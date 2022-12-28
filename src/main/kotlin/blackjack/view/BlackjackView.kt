package blackjack.view

import blackjack.domain.Blackjack
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

interface BlackjackView {
    fun printlnInitialPlayersCards(dealer: Dealer, players: Players)

    fun printlnBlackjack(blackjack: Blackjack)

    fun printlnBlackjackResult(blackjack: Blackjack)

    fun printlnPlayer(player: Player)

    fun printlnDealer(dealer: Dealer)

    fun printlnBlackjackFinalResult(blackjack: Blackjack)

    fun printlnDealerAddedCards(dealer: Dealer)
}
