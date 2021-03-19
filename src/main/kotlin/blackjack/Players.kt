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
        if (dealer.score() > 21) {
            return map { PlayerResult(it, wins = 1) } + PlayerResult(dealer, losses = 1)
        }

        return map {
            result {
                update(it vs dealer)
            }.build(it)
        } + (dealer vs this)
    }

    private infix fun CardPlayer.Dealer.vs(players: List<CardPlayer>): PlayerResult {
        val vs: (CardPlayer) -> PlayResult = { player -> this vs player }

        return result {
            for (player in players) {
                update(vs(player))
            }
        }.build(this)
    }

    interface LastTake {
        fun required(): Boolean
        fun take(card: Card)
    }
}
