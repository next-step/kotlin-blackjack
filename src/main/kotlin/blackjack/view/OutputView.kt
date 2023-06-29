package blackjack.view

import blackjack.Player

interface OutputView {
    fun printInitialCardCasting(players: List<Player>, castedCard: Int)

    fun printPlayerCards(playexr: Player)

    fun printBlackJackResult(player: Player, result: Int)
}
