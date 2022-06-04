package blackjack.ui

import blackjack.domain.Player

object BlackjackInputView {

    fun queryAboutPlayer(): String {
        println("enter the players name(delimiter [,] )")
        return readln()
    }

    fun queryTakeCard(player: Player): String {
        println("Does ${player.name} want to take the card?[y/n]")
        return readln().take(1).lowercase()
    }
}
