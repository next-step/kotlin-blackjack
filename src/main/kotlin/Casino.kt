import card.Deck
import card.PlayingCard
import participant.Dealer
import participant.Participant
import participant.Player
import state.Bust
import state.FirstTurn
import view.InputView
import view.OutputView

class Casino(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val deck = Deck(PlayingCard.ALL.shuffled())
        val names = inputView.getPlayerNames()
        val players = names.map { Player(it, FirstTurn(Hand(emptyList()))) }
        val dealer = Dealer(state = FirstTurn(Hand(emptyList())))

        val participants: List<Participant> = players + dealer
        repeat(2) { participants.forEach { it.drawCard(deck.drawOne()) } }

        outputView.printFirstTurn(participants)

        participants.forEach { turn(it, deck) }
        participants.forEach { outputView.printScore(it) }

        val winningResult = WinningResult(dealer)
        participants.forEach {
            val result = winningResult.versus(it)
            outputView.printResult(it, result)
        }
    }

    private fun turn(
        participant: Participant,
        deck: Deck,
    ) {
        while (true) {
            val response = inputView.getResponse(participant)
            if (!response || !participant.wantDraw()) {
                participant.stay()
                return
            }
            participant.drawCard(deck.drawOne())
            outputView.printPlayerCards(participant)
            if (participant.state is Bust) return
        }
    }
}
