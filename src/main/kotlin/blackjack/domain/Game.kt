package blackjack.domain

class Game(
    private val dealer: Dealer,
    private val players: Players,
) {
    init {
        dealer.deal(players)
    }

    fun processPlayerTurn(
        player: Player,
        hitCommand: HitCommand,
    ) = when (hitCommand) {
        HitCommand.HIT -> dealer.hit(player)
        HitCommand.STAY -> player.stay()
    }

    fun isPlayerDone(player: Player): Boolean {
        // todo: 사용자의 턴을 종료할지 진행할지에 대한 구현
        return false
    }

    fun playersStatus(): Players = players
}
