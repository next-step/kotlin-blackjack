package blackjack.domain

@JvmInline
value class PlayerCard(val cards: MutableList<Card> = mutableListOf()) {

    fun init(trumpCard: TrumpCard) {
        cards.addAll(mutableListOf(trumpCard.draw(), trumpCard.draw()))
    }

    fun add(card: Card) {
        cards.add(card)
    }
}
