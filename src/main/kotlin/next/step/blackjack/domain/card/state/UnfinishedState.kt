package next.step.blackjack.domain.card.state

import next.step.blackjack.domain.card.Cards

object UnfinishedState : CardsState() {

    override fun next(cards: Cards): CardsState = when {
        cards.isBlackjack() -> BlackjackState
        cards.isFinished() -> FinishedState
        cards.isBurst() -> BurstState
        else -> UnfinishedState
    }
}
