package blackjack.domain

enum class ResultStatus {
    WIN,
    LOSE,
    DRAW;
}

infix fun Int.match(value: Int): ResultStatus {
    if (this == value) return ResultStatus.DRAW

    return when (this > value) {
        true -> ResultStatus.WIN
        false -> ResultStatus.LOSE
    }
}
