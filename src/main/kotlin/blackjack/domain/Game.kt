package blackjack.domain

class Game(names: Names, private var cards: GameCards = GameCards()) {
    val players: Players = Players(generatePlayer(names))

    val state: GameStates
        get() {
            if (isEndState) {
                return GameStates.END
            }
            return GameStates.PLAYING
        }

    private val isEndState
        get() = players.countOfPlayingState == NO_PLAYING_COUNT

    fun draw(target: Player) {
        target.throwExceptionIfIsNotPlayingState()

        val card = cards.poll()
        target.draw(card)
    }

    private fun generatePlayer(names: Names) = names.map {
        Player(it, (START_INDEX..BLACK_JACK_CARD_COUNT).map { cards.poll() }.toSet())
    }.toSet()

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val BLACK_JACK_CARD_COUNT = 2
        const val START_INDEX = 1
        private const val NO_PLAYING_COUNT = 0
    }
}
