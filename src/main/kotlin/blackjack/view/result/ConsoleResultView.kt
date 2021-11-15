package blackjack.view.result

import blackjack.domain.Dealer.Companion.BASIC_CARD_NUMBER
import blackjack.domain.Player
import blackjack.domain.Players

class ConsoleResultView : ResultView {
    override fun showDeliveredBasicCards(players: Players) {
        println("%s에게 ${BASIC_CARD_NUMBER}장의 카드를 나누었습니다.".format(players.getNames()))
        players.items.forEach {
            showPlayerCards(it)
            println()
        }
        println()
    }

    override fun showPlayerCards(player: Player, shouldPrintNewLineCharacter: Boolean) {
        print("%s카드: %s".format(player.name, player.cardsHandler.getCardsString()))

        if (shouldPrintNewLineCharacter) {
            println()
        }
    }

    override fun showPlayerResults(players: Players) {
        players.items.forEach {
            showPlayerCards(it)
            showPlayerResult(it)
        }
    }

    private fun showPlayerResult(player: Player) {
        println(" - 결과 ${player.cardsHandler.getCardsResultPoint()}")
    }
}
