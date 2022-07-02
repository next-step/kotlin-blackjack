package blackjack.domain.judge

class Push : Judgement {
    override fun isWinner(): Boolean = true
    override fun toString(): String = "무"
}
