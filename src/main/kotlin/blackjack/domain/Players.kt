package blackjack.domain

class Players(private val players: List<Player>) : List<Player> by players {
    fun getNames(): List<String> {
        return players.map { it.name }
    }

    fun receiveCards(cards: List<Card>) {
        players.forEachIndexed { index, player ->
            player.getCard(cards[index])
        }
    }

    fun withHit(): List<Player> {
        return players.filter { it.decision == PlayerDecision.HIT }
    }
}
