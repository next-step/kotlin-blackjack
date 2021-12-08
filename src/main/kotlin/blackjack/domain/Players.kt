package blackjack.domain

class Players private constructor(players: List<Player>) {

    private val _players: MutableList<Player> = players.toMutableList()
    val players: List<Player>
        get() = _players
            .map {
                it.copy()
            }

    val dealer: Dealer?
        get() = _players
            .filterIsInstance(Dealer::class.java)
            .firstOrNull()

    fun isContainDealer(): Boolean {
        return dealer != null
    }

    fun sortedDealerFirst(): List<Player> = players
        .sortedWith(
            compareBy {
                when (it) {
                    is Dealer -> -1
                    else -> 0
                }
            }
        ).map { it.copy() }

    fun filteredExceptedDealer(): List<Player> {
        return players.filter {
            it !is Dealer
        }.map {
            it.copy()
        }
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
            return Players(list)
        }
    }
}
