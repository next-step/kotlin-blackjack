package blackjack.domain

class ShuffledDeck : Deck {
    private val cards = ArrayDeque<Card>()

    init {
        cards.addAll(Card.ALL_CARDS)
        cards.shuffle()
    }

    override val size: Int
        get() = cards.size

    override fun draw(): Card = cards.removeFirstOrNull() ?: throw IllegalStateException("덱에 카드가 없습니다")
}
