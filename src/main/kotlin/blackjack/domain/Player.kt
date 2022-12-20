package blackjack.domain

interface Player {
    val playerInfo: PlayerInfo
    val cards: Cards

    fun getPlayerName(): String = playerInfo.getName()

    fun initialCard(deck: Deck): Player

    fun hit(deck: Deck): Player

    fun canHit(): Boolean = !isBurst()

    fun countingCard(): Int = cards.countingCard()

    fun isBurst(): Boolean = countingCard() > BLACKJACK_SCORE
}
