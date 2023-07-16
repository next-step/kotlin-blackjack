package blackjack.domain

enum class Result(val displayName: String) {
    WIN("승"),
    LOSE("패"),
    PUSH("무");

    fun getOpposite(): Result {
        return when(this) {
            WIN -> LOSE
            LOSE -> WIN
            PUSH -> PUSH
        }
    }
}