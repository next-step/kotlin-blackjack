package blackJack.domain.enums

enum class BlackjackResult(val result: String, val reward: Double) {
    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1.0),
    DRAW("무", 0.0),
    LOSE("패", -1.0)
}
