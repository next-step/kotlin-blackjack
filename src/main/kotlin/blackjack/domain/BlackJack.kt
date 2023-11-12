package blackjack.domain

class BlackJack(
    private val deck: Deck,
) {
    fun play(players: List<PreparedPlayer>): List<Player> {
        return players.map { player ->
            val drawnCards = Cards(
                listOf(
                    deck.draw(),
                    deck.draw(),
                )
            )

            OnGoingPlayer.of(player.name, drawnCards)
        }
    }

    fun hit(player: OnGoingPlayer): Player {
        val drawnCard = deck.draw()

        return OnGoingPlayer.of(player.name, player.cards + drawnCard)
    }

    fun stay(player: Player): FinishedPlayer {
        return FinishedPlayer(player)
    }

    companion object {
        const val BlackJackedNumber = 21
    }
}
