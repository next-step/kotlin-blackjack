package blackjack.domain

import blackjack.domain.player.User

/**
 * Created by Jaesungchi on 2022.06.19..
 */
interface InputInterface {
    fun getYesOrNo(readStringValue: () -> String? = { readlnOrNull() }): Boolean

    fun getBatMoney(user: User, readStringValue: () -> String? = { readlnOrNull() }): Int
}
