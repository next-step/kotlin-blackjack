package model

import blackjack.BlackJackGame
import view.ResultView

class DealerPlayer(name: PlayerName = PlayerName("딜러")) : AbstractPlayer(name, PlayerType.DEALER) {

    private fun isAbleReceiveCard(): Boolean {
        return this.score() <= DEALER_RECEIVE_CARD_SCORE
    }

    override fun receiveCard(blackJackGame: BlackJackGame) {
        if (!isAbleReceiveCard()) {
            return
        }
        ResultView.printReceiveCardDealer()
        receive(blackJackGame.dealer.draw())
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

    companion object {
        const val DEALER_RECEIVE_CARD_SCORE = 17
    }
}
