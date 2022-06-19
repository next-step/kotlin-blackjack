package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.BlackJackGame
import camp.nextstep.blackjack.player.Player

object GameInitializedInfoWriter {

    fun write(participants: List<Player>) {
        val players = participants.joinToString(",") { it.name }
        println("${players}에게 각각 ${BlackJackGame.INIT_CARD_NUMBER}장의 나누었습니다.")
    }
}
