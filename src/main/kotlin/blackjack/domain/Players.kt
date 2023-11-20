package blackjack.domain

class Players(
    val values: List<Player>
) {
    fun withHit(): List<Player> {
        return values.filter { it.decision == PlayerDecision.HIT }
    }
}
