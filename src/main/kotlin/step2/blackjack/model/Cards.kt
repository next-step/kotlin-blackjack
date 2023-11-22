package step2.blackjack.model


class Cards private constructor(cards: Set<Card>) {

    private var _cards: Set<Card> = cards
    val cards: Set<Card>
        get() = _cards

    fun peek(): Card {
        val card: Card = _cards.lastOrNull() ?: throw IllegalArgumentException("카드 리스트에 카드가 비어 있어서 카드를 뽑을 수 없습니다.")
        _cards - card
        return card
    }

    operator fun plus(card: Card) = from(_cards + card)
    operator fun minus(card: Card) = from(_cards - card)

    companion object {
        fun from(cards: Set<Card>) = Cards(cards)
        fun empty() = Cards(emptySet())
    }
}