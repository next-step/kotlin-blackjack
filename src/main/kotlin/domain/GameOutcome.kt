package domain

enum class GameOutcome {
    PLAYER_BUST,
    DEALER_BUST,
    PUSH,
    PLAYER_BLACKJACK,
    DEALER_BLACKJACK,
    PLAYER_WIN,
    DEALER_WIN
    ;

    fun profitOf(bet: Money): SignedMoney = when (this) {
        // 정수형 처리를 위해 1.5배 하는 것이 아니라 3배 후 2로 나눔
        PLAYER_BLACKJACK -> SignedMoney((bet.amount * 3) / 2)
        PLAYER_WIN, DEALER_BUST -> SignedMoney(bet.amount)
        PUSH -> SignedMoney(0)
        DEALER_WIN, DEALER_BLACKJACK, PLAYER_BUST -> SignedMoney(-bet.amount)
    }
}