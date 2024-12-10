package blackjack.domain

enum class GameResult(private val result: String) {
    WIN("승"),
    LOSE("패"),
    ;

    fun getResult(): String {
        return result
    }
}