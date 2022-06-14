package blackjack.dto

import blackjack.Player

class ResultDto(private val players: List<Player>) {

    fun result() {
        players.forEach {
            println("%s카드: %s - 결과: %s".format(it.name, it.cards.map { c -> c.show() }, it.getScore()))
        }
    }
}
