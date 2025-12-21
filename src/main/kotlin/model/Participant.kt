package model

abstract class Participant(
    val name: String,
    val cards: Cards,
) {
    init {
        require(name.isNotBlank()) { "참가자 이름은 빈 값일 수 없습니다." }
    }

    abstract fun getPublicCardsOnFirstRound(): Cards

    fun drawCardFromDeck(deck: CardDeck) {
        cards.addCard(deck.drawCard())
    }

    fun isBust(): Boolean = cards.isBust()

    fun isBlackJack(): Boolean = cards.calculateScore() == BlackJackConstants.BLACK_JACK_SCORE && cards.cards().size == 2

    fun calculateScore(): Int = cards.calculateScore()

    fun calculateScoreTreatAceAsOne(): Int = cards.calculateScoreTreatAceAsOne()
}
