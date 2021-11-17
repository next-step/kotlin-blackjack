package blackjack.view.input

import blackjack.domain.Player
import blackjack.domain.Players

interface InputView {
    fun getPlayers(): Players
    fun askToReceiveAdditionalCardOrNot(player: Player): Boolean
}
