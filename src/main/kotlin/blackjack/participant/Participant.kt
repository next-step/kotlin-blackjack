package blackjack.participant

import betting.Bet
import betting.BetResult
import blackjack.card.Card
import blackjack.machine.BlackJackMachine
import blackjack.player.Hand
import blackjack.player.Players

interface Participant<out T : Participant<T>> {
    val name: String
    val hand: Hand
    val betResult: BetResult
    val bet: Bet
        get() = betResult.bet

    val betAmount: Double
        get() = betResult.bet.amount

    val winningAmount: Double
        get() = betResult.winning.amount

    fun isBust(): Boolean = hand.sum() > BlackJackMachine.BLACKJACK

    fun hitCard(card: Card): T

    fun isBlackjack(): Boolean = hand.sum() == BlackJackMachine.BLACKJACK
}

fun <T : Participant<T>> createParticipants(
    dealer: T,
    players: Players,
): List<Participant<*>> = listOf(dealer) + players.players
