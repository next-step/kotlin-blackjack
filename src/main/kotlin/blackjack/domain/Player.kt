package blackjack.domain

interface Player {
    val name: Name
    val cards: Cards

    fun initialCard(deck: Deck): Player

    fun hit(deck: Deck): Player

    fun canHit(): Boolean = !isBurst()

    fun countingCard(): Int = cards.countingCard()

    fun isBurst(): Boolean = countingCard() > BLACKJACK_SCORE
}
