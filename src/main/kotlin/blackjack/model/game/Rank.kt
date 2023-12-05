package blackjack.model.game

enum class Rank(val rank: String) {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    operator fun not(): Rank {
        return when (this) {
            WIN -> LOSE
            DRAW -> DRAW
            LOSE -> WIN
        }
    }
}
