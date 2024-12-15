package blackjack.player

import betting.BetResult
import blackjack.card.Card
import blackjack.dealer.Dealer
import blackjack.machine.BlackJackMachine.Companion.BONUS_RATIO
import blackjack.participant.Participant
import blackjack.view.InputView
import blackjack.view.ResultView

class Player(
    override val name: String,
    override val hand: Hand = Hand(cards = emptyList()),
    override val betResult: BetResult = BetResult.Default(),
) : Participant<Player> {
    override fun hitCard(card: Card): Player = Player(name = name, hand = hand.add(card), betResult = betResult)

    fun handleBlackJack(dealer: Dealer): Player =
        when {
            !this.isBlackjack() -> this
            dealer.isBlackjack() -> updateBetResult(BetResult.Win(bet = this.bet, amount = this.betAmount))
            else ->
                updateBetResult(
                    BetResult.Win(
                        bet = this.bet,
                        amount = this.betAmount.times(BONUS_RATIO),
                    ),
                )
        }

    fun win(): Player =
        updateBetResult(betResult = BetResult.Win(bet = this.bet, amount = this.betAmount))

    fun lose(): Player =
        updateBetResult(betResult = BetResult.Lose(bet = this.bet, amount = this.bet.negative()))

    fun updateBetResult(betResult: BetResult): Player =
        Player(name = this.name, hand = this.hand, betResult = betResult)

    fun play(
        isHitCard: Boolean,
        draw: () -> Card,
    ): Player =
        when {
            !isHitCard -> this
            else -> this.hitCard(draw())
        }

    companion object {
        fun ready(name: String): Player = Player(name = name)
    }
}
