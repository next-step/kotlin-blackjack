package blackjack.domain

import blackjack.UserCards

class Player(val name: String, var isDrawContinue: Boolean = true) {
    private var userCards = UserCards(mutableListOf())

    fun receiveCard(card: Card) {
        require(isDrawContinue) { "카드를 받을 수 없습니다" }
        userCards.addCard(card)
    }

    fun cardSize(): Int {
        return userCards.size
    }

    fun calculateCardPoints(): Int {
        return userCards.calculatePoints()
    }

    fun stopCardDraw() {
        isDrawContinue = false
    }

    fun continueCardDraw() {
        isDrawContinue = true
    }

    fun findAllCardsNames(): List<String> {
        return userCards.map { card ->
            card.rank.keyword + card.suits.koreanName
        }
    }
}
