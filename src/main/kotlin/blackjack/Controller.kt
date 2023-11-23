package blackjack

import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.Referee
import blackjack.model.pack.ShuffledPack
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
        it.hitOrStand()
    }
    when (participants.dealer.playing(ShuffledPack)) {
        true -> println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        false -> println("딜러는 17이상이라 카드를 받지 않았습니다.")
    }
}

private fun Player.hitOrStand() {
    if (InputView.askHit(this)) {
        this.hit(ShuffledPack)
    }
    OutputView.playerCardPresent(this)
}
