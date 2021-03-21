package blackjack.domain

internal enum class MatchResult(val desc: String, private val opposite: String) {

    WIN("승", "LOSE"),
    DRAW("무", "DRAW"),
    LOSE("패", "WIN");

    fun findOpposite(): MatchResult {
        return valueOf(this.opposite)
    }
}
