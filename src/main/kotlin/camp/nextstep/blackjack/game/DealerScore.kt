package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.player.Dealer

data class DealerScore(val dealer: Dealer, val score: Score, val results: List<GameResult>)
