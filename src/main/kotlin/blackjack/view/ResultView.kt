package blackjack.view

import blackjack.domain.player.UserRole

class ResultView(private val players: List<UserRole>) {

    fun result() {
        players.forEach {
            println("%s카드: %s - 결과: %s".format(it.userSetting.name, it.cards.map { c -> c.toString() }, it.getScore()))
        }

        println("최종 결과")
        players.forEach {
            println("%s - %s".format(it.userSetting.name, it.gameStatus.judgements.toString()))
        }
    }
}
