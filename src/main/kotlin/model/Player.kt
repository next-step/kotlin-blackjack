package model

import blackjack.BlackJackGame
import game.BlackJackWinner
import view.InputView
import view.ResultView

class Player(name: PlayerName) : AbstractPlayer(name, PlayerType.PLAYER) {
    constructor(name: String) : this(PlayerName(name))

    override fun receiveCard(blackJackGame: BlackJackGame) {
        while (isReceiveCard(InputView.receiveCard(this))) {
            receive(blackJackGame.dealer.draw())
            ResultView.printCardCardReceive(this)
        }
    }

    override fun firstCardList(): List<Card> {
        return cards
    }

    override fun compareResult(player: AbstractPlayer): BlackJackWinner {
        player as DealerPlayer
        if (player.isOver()) {
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
