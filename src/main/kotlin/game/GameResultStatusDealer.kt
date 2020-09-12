package game

import model.AbstractPlayer

class GameResultStatusDealer(player: AbstractPlayer, list: List<AbstractPlayer>) : GameResultStatus(player) {
    init {
        list.forEach {
            when (player.compareResult(it)) {
                BlackJackWinner.WIN -> win++
                BlackJackWinner.DRAW -> draw++
                else -> {
                    lose++
                }
            }
        }
    }
}
