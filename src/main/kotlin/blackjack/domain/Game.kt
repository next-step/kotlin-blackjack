package blackjack.domain

class Game(
    val dealer: Dealer,
    val players: Players,
) {
    fun start() {
        dealer.deal(players)
    }

    fun processPlayerTurn(
        player: Player,
        hitCommand: HitCommand,
    ) {
        if (hitCommand == HitCommand.Y) {
            dealer.hit(player)
        }
    }

    fun isPlayerDone(player: Player): Boolean {
        // todo: 사용자의 턴을 종료할지 진행할지에 대한 구현
        return true
    }
}
