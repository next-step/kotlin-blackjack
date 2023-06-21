package blackjack.io

import blackjack.domain.Users

object ResultView {
    fun printUsersDeck(users: Users) {
        println()
        for (user in users) {
            println("${user.name}카드: ${user.deck}")
        }
        println()
    }
}
