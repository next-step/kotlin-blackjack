package blakjack.domain

import blakjack.domain.Participant.ParticipantAction

class Game(
    val dealer: Dealer = Dealer(),
    val players: List<Player>
) {
    fun initialDraw() {
        players.forEach { player -> player.add(dealer.drawTwoCards()) }
        dealer.add(dealer.drawTwoCards())
    }

    fun hit(player: Player) {
        player.add(dealer.drawOneCard())
    }

    fun hitOrStandDealer(): ParticipantAction {
        if (dealer.isOver17) {
            return ParticipantAction.STAND
        }

        dealer.add(dealer.drawOneCard())
        return ParticipantAction.HIT
    }

    fun result() {
        if (dealer.isBust()) {
            resultIfDealerIsBust()
            return
        }

        resultIfDealerIsNotBust()
    }

    private fun resultIfDealerIsBust() {
        players.forEach { player ->
            if (player.isBust()) {
                dealer.win(player)
            } else {
                player.win(dealer)
            }
        }
    }

    private fun resultIfDealerIsNotBust() {
        players.forEach { player ->
            if (player.isBust() || !player.isWin(dealer)) {
                dealer.win(player)
            } else {
                player.win(dealer)
            }
        }
    }
}
