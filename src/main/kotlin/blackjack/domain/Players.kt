package blackjack.domain

class Players(
    val values: List<Player>
) {
    val size
        get() = values.size

    fun getPlayerNamesAndCards(): Map<String, List<Card>> {
        return values.associate { it.name to it.cards.value }
    }

    fun receiveCards(cards: List<Card>) {
        values.forEachIndexed { index, player ->
            player.getCard(cards[index])
        }
    }

    fun withHit(): List<Player> {
        return values.filter { it.decision == PlayerDecision.HIT }
    }
}
