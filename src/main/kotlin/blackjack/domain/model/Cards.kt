package blackjack.domain.model

class Cards private constructor(cards: Set<Card>) {

    private var _cards: Set<Card> = cards
    val cards: Set<Card>
        get() = _cards

    fun peek(): Card {
        val card: Card = _cards.lastOrNull() ?: throw IllegalArgumentException("카드 리스트에 카드가 비어 있어서 카드를 뽑을 수 없습니다.")
        _cards = cards - card
        return card
    }

    fun sum(target: Int): Score {
        val (aces, normals) = cards.partition { card -> card.sign == Sign.ACE }
        val normalSum = normals.sumOf { card -> card.sign.number }
        val totalSum = aces.foldIndexed(normalSum) { index, acc, card ->
            if (acc + (card.sign.secretNumber) + aces.lastIndex - index <= target) {
                acc + card.sign.secretNumber
            } else {
                acc + card.sign.number
            }
        }
        return Score.from(totalSum)
    }

    operator fun plus(card: Card) {
        _cards = cards + card
    }

    operator fun minus(card: Card) {
        _cards = cards - card
    }

    companion object {
        fun from(cards: Set<Card>) = Cards(cards)
        fun empty() = Cards(emptySet())
    }
}
