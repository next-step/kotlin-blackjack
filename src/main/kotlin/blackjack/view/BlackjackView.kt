package blackjack.view

import blackjack.domain.Blackjack
import blackjack.domain.player.Player

interface BlackjackView {
    fun printlnInitialPlayersCards(blackjack: Blackjack)

    fun printlnBlackjack(blackjack: Blackjack)

    fun printlnBlackjackResult(blackjack: Blackjack)

    fun printlnPlayer(player: Player)
}
