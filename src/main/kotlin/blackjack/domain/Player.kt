package blackjack.domain

class Player(
    override val name: String,
    override val gameState: GameState,
) : Participant(name, gameState) {
    constructor(
        name: String,
        betValue: Int = 0,
    ) : this(name, GameState(bet = Bet(betValue)))

    fun settleBet(result: Result): Bet =
        when {
            isBlackjack && result == Result.WIN -> gameState.blackjackBet()
            result == Result.WIN || result == Result.DRAW -> Bet(profit)
            result == Result.LOSE -> gameState.loseBet()
            else -> throw IllegalArgumentException("올바르지 않은 결과입니다.")
        }
}
