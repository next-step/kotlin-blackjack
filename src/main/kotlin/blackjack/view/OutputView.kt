package blackjack.view

import blackjack.constant.Messages
import blackjack.domain.card.ACE
import blackjack.domain.card.Card
import blackjack.domain.card.CardType
import blackjack.domain.card.JACK
import blackjack.domain.card.KING
import blackjack.domain.card.QUEEN
import blackjack.domain.user.User
import blackjack.domain.user.Users

/**
 * 출력을 담당하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
object OutputView {
    fun printHandOutMessage(users: Users) {
        println()
        println(Messages.HAND_OUT_CARD.format(users.names().joinToString()))
    }

    fun printUsersCard(users: Users) {
        users.users.forEach {
            printUserCard(it)
        }
        println()
    }

    fun printUserCard(user: User) {
        println(
            Messages.PRINT_HAVE_CARDS.format(user.name) + user.cards.hands.joinToString {
                cardToString(it)
            }
        )
    }

    fun printMoreCard(user: User) {
        println(Messages.WANT_MORE_CARD.format(user.name))
    }

    fun printResult(users: Users) {
        println()
        users.users.forEach {
            printCardAndScore(it)
        }
    }

    private fun cardToString(card: Card): String {
        val number = when (card) {
            is ACE -> "A"
            is JACK -> "J"
            is QUEEN -> "Q"
            is KING -> "K"
            else -> card.score
        }
        val type = when (card.type) {
            CardType.HEART -> "하트"
            CardType.DIAMOND -> "다이아"
            CardType.SPADE -> "스페이드"
            CardType.CLUB -> "클로버"
        }
        return "$number$type"
    }

    private fun printCardAndScore(user: User) {
        println(
            Messages.PRINT_CARDS_AND_SCORE.format(
                user.name,
                user.cards.hands.joinToString { cardToString(it) },
                user.cards.getScore().value
            )
        )
    }
}
