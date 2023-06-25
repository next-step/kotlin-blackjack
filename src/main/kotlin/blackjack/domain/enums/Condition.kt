package blackjack.domain.enums

enum class Condition(
    val raceFlag: String
) {
    PLAY("y"),
    STAY("n"),
    BUST("n"),
    BLACKJACK("n")
}
