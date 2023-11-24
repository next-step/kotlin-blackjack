package blackjack

import blackjack.model.Participants
import blackjack.model.Referee
import blackjack.model.pack.ShuffledPack
import blackjack.model.playable.PlayableReaction
import blackjack.model.playable.impl.Player
import blackjack.model.playblestrategy.impl.DealerStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val participants = InputView.join()
    participants.dealing(ShuffledPack)
    OutputView.dealing(participants)
    OutputView.presentCards(participants)
    while (participants.isContinue()) {
        playingBlackJack(participants)
    }
    OutputView.presentScores(participants)
    OutputView.presentResult(Referee.blackJackResult(participants))
}

fun playingBlackJack(participants: Participants) {
    participants.players.values.forEach {
        it.playing(InputView.askHit(it), ShuffledPack)
    }
    when (participants.dealer.playing(DealerStrategy(participants.dealer.score()), ShuffledPack)) {
        PlayableReaction.HIT -> println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        PlayableReaction.STAND -> println("딜러는 17이상이라 카드를 받지 않았습니다.")
    }
}

private fun Player.hitOrStand() {

//    if () {
//        this.hit(ShuffledPack)
//    }
//    OutputView.playerCardPresent(this)
}
