package blackjack.step2.domain

class PlayerCards private constructor(
    private val playerCards: List<PlayerCard>,
) {
    val cards: List<PlayerCard>
        get() = playerCards.toList()

    fun add(playerCard: PlayerCard): PlayerCards {
        return PlayerCards(this.playerCards + playerCard)
    }

    companion object {
        fun of(playerCards: List<PlayerCard>): PlayerCards {
            return PlayerCards(playerCards.toMutableList())
        }
    }
}
