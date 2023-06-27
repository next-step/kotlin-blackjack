package blackjack.domain

class Participants(
    val dealer: Dealer,
    val players: Players,
) {
    fun members(): List<Participant> {
        return listOf(dealer, *players.toTypedArray())
    }

    companion object {
        private const val INITIAL_DRAW_COUNT = 2

        fun init(playerInfos: List<PlayerInfo>, deck: Deck): Participants = Participants(
            Dealer(deck.draw(INITIAL_DRAW_COUNT)),
            Players(
                playerInfos.map {
                    Player(
                        name = it.name,
                        betAmount = it.betAmount,
                        cards = deck.draw(INITIAL_DRAW_COUNT),
                        state = Alive,
                    )
                }
            ),
        )
    }
}
