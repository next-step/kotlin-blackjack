package blackjack.domain

class Game(names: Names, private var cards: GameCards = GameCards()) {
    val players: Players = Players(generatePlayer(names))

    val state: GameStates
        get() {
            if (players.allEndGame) {
                return GameStates.END
            }
            return GameStates.PLAYING
        }

    fun draw(target: Player) {
        val card = cards.nextCard()
        target.draw(card)

        cards.removeFront()
    }

    private fun generatePlayer(names: Names) = names.map {
        Player(it, (START_INDEX..BLACK_JACK_CARD_COUNT).map { cards.poll() }.toSet())
    }.toSet()

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val BLACK_JACK_CARD_COUNT = 2
        const val START_INDEX = 1
    }
}
