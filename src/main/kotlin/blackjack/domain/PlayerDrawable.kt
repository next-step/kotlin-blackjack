package blackjack.domain

import blackjack.exception.InvalidInputValueException

class PlayerDrawable(private val participant: Participant) : Drawable {
    override fun canDraw(): Boolean {
        println("${participant.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln().toBoolean().also {
            if (it) participant.setBlackJackStatusStay()
        }
    }

    private fun String.toBoolean(): Boolean {
        return when (uppercase()) {
            YES -> true
            NO -> false
            else -> throw InvalidInputValueException()
        }
    }

    companion object {
        private const val YES = "Y"
        private const val NO = "N"
    }
}
