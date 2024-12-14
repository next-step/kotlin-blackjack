package blackjack.participant

import blackjack.card.Card
import blackjack.machine.BlackJackMachine
import blackjack.player.Hand
import blackjack.player.Players

interface Participant<out T : Participant<T>> {
    val name: String
    val hand: Hand

    fun isBust(): Boolean = hand.sum() > BlackJackMachine.BLACKJACK

    fun hitCard(card: Card): T

    fun isWin(opponent: Participant<*>): Boolean?

    fun isBlackjack(): Boolean = hand.sum() == BlackJackMachine.BLACKJACK
}

fun <T : Participant<T>> createParticipants(
    dealer: T,
    players: Players,
): List<Participant<*>> = listOf(dealer) + players.players
