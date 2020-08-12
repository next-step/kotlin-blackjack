package blackjack.domain

class BlackJackGame(
    private val deck: DrawStrategy
) {
    fun dealWith(players: List<String>): List<Player> {
        return players.map { Player(it) }
            .map { it.deal(deck) }
    }

    fun askHit(player: Player, agreed: Boolean): Player {
        return if (agreed) player.hit(deck)
        else player.stand()
    }
}
