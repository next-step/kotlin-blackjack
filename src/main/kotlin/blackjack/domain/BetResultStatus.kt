package blackjack.domain

enum class BetResultStatus(val earnMoneyRatio: Float) {
    WinByDealerBust(1.0f),
    WinByBlackJack(1.5f),
    WinByScore(1.0f),
    LoseByBust(-1.0f),
    LoseByScore(-1.0f),
    DrawWithBlackJack(1.0f),
    Draw(0f),
    None(1f);
}
