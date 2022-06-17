package blackjack.common

enum class PlayerDecision {
    HIT, STAND;

    companion object {
        fun ofText(playerDecision: String): PlayerDecision? = when (playerDecision) {
            "y" -> HIT
            "n" -> STAND
            else -> null
        }
    }
}
