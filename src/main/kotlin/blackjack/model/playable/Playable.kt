package blackjack.model.playable

import blackjack.model.pack.Pack
import blackjack.model.playblestrategy.PlayingStrategy

interface Playable {
    fun score(): Int
    fun dealing(pack: Pack)
    fun playing(playingStrategy: PlayingStrategy, pack: Pack): PlayableReaction
}
