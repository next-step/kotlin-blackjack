package blackjack.domain

@JvmInline
value class PlayerCard(val cards: MutableList<Card> = mutableListOf()) {

    fun add(card: Card) {
        cards.add(card)
    }

    companion object {
        fun init(trumpCard: TrumpCard): PlayerCard {
            return PlayerCard(mutableListOf(trumpCard.draw(), trumpCard.draw()))
        }
    }
}
