package blackjack

class Players(
    private val players: List<CardPlayer>,
    val dealer: CardPlayer.Dealer = CardPlayer.Dealer()
) : List<CardPlayer> by players {

    constructor(vararg players: CardPlayer) : this(players.toList())

    fun all(action: (CardPlayer) -> Unit) = forEach(action)

    fun allPlayers(): Players = Players(players + dealer)

    fun gameResult(): List<PlayerResult> {
        return players vs dealer
    }

    private infix fun List<CardPlayer>.vs(dealer: CardPlayer.Dealer): List<PlayerResult> {
        if (dealer.score() > 21) {
            return map { PlayerResult(it, wins = 1) } + PlayerResult(dealer, losses = 1)
        }

        return map { it to (it vs dealer) }
            .map { (player, playResult) ->
                PlayerResultBuilder().apply {
                    update(playResult)
                }.build(player)
            } + (dealer vs this)
    }

    private infix fun CardPlayer.Dealer.vs(players: List<CardPlayer>): PlayerResult {
        val thisPlayer = this
        return PlayerResultBuilder().apply {
            for (player in players) {
                update(thisPlayer vs player)
            }
        }.build(this)
    }
}
