package blackjack.dto

import blackjack.UserRole

class ResultDto(private val players: List<UserRole>) {

    fun result() {
        players.forEach {
            println("%s카드: %s - 결과: %s".format(it.name, it.cards.map { c -> c.toString() }, it.getScore()))
        }

        println("최종 결과")
        players.forEach {
            println("%s - %s".format(it.name, it.judgements.toString()))
        }
    }
}
