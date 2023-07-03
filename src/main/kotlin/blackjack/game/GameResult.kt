package blackjack.game

class GameResult(private val matchResult: Map<String, Result>) {

    val getMatchResult: Map<String, Result>
        get() = matchResult
}
