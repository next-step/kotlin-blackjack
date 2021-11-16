package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

sealed interface GameResult {
    fun isApplicableTo(dealer: Dealer, player: Player): Boolean
    fun getType(): Type

    enum class Type {
        WIN,
        DRAW,
        LOSE,
        ;
    }
}

object PlayerBust : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = player.isBust()
    override fun getType() = GameResult.Type.LOSE
}

object DealerBust : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = dealer.isBust()
    override fun getType() = GameResult.Type.WIN
}

object PlayerWin : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = (dealer.score < player.score)
    override fun getType() = GameResult.Type.WIN
}

object PlayerDraw : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = (dealer.score == player.score)
    override fun getType() = GameResult.Type.DRAW
}

object PlayerLose : GameResult {
    override fun isApplicableTo(dealer: Dealer, player: Player) = (dealer.score > player.score)
    override fun getType() = GameResult.Type.LOSE
}
