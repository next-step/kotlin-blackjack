package blackjack.model

import blackjack.model.pack.Pack

interface Playable {
    fun score(): Int
    fun playing(pack: Pack)
}
