package blackjack.domain

class Game(
    private val dealer: Dealer,
    private val players: Players,
) {
    fun processPlayerTurn(
        player: Player,
        hitCommand: HitCommand,
    ) {
        if (hitCommand == HitCommand.Y) {
            dealer.hit(player)
        }
    }
}
