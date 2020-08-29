package model

class DealerPlayer(name: PlayerName = PlayerName("딜러")) : AbstractPlayer(name, PlayerType.DEALER) {

    fun isAbleReceiveCard(): Boolean {
        return this.score() <= DEALER_RECEIVE_CARD_SCORE
    }

    companion object {
        const val DEALER_RECEIVE_CARD_SCORE = 16
    }

    override fun compareResult(player: AbstractPlayer): BlackJackWinner {
        if (this.isOver()) {
            return BlackJackWinner.LOSE
        } else if (player.isOver()) {
            return BlackJackWinner.WIN
        } else if (this.score() == WINNING_POINT) {
            return BlackJackWinner.WIN
        } else if (this.score() > player.score()) {
            return BlackJackWinner.WIN
        } else if (this.score() == player.score()) {
            return BlackJackWinner.DRAW
        } else {
            return BlackJackWinner.LOSE
        }
    }
}
