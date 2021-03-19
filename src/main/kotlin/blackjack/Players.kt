package blackjack

class Players(
    private val players: List<CardPlayer>,
    private val dealer: CardPlayer.Dealer = CardPlayer.Dealer()
) : List<CardPlayer> by players {

    constructor(vararg players: CardPlayer) : this(players.toList())
    constructor(dealer: CardPlayer.Dealer, vararg players: CardPlayer) : this(players.toList(), dealer)

    fun all(action: (CardPlayer) -> Unit) = forEach(action)

    fun allPlayers(): Players = Players(players + dealer)

    fun gameResult(): List<PlayerResult> = players vs dealer

    fun lastTake() = object : LastTake {
        override fun required(): Boolean = dealer.lastWant()

        override fun take(card: Card) = dealer.take(card)
    }

    private infix fun List<CardPlayer>.vs(dealer: CardPlayer.Dealer): List<PlayerResult> {
        return map {
            playerResult {
                it vs dealer
            }.build(it)
        }.let {
            it + playerResult {
                it apply this::inversely
            }.build(dealer)
        }
    }

    interface LastTake {
        fun required(): Boolean
        fun take(card: Card)
    }
}
