package game.blackjack.v2.domain.participant

enum class GameResult(val description: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("동점")
    ;
}
