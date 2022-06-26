package blackjack.dto

import blackjack.UserRole

class PlayersDto(private val players: List<UserRole>) {

    fun showPlayInit() {
        showPlayerNames()
        showInitCards()
    }

    private fun showPlayerNames() {
        val names = players.map { it.name }
            .toMutableList()
            .joinToString(",")
        println("%s에게 2장의 나누었습니다.".format(names))
    }

    private fun showInitCards() {
        players.forEach {
            println("%s카드: %s".format(it.name, it.cards.map { c -> c.toString() }))
        }
    }
}
