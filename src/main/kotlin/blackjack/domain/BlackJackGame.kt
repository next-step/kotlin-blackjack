package blackjack.domain

class BlackJackGame(
    private val deck: DrawStrategy
) {
    fun deal(players: List<Player>) {
        players.forEach { it.deal(deck) }
    }

    fun askHit(player: Player, agreed: Boolean) {
        if (agreed) player.hit(deck)
        else player.stand()
    }
}
