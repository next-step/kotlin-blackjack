package blackjack

class Participants(
    val dealer: Dealer,
    val players: Players,
) {
    fun members(): List<Participant> {
        return listOf(dealer, *players.toTypedArray())
    }

    companion object {
        private const val INITIAL_DRAW_COUNT = 2

        fun init(playerNames: List<String>, deck: Deck): Participants = Participants(
            Dealer(deck.draw(INITIAL_DRAW_COUNT)),
            Players(playerNames.map { Player(it, deck.draw(INITIAL_DRAW_COUNT)) }),
        )
    }
}
