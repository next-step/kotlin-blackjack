package blackjack.domain

@JvmInline
value class Players private constructor(private val _players: MutableList<Player> = mutableListOf()) {

    val players: List<Player>
        get() = _players.sortedWith(
            compareBy {
                when (it) {
                    is Dealer -> -1
                    else -> 0
                }
            }
        ).map { it.copy() }

    val playersExceptedDealer: List<Player>
        get() = _players.filter { it !is Dealer }.map { it.copy() }

    val dealer: Dealer?
        get() = _players.filterIsInstance(Dealer::class.java).firstOrNull()

    fun isContainDealer(): Boolean {
        return dealer != null
    }

    fun eachAcceptCards(cardDeck: CardDeck) {
        _players.forEach {
            it.hit(cardDeck.next())
        }
    }

    operator fun plus(other: Player): Players = from(_players + other)

    companion object {

        fun getPlayerListByNames(names: List<String>): List<Player> {
            return names.map { Player.of(Name.from(it)) }
        }

        fun from(list: List<Player>): Players {
            return Players(list.toMutableList())
        }
    }
}
