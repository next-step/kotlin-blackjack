package blackjack

data class Game(private val players: Players, private var cards: GameCards = GameCards()) {
    val state: GameStates
        get() {
            if (isEndState()) {
                return GameStates.END
            }

            return GameStates.PLAYING
        }

    fun draw(target: Player) {
        target.throwExceptionIfIsNotPlayingState()

        val card = cards.poll()
        target.draw(card)
    }
    private fun isEndState() = players.countOfPlayingState == NO_PLAYING_COUNT

    companion object {
        const val BLACK_JACK_SCORE = 21
        private const val NO_PLAYING_COUNT = 0
    }
}
