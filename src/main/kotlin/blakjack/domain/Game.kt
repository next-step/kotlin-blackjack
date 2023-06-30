package blakjack.domain

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
}
