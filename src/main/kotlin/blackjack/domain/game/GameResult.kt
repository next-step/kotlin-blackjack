package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

private typealias ResultType = GameResult.Type

sealed interface GameResult {
    fun isApplicableTo(dealer: Dealer, player: Player): Boolean
    fun getPlayerResult(dealer: Dealer, player: Player): Type

    enum class Type(private val times: Double) {
        WIN(1.0),
        DRAW(0.0),
        LOSE(-1.0),
        BLACK_JACK(0.5),
        ;

        fun calculateProfit(money: Money): Profit {
            return Profit(money.value * times)
        }
    }
}

object PlayerBust : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = player.isBust()

    override fun getPlayerResult(dealer: Dealer, player: Player): ResultType {
        return ResultType.LOSE
    }
}

object DealerBust : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = dealer.isBust()

    override fun getPlayerResult(dealer: Dealer, player: Player): ResultType {
        return ResultType.WIN
    }
}

object PlayerBlackJack : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player): Boolean {
        return player.isBlackJack() && !dealer.isBlackJack()
    }

    override fun getPlayerResult(dealer: Dealer, player: Player): ResultType {
        return ResultType.BLACK_JACK
    }
}

object DealerBlackJack : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player): Boolean {
        return dealer.isBlackJack() && !player.isBlackJack()
    }

    override fun getPlayerResult(dealer: Dealer, player: Player): ResultType {
        return ResultType.LOSE
    }
}

object PlayerWin : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = (dealer.score < player.score)

    override fun getPlayerResult(dealer: Dealer, player: Player): ResultType {
        return ResultType.WIN
    }
}

object PlayerDraw : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = (dealer.score == player.score)

    override fun getPlayerResult(dealer: Dealer, player: Player): ResultType {
        return ResultType.DRAW
    }
}

object PlayerLose : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = (dealer.score > player.score)

    override fun getPlayerResult(dealer: Dealer, player: Player): ResultType {
        return ResultType.LOSE
    }
}
