package blackjack.domain.game

import blackjack.domain.participant.Participant

enum class Rank(val value: Double) {
    BLACKJACK(1.5),
    WON(1.0),
    LOST(-1.0),
    DRAW(1.0);

    companion object {
        fun of(player: Participant, dealer: Participant): Rank {
            return when {
                player.isBlackJack() && dealer.isBlackJack() -> DRAW
                player.isBlackJack() -> BLACKJACK
                player.score() == dealer.score() -> DRAW
                dealer.score() > BlackJack.BLACKJACK_MAX_SCORE -> WON
                player.score() > BlackJack.BLACKJACK_MAX_SCORE -> LOST
                player.score() > dealer.score() -> WON
                player.score() < dealer.score() -> LOST
                else -> DRAW
            }
        }
    }
}
