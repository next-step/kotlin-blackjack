package blackjack.model.blackjack.judgment.impl

import blackjack.model.blackjack.judgment.Judgment
import blackjack.model.player.playable.Playable
import blackjack.model.result.PlayableResult

object BlackJackJudgment : Judgment {
    override fun sentence(thisPlayable: Playable, otherPlayable: Playable): PlayableResult {
        if (thisPlayable.isBurst() && otherPlayable.isBurst()) {
            return PlayableResult.DRAW
        }
        if (thisPlayable.isBurst() && !otherPlayable.isBurst()) {
            return PlayableResult.LOSE
        }
        if (!thisPlayable.isBurst() && otherPlayable.isBurst()) {
            return PlayableResult.WIN
        }
        return thisPlayable.score() vs otherPlayable.score()
    }
}
