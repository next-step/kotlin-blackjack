package game

import model.AbstractPlayer
import model.DealerPlayer

class GameResultStatusPlayer(player: AbstractPlayer, dealer: DealerPlayer) : GameResultStatus(player) {
    init {
        when (player.compareResult(dealer)) {
            BlackJackWinner.WIN -> win++
            BlackJackWinner.DRAW -> draw++
            else -> {
                lose++
            }
        }
    }
}
