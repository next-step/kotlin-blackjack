package blackjack.view.result

import blackjack.domain.Players

class ConsoleResultView : ResultView {
    override fun showCardsDelivered(players: Players) {
        println(getPlayerNames(players) + "에게 2장의 카드를 나누었습니다.")
    }

    private fun getPlayerNames(players: Players): String {
        return players.players.joinToString { it.name }
    }
}
