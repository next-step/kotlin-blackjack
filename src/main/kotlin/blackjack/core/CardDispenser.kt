package blackjack.core

class CardDispenser {
    private val cards: Iterator<Card> =
        Denomination.entries
            .flatMap { denomination ->
                Suit.entries.map { suit ->
                    Card(denomination, suit)
                }
            }
            .toList()
            .shuffled()
            .iterator()

    fun deal(
        players: Players,
        cardCount: Int = DEFAULT_CARD_NUM,
    ): Boolean {
        players.forEach {
            if (!deal(it, cardCount)) {
                return false
            }
        }

        return true
    }

    fun deal(
        player: Player,
        cardCount: Int,
    ): Boolean {
        return (0..< cardCount).all { deal(player) }
    }

    fun deal(player: Player): Boolean {
        if (cards.hasNext().not()) {
            return false
        }

        player.draw(cards.next())
        return true
    }

    fun checkRemainCard(): Boolean {
        return cards.hasNext()
    }

    companion object {
        private const val DEFAULT_CARD_NUM = 2
    }
}
