package blackjack.domain.enums

enum class Condition(
    val raceFlag: RaceFlag
) {
    PLAY(RaceFlag.Y),
    STAY(RaceFlag.N),
    BUST(RaceFlag.N),
    BLACKJACK(RaceFlag.N)
}
