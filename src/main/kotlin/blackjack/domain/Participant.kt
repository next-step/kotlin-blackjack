package blackjack.domain

sealed class Participant(
    val name: PlayerName,
    protected var hands: Hands
) {
    fun cardsOfHands(): PlayingCards = hands.cards

    fun score(): Score = hands.score

    fun receive(playingCards: PlayingCards) {
        hands += playingCards
    }

    abstract fun isReceivable(): Boolean

    class Player(
        name: PlayerName,
        hands: Hands
    ) : Participant(name, hands) {
        constructor(name: String, vararg initialCards: PlayingCard) : this(
            PlayerName(name),
            Hands.from(PlayingCards.from(initialCards.toList()))
        )

        fun stay() {
            hands = hands.stay()
        }

        override fun isReceivable(): Boolean = hands.isReceivable()
    }
}
