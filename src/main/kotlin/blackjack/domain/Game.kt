package blackjack.domain

object Game {
    val players: MutableList<Player> = mutableListOf()

    fun hit(player: Player) {
        player.getCards(Deck.draw())
    }
}
