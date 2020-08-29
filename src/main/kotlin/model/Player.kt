package model

class Player(name: PlayerName): AbstractPlayer(name, PlayerType.PLAYER) {
    constructor(name: String): this(PlayerName(name))

    override fun compareResult(player: AbstractPlayer): BlackJackWinner {
        if(this.score() == WINNING_POINT && this.score() == player.score()) {
            return BlackJackWinner.DRAW
        } else if(this.score() == WINNING_POINT && this.score() != player.score()) {
                return BlackJackWinner.WIN
        } else if(player is DealerPlayer && player.isOver()) {
            return BlackJackWinner.WIN
        } else if(!this.isOver() && this.score() > player.score()) {
            return BlackJackWinner.WIN
        } else if(!this.isOver() && this.score() == player.score()) {
            return BlackJackWinner.DRAW
        } else {
            return BlackJackWinner.LOSE
        }
    }
}
