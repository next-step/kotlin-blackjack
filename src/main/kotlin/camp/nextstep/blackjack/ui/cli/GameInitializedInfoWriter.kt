package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.BlackJackGame
import camp.nextstep.blackjack.player.Gambler

object GameInitializedInfoWriter {

    fun write(participants: List<Gambler>) {
        val gamblers = participants.joinToString(",") { it.name }
        println("${gamblers}에게 각각 ${BlackJackGame.INIT_CARD_NUMBER}장의 나누었습니다.")
    }
}
