package blackjack.view

import blackjack.domain.Player

interface Output {

    fun printFirstDeal(users: List<String>)

    fun printPlayerCards(player: Player)

    fun printResult(player: Player)
}
