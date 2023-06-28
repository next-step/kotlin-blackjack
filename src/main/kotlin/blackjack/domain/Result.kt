package blackjack.domain

enum class Result(
    val description: String
) {
    WIN("승"),
    LOSE("패"),
    DRAW("무")
}
