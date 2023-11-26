package blackjack.model.playable

import blackjack.model.pack.Pack
import blackjack.model.playblestrategy.PlayingStrategy

interface Playable {
    fun score(): BlackjackScore
    fun dealing(pack: Pack)
    fun playing(playingStrategy: PlayingStrategy, pack: Pack): PlayableReaction
    fun result(playable: Playable): PlayableResult
    fun isBurst(): Boolean
}
