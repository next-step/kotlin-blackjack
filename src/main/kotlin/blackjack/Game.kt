package blackjack

class Game(names: Names, private var cards: GameCards = GameCards()) {
    val players: Players = Players(
        names.map {
            Player(it, cards.poll(), cards.poll())
        }.toSet()
    )

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

    fun isEndState() = players.countOfPlayingState == NO_PLAYING_COUNT

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val BLACK_JACK_CARD_COUNT = 2
        private const val NO_PLAYING_COUNT = 0
    }
}
