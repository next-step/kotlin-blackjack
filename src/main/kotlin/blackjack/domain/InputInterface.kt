package blackjack.domain

/**
 * Created by Jaesungchi on 2022.06.19..
 */
interface InputInterface {
    fun getYesOrNo(readStringValue: () -> String? = { readlnOrNull() }): Boolean
}
