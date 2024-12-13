package blackjack.domain

class Players private constructor(
    val members: List<Player>,
) {
    val playerIterator: Iterator<Player> = members.iterator()

    fun drawDefaultCards(
        deck: Deck,
        cardCount: Int,
    ) {
        members.forEach { player -> repeat(cardCount) { player.addCard(deck.draw()) } }
    }

    fun getNextPlayer(): Player {
        return playerIterator.next()
    }

    fun hasBlackJackPlayer(): Boolean {
        return members.any { it.status == PlayerStatus.BLACKJACK }
    }

    fun hasHitPlayer(): Boolean {
        return members.any { it.status == PlayerStatus.HIT }
    }

    companion object {
        fun from(names: List<String>): Players {
            return Players(names.map { Player(it) })
        }
    }
}
