package blackjack

interface Rule {
    fun evaluate(
        dealerState: State,
        playerState: State,
    ): PlayerResult?
}

class BlackjackRule(private val next: Rule?) : Rule {
    override fun evaluate(
        dealerState: State,
        playerState: State,
    ): PlayerResult? {
        return when {
            dealerState is Blackjack && playerState is Blackjack -> PlayerResult.PUSH
            dealerState is Blackjack -> PlayerResult.LOSS
            playerState is Blackjack -> PlayerResult.WIN
            else -> next?.evaluate(dealerState, playerState)
        }
    }
}

class BustRule(private val next: Rule?) : Rule {
    override fun evaluate(
        dealerState: State,
        playerState: State,
    ): PlayerResult? {
        return when {
            dealerState is Bust && playerState is Bust -> PlayerResult.LOSS
            dealerState is Bust -> PlayerResult.WIN
            playerState is Bust -> PlayerResult.LOSS
            else -> next?.evaluate(dealerState, playerState)
        }
    }
}

class StaySumComparisonRule : Rule {
    override fun evaluate(
        dealerState: State,
        playerState: State,
    ): PlayerResult {
        val dealerSum = dealerState.cards().sum()
        val playerSum = playerState.cards().sum()

        return when {
            dealerSum > playerSum -> PlayerResult.LOSS
            dealerSum < playerSum -> PlayerResult.WIN
            else -> PlayerResult.PUSH
        }
    }
}

class DefaultBlackjackGameJudgeStrategy : BlackjackGameJudgeStrategy {
    private val chain: Rule = BlackjackRule(next = BustRule(next = StaySumComparisonRule()))

    override fun evaluatePlayerResult(
        dealerState: State,
        playerState: State,
    ): PlayerResult {
        return chain.evaluate(dealerState, playerState)
            ?: throw IllegalStateException("어떤 규칙에도 해당하지 않는 상태입니다: dealerState=$dealerState, playerState=$playerState")
    }
}
