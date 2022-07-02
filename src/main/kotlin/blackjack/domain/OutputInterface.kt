package blackjack.domain

import blackjack.domain.player.Player

/**
 * view와 domain의 결합을 없애기 위한 interface
 * Created by Jaesungchi on 2022.06.15..
 */
interface OutputInterface {
    fun drawUserCard(user: Player)
    fun drawMoreCard(user: Player)

    fun drawDealerHitMessage()
}
