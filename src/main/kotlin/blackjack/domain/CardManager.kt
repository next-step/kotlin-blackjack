package blackjack.domain

import java.util.Stack

class CardManager(
    cardGenerator: CardGenerator
) {
    private val cards = Stack<Card>()

    init {
        val generated = cardGenerator.generate().toMutableList()
        require(generated.isNotEmpty()) { "카드가 없습니다." }
        cards.addAll(generated)
    }

    fun getCard(): Card {
        return cards.pop()
    }
}
