package blackjack.model.blackjack.judgment

import blackjack.model.player.playable.Playable
import blackjack.model.result.PlayableResult

interface Judgment {
    fun sentence(thisPlayable: Playable, otherPlayable: Playable): PlayableResult
}
