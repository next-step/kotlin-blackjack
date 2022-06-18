package blackjack.view

import blackjack.domain.user.User

/**
 * view와 domain의 결합을 없애기 위한 interface
 * Created by Jaesungchi on 2022.06.15..
 */
interface OutputInterface {
    fun printUserCard(user: User)
    fun printMoreCard(user: User)

    fun printDealerHitMessage()
}
