package blackjack.domain

class BlackJackGame(private val deck: Deck) {
    fun start(players: Players): Players {
        return players.addCards { deck.drawCards(START_CARD_COUNT) }
    }

    fun play(
        players: Players,
        isHit: (Player) -> Boolean,
        afterDrawCard: (Player) -> Unit
    ): Players {
        val result = players.map { play(it, isHit, afterDrawCard) }
        return Players(result)
    }

    private fun play(
        player: Player,
        isHit: (Player) -> Boolean,
        afterDrawCard: (Player) -> Unit
    ): Player {
        var currentPlayer = player
        while (currentPlayer.canHit() && isHit(currentPlayer)) {
            val card = deck.drawCard()
            currentPlayer = currentPlayer.addCard(card)
            afterDrawCard(currentPlayer)
        }
        return currentPlayer
    }

    companion object {
        private const val START_CARD_COUNT = 2

        fun create() = BlackJackGame(Deck.create())
    }
}
