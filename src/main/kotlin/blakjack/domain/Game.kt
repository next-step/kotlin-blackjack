package blakjack.domain

import blakjack.domain.Participant.ParticipantAction

class Game(
    val dealer: Dealer = Dealer(),
    val players: List<Player>
) {
    init {
        require(players.isNotEmpty()) { "플레이어는 최소 한 명 이상이어야 합니다." }
    }

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
        players.forEach(::resultIfDealerIsBust)
    }

    private fun resultIfDealerIsBust(player: Player) {
        if (player.isBust()) {
            dealer.win(player)
            return
        }

        player.win(dealer)
    }

    private fun resultIfDealerIsNotBust() {
        players.forEach(::resultIfDealerIsNotBust)
    }

    private fun resultIfDealerIsNotBust(player: Player) {
        if (player.isBust() || !player.isWin(dealer)) {
            dealer.win(player)
            return
        }

        player.win(dealer)
    }
}
