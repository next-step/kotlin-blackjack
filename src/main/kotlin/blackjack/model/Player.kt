package blackjack.model

import blackjack.service.FinalScoreCalculator

class Player(val name: String) {
    var cards = mutableListOf<Card>()

    fun addCard(card: Card) {
        check(getScore() < 21)
        cards.add(card)
    }

    fun addCards(cards: List<Card>) {
        check(getScore() < 21)
        cards.forEach(::addCard)
    }

    fun getScore(): Int {
        return cards.sumOf { it.getScore() }
    }

    fun getFinalScore(): Int {
        return FinalScoreCalculator.calculate(cards)
    }
}
