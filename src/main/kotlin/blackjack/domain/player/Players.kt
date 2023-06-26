package blackjack.domain.player

class Players(val players: List<AbstractPlayer>) {
    constructor(dealer: Dealer, players: GamePlayers) : this(listOf(listOf(dealer), players.players).flatten())

    fun getDealer(): Dealer = players.filterIsInstance<Dealer>().firstOrNull()
        ?: throw IllegalArgumentException("딜러는 필수 입니다.")

    fun getGamePlayers(): GamePlayers = GamePlayers(players.filterIsInstance<GamePlayer>())
}
