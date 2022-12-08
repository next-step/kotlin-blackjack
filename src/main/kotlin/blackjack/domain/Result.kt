package blackjack.domain

class Result private constructor(
    val winner: List<Player>,
    val loser: List<Player>
) {

    companion object {
        fun init(dealer: Dealer, players: Players): Result {
            if (dealer.isOverBlackjackNumber()) {
                return Result(players.items, emptyList())
            }

            return Result(players.winner(dealer), players.loser(dealer))
        }
    }
}
