package blackjack.dto

import blackjack.Player
import blackjack.UserRole

class ResultDto(private val players: List<UserRole>) {

    fun result() {
        players.forEach {
            println("%s카드: %s - 결과: %s".format(it.name, it.cards.map { c -> c.show() }, it.getScore()))
        }
    }
}
