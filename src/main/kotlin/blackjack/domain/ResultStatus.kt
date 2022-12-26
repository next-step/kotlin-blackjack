package blackjack.domain

enum class ResultStatus(val value: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무");
}

infix fun Int.match(value: Int): ResultStatus {
    if (this == value) return ResultStatus.DRAW

    return when (this > value) {
        true -> ResultStatus.WIN
        false -> ResultStatus.LOSE
    }
}
