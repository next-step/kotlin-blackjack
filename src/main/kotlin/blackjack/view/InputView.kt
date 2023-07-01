package blackjack.view

import blackjack.CardDrawCommand
import blackjack.Player

interface InputView {
    fun fetchPlayerNames(): List<String>
    fun fetchCardDrawCommand(player: Player): CardDrawCommand
}
