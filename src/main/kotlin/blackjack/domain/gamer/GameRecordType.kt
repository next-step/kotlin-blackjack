package blackjack.domain.gamer

enum class GameRecordType(val value: String) {
    WIN("승"), LOSE("패"), DRAW("무"), NONE("NONE")
}