package blackjack.model

import blackjack.model.pack.impl.ShuffledPack
import blackjack.model.playable.PlayableReaction
import blackjack.model.playblestrategy.impl.DealerStrategy
import blackjack.view.InputView

class BlackJackGame(
    private val participants: Participants,
) {
    fun start() {
        while (participants.isContinue()) {
            playingBlackJack(participants)
        }
    }

    private fun playingBlackJack(participants: Participants) {
        participants.players.values.forEach {
            it.playing(InputView.askHit(it), ShuffledPack)
        }
        when (participants.dealer.playing(DealerStrategy(participants.dealer.score()), ShuffledPack)) {
            PlayableReaction.HIT -> println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            PlayableReaction.STAND -> println("딜러는 17이상이라 카드를 받지 않았습니다.")
        }
    }
}
