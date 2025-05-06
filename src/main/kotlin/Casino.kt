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

        val winningResult = WinningResult()
        participants.forEach {
            when (it) {
                is Player -> {
                    val result = winningResult.versus(dealer, it)
                    outputView.printResult(it, result)
                }
                is Dealer -> {
                    outputView.printDealerResult(it, winningResult.getResult())
                }
            }
        }
    }

    private fun turn(
        participant: Participant,
        deck: Deck,
    ) {
        while (true) {
            when (participant) {
                is Player -> {
                    val response = inputView.getResponse(participant.name)
                    if (!response) {
                        participant.stay()
                        return
                    }
                }
                is Dealer -> {
                    if (participant.score() > 17) {
                        participant.stay()
                        return
                    }
                    outputView.printDealerDrawGuide()
                }
            }
            participant.drawCard(deck.drawOne())
            outputView.printPlayerCards(participant)
            if (participant.state is Bust) return
        }
    }
}
