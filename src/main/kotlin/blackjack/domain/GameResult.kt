package blackjack.domain

enum class GameResult(private val result: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부"),
    ;

    fun getResult(): String {
        return result
    }
}
