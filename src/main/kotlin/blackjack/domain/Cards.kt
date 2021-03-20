package blackjack.domain

class Cards private constructor(
    private val _cards: MutableList<Card>
){
    val cards: List<Card>
        get() = this._cards.toList()

    fun addCard(card: Card): List<Card> {
        _cards.add(card)
        return this.cards
    }

    fun bust(): Boolean {
        return calculate() > GameConfig.BUST_CONDITION
    }

    fun calculate(): Int {
        return cards.fold(0) {
            total, next -> total + next.optimizeScore(total)
        }
    }

    companion object {
        fun from(cards: List<Card>): Cards {
            return Cards(cards.toMutableList())
        }
    }
}