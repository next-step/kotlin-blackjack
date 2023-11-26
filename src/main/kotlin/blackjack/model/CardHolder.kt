package blackjack.model

sealed class CardHolder(val id: Int, val cardHand: CardHand, val name: String, val role: Role) {
    class GameDealer(id: Int, cardHand: CardHand) : CardHolder(id, cardHand, "딜러", Role.DEALER) {
        val isDealerShouldMoreCard: Boolean
            get() = cardHand.totalScore <= DEALER_MUST_MORE_SCORE

        companion object {
            const val DEALER_MUST_MORE_SCORE = 16
        }
    }

    class Player(id: Int, cardHand: CardHand, name: String) : CardHolder(id, cardHand, name, Role.PLAYER) {
        fun moreCardOrNot(askToPlayer: (name: String) -> PlayAnswer) = when (askToPlayer(name)) {
            PlayAnswer.Y -> true
            PlayAnswer.N -> false
        }
    }
}
