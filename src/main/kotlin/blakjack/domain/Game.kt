package blakjack.domain

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
        player.hit(dealer.drawOneCard())
    }

    fun hitOrStandDealer() {
        if (dealer.isScoreToStand) {
            dealer.stand()
            return
        }

        dealer.hit(dealer.drawOneCard())
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
