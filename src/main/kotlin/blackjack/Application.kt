package blackjack

import blackjack.domain.Cards
import blackjack.domain.Player
import blackjack.view.InputView

fun main() {
    val names = InputView.getPlayersName().split(",")
    val cards = Cards()

    var players = mutableListOf<Player>()
    for (name in names) {
        players.add(Player(name))
    }

    players.forEach {
        it.addCard(cards.takeCard())
        it.addCard(cards.takeCard())
        println(it.getCards())
    }

    // 플레이어가 카드를 그만 받는다고 하거나 블랙잭일때 까지
}
