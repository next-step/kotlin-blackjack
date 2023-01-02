package blackjack.view.input

import blackjack.domain.Blackjack
import blackjack.domain.player.Player

interface BlackjackInputReader {
    fun printlnPlayerNamesInputPrompt()

    fun printlnPlayerHitYnInputPrompt(player: Player)

    fun printlnPlayerBettingAmountInputPrompt(playerName: String)

    fun readPlayerNames(): List<String>

    fun readPlayersHitOrStay(blackjack: Blackjack, playerActionWhenHitYn: (Player, Boolean) -> Unit)

    fun readPlayerHitYn(): String

    fun readPlayerBettingAmounts(playerNames: List<String>): List<Double>
}
