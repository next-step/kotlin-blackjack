package blackjack.domain

class Dealer(hands: Hands) : Player("딜러", hands) {
    fun shouldHit(): Boolean {
        return sum() <= HIT_THRESHOLD
    }

    override fun hit(card: Card) {
        val sum = sum()
        require(sum <= HIT_THRESHOLD) { "딜러는 점수가 $HIT_THRESHOLD 이하일 때만 hit 할 수 있습니다. 점수:$sum" }

        super.hit(card)
    }

    fun calculatePlayerResult(player: Player): Result {
        if (player.isBust()) return Result.LOSE

        if (isBust()) return Result.WIN

        val dealerScore = sum()
        val playerScore = player.sum()

        if (dealerScore > playerScore) return Result.LOSE
        if (dealerScore < playerScore) return Result.WIN

        return Result.PUSH
    }

    companion object {
        const val HIT_THRESHOLD = 16
    }
}