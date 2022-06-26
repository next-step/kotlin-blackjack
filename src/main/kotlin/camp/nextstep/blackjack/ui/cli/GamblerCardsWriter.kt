package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.player.Gambler

object GamblerCardsWriter {

    fun write(gambler: Gambler) {
        print("${gambler.name}카드: ")
        HandWriter.write(gambler.hand)
        println()
    }
}
