package blackjack.domain.result

import blackjack.domain.player.Player

interface GameResultStrategy {

    fun isMatch(dealer: Player, challenger: Player): Boolean
}

class WinResultStrategy : GameResultStrategy {

    override fun isMatch(dealer: Player, challenger: Player): Boolean = when {
        dealer.isBusted() -> true
        challenger.isBusted() -> false
        challenger.isBiggerScoreThan(dealer) -> true
        else -> false
    }
}

class LoseResultStrategy : GameResultStrategy {

    override fun isMatch(dealer: Player, challenger: Player): Boolean = when {
        dealer.isBusted() -> false
        dealer.isBiggerScoreThan(challenger) || challenger.isBusted() -> true
        else -> false
    }
}

class DrawResultStrategy : GameResultStrategy {

    override fun isMatch(dealer: Player, challenger: Player): Boolean = when {
        isEitherBusted(dealer, challenger) -> false
        dealer.getScore() == challenger.getScore() -> true
        else -> false
    }

    private fun isEitherBusted(dealer: Player, challenger: Player): Boolean {
        return !dealer.isBusted() && !challenger.isBusted()
    }
}
