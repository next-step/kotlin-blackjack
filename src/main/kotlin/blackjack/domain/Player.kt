package blackjack.domain

interface Player {
    val name: Name
    val cards: Cards

    fun initialCard(deck: Deck): Player

    fun hit(deck: Deck): Player

    fun canHit(): Boolean = !isBurst()

    fun countingCard(): Int = cards.countingCard()

    fun compareTo(player: Player): GameResult {
        val score = getScore(this) - getScore(player)
        return GameResult.of(score)
    }

    private fun isBurst(): Boolean = countingCard() > BLACKJACK_SCORE

    private fun getScore(player: Player): Int {
        return if (player.isBurst()) 0 else player.countingCard()
    }
}
