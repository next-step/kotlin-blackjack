package blackjack.domain

class BlackJackGame(
    val players: Players,
    val deck: Deck
) {

    fun start() {
        players.forEach { player ->
            player.hit(deck.draw())
            player.hit(deck.draw())
        }
    }

    fun hit(player: Player) {
        player.hit(deck.draw())
    }

    fun canReceivePlayers(): Players {
        return players.canReceivePlayers()
    }
}
