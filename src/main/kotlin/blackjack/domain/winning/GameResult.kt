package blackjack.domain.winning

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Stay

enum class GameResult {
    WIN, DRAW, LOSE;
}