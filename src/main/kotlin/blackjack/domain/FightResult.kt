package blackjack.domain

enum class FightResult(
    val description: String,
) {
    WIN("승"),
    LOSE("패"),
    DRAW("무")
}
