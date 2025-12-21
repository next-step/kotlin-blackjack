package model

abstract class Participant(val name: String, val cards: Cards) {
    init {
        require(name.isNotBlank()) { "참가자 이름은 빈 값일 수 없습니다." }
    }

    abstract fun getPublicCardsOnFirstRound(): Cards

    fun drawCardFromDeck(deck: CardDeck) {
        cards.addCard(deck.drawCard())
    }

    fun isBust(): Boolean {
        return cards.isBust()
    }

    fun isBlackJack(): Boolean {
        return cards.calculateScore() == BlackJackConstants.BLACK_JACK_SCORE && cards.cards().size == 2
    }

    fun calculateScore(): Int {
        return cards.calculateScore()
    }

    fun calculateScoreTreatAceAsOne(): Int {
        return cards.calculateScoreTreatAceAsOne()
    }
}
