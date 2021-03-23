package blackjack.domain

import java.util.Stack

object CardDeck {

    private val numbers = Number.values().toList()
    private val patterns = Pattern.values().toList()
    private val combine = numbers.associateWith { patterns }
    private val cards: Stack<Card> = init()

    private fun init(): Stack<Card> {
        val tempStack = Stack<Card>()
        for (pattern in patterns) {
            tempStack.addAll(numbers.map { number -> Card(number to pattern) })
        }
        tempStack.shuffle()
        return tempStack
    }

    fun drawCard(): Card {
        if (cards.isNullOrEmpty()) throw IllegalStateException("더 이상 카드가 없습니다.")
        return cards.pop()
    }
}
