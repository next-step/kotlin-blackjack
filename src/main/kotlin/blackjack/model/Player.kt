package blackjack.model

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
        return cards.sumOf { it.denomination.value }
    }

    fun getFinalScore(): Int {
        TODO("결과점수 계산 알고리즘 구현")
    }
}
