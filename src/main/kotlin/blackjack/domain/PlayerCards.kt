package blackjack.domain

internal class PlayerCards(val cards: List<Card> = listOf()) {

    fun add(card: Card): PlayerCards {
        return PlayerCards(cards.plus(card))
    }

    fun score(): Int {
        return CardNumber.score(this.cards.map { it.number })
    }

    fun isBurst(): Boolean {
        return this.score() > BlackJackGame.MAX_SCORE
    }

    fun isBlackJack(): Boolean {
        return this.cards.size == BlackJackGame.START_CARD_COUNT && this.score() == BlackJackGame.MAX_SCORE
    }
}
