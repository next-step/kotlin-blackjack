package blackjack.domain.player

enum class ResultStatus(
    val value: String,
) {
    WIN("승"),
    LOSE("패"),
    TIE("무"),
    UNKNOWN("미승부"),
}
