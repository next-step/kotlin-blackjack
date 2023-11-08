package blackjack.domain

class Player(val name: String, val playerCard: PlayerCard = PlayerCard()) {

    fun hit(trumpCard: TrumpCard) {
        playerCard.add(trumpCard.draw())
    }

    fun score(): Int {
        val first = playerCard.cards.sumOf { it.calculateScore().first }
        val second = playerCard.cards.sumOf { it.calculateScore().second }
        if (first <= BURST_SCORE && second <= BURST_SCORE) {
            return maxOf(first, second)
        }
        return minOf(first, second)
    }

    fun burst(): Boolean {
        return score() > BURST_SCORE
    }

    companion object {
        const val BURST_SCORE = 21
    }
}
