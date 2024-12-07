package blackjack.domain

enum class Result(val message: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부"),
    ;
}
