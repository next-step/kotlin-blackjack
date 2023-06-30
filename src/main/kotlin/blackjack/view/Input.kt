package blackjack.view

import blackjack.domain.Answer
import blackjack.domain.Player

interface Input {

    fun inputPlayers(): List<String>

    fun inputHitOrStay(player: Player): Answer
}
