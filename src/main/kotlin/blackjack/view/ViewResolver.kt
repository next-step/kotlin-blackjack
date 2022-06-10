package blackjack.view

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.game.Result

class ViewResolver {
    val decidePlayerHitDecision: (player: Player) -> Boolean = InputView::decidePlayerHitDecision
    val printInitialHand: (dealer: Dealer, players: List<Player>) -> Unit = ParticipantView::printInitialHand
    val printParticipantInfo: (participant: Participant) -> Unit = ParticipantView::printParticipantInfo
    val printDealerDrawOneCard: () -> Unit = ParticipantView::printDealerDrawOneCard
    val printResult: (dealer: Dealer, players: List<Player>, result: Result) -> Unit = ResultView::printResult
}
