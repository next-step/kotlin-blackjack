package blackjack.core

class CardDispenser {
    private val cards: Iterator<Card> =
        Denomination.entries.flatMap {
                denomination ->
            Suit.entries.map { suit -> Card(denomination, suit) }
        }.toList().shuffled().iterator()

    fun deal(
        players: Players,
        cardCount: Int,
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
        repeat(cardCount) {
            if (!deal(player)) {
                return false
            }
        }

        return true
    }

    fun deal(player: Player): Boolean {
        if (!cards.hasNext()) {
            return false
        }

        player.draw(cards.next())
        return true
    }

    fun checkRemainCard(): Boolean {
        return cards.hasNext()
    }
}
