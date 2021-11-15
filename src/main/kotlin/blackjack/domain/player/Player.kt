package blackjack.domain.player

import blackjack.domain.card.Hand
import blackjack.domain.card.Score

private const val PLAYER_FIRST_OPEN_COUNT = 2

class Player(name: PlayerName, hand: Hand = Hand.createEmpty()) : Gamer(name, hand) {

    override fun canHit() = (score < Score.BLACK_JACK_SCORE)
    override fun firstOpenCardsCount() = PLAYER_FIRST_OPEN_COUNT
}
