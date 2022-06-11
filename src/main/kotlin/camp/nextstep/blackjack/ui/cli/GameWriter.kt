package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.BlackJackGame

object GameWriter {

    fun write(game: BlackJackGame) {
        val players = game.participants.joinToString(",") { it.name }
        println("${players}에게 각각 ${BlackJackGame.INIT_CARD_NUMBER}장의 나누었습니다.")
    }
}
