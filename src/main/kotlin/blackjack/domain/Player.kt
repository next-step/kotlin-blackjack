package blackjack.domain

class Player : Participant {

    constructor(name: String, cards: Set<Card>) : super(name, PlayerCards(cards))
    constructor(name: String, vararg cards: Card) : this(name, PlayerCards(cards.toSet()))

    override fun findStateByScore(score: Int): States {
        return when {
            score < Game.BLACK_JACK_SCORE -> States.HIT
            score > Game.BLACK_JACK_SCORE -> States.BUST
            else -> States.STAY
        }
    }

    companion object {
        fun generatePlayers(names: Names, cards: GameCards): Participants {
            return Participants(names.map { Player(it, cards.pollCardsToFirstDraw()) }.toSet())
        }
    }
}
