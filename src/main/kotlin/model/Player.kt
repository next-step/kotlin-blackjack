package model

import blackjack.BlackJackGame
import view.InputView
import view.ResultView

class Player(name: PlayerName) : AbstractPlayer(name, PlayerType.PLAYER) {
    constructor(name: String) : this(PlayerName(name))

    override fun compareResult(player: AbstractPlayer): BlackJackWinner {
        if (this.isOver()) {
            return BlackJackWinner.LOSE
        } else if (player.isOver()) {
            return BlackJackWinner.WIN
        } else if (this.score() == WINNING_POINT && this.score() == player.score()) {
            return BlackJackWinner.DRAW
        } else if (this.score() == WINNING_POINT && this.score() != player.score()) {
            return BlackJackWinner.WIN
        } else if (player is DealerPlayer && player.isOver()) {
            return BlackJackWinner.WIN
        } else if (this.score() > player.score()) {
            return BlackJackWinner.WIN
        } else if (this.score() == player.score()) {
            return BlackJackWinner.DRAW
        } else {
            return BlackJackWinner.LOSE
        }
    }

    override fun receiveCard(blackJackGame: BlackJackGame) {
        while (isReceiveCard(InputView.receiveCard(this))) {
            receive(blackJackGame.dealer.draw())
            ResultView.printCardCardReceive(this)
        }
    }
}
