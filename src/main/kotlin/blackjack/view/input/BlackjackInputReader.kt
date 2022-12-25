package blackjack.view.input

import blackjack.domain.Blackjack
import blackjack.domain.player.Player

interface BlackjackInputReader {
    fun printlnPlayerNamesInputPrompt()

    fun printlnPlayerHitYnInputPrompt(player: Player)

    fun readPlayerNames(): List<String>

    fun readPlayersHitOrStay(blackjack: Blackjack, playerActionWhenHitYn: (Player, Boolean) -> Unit)

    fun readPlayerHitYn(): String
}
