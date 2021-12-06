package blackjack.domain

enum class GameResultState(val displayName: String) {
    Win("승"), Draw("무"), Lose("패");
}
