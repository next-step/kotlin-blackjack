package blackjack.domain

enum class MatchType {
    WIN,
    LOSE,
    DRAW, ;

    companion object {
        fun evaluate(o1: Int, o2: Int): MatchType {
            return when {
                o1 == o2 -> DRAW
                o1 < o2 -> WIN
                else -> LOSE
            }
        }
    }
}
