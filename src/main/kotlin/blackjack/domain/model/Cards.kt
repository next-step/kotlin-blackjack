package blackjack.domain.model

class Cards private constructor(cards: Set<Card>) {

    private var _values: Set<Card> = cards
    val values: Set<Card>
        get() = _values

    fun peek(): Card {
        val card: Card = _values.lastOrNull() ?: throw IllegalArgumentException("카드 리스트에 카드가 비어 있어서 카드를 뽑을 수 없습니다.")
        _values = values - card
        return card
    }

    fun isContainAce(): Boolean = values.any { card -> card.sign == Sign.ACE }
    fun score(): BlackjackScore = BlackjackScore.create(this)
    operator fun plus(card: Card) {
        _values = values + card
    }

    operator fun minus(card: Card) {
        _values = values - card
    }

    companion object {
        fun from(cards: Set<Card>) = Cards(cards)
        fun empty() = Cards(emptySet())
    }
}
