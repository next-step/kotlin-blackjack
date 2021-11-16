package blackjack.domain.player

import blackjack.domain.card.Hand
import blackjack.domain.card.Score

private const val PLAYER_FIRST_OPEN_COUNT = 2

class Player(
    name: PlayerName,
    hand: Hand = Hand.createEmpty(),
    override val afterHitCallBack: AfterHitWhileCallback? = null,
) : Gamer(name, hand) {

    override fun wantHit(answerProvider: AnswerProvider): Boolean {
        return (score < Score.BLACK_JACK_SCORE) && answerProvider.getAnswer(this).hit
    }

    override fun firstOpenCardsCount() = PLAYER_FIRST_OPEN_COUNT
}
