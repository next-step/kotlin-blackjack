package blackjack.domain

import blackjack.domain.state.Bust

enum class Result(val value: String) {
    WIN("승리"),
    LOSE("패배");

    companion object {
        fun dealerResult(dealer: Dealer, players: List<Player>): List<Result> {
            return players.map { player ->
                when {
                    dealer.state is Bust -> LOSE
                    player.state is Bust -> WIN
                    dealer.score() >= player.score() -> WIN
                    else -> LOSE
                }
            }
        }

        fun playerResult(dealer: Dealer, player: Player): Result = when {
            dealer.state is Bust -> WIN
            player.state is Bust -> LOSE
            player.score() > dealer.score() -> WIN
            else -> LOSE
        }
    }
}
