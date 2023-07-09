package blackjack.domain

class BlackJackGame(
    val players: Players,
    val deck: Deck
) {

    fun start() {
        players.forEach { player ->
            player.hit(deck.pop())
            player.hit(deck.pop())
        }
    }

    fun hit(player: Player) {
        player.hit(deck.pop())
    }

    fun canReceivePlayers(): Players {
        return players.canReceivePlayers()
    }
}
