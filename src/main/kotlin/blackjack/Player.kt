package blackjack

class Player(name: String) : User(name)

class Players(private val _players: List<Player>) {
    val players: List<Player>
        get() = _players.toList()

    fun hit(cardExtractor: CardExtractor) {
        repeat(HIT_COUNT) {
            players.forEach { it.addCard(cardExtractor.getCard()) }
        }
    }

    companion object {
        private const val HIT_COUNT = 2
    }
}
