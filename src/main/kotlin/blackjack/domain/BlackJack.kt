package blackjack.domain

class BlackJack(
    private val deck: Deck,
) {
    fun play(players: List<PreparedPlayer>): List<OnGoingPlayer> {
        return players.map { player ->
            val drawnCards = Cards(
                listOf(
                    deck.draw(),
                    deck.draw(),
                )
            )

            OnGoingPlayer(player.name, drawnCards)
        }
    }
}
