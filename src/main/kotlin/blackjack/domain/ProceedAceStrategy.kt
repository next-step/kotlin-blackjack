package blackjack.domain

interface ProceedAceStrategy {
    fun proceedAceNumber(sum: Int): Int
}