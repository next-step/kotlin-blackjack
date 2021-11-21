package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

private typealias ResultType = GameResult.Type

sealed interface GameResult {
    fun isApplicableTo(dealer: Dealer, player: Player): Boolean
    fun get(dealer: Dealer, player: Player): GamersResult

    enum class Type {
        WIN,
        DRAW,
        LOSE,
        BLACK_JACK,
        ;
    }
}

object PlayerBust : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = player.isBust()

    override fun get(dealer: Dealer, player: Player): GamersResult {
        return GamersResult(
            playerResult = GamerResult(ResultType.LOSE, player),
            dealerResult = GamerResult(ResultType.WIN, dealer),
        )
    }
}

object DealerBust : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = dealer.isBust()

    override fun get(dealer: Dealer, player: Player): GamersResult {
        return GamersResult(
            playerResult = GamerResult(ResultType.WIN, player),
            dealerResult = GamerResult(ResultType.LOSE, dealer),
        )
    }
}

object PlayerBlackJack : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player): Boolean {
        return player.isBlackJack() && !dealer.isBlackJack()
    }

    override fun get(dealer: Dealer, player: Player): GamersResult {
        return GamersResult(
            playerResult = GamerResult(ResultType.BLACK_JACK, player),
            dealerResult = GamerResult(ResultType.LOSE, dealer),
        )
    }
}

object DealerBlackJack : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player): Boolean {
        return dealer.isBlackJack() && !player.isBlackJack()
    }

    override fun get(dealer: Dealer, player: Player): GamersResult {
        return GamersResult(
            playerResult = GamerResult(ResultType.LOSE, player),
            dealerResult = GamerResult(ResultType.WIN, dealer),
        )
    }
}

object PlayerWin : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = (dealer.score < player.score)

    override fun get(dealer: Dealer, player: Player): GamersResult {
        return GamersResult(
            playerResult = GamerResult(ResultType.WIN, player),
            dealerResult = GamerResult(ResultType.LOSE, dealer),
        )
    }
}

object PlayerDraw : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = (dealer.score == player.score)

    override fun get(dealer: Dealer, player: Player): GamersResult {
        return GamersResult(
            playerResult = GamerResult(ResultType.DRAW, player),
            dealerResult = GamerResult(ResultType.DRAW, dealer),
        )
    }
}

object PlayerLose : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = (dealer.score > player.score)

    override fun get(dealer: Dealer, player: Player): GamersResult {
        return GamersResult(
            playerResult = GamerResult(ResultType.LOSE, player),
            dealerResult = GamerResult(ResultType.WIN, dealer),
        )
    }
}
