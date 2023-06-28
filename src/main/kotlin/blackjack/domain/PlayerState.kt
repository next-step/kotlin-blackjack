package blackjack.domain

enum class PlayerState(
    val description: String
) {
    DEFAULT("기본"),
    HIT("힛"),
    STAND("스탠드"),
    BUST("버스트"),
    BLACK_JACK("블랙잭")
}
