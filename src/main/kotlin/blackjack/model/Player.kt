package blackjack.model

class Player(
    val name: String,
    override var cards: Cards = Cards()
) : Gambler {
    override val stopScore = PLAYER_STOP_SCORE

    companion object {
        const val PLAYER_STOP_SCORE = 20

        fun init(name: String, cardDeck: CardDeck, initCardCount: Int): Player {
            return Player(name, cardDeck.drawCards(initCardCount))
        }
    }
}
