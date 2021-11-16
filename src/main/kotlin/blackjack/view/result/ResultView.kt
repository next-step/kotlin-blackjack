package blackjack.view.result

import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.ResultCalculator

interface ResultView {
    fun showDeliveredBasicCards(players: Players)
    fun showPlayerCards(player: Player, shouldPrintNewLineCharacter: Boolean = false)
    fun showPlayerResults(resultCalculator: ResultCalculator, players: Players)
}
