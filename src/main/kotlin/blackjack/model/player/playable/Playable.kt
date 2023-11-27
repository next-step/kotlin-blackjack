package blackjack.model.player.playable

import blackjack.model.blackjack.BlackJackStatus
import blackjack.model.card.pack.Pack
import blackjack.model.player.playblestrategy.PlayingStrategy
import blackjack.model.player.BlackjackScore
import blackjack.model.player.PlayableReaction
import blackjack.model.result.PlayableResult

interface Playable {
    fun score(): BlackjackScore
    fun dealing(pack: Pack)
    fun playing(playingStrategy: PlayingStrategy, pack: Pack): PlayableReaction
    fun result(playable: Playable): PlayableResult
    fun isBurst(): Boolean
    fun status(): BlackJackStatus
}
